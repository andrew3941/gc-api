package com.preving.intranet.gestioncentrosapi.model.services;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.*;
import com.preving.intranet.gestioncentrosapi.model.dao.vendor.ProviderByAreasRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.*;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByAreas;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.security.JwtTokenUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


import java.nio.file.Files;
import java.util.Date;
import java.util.List;




@Service
public class MaintenanceManager implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired

    private MaintenanceCustomRepository maintenanceCustomRepository;
    private static final String EXPORT_TITLE_1 = "Type";
    static final String EXPORT_TITLE_2 = "Provider";
    private static final String EXPORT_TITLE_3 = "Periodicity";
    private static final String EXPORT_TITLE_4 = "Amount";
    private static final String EXPORT_TITLE_5 = "Date";
    private static final String EXPORT_TITLE_6 = "Observations";


    private static final int NEW_MAINTENANCE = 2;


    @Autowired
    private MaintenanceByAttachmentRepository maintenanceByAttachmentRepository;

    @Autowired
    private MaintenanceTypesRepository maintenanceTypesRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MaintenanceByWorkCentersRepository maintenanceByWorkCentersRepository;




    @Override
    public Maintenance getMaintenanceById(int maintenanceId){
        return maintenanceRepository.findMaintenanceById(maintenanceId);
    }

    public ResponseEntity<?> downloadMaintenanceDoc(HttpServletRequest request, int workCenterId, int maintenanceId) {
        Maintenance maintenance = null;
        File file = null;
        byte[] content = null;

        try {
            String docUrl = this.maintenanceCustomRepository.findDocUrlByMaintenanceId(maintenanceId, workCenterId);

            file = new File(docUrl);
            if (file.exists()) {
                content = Files.readAllBytes(file.toPath());
            } else {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<?> editMaintenance(int workCenterId, Maintenance maintenance, MultipartFile[] attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        maintenance.setModifiedBy(new User());
        maintenance.getModifiedBy().setId(userId);

        try {

            maintenanceRepository.editMaintenance(maintenance);

            for (MaintenanceByAttachment maintFile : maintenance.getMaintenanceByAttachments()){
                // Borramos el documento anterior del servidor
                commonService.deleteDocumentServer(workCenterId, maintFile.getId(), NEW_MAINTENANCE);

                maintenanceByAttachmentRepository.deleteById(maintFile.getId());
            }

            if (attachedFile.length > 0) {

                for (MultipartFile mtFile : attachedFile){

                    MaintenanceByAttachment maintenanceByAttachment = new MaintenanceByAttachment();

                    maintenanceByAttachment.setMaintenance(maintenance);
                    maintenanceByAttachment.setDocName(mtFile.getOriginalFilename());
                    maintenanceByAttachment.setDocumentContentType(mtFile.getContentType());
                    maintenanceByAttachment.setDocumentUrl("default_Url");

                    MaintenanceByAttachment savedMaintenanceFile = maintenanceByAttachmentRepository.save(maintenanceByAttachment);

                    String url = null;
                    // Guardamos documento en el server
                    url = commonService.saveDocumentServer(workCenterId, maintenance.getId(), mtFile, NEW_MAINTENANCE);

                    // Actualizamos la ruta del documento guardado
                    if (url != null) {
                        this.maintenanceByAttachmentRepository.updateNewMaintenanceByAttachmentUrl(savedMaintenanceFile.getId(), url);
                    }

                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Autowired
    public MaintenanceManager(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> findAllMaintenance(){
        return maintenanceRepository.findMaintenancesByDeletedByIsNullOrderByCreatedDesc();
    }


    @Override
    public List<Maintenance> getFilteredMaintenances(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user) {
        return this.maintenanceCustomRepository.getMaintenanceFiltered(workCenterId, maintenanceFilter, user);
    }


    @Override
    public ResponseEntity<?> exportMaintenance(MaintenanceFilter maintenanceFilter, HttpServletResponse response, UsuarioWithRoles user) {
        byte[] content=null;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet hoja = workbook.createSheet();
        workbook.setSheetName(0, "Actuaciones");
        //We create style for the header
        CellStyle cellStyleHeaders = workbook.createCellStyle();
        CellStyle dateCell = workbook.createCellStyle();
        Font font = workbook.createFont();
        // TODO color the background of the headers
        font.setBold(true);
        cellStyleHeaders.setFont(font);
        // We create style for date format
        CellStyle cellStyleData = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        // We get the data
        List<Maintenance> maintenances = getFilteredMaintenances(0,maintenanceFilter, user);

        String[] arrayTitle = {EXPORT_TITLE_1, EXPORT_TITLE_2, EXPORT_TITLE_3, EXPORT_TITLE_4, EXPORT_TITLE_5, EXPORT_TITLE_6};

        // We create a row in the sheet at position 0 for the headers
        HSSFRow headerRow = hoja.createRow(0);

        // We create the headers
        for (int i = 0; i < arrayTitle.length; i++) {
            HSSFCell celda = headerRow.createCell(i);
            celda.setCellValue(arrayTitle[i]);
            celda.setCellStyle(cellStyleHeaders);
        }

        // We create the rows
        for (int i = 0; i < maintenances.size(); i++) {
            HSSFRow dataRow = hoja.createRow(1 + i);

            // type
            HSSFCell type = dataRow.createCell(0);
            type.setCellValue(maintenances.get(i).getMaintenanceTypes().getDenomination());

            // Provider
            HSSFCell provider = dataRow.createCell(1);
            provider.setCellValue(maintenances.get(i).getProvider().getName());

            // Periodicity
            HSSFCell periodicity = dataRow.createCell(2);
            periodicity.setCellValue(maintenances.get(i).getExpenditurePeriod().getName());

            // amount
            HSSFCell amount = dataRow.createCell(3);
            amount.setCellValue(maintenances.get(i).getAmount());

            //date
            HSSFCell date = dataRow.createCell(4);
            date.setCellValue(maintenances.get(i).getDate());
            date.setCellStyle(cellStyleData);

            // observations
            HSSFCell observations = dataRow.createCell(5);
            observations.setCellValue(maintenances.get(i).getObservations());


        }

        // Ajustamos columnas
        for (int i = 0; i < arrayTitle.length; i++) {
            hoja.autoSizeColumn(i);
        }

        try {
            String nombreFichero = "reporte-actuaciones";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=\"" +
                    java.net.URLEncoder.encode(nombreFichero, "UTF-8")
                    + "\"");

            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();

        } catch (IOException ex) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

    /**
     * Guardar mantenimiento
     *
     * @Override
     */
    //Logic to Save New Maintenance
    @Transactional
    public ResponseEntity<?> saveNewMaintenance(int workCenterId, Maintenance newMaintenance, MultipartFile[] attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        try {

            newMaintenance.setCreated(new Date());
            newMaintenance.getCreatedBy().setId(userId);

            // Save maintenance
            Maintenance saveMaintenance = this.maintenanceRepository.save(newMaintenance);

            MaintenanceByWorkCenters maintenanceByWorkCenters = new MaintenanceByWorkCenters();

            maintenanceByWorkCenters.getMaintenance().setId(saveMaintenance.getId());
            maintenanceByWorkCenters.getWorkCenter().setId(workCenterId);

            // Save maintenanceByWorkCenter
            this.maintenanceByWorkCentersRepository.save(maintenanceByWorkCenters);

            if (attachedFile.length > 0) {

                for (MultipartFile mpFile : attachedFile) {

                    MaintenanceByAttachment maintenanceByAttachment = new MaintenanceByAttachment();

                    maintenanceByAttachment.setMaintenance(newMaintenance);
                    maintenanceByAttachment.setDocumentUrl("DOC_URL");
                    maintenanceByAttachment.setDocName(mpFile.getOriginalFilename());
                    maintenanceByAttachment.setDocumentContentType(mpFile.getContentType());
                    this.maintenanceByAttachmentRepository.save(maintenanceByAttachment);

                    String url = null;

                    // Guardamos documento en el server
                    url = commonService.saveDocumentServer(workCenterId, saveMaintenance.getId(), mpFile, NEW_MAINTENANCE);

                    // Actualizamos la ruta del documento guardado
                    if (url != null) {
                        this.maintenanceByAttachmentRepository.updateNewMaintenanceByAttachmentUrl(maintenanceByAttachment.getId(), url);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteMaintenance(HttpServletRequest request, int workCenterId, int maintenanceId) {
        long mId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        Maintenance maintenance = this.maintenanceRepository.findMaintenanceById(maintenanceId);

        if (maintenance==null){
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }

        try {
            this.maintenanceRepository.maintenanceLogicDeleted((int) mId,maintenanceId);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<MaintenanceTypes> getAllMaintenanceTypes() {
        return maintenanceTypesRepository.findAll();
    }


}

