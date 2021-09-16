package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vendor.*;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersCustomizeRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.*;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
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
    WorkCentersCustomizeRepository workCentersCustomizeRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final int PROVIDER_DOCUMENTS = 2;

    @Override
    public List<Provider> getProviders(int workCenterId, ProviderFilter providerFilter) {
        List<Provider> providers = this.providerCustomRepository.getProviders(workCenterId, providerFilter);  ;
        return providers;
    }

    @Override
    public List<ProviderTypes> getProviderTypes(int workCenterId) {
        return providerTypesRepository.findAllByOrderByOrder();
    }

    @Override
    public List<ProviderArea> getProviderArea(int workCenterId) {
        return providerAreaRepository.findAllByOrderByOrder();
    }

    @Override
    public List<ProviderEvaluationTypes> getProviderEvaluationTypes(int workCenterId) {
        return providerEvaluationTypesRepository.findAll();
    }

    @Override
    public List<ExpenditurePeriod> getExpenditurePeriod(int workCenterId) {
        return expenditurePeriodRepository.findAll();
    }

    @Override
    public ResponseEntity<?> saveProvider(int workCenterId, Provider newProvider, MultipartFile attachedFile, HttpServletRequest request){

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newProvider.getWorkCenter().setId(workCenterId);
        newProvider.setCreated(new Date());
        newProvider.getCreatedBy().setId(userId);

        if (attachedFile != null) {
            newProvider.setDocUrl("doc_url");
            newProvider.setDocName(attachedFile.getOriginalFilename());
            newProvider.setDocContentType(attachedFile.getContentType());
        }

        try {
            if (attachedFile != null) {
                String url = null;

                // Guardamos proveedor
                Provider provider = providerRepository.save(newProvider);

                // Guardamos el nuevo documento adjunto
                url = commonService.saveDocumentServer(workCenterId, provider.getId(), attachedFile, PROVIDER_DOCUMENTS);

                // Actualizamos la URL del documento
                if(url != null){
                    this.providerRepository.updateProviderDocUrl(provider.getId(), url);
                }
            }

    } catch (Exception e) {

        e.printStackTrace();

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
        return new ResponseEntity<>(HttpStatus.OK);
}

    @Override
    public Provider getProviderById(int workCenterId, int providerId) {

        return this.providerRepository.findProviderByWorkCenterIdAndId(workCenterId, providerId);

    }

    @Override
    public ResponseEntity<?> editProvider(int workCenterId, int providerId, Provider provider, MultipartFile attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        provider.setModifiedBy(new User());
        provider.getModifiedBy().setId(userId);

        if (attachedFile != null) {
            provider.setDocUrl("doc_url");
            provider.setDocName(attachedFile.getOriginalFilename());
            provider.setDocContentType(attachedFile.getContentType());
        }

        // Editamos el proveedor
        providerRepository.editProvider(provider);

        try {
            if (attachedFile != null) {
                // Borramos el documento anterior del servidor
                commonService.deleteDocumentServer(workCenterId, provider.getId(), PROVIDER_DOCUMENTS);

                String url = null;

                url = commonService.saveDocumentServer(workCenterId, provider.getId(), attachedFile, PROVIDER_DOCUMENTS);

                if(url != null){
                    this.providerRepository.updateProviderDocUrl(provider.getId(), url);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
