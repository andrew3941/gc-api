package com.preving.intranet.gestioncentrosapi.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

}
