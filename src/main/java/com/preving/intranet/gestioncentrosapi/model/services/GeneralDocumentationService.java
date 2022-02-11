package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GeneralDocumentationService {

    List<GeneralDocumentation> getGeneralDocumentationListByWorkCenter(int workCenterId);

    List<GeneralDocumentationTypes> getGeneralDocTypes();

    List<CertificateTypes> getCertificateTypes();

    List<TaxesTypes> getTaxesTypes();

    ResponseEntity<?> saveGeneralDocumentation(int workCenterId, GeneralDocumentation newGeneralDoc, MultipartFile[] attachedFile, HttpServletRequest request);

    ResponseEntity<?> editGeneralDoc(int workCenterId,  GeneralDocumentation generalDoc, MultipartFile[] attachedFile, HttpServletRequest request);

    GeneralDocumentation getGeneralDocById(int generalDocId);

    ResponseEntity<?> downloadGeneralDoc(HttpServletRequest request, int generalDocAttachId);

}
