package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.GeneralDocTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.GeneralDocumentationRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentationTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralDocumentationManager implements GeneralDocumentationService {

    @Autowired
    private GeneralDocumentationRepository generalDocumentationRepository;

    @Autowired
    GeneralDocTypesRepository generalDocTypesRepository;


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


}
