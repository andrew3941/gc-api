package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.CertificateTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentationTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;

import java.util.List;

public interface GeneralDocumentationService {

    List<GeneralDocumentation> getGeneralDocumentation(int workCenterId);
    List<GeneralDocumentationTypes> getGeneralDocTypes();
    List<CertificateTypes> getCertificateTypes();
    List<TaxesTypes> getTaxesTypes();


}
