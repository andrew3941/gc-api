package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.department.DepartmentRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.dimNavision.DimNavisionRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.drawing.DrawingRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.room.RoomByTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.room.RoomRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.room.RoomTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.users.UserCustomRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.users.UserRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.*;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCenterDetailsRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.cities.CitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.entities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.zona.ZonaRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.*;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import com.preving.security.JwtTokenUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkCenterManager implements WorkCenterService{

    @Autowired
    private ProvincesRepository provincesRepository;

    @Autowired
    private WorkCentersRepository workCentersRepository;

    @Autowired
    private WorkCentersCustomizeRepository workCentersCustomizeRepository;

    @Autowired
    EntitiesRepository entitiesRepository;

    @Autowired
    CitiesRepository citiesRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private DimNavisionRepository dimNavisionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private WorkCenterDetailsRepository workCenterDetailsRepository;

    @Autowired
    private WorkCentersByEntityRepository workCentersByEntitiesRepository;

    @Autowired
    private WorkCenterDetailsByDepartRepository workCenterDetailsByDepartRepository;

    @Autowired
    private DrawingRepository drawingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private RoomTypesRepository roomTypesRepository;

    @Autowired
    private RoomByTypesRepository roomByTypesRepository;

    @PersistenceContext
    private EntityManager manager;

    private static final String EXPORT_TITLE_1 = "Centro";
    private static final String EXPORT_TITLE_2 = "Provincia";
    private static final String EXPORT_TITLE_3 = "Localidad";
    private static final String EXPORT_TITLE_4 = "Direccion";
    private static final String EXPORT_TITLE_5 = "Telefono";
    private static final String EXPORT_TITLE_6 = "Estado";
    private static final String EXPORT_TITLE_7 = "Entidades";

    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;

    private static final int DRAWINGS_DOCUMENTS = 1;

    @Transactional
    public ResponseEntity<?> addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {

        // Obtenemos el usuario creador mediante el token
        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        // Construimos el objeto zona
        seteamosZona(newWorkCenter);

        // Construimos el objeto dimNavision
        seteamosDimNavision(newWorkCenter);

        // Comprobamos si tiene fecha de baja y seteamos valores
        if (newWorkCenter.getEndDate() != null) {
            newWorkCenter.setActive(INACTIVE);
            newWorkCenter.setVisible(INACTIVE);
        } else {
            newWorkCenter.setActive(ACTIVE);
            newWorkCenter.setVisible(ACTIVE);
        }

        // Seteamos valores de creación
        newWorkCenter.setCreated(new Date());
        newWorkCenter.getCreatedBy().setId(userId);

        // Insertamos delegación en GC2006_RELEASE.PC_DELEGACIONES
        workCentersRepository.save(newWorkCenter);

        // Insertamos valores por defecto para detalles de centro
        // TODO: 27/08/2021 - Meter en el save de work-center
        WorkCenterDetails workCenterDetails = new WorkCenterDetails();
        workCenterDetails.setWorkCenter(newWorkCenter);
        workCenterDetails.getCreatedBy().setId(userId);
        workCenterDetailsRepository.save(workCenterDetails);

        // save entity in the WorkCenterByEntity table
        saveWorkCenterForEntity(newWorkCenter.getWorkCentersByEntities());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private void seteamosZona(WorkCenter newWorkCenter) {

        newWorkCenter.getZona().setDenomination(newWorkCenter.getName());
        newWorkCenter.getZona().setName(newWorkCenter.getName());
        newWorkCenter.getZona().setTelephone(newWorkCenter.getPhoneNumber());
        newWorkCenter.getZona().setEmail(newWorkCenter.getEmail());
        newWorkCenter.getZona().setAddress(newWorkCenter.getAddress());
        newWorkCenter.getZona().setCodPostal(newWorkCenter.getPostalCode());
        newWorkCenter.getZona().setPoblacion(newWorkCenter.getCity().getName());

    }

    private void seteamosDimNavision(WorkCenter newWorkCenter) {

        newWorkCenter.getDimNavision().setType("GEO");
        newWorkCenter.getDimNavision().setName(newWorkCenter.getName());
        newWorkCenter.getDimNavision().setActive(ACTIVE);
        newWorkCenter.getDimNavision().setMccLnMf("PT");
        newWorkCenter.getDimNavision().setProvinceCod(newWorkCenter.getCity().getProvince().getCod());

    }

    private void saveWorkCenterForEntity(List<WorkCentersByEntity> entities) {

        for(WorkCentersByEntity workCentersByEntity : entities) {
            workCentersByEntitiesRepository.save(workCentersByEntity);
        }

    }

    private void saveRoomByTypes(int roomId, List<RoomByTypes> types) {

        for(RoomByTypes type : types) {
            // Seteamos el id de la sala relacionada
            type.getRoom().setId(roomId);

            // Guardamos tipos de salas
            roomByTypesRepository.save(type);
        }
    }

    @Transactional
    public ResponseEntity<?> editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {

        // Construimos el objeto zona
        seteamosZona(newWorkCenter);

        // Editamos la delegación en la tabla MP2.ZONA
        zonaRepository.editWorkCenter(newWorkCenter.getZona());

        if (newWorkCenter.getDimNavision() != null && newWorkCenter.getDimNavision().getId() > 0) {

            // Construimos el objeto dimNavision
            seteamosDimNavision(newWorkCenter);

            // Insertamos delegación en RRHH.TM_DIM_NAVISION
            dimNavisionRepository.editWorkCenter(newWorkCenter.getDimNavision());

        }

        // Seteamos NO activo si viene con fecha de baja incluida
        if (newWorkCenter.getEndDate() != null) {
            newWorkCenter.setActive(INACTIVE);
            newWorkCenter.setVisible(INACTIVE);
        } else {
            newWorkCenter.setActive(ACTIVE);
            newWorkCenter.setVisible(ACTIVE);
        }

        // Editamos la delegación en la tabla GC2006_RELEASE.PC_DELEGACIONES
        workCentersRepository.editWorkCenter(workCenterId, newWorkCenter, this.jwtTokenUtil.getUserWithRolesFromToken(request).getId());

        // Eliminamos las entidades asociadas al centro
        workCentersByEntitiesRepository.deleteByWorkCenter(newWorkCenter);

        // Guardamos la nueva relaciÃ³n de entidades
        for(WorkCentersByEntity workCentersByEntity : newWorkCenter.getWorkCentersByEntities()) {
            workCentersByEntitiesRepository.save(workCentersByEntity);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter) {

        // Getting the work centers list by filter
        List<WorkCenter> workCenters = this.workCentersCustomizeRepository.getWorkCenters(workCenterFilter);

        // Setting entities related with the work center
        for(WorkCenter workCenter : workCenters) {
            workCenter.setWorkCentersByEntities(this.workCentersByEntitiesRepository.findByWorkCenter(workCenter));
        }

        return workCenters;

    }

    @Override
    public List<Department> getDepartments() {
        return this.departmentRepository.findAllByOrderByName();
    }

    @Override
    public WorkCenter getWorkCenterById(int workCenterId) {
        WorkCenter workCenter = this.workCentersRepository.findWorkCenterById(workCenterId);

        if (workCenter.getHeadPerson() != null) {
            workCenter.getHeadPerson().setCompleteName(workCenter.getHeadPerson().getLastname() + ", " + workCenter.getHeadPerson().getFirstname());
        }

        int totalEmployee = this.workCentersCustomizeRepository.getTotalEmployee(workCenterId);
        workCenter.setEmployee(totalEmployee);

        return workCenter;
    }

    @Override
    public List<City> findCitiesByProvince(String provinceCod, String criterion) {
        return citiesRepository.findCitiesByProvince(provinceCod, criterion);
    }

    @Override
    public List<User> findUsersByCriterion(String criterion) {
        return userCustomRepository.findUserByCriterion(criterion);
    }

    @Override
    @Transactional
    public ResponseEntity<?> editWorkCenterDetails(int workCenterId, WorkCenterDetails workCenterDetails, HttpServletRequest request) {

        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        List<WorkCenterDetailsByDepart> departments = workCenterDetails.getDepartments();

        workCenterDetails.getWorkCenter().setId(workCenterId);

        WorkCenterDetails wcDetails = workCenterDetailsRepository.findWorkCenterDetailsByWorkCenterId(workCenterId);
        if (wcDetails == null) {
            workCenterDetails.setCreated(new Date());
            workCenterDetails.setCreatedBy(new User());
            workCenterDetails.getCreatedBy().setId(userId);

            workCenterDetailsRepository.save(workCenterDetails);
        } else {
            workCenterDetails.setModified(new Date());
            workCenterDetails.setModifiedBy(new User());
            workCenterDetails.getModifiedBy().setId(userId);

            workCenterDetailsRepository.updateWorkCenterDetails(workCenterDetails);
        }

        this.saveDelegationDepartment(departments, workCenterDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void saveDelegationDepartment(List<WorkCenterDetailsByDepart> departments, WorkCenterDetails workCenterDetails) {

        // Deleting all the departments previously saved by the work center
        workCenterDetailsByDepartRepository.deleteByWorkCenterDetailsId(workCenterDetails.getId());

        // Saving the new departments related with the work center
        for(WorkCenterDetailsByDepart department: departments) {
            workCenterDetailsByDepartRepository.save(department);
        }

    }

    @Override
    public WorkCenterDetails getWorkCenterDetails(int workCenterId) {

        WorkCenter workCenter = workCentersRepository.getOne(workCenterId);
        WorkCenterDetails workCenterDetails = workCenterDetailsRepository.findWorkCenterDetailsByWorkCenter(workCenter);

        if(workCenterDetails == null) {
            workCenterDetails = new WorkCenterDetails();
            workCenterDetails.setWorkCenter(workCenter);
        }

        return workCenterDetails;

    }


    public ResponseEntity<?> exportWorkCenters(WorkCenterFilter workCenterFilter, HttpServletResponse response){

        byte[] content=null;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet hoja = workbook.createSheet();
        workbook.setSheetName(0, "Actuaciones");

        // Creamos estilo para el encabezado
        CellStyle cellStyleHeaders = workbook.createCellStyle();
        CellStyle dateCell = workbook.createCellStyle();
        Font font = workbook.createFont();
//        HSSFPalette palette = workbook.getCustomPalette();
//        HSSFColor myColor = palette.findSimilarColor(87, 35, 100);
//        short palIndex = myColor.getIndex();
        // TODO colorear el fondo de las cabeceras
//        cellStyleHeaders.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
//        cellStyleHeaders.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        font.setColor(HSSFColor.WHITE.index);
        font.setBold(true);
        cellStyleHeaders.setFont(font);

        // *Formatos de fecha en caso de necesitarlo
//        dateCell.setAlignment(CellStyle.ALIGN_RIGHT);
//        dateCell.setFont(font);
//        dateCell.setFillForegroundColor(palIndex);
//        dateCell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // Creamos estilo para formato fecha
        CellStyle cellStyleData = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));

        // Obtenemos los datos
        List<WorkCenter> workCenters = this.workCentersCustomizeRepository.getWorkCenters(workCenterFilter);

        // Setting entities related with the work center
        for(WorkCenter workCenter : workCenters) {
            workCenter.setWorkCentersByEntities(this.workCentersByEntitiesRepository.findByWorkCenter(workCenter));
        }


        String[] titulos = {EXPORT_TITLE_1, EXPORT_TITLE_2, EXPORT_TITLE_3, EXPORT_TITLE_4,
                EXPORT_TITLE_5, EXPORT_TITLE_6, EXPORT_TITLE_7};

        // Creamos una fila en la hoja en la posicion 0 para los headers
        HSSFRow headerRow = hoja.createRow(0);

        // Creamos los headers
        for (int i = 0; i < titulos.length; i++) {
            HSSFCell celda = headerRow.createCell(i);
            celda.setCellValue(titulos[i]);
            celda.setCellStyle(cellStyleHeaders);
        }

        // Creamos las filas
        for (int i = 0; i < workCenters.size(); i++) {
            HSSFRow dataRow = hoja.createRow(1 + i);

            // Centro
            HSSFCell center = dataRow.createCell(0);
            center.setCellValue(workCenters.get(i).getName());

            // Provincia
            HSSFCell province = dataRow.createCell(1);
            province.setCellValue(workCenters.get(i).getCity().getProvince().getName());

            // Localidad
            HSSFCell locality = dataRow.createCell(2);
            locality.setCellValue(workCenters.get(i).getCity().getName());

            // DirecciÃ³n
            HSSFCell address = dataRow.createCell(3);
            address.setCellValue(workCenters.get(i).getAddress());

            // TelÃ©fono
            HSSFCell phoneNumber = dataRow.createCell(4);
            phoneNumber.setCellValue(workCenters.get(i).getPhoneNumber());

            // Estado
            HSSFCell status = dataRow.createCell(5);
            if (workCenters.get(i).getActive() == 1) {
                status.setCellValue("Activo");
            } else {
                status.setCellValue("Inactivo");
            }

            // Entidades
            HSSFCell entities = dataRow.createCell(6);
            for (WorkCentersByEntity workCenterByEntity: workCenters.get(i).getWorkCentersByEntities()) {
                entities.setCellValue(entities + workCenterByEntity.getEntity().getName() + ", ");
            }


        }

        // Ajustamos columnas
        for (int i = 0; i < titulos.length; i++) {
            hoja.autoSizeColumn(i);
        }

        try {
            String nombreFichero = "reporte-actuaciones";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader ("Content-Disposition", "inline; filename=\"" +
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

    @Override
    public List<Drawing> getDrawingByWorkCenter(int workCenterId) {
      return this.drawingRepository.findAllByWorkCenterIdAndDeletedIsNullOrderByCreatedDesc(workCenterId);
    }

    @Override
    public ResponseEntity<?> deleteDrawing(HttpServletRequest request, int workCenterId, int drawingId) {

        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        Drawing drawing = this.drawingRepository.findDrawingById(drawingId);

        if (drawing==null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }

        try {
            this.drawingRepository.drawingLogicDelete((int) uId, drawing.getId(), workCenterId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addWorkCenterDrawing(int workCenterId, Drawing newWorkCenterDrawing, MultipartFile attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newWorkCenterDrawing.getWorkCenter().setId(workCenterId);
        newWorkCenterDrawing.setCreated(new Date());
        newWorkCenterDrawing.getCreatedBy().setId(userId);

        newWorkCenterDrawing.setDocUrl("doc_url");
        newWorkCenterDrawing.setDocName(attachedFile.getOriginalFilename());
        newWorkCenterDrawing.setDocContentType(attachedFile.getContentType());

        try {
            String url = null;

            Drawing drawing = drawingRepository.save(newWorkCenterDrawing);

            url = commonService.saveDocumentServer(workCenterId, drawing.getId(), attachedFile, DRAWINGS_DOCUMENTS);

            if(url != null){
                this.drawingRepository.updateDrawingDocUrl(drawing.getId(), url);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editWorkCenterDrawing(int workCenterId, int workCenterDrawingId, Drawing drawing, MultipartFile attachedFile, HttpServletRequest request) {

        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        if (attachedFile != null) {
            drawing.setDocUrl("doc_url");
            drawing.setDocName(attachedFile.getOriginalFilename());
            drawing.setDocContentType(attachedFile.getContentType());
        }

        drawing.setModifiedBy(new User());
        drawing.getModifiedBy().setId(uId);

        drawingRepository.editWorkCenterDrawing(drawing);

        try {
            if (attachedFile != null) {
                // Borramos el documento anterior del servidor
                commonService.deleteDocumentServer(workCenterId, drawing.getId(), DRAWINGS_DOCUMENTS);

                String url = null;

                // Guardamos el nuevo documento adjunto
                url = commonService.saveDocumentServer(workCenterId, drawing.getId(), attachedFile, DRAWINGS_DOCUMENTS);

                // Actualizamos la URL del documento
                if(url != null){
                    this.drawingRepository.updateDrawingDocUrl(drawing.getId(), url);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public List<RoomTypes> getRoomTypes() {
        return this.roomTypesRepository.findAll();
    }

    @Override
    public List<Room> getRoomListByWorkCenter(int workCenterId){
        return this.roomRepository.findRoomListByWorkCenterIdAndDeletedIsNullOrderByCreatedDesc(workCenterId);
    }

    @Transactional
    public void editWorkCenterRoom(Room room, HttpServletRequest request) {

        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();
        room.setModifiedBy(new User());
        room.getModifiedBy().setId(uId);

        // Guardamos la sala
        roomRepository.editWorkCenterRoom(room);

        // Borramos los tipos de las salas guardadas
        this.roomByTypesRepository.deleteByRoomId(room.getId());

        // Guardamos los tipos de salas
        saveRoomByTypes(room.getId(), room.getTypes());

    }

    @Transactional
    public ResponseEntity<?> deleteRoom(HttpServletRequest request, int workCenterId, int roomId) {

        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        Room room = this.roomRepository.findRoomById(roomId);

        if (room==null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        try {

            this.roomRepository.roomLogicDelete((int) uId, roomId, workCenterId);

            this.roomByTypesRepository.deleteByRoomId(roomId);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> downloadDrawingDoc(HttpServletRequest request, int drawingId) {

        Drawing dra = null;
        File file = null;
        byte[] content=null;

        try {
            dra = this.drawingRepository.findDrawingById(drawingId);

            file = new File(dra.getDocUrl());
            if (file.exists()) {
                content = Files.readAllBytes(file.toPath());
            }else{
                return new ResponseEntity<>("File not found",HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Uknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> addWorkCenterRoom(int workCenterId, Room newWorkCenterRoom, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newWorkCenterRoom.getWorkCenter().setId(workCenterId);
        newWorkCenterRoom.setCreated(new Date());
        newWorkCenterRoom.getCreatedBy().setId(userId);

        try {
            // Guardamos la sala
            Room room = roomRepository.save(newWorkCenterRoom);

            // Guardamos el tipo de la sala
            saveRoomByTypes(room.getId(), newWorkCenterRoom.getTypes());

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Drawing getDrawingById(int drawingId) {
        return drawingRepository.findDrawingById(drawingId);
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomRepository.findRoomById(roomId);
    }

    @Override
    public void desactivateWorkCenters() {

        System.out.println("--------------------------------------------------------------");
        System.out.println("--- INICIO DEL PROCESO DE DESACTIVACION DE DELEGACIONES");
        System.out.println("--------------------------------------------------------------");

        // Getting work centers with expired end date
        List<WorkCenter> workCenters = workCentersRepository.findWorkCentersByEndDateIsLessThanEqual(new Date());

        System.out.println("----- Se han obtenido " + workCenters.size() + " delegaciones para finalizar");

        // Setting inactive attribute for each work center
        workCenters.forEach(workCenter -> {
            workCentersRepository.setInactiveWorkCenter(workCenter.getId());
            System.out.println("--------- Delegacion (" + workCenter.getId() + ") -> Desactivada");
        });

        System.out.println("--------------------------------------------------------------");
        System.out.println("--- FIN DEL PROCESO DE DESACTIVACION DE DELEGACIONES");
        System.out.println("--------------------------------------------------------------");

    }

    @Override
    public List<WorkCenter> findByWorkCenters() {
        return workCentersCustomizeRepository.findAllByActive();
    }

}

