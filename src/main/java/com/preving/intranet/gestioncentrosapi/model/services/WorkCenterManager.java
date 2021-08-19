package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.department.DepartmentRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.dimNavision.DimNavisionRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.drawing.DrawingRepository;
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
import com.preving.security.domain.UsuarioWithRoles;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @PersistenceContext
    private EntityManager manager;

    private static final String EXPORT_TITLE_1 = "Centro";
    private static final String EXPORT_TITLE_2 = "Provincia";
    private static final String EXPORT_TITLE_3 = "Localidad";
    private static final String EXPORT_TITLE_4 = "Direccion";
    private static final String EXPORT_TITLE_5 = "Telefono";
    private static final String EXPORT_TITLE_6 = "Estado";
    private static final String EXPORT_TITLE_7 = "Entidades";

    @Transactional
    public ResponseEntity<?> addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {

        // Obtenemos el usuario creador mediante el token
        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        // Construimos el objeto zona
        Zona zona = seteamosZona(newWorkCenter);

        // Insertamos delegación en MP2.ZONA
        zonaRepository.save(zona);

        // Construimos el objeto dimNavision
        DimNavision dimNavision = seteamosDimNavision(newWorkCenter);

        // Insertamos delegación en RRHH.TM_DIM_NAVISION
        dimNavisionRepository.save(dimNavision);

        // Seteamos los valores necesarios para hacer el insert
        // TODO verificar con fecha de baja
        newWorkCenter.setActive(1);
        newWorkCenter.setVisible(1);
        // Seteamos valores de creación
        newWorkCenter.setCreated(new Date());
        newWorkCenter.getCreatedBy().setId(userId);
        // Seteamos las ids de las tablas secundarias
        newWorkCenter.setIdInMp2(zona.getCodZona());
        newWorkCenter.setLineId(dimNavision.getId());

        // Insertamos delegación en GC2006_RELEASE.PC_DELEGACIONES
        workCentersRepository.save(newWorkCenter);

        // Insertamos valores por defecto para detalles de centro
        WorkCenterDetails workCenterDetails = new WorkCenterDetails();
        workCenterDetails.setWorkCenter(newWorkCenter);
        workCenterDetails.getCreatedBy().setId(userId);
        workCenterDetailsRepository.save(workCenterDetails);

        // save entity in the WorkCenterByEntity table
        saveWorkCenterForEntity(newWorkCenter.getWorkCentersByEntities());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Zona seteamosZona(WorkCenter newWorkCenter) {

        Zona zona = new Zona();

        zona.setCodZona(newWorkCenter.getIdInMp2());
        zona.setDenomination(newWorkCenter.getName());
        zona.setName(newWorkCenter.getName());
        zona.setTelephone(newWorkCenter.getPhoneNumber());
        zona.setEmail(newWorkCenter.getEmail());
        zona.setAddress(newWorkCenter.getAddress());
        zona.setCodPostal(newWorkCenter.getPostalCode());
        zona.setPoblacion(newWorkCenter.getCity().getName());

        return zona;
    }

    private DimNavision seteamosDimNavision(WorkCenter newWorkCenter) {

        DimNavision dimNavision = new DimNavision();

        if (newWorkCenter.getLineId() != null) {
            dimNavision.setId(newWorkCenter.getLineId());
        }
        dimNavision.setType("GEO");
        dimNavision.setCod("pru");
        dimNavision.setName(newWorkCenter.getName());
        dimNavision.setActive(1);
        dimNavision.setOrder(null);
        dimNavision.setMcc_ln_mf("PT");
        String provinceCod = newWorkCenter.getCity().getProvince().getCod();
        dimNavision.setProvinceCod(provinceCod);

        return dimNavision;
    }

    private void saveWorkCenterForEntity(List<WorkCentersByEntity> entities) {

        for(WorkCentersByEntity workCentersByEntity : entities) {
            workCentersByEntitiesRepository.save(workCentersByEntity);
        }

    }

    private void saveWorkCenterForRoom(List<Room> rooms) {
        for(Room room : rooms) {
            roomRepository.save(room);
        }
    }

    @Transactional
    public ResponseEntity<?> editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {

        // Construimos el objeto zona
        Zona zona = seteamosZona(newWorkCenter);

        // Editamos la delegación en la tabla MP2.ZONA
        zonaRepository.editWorkCenter(zona);

        if (newWorkCenter.getLineId() != null) {
            // Construimos el objeto dimNavision
            DimNavision dimNavision = seteamosDimNavision(newWorkCenter);

            // Insertamos delegación en RRHH.TM_DIM_NAVISION
            dimNavisionRepository.editWorkCenter(dimNavision);
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
      return this.drawingRepository.findAllByWorkCenterIdAndDeletedIsNull(workCenterId);
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

        newWorkCenterDrawing.setDoc_url("doc_url");
        newWorkCenterDrawing.setDoc_name(attachedFile.getOriginalFilename());
        newWorkCenterDrawing.setDoc_content_type(attachedFile.getContentType());

        try {
            String url = null;

            Drawing drawing = drawingRepository.save(newWorkCenterDrawing);

            url = commonService.saveDocumentServer(workCenterId, drawing.getId(), attachedFile);

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
//        UsuarioWithRoles user = this.jwtTokenUtil.getUserWithRolesFromToken(request);

        drawing.setDoc_url("doc_url");
        drawing.setDoc_name(attachedFile.getOriginalFilename());
        drawing.setDoc_content_type(attachedFile.getContentType());
        drawing.setModifiedBy(new User());
        drawing.getModifiedBy().setId(uId);

        drawingRepository.editWorkCenterDrawing(drawing);

        try {
            // TODO borrar el doc antiguo
//            commonService.deleteDocumentServer(workCenterId, drawing.getId());

            String url = null;

            url = commonService.saveDocumentServer(workCenterId, drawing.getId(), attachedFile);

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
    public List<RoomTypes> getRoomTypes() {
        return this.roomTypesRepository.findAll();
    }

    @Override
    public List<Room> getRoomListByWorkCenter(int workCenterId){
        return this.roomRepository.findRoomListByWorkCenterId(workCenterId);
    }

    @Override
    public void editWorkCenterRoom(Room room, HttpServletRequest request) {

        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();
        room.setModifiedBy(new User());
        room.getModifiedBy().setId(uId);

        roomRepository.editWorkCenterRoom(room);

    }

    @Override
    public ResponseEntity<?> deleteRoom(HttpServletRequest request, int workCenterId, int roomId) {

        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        Room room = this.roomRepository.findRoomById(roomId);

        if (room==null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        try {

            this.roomRepository.roomLogicDelete((int) uId, roomId, workCenterId);

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

            file = new File(dra.getDoc_url());
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

    @Override
    public ResponseEntity<?> addWorkCenterRoom(int workCenterId, Room newWorkCenterRoom, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newWorkCenterRoom.getWorkCenter().setId(workCenterId);
        newWorkCenterRoom.setCreated(new Date());
        newWorkCenterRoom.getCreatedBy().setId(userId);

        try {
            Room room = roomRepository.save(newWorkCenterRoom);

//            saveWorkCenterForRoom(room.getWorkCenter().getRooms());

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
}

