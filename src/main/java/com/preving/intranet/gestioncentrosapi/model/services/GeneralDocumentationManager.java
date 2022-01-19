package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.CertificateTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.GeneralDocTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.GeneralDocumentationRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.TaxesTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.CertificateTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentationTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;
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
public class GeneralDocumentationManager implements GeneralDocumentationService {

    @Autowired
    private GeneralDocumentationRepository generalDocumentationRepository;

    @Autowired
    GeneralDocTypesRepository generalDocTypesRepository;

    @Autowired
    private CertificateTypesRepository certificateTypesRepository;

    @Autowired
    private TaxesTypesRepository taxesTypesRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public List<GeneralDocumentation> getGeneralDocumentationListByWorkCenter(int workCenterId) {
        return generalDocumentationRepository.findGeneralDocumentationsByWorkCenterIdAndDeletedIsNullOrderByCreatedDesc(workCenterId);
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

    @Override
    public ResponseEntity<?> saveGeneralDocumentation(int workCenterId, GeneralDocumentation newGeneralDoc, MultipartFile attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newGeneralDoc.setCreated(new Date());
        newGeneralDoc.getCreatedBy().setId(userId);
        newGeneralDoc.getWorkCenter().setId(workCenterId);

        try{

           generalDocumentationRepository.save(newGeneralDoc);

        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
