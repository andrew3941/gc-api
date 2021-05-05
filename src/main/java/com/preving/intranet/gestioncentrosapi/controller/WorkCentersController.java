package com.preving.intranet.gestioncentrosapi.controller;

import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/workCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkCentersController {

    @Autowired
    private CommonService commonService;


 @RequestMapping( value = "provinces" , method = RequestMethod.GET)
    public ResponseEntity<?> findAllProvinces(){
     List<Province> provinces = null;
     provinces = commonService.findAllProvinces();

     if(provinces == null ) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

     return new ResponseEntity<List<Province>>(provinces, HttpStatus.OK);
 }


    @RequestMapping( value = "entities" , method = RequestMethod.GET)
    public ResponseEntity<?> findAllEntities(){
        List<Entity> entities = null;
        entities = commonService.findAllEntities();

        if(entities == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Entity>>(entities, HttpStatus.OK);
    }


}
