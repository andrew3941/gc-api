package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vendor.*;
import com.preving.intranet.gestioncentrosapi.model.domain.Drawing;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.*;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ProviderManager implements ProviderService {

   @Autowired
   private ProviderTypesRepository providerTypesRepository;

    @Autowired
    private ProviderAreaRepository providerAreaRepository;

    @Autowired
    private ProviderEvaluationTypesRepository providerEvaluationTypesRepository;

    @Autowired
    private ExpenditurePeriodRepository expenditurePeriodRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderCustomRepository providerCustomRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

   @Override
    public List<Provider> getProviders(int workCenterId, ProviderFilter providerFilter) {
    List<Provider> providers = this.providerCustomRepository.getProviders(workCenterId, providerFilter);  ;
       return providers;
    }

    @Override
    public List<ProviderTypes> getProviderTypes(int workCenterId) {
    return providerTypesRepository.findAll(); }

    @Override
    public List<ProviderArea> getProviderArea(int workCenterId) {  return providerAreaRepository.findAll(); }

    @Override
    public List<ProviderEvaluationTypes> getProviderEvaluationTypes(int workCenterId) { return providerEvaluationTypesRepository.findAll(); }

    @Override
    public List<ExpenditurePeriod> getExpenditurePeriod(int workCenterId) { return expenditurePeriodRepository.findAll(); }

    @Override
    public ResponseEntity<?> saveProvider(int workCenterId, Provider newProvider, MultipartFile attachedFile, HttpServletRequest request) {

  long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

  newProvider.getWorkCenter().setId(workCenterId);
  newProvider.setCreated(new Date());
  newProvider.getCreatedBy().setId((long) 1);
  newProvider.setDocName(attachedFile.getOriginalFilename());
  newProvider.setDocContentType(attachedFile.getContentType());
  newProvider.setServiceStartDate(new Date());

  try {
   String url = null;

   Provider provider = providerRepository.save(newProvider);

   url = commonService.saveDocumentServer(workCenterId, provider.getId(), attachedFile);

   if(url != null){
    this.providerRepository.updateProviderDocUrl(provider.getId(), url);
   }

  } catch (Exception e) {
   e.printStackTrace();
   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
  return new ResponseEntity<>(HttpStatus.OK);
 }



}
