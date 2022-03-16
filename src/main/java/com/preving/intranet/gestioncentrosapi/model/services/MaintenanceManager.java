package com.preving.intranet.gestioncentrosapi.model.services;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceByAttachmentRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceCustomRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes;
import com.preving.security.JwtTokenUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.Transactional;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
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
    private static final int MAINTENANCE = 3;
    //    export
    static final String EXPORT_TITLE_1 = "maintenanceProvider";
    private static final String EXPORT_TITLE_2 = "maintenanceType";
    static final String EXPORT_TITLE_3 = "maintenanceStartDate";
    static final String EXPORT_TITLE_4 = "maintenanceEndDate";

    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private MaintenanceCustomRepository maintenanceCustomRepository;

    @Autowired
    private MaintenanceByAttachmentRepository maintenanceByAttachmentRepository;

    @Autowired
    private MaintenanceTypesRepository maintenanceTypesRepository;

      @Autowired
    private CommonService commonService;


    private static final int NEW_MAINTENANCE = 3;

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

                for (MaintenanceByAttachement maintFile : maintenance.getMaintenanceByAttachments()){
                    // Borramos el documento anterior del servidor
                    commonService.deleteDocumentServer(workCenterId, maintFile.getId(), NEW_MAINTENANCE);

                   maintenanceByAttachmentRepository.deleteById(maintFile.getId());
                }

                if (attachedFile.length > 0) {

                    for (MultipartFile mtFile : attachedFile){

                        MaintenanceByAttachement maintenanceByAttachement = new MaintenanceByAttachement();

                        maintenanceByAttachement.setMaintenance(maintenance);
                        maintenanceByAttachement.setDocName(mtFile.getOriginalFilename());
                        maintenanceByAttachement.setDocumentContentType(mtFile.getContentType());
                        maintenanceByAttachement.setDocumentUrl("default_Url");

                       MaintenanceByAttachement savedMaintenanceFile = maintenanceByAttachmentRepository.save(maintenanceByAttachement);

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

        byte[] content = null;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet hoja = workbook.createSheet();
        workbook.setSheetName(0, "Actuaciones");

        // Creamos estilo para el encabezado
        CellStyle cellStyleHeaders = workbook.createCellStyle();
        CellStyle dateCell = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setBold(true);
        cellStyleHeaders.setFont(font);

        // Creamos estilo para formato fecha
        CellStyle cellStyleData = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));

        // Obtenemos los datos
        List<Maintenance> maintenanceFilters = this.maintenanceCustomRepository.getMaintenanceFiltered(0,maintenanceFilter, user);


        String[] titulos = {
                EXPORT_TITLE_1,
                EXPORT_TITLE_2,
                EXPORT_TITLE_3,
                EXPORT_TITLE_4,
        };

        // Creamos una fila en la hoja en la posicion 0 para los headers
        HSSFRow headerRow = hoja.createRow(0);

        // Creamos los headers
        for (int i = 0; i < titulos.length; i++) {
            HSSFCell celda = headerRow.createCell(i);
            celda.setCellValue(titulos[i]);
            celda.setCellStyle(cellStyleHeaders);
        }
        // Creamos las filas
        for (int i = 0; i < maintenanceFilters.size(); i++) {
            HSSFRow dataRow = hoja.createRow(1 + i);

            // provider
            HSSFCell provider = dataRow.createCell(0);
            provider.setCellValue(maintenanceFilter.getMaintenanceProvider());

            // maintenancType
            HSSFCell maintenancType = dataRow.createCell(1);
            maintenancType.setCellValue((Date) maintenanceFilter.getMaintenanceTypes());

            // startDate
            HSSFCell startDate = dataRow.createCell(2);
            startDate.setCellValue(maintenanceFilter.getMaintenanceStartDate());

            // endDate
            HSSFCell endDate = dataRow.createCell(3);
            endDate.setCellValue(maintenanceFilter.getMaintenanceEndDate());

        }
        // Ajustamos columnas
        for (int i = 0; i < titulos.length; i++) {
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

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();;

        newMaintenance.setCreated(new Date());
        newMaintenance.getCreatedBy().setId(userId);

        try {

            Maintenance saveMaintenance = this.maintenanceRepository.save(newMaintenance);

            if (attachedFile.length > 0) {

                for (MultipartFile mpFile : attachedFile) {

                    MaintenanceByAttachement maintenanceByAttachement = new MaintenanceByAttachement();

                    maintenanceByAttachement.setMaintenance(newMaintenance);
                    maintenanceByAttachement.setDocumentUrl("DOC_URL");
                    maintenanceByAttachement.setDocName(mpFile.getOriginalFilename());
                    maintenanceByAttachement.setDocumentContentType(mpFile.getContentType());
                    this.maintenanceByAttachmentRepository.save(maintenanceByAttachement);

                    String url = null;

                    // Guardamos documento en el server
                    url = commonService.saveDocumentServer(workCenterId, saveMaintenance.getId(), mpFile, NEW_MAINTENANCE);

                    // Actualizamos la ruta del documento guardado
                    if (url != null) {
                        this.maintenanceByAttachmentRepository.updateNewMaintenanceByAttachmentUrl(maintenanceByAttachement.getId(), url);
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

