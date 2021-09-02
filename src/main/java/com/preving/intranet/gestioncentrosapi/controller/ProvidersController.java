package com.preving.intranet.gestioncentrosapi.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import com.preving.intranet.gestioncentrosapi.model.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "/workCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class ProvidersController {

    @Autowired
    public ProviderService providerService;

    /**
     *
     * @param workCenterId
     * @return
     */
    @RequestMapping(value = "{workCenterId}/providers/types", method = RequestMethod.GET)
    public ResponseEntity<?> getProviderTypes( @PathVariable(value="workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(providerService.getProviderTypes(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "{workCenterId}/providers/evaluations", method = RequestMethod.GET)
    public ResponseEntity<?> getProviderEvaluationTypes( @PathVariable(value="workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(providerService.getProviderEvaluationTypes(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "{workCenterId}/providers/areas", method = RequestMethod.GET)
    public ResponseEntity<?> getProviderArea( @PathVariable(value="workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(providerService.getProviderArea(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "{workCenterId}/providers/periodicity", method = RequestMethod.GET)
    public ResponseEntity<?> getExpenditurePeriod( @PathVariable(value="workCenterId") int workCenterId){

        try {
            return new ResponseEntity<>(providerService.getExpenditurePeriod(workCenterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Obtain filter based on providerFilter Class
     * @param providerFilter
     * @param workCenterId
     * @return
     */

    @RequestMapping(value = "{workCenterId}/providers/filter", method = RequestMethod.POST)
    public ResponseEntity<?> findWorkCenterByFilter(@RequestBody ProviderFilter providerFilter,
                                                    @PathVariable(value="workCenterId") int workCenterId) {
        try {
            return new ResponseEntity<>(this.providerService.getProviders(workCenterId, providerFilter), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "{workCenterId}/providers/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveProvider(
            @RequestParam("provider") String myProvider,
            @PathVariable("workCenterId") int workCenterId,
            @RequestParam("attachedFile") MultipartFile attachedFile,
            HttpServletRequest request) {

       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Provider newProvider = gson.fromJson(myProvider, Provider.class);

        try {
            providerService.saveProvider(workCenterId, newProvider, attachedFile, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
