package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.CertificateTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.GeneralDocTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.GeneralDocumentationRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.TaxesTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.CertificateTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentationTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralDocumentationManager implements GeneralDocumentationService {

    @Autowired
    private GeneralDocumentationRepository generalDocumentationRepository;

    @Autowired
    GeneralDocTypesRepository generalDocTypesRepository;

    @Autowired
    private CertificateTypesRepository certificateTypesRepository;

    @Autowired
    private TaxesTypesRepository taxesTypesRepository;



    @Override
    public List<GeneralDocumentation>  getGeneralDocumentation(int workCenterId) {

//        List<GeneralDocumentation> generalDocumentations = this.generalDocumentationRepository.getGeneralDocumentation(workCenterId);

//        return generalDocumentations;
         return null;
    }


    @Override
    public List<GeneralDocumentationTypes> getGeneralDocTypes() {
        return generalDocTypesRepository.findAll();
    }

    @Override
    public List<CertificateTypes> getCertificateTypes() {
        return certificateTypesRepository.findAll();
    }

    @Override
    public List<TaxesTypes> getTaxesTypes() {
        return taxesTypesRepository.findAll();
    }


}
