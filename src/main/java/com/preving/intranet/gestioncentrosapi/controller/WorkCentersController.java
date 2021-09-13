package com.preving.intranet.gestioncentrosapi.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.preving.intranet.gestioncentrosapi.model.domain.Drawing;
import com.preving.intranet.gestioncentrosapi.model.domain.Room;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import com.preving.intranet.gestioncentrosapi.model.services.CommonService;
import com.preving.intranet.gestioncentrosapi.model.services.WorkCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path= "/workCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkCentersController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private WorkCenterService workCenterService;

    @Value("${modo-debug}")
    private boolean modoDebug;

    /**
     * Obtiene la lista de provincias
     * @return
     */
    @RequestMapping(value = "provinces", method = RequestMethod.GET)
    public ResponseEntity<?> findAllProvinces() {
        try{
            return new ResponseEntity<>(this.commonService.findAllProvinces(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene las delegaciones mediante filtro
     * @RequestBody WorkCenterFilter
     * @return
     */
    @RequestMapping(value = "filter", method = RequestMethod.POST)
    public ResponseEntity<?> findWorkCenterByFilter(@RequestBody WorkCenterFilter workCenterFilter) {

        try {
            return new ResponseEntity<>(this.workCenterService.getWorkCenters(workCenterFilter), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtiene la lista de entidades
     * @return
     */
    @RequestMapping(value = "entities", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(this.commonService.findAllEntities(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene la lista de departamentos
     * @return
     */
    @RequestMapping(value = "departments", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartments() {

        try {
            return new ResponseEntity<>(this.workCenterService.getDepartments(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * Agregamos un centro de trabajo
     * @RequestBody WorkCenter
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkCenter(HttpServletRequest request, @RequestBody WorkCenter newWorkCenter) {

        try {
            workCenterService.addWorkCenter(newWorkCenter, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Editamos un centro de trabajo
     * @param workCenterId
     * @RequestBody WorkCenter
     * @return
     */
    @RequestMapping(value = "{workCenterId}/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editWorkCenter(HttpServletRequest request,
                                            @PathVariable(value="workCenterId") int workCenterId,
                                            @RequestBody WorkCenter newWorkCenter) {

        ResponseEntity<?> response;

        try {
            response =  workCenterService.editWorkCenter(workCenterId, newWorkCenter,request);
        } catch (Exception e) {
            e.printStackTrace();
            response=  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    /**
     * Obtiene un listado de localidades por cod_provincia
     * @param provinceCod, criterion
     * @return
     */
    @RequestMapping(value = "provinces/{provinceCod}/localities", method = RequestMethod.GET)
    public ResponseEntity<?> findCitiesByProvince(@PathVariable(value = "provinceCod") String provinceCod,
                                                  @RequestParam(value = "criterion") String criterion) {

        try {
            return new ResponseEntity<>(this.workCenterService.findCitiesByProvince(provinceCod, criterion), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene usuarios por criterio
     * @param  criterion
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<?> findUsers ( @RequestParam(value = "criterion") String criterion) {

        try {
            return new ResponseEntity<>(this.workCenterService.findUsersByCriterion(criterion), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Obtiene un centro de trabajo por Id
     * @param centerId
     * @return
     */
    @RequestMapping(value = "{centerId}", method = RequestMethod.GET)
    public ResponseEntity<?> findWorkCenterById(@PathVariable(value = "centerId") int centerId){

        try {
            return new ResponseEntity<>(workCenterService.getWorkCenterById(centerId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtiene los detalles del centro de trabajo por Id
     * @param workCenterId
     * @regreso
     */
    @RequestMapping(value = "{workCenterId}/details", method = RequestMethod.GET)
    public ResponseEntity<?> findWorkCenterDetails(@PathVariable(value = "workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(workCenterService.getWorkCenterDetails(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Editamos un centro de trabajo por Id
     * @param workCenterId
     * @regreso
     */
    @RequestMapping(value = "{workCenterId}/details/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editWorkCenterDetails(HttpServletRequest request,
                                                   @PathVariable(value="workCenterId") int workCenterId,
                                                   @RequestBody WorkCenterDetails workCenterDetails){

        try {
            return new ResponseEntity<>(workCenterService.editWorkCenterDetails(workCenterId, workCenterDetails, request), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Exportación de actuaciones por filtro de fechas
     * @param
     * @return
     */
    @RequestMapping(value="exportWorkCenters", method = RequestMethod.POST)
    public ResponseEntity<?> exportActions(HttpServletResponse response,
                                           @RequestParam ("workCentersList") String workCentersList) {

        ResponseEntity<?> resp = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        WorkCenterFilter workCenterFilter = gson.fromJson(workCentersList, WorkCenterFilter.class);

        try {
            return new ResponseEntity<>(workCenterService.exportWorkCenters(workCenterFilter, response), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // TODO mover a otro controller todos los métodos desde aquí
    /**
     * Obtiene listado de planos de un centro de trabajo por Id
     * @return
     */
    @RequestMapping(value = "{workCenterId}/drawings", method = RequestMethod.GET)
    public ResponseEntity<?> getDrawingByWorkCenter(@PathVariable(value = "workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(workCenterService.getDrawingByWorkCenter(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtiene los detalles de un plano de un centro de trabajo por Id
     * @return
     */
    @RequestMapping(value = "drawings/{drawingId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDrawingById(@PathVariable(value = "drawingId") int drawingId){

        try {
            return new ResponseEntity<>(workCenterService.getDrawingById(drawingId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtiene listado de los tipos de salas
     * @return
     */
    @RequestMapping(value = "roomTypes", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomTypes(){

        try {
            return new ResponseEntity<>(workCenterService.getRoomTypes(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtiene listado de salas de un centro de trabajo por Id
     * @return
     */
    @RequestMapping(value = "{workCenterId}/rooms", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomListByWorkCenter(@PathVariable(value = "workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(workCenterService.getRoomListByWorkCenter(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtiene los detalles de una sala de reuniones de un centro de trabajo por Id
     * @return
     */
    @RequestMapping(value = "rooms/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomById(@PathVariable(value = "roomId") int roomId){

        try {
            return new ResponseEntity<>(workCenterService.getRoomById(roomId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Borramos un plano de un centro de trabajo por Id
     * @return
     */
    @RequestMapping(value = "{workCenterId}/drawings/{drawingId}/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteDrawing (HttpServletRequest request,
                                            @PathVariable(value = "workCenterId") int workCenterId,
                                            @PathVariable(value = "drawingId") int drawingId) {


        return workCenterService.deleteDrawing(request,workCenterId,drawingId);

    }

    /**
     * Agregamos un plano al centro de trabajo
     * @param workCenterDrawing
     * @param workCenterId
     * @param request
     * @return
     */
    @RequestMapping(value = "{workCenterId}/drawings/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkCenterDrawing(
            @RequestParam("workCenterDrawing") String workCenterDrawing,
            @PathVariable("workCenterId") int workCenterId,
            @RequestParam("attachedFile") MultipartFile attachedFile,
            HttpServletRequest request) {

        Gson gson = new GsonBuilder().create();
        Drawing newWCDrawing= gson.fromJson(workCenterDrawing, Drawing.class);

        try {
            workCenterService.addWorkCenterDrawing(workCenterId, newWCDrawing, attachedFile, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * Editamos un plano de un centro de trabajo
     * @param workCenterDrawing
     * @param workCenterDrawingId
     * @param request
     * @return
     */
    @RequestMapping(value = "{workCenterId}/drawings/{workCenterDrawingId}/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editWorkCenterDrawing(@RequestParam("workCenterDrawing") String workCenterDrawing,
                                                   @RequestParam(value="attachedFile", required = false) MultipartFile attachedFile,
                                                   @PathVariable("workCenterId") int workCenterId,
                                                   @PathVariable("workCenterDrawingId") int workCenterDrawingId, HttpServletRequest request) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Drawing drawing= gson.fromJson(workCenterDrawing, Drawing.class);

        try {
            workCenterService.editWorkCenterDrawing(workCenterId, workCenterDrawingId, drawing, attachedFile, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Editamos una sala de un centro de trabajo
     * @param workCenterId
     * @param roomId
     * @param request
     * @return
     */
    @RequestMapping(value = "{workCenterId}/rooms/{roomId}/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editRoomList(HttpServletRequest request,
                                          @RequestBody Room room,
                                          @PathVariable(value="workCenterId") int workCenterId,
                                          @PathVariable(value = "roomId") int roomId){

        try {
            workCenterService.editWorkCenterRoom(room, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Borramos una sala de reuniones de un centro de trabajo por Id
     * @return
     */
    @RequestMapping(value = "{workCenterId}/rooms/{roomId}/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRoom (HttpServletRequest request,
                                            @PathVariable(value = "workCenterId") int workCenterId,
                                            @PathVariable(value = "roomId") int roomId) {


        return workCenterService.deleteRoom(request,workCenterId,roomId);

    }


    /**
     * Descargamos el fichero de un plano
     * @param drawingId
     * @param request
     * @return
     */
    @RequestMapping(value = "{drawingId}/download", method = RequestMethod.GET)
    public ResponseEntity<?> downloadDrawingDoc(HttpServletRequest request, @PathVariable(value = "drawingId") int drawingId) {

        return ( workCenterService.downloadDrawingDoc(request,drawingId));
    }

    /**
     * Agregamos una sala de reunions al centro de trabajo
     * @RequestBody Salas
     * @return
     */
    @RequestMapping(value = "{workCenterId}/rooms/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkCenterRoom(
            @RequestParam("workCenterRoom") String workCenterRoom,
            @PathVariable("workCenterId") int workCenterId,
            HttpServletRequest request) {

        Gson gson = new GsonBuilder().create();
        Room newWCRoom = gson.fromJson(workCenterRoom, Room.class);
        try {
            workCenterService.addWorkCenterRoom(workCenterId, newWCRoom, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Scheduled(cron="0 0 0 * * ?")
    public void initProjectService() {

        if(!modoDebug) {

            try {
                workCenterService.desactivateWorkCenters();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Obtención listado de workCenters
     * @return
     */
    @RequestMapping(value = "workCenters", method = RequestMethod.GET)
    public ResponseEntity<?> findAllByActiveIsTrue() {

        try {
            return new ResponseEntity<>(workCenterService.findByWorkCenters(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
