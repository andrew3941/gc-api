package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.CertificateTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentationTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GeneralDocumentationService {

    List<GeneralDocumentation> getGeneralDocumentationListByWorkCenter(int workCenterId);

    List<GeneralDocumentationTypes> getGeneralDocTypes();

    List<CertificateTypes> getCertificateTypes();

    List<TaxesTypes> getTaxesTypes();

    ResponseEntity<?> saveGeneralDocumentation(int workCenterId, GeneralDocumentation newGeneralDoc, MultipartFile attachedFile, HttpServletRequest request);


}
