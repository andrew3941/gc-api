package com.preving.intranet.gestioncentrosapi.controller;

import com.preving.intranet.gestioncentrosapi.model.services.WorkCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GeneralDocumentationListController {

    @Autowired
    private WorkCenterService workCenterService;

    @RequestMapping(value = "workCenter/{generalDoc}", method = RequestMethod.GET)
    public ResponseEntity<?> getGeneralDocumentation(@PathVariable(value = "generalDoc") int generalDoc){

        try {
            return new ResponseEntity<>(workCenterService.getGenerlDocumentationListByWorkCenter(generalDoc), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}