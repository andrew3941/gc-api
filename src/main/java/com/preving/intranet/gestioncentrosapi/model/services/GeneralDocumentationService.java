package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.GeneralDocumentation;

import java.util.List;

public interface GeneralDocumentationService {

    List<GeneralDocumentation> getGeneralDocumentation(int workCenterId);

}
