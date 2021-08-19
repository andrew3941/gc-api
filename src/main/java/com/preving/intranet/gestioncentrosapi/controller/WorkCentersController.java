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
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * A�adimos una delegaci�n
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
     * Editamos una delegaci�n
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
     * Obtenci�n listado de localidades por cod_provincia
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
     * Obtenci�n de delegaci�n por Id
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
     * Obtención de detalles del centro de trabajo por workCenterId
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

    @RequestMapping(value = "{workCenterId}/drawings", method = RequestMethod.GET)
    public ResponseEntity<?> getDrawingByWorkCenter(@PathVariable(value = "workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(workCenterService.getDrawingByWorkCenter(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "drawings/{drawingId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDrawingById(@PathVariable(value = "drawingId") int drawingId){

        try {
            return new ResponseEntity<>(workCenterService.getDrawingById(drawingId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "roomTypes", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomTypes(){

        try {
            return new ResponseEntity<>(workCenterService.getRoomTypes(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "{workCenterId}/rooms", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomListByWorkCenter(@PathVariable(value = "workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(workCenterService.getRoomListByWorkCenter(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "rooms/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomById(@PathVariable(value = "roomId") int roomId){

        try {
            return new ResponseEntity<>(workCenterService.getRoomById(roomId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "{workCenterId}/drawings/{drawingId}/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteDrawing (HttpServletRequest request,
                                            @PathVariable(value = "workCenterId") int workCenterId,
                                            @PathVariable(value = "drawingId") int drawingId) {


        return workCenterService.deleteDrawing(request,workCenterId,drawingId);

    }

    /**
     *
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
     *
     * @param workCenterDrawing
     * @param workCenterDrawingId
     * @param request
     * @return
     */
    @RequestMapping(value = "{workCenterId}/drawings/{workCenterDrawingId}/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editWorkCenterDrawing(@RequestParam("workCenterDrawing") String workCenterDrawing,
                                                   @RequestParam("attachedFile") MultipartFile attachedFile,
                                                   @PathVariable("workCenterId") int workCenterId,
                                                   @PathVariable("workCenterDrawingId") int workCenterDrawingId, HttpServletRequest request) {

        Gson gson = new GsonBuilder().create();
        Drawing drawing= gson.fromJson(workCenterDrawing, Drawing.class);

        try {
            workCenterService.editWorkCenterDrawing(workCenterId, workCenterDrawingId, drawing, attachedFile, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


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


    @RequestMapping(value = "{workCenterId}/rooms/{roomId}/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRoom (HttpServletRequest request,
                                            @PathVariable(value = "workCenterId") int workCenterId,
                                            @PathVariable(value = "roomId") int roomId) {


        return workCenterService.deleteRoom(request,workCenterId,roomId);

    }


    /**
     * @param drawingId
     * @param request
     * @return
     */
    @RequestMapping(value = "{drawingId}/download", method = RequestMethod.GET)
    public ResponseEntity<?> downloadDrawingDoc(HttpServletRequest request, @PathVariable(value = "drawingId") int drawingId) {

        return ( workCenterService.downloadDrawingDoc(request,drawingId));
    }

    /**
     * A�adimos una delegaci�n
     * @RequestBody Salas
     * @return
     */
    @RequestMapping(value = "{workCenterId}/rooms/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkCenterRoom(
            @RequestParam("workCenterRoom") String workCenterRoom,
            // @RequestBody Room workCenterRoom,
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
}
