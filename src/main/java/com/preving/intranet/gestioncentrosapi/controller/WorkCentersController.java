package com.preving.intranet.gestioncentrosapi.controller;

import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.Entities;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.services.CommonService;
import com.preving.intranet.gestioncentrosapi.model.services.WorkCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/workCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkCentersController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private WorkCenterService workCenterService;


       @RequestMapping(value = "provinces", method = RequestMethod.GET)
    public ResponseEntity<?> findAllProvinces() {
           try{
               return new ResponseEntity<>(this.commonService.findAllProvinces(), HttpStatus.OK);
           } catch (Exception e){
               e.printStackTrace();
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           }
    }

    @RequestMapping(value = "filter", method = RequestMethod.POST)
    public ResponseEntity<?> findWorkCenterByFilter(@RequestBody WorkCenterFilter workCenterFilter) {

        try {
            return new ResponseEntity<>(this.workCenterService.getWorkCenters(workCenterFilter), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

       }

    @RequestMapping(value = "entities", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Entities> entities = null;
        entities = commonService.findAll();

        if (entities == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Entities>>(entities, HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkCenter(HttpServletRequest request, @RequestBody WorkCenter newWorkCenter) {

        try {
            workCenterService.addWorkCenter(newWorkCenter, request);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "{workCenterId}/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editWorkCenter(HttpServletRequest request,
                                            @PathVariable(value="workCenterId") int workCenterId,
                                            @RequestBody WorkCenter newWorkCenter) {
        try {
            workCenterService.editWorkCenter(workCenterId, newWorkCenter,request);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Obtain list of localities via province code
     * @param provinceCod, criterion
     * @return
     */
    @RequestMapping(value = "provinces/{provinceCod}/localities", method = RequestMethod.GET)
    public ResponseEntity<?> findCitiesByProvince(@PathVariable(value = "provinceCod") int provinceCod,
                                                  @RequestParam(value = "criterion") String criterion) {

        try {
            return new ResponseEntity<>(this.workCenterService.findCitiesByProvince(provinceCod, criterion), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtain head person
     * @param  criterion
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<?> findUsers ( @RequestParam(value = "criterion") String criterion) {

        try {
            return new ResponseEntity<>(this.workCenterService.getUsers(criterion), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
