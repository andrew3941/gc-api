package com.preving.intranet.ssggapi.web;

import com.preving.intranet.ssggapi.model.domain.SimpleJavaBean;
import com.preving.intranet.ssggapi.model.service.ora2Postgre.Ora2PostgreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "ora2Postgre")
public class Ora2PostgreRestController {

    @Autowired
    private Ora2PostgreService ora2PostgreService;

    @GetMapping(value = "sincronizar")
    public ResponseEntity sincronizar () {

        try {
            SimpleJavaBean resultado = this.ora2PostgreService.sincronizar();
            return new ResponseEntity<>((resultado.getId() == 0) ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
