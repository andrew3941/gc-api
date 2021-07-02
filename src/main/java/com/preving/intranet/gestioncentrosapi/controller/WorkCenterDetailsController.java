package com.preving.intranet.gestioncentrosapi.controller;


import com.preving.intranet.gestioncentrosapi.model.services.WorkCenterDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path= "/workCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkCenterDetailsController {

    @Autowired
    private WorkCenterDetailsService workCenterDetailsService;

    /**
     * Obtención de detalles del centro de trabajo por workCenterId
     * @param workCenterId
     * @regreso
     */

    @RequestMapping(value = "getDetails/{workCenterId}", method = RequestMethod.GET)
    public ResponseEntity<?> findWorkCenterDetails(@PathVariable(value = "workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(workCenterDetailsService.getWorkCenterDetails(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
