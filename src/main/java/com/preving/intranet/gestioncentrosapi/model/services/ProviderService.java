package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.*;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetail;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetailConf;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProviderService {

    List<Provider> getProviders(int workCenterId, ProviderFilter providerFilter);

    List<ProviderTypes> getProviderTypes(int workCenterId);

    List<ProviderArea> getProviderArea(int workCenterId);

    List<ProviderEvaluationTypes> getProviderEvaluationTypes(int workCenterId);

    List<ExpenditurePeriod> getExpenditurePeriod(int workCenterId);

    ResponseEntity<?> saveProvider(int workCenterId, Provider newProvider, List<ProviderDetail> specificData,
                                   MultipartFile attachedFile, HttpServletRequest request);

    ResponseEntity<?> editProvider(int workCenterId, int providerId, Provider provider, List<ProviderDetail> details,
                                   MultipartFile attachedFile, HttpServletRequest request);

    Provider getProviderById(int workCenterId, int providerId);

    ResponseEntity<?> downloadProviderDoc(HttpServletRequest request, int workCenterId, int providerId);

    void desactivateProvider();

    void activateProvider();

    List<ProviderDetailConf> getSpecificProviderForm(int providerTypeId);

}
