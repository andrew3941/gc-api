package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.GeneralDocumentationRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.GeneralDocumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralDocumentationManager implements GeneralDocumentationService {

    @Autowired
    private GeneralDocumentationRepository generalDocumentationRepository;

    @Override
    public List<GeneralDocumentation>  getGeneralDocumentation(int workCenterId) {

//        List<GeneralDocumentation> generalDocumentations = this.generalDocumentationRepository.getGeneralDocumentation(workCenterId);

//        return generalDocumentations;
         return null;
    }


}
