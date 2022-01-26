package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.*;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.*;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
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
    private GeneralDocByAttachmentRepository generalDocByAttachmentRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final int GENERAL_DOCUMENTS = 3;

    @Override
    public List<GeneralDocumentation> getGeneralDocumentationListByWorkCenter(int workCenterId) {

//        List<GeneralDocumentation> generalDocumentations = generalDocumentationRepository.findGeneralDocumentationsByWorkCenterIdAndDeletedIsNullOrderByCreatedDesc(workCenterId);
//        List<GeneralDocumentation> generalD = new ArrayList<GeneralDocumentation>();
//        for (GeneralDocumentation gDoc: generalDocumentations){
//
//            GeneralDocByAttachment gDA = this.generalDocByAttachmentRepository.findByGeneralDocId(gDoc.getId());
//            // gDoc.setGeneralDocByAttachment(gDA);
//            gDoc.getGeneralDocByAttachment().setId(gDA.getId());
//            gDoc.getGeneralDocByAttachment().setAttachedName(gDA.getAttachedName());
//            gDoc.getGeneralDocByAttachment().setAttachedUrl(gDA.getAttachedUrl());
//            gDoc.getGeneralDocByAttachment().setAttachedContentType(gDA.getAttachedContentType());
//
//
//            generalD.add(gDoc);
//        }
//   return generalD;

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

        //TODO remove this code after getting the real data from the front
        //=========================================================
        // Temporal value if Certificate Types is null
        CertificateTypes certType = new CertificateTypes();
        certType.setId(1);
        // Temporal value if TaxesTypes is null
        TaxesTypes taxes = new TaxesTypes();
        taxes.setId(2);
        //=========================================================

        newGeneralDoc.setCreated(new Date());
        newGeneralDoc.getCreatedBy().setId(userId);
        newGeneralDoc.getWorkCenter().setId(workCenterId);

        //TODO accepting real data coming from the front
        newGeneralDoc.setCertificateTypes(certType);
        newGeneralDoc.setTaxesTypes(taxes);

        try{

          GeneralDocumentation savedGeneralDoc = this.generalDocumentationRepository.save(newGeneralDoc);


            if (attachedFile != null) {

                GeneralDocByAttachment generalDocByAttachment = new GeneralDocByAttachment();

                generalDocByAttachment.setGeneralDoc(savedGeneralDoc);
                generalDocByAttachment.setAttachedUrl("Doc_Url");
                generalDocByAttachment.setAttachedName(attachedFile.getOriginalFilename());
                generalDocByAttachment.setAttachedContentType(attachedFile.getContentType());

                 this.generalDocByAttachmentRepository.save(generalDocByAttachment);

                String url = null;

                // Guardamos documento en el server
                url = commonService.saveDocumentServer(workCenterId, savedGeneralDoc.getId(), attachedFile, GENERAL_DOCUMENTS);

                // Actualizamos la ruta del documento guardado
                if (url != null) {
                    this.generalDocByAttachmentRepository.updateGeneralDocByAttachmentUrl(generalDocByAttachment.getId(), url);
                }
            }


        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editGeneralDoc(int workCenterId, GeneralDocumentation generalDoc, MultipartFile attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        //TODO remove this code after getting the real data from the front
        //=========================================================
        // Temporal value if Certificate Types is null
        CertificateTypes certType = new CertificateTypes();
        certType.setId(1);
        // Temporal value if TaxesTypes is null
        TaxesTypes taxes = new TaxesTypes();
        taxes.setId(2);
        //=========================================================


        generalDoc.setModifiedBy(new User());
        generalDoc.getModifiedBy().setId(userId);
        generalDoc.getWorkCenter().setId(workCenterId);

        //TODO accepting real data coming from the front
        generalDoc.setCertificateTypes(certType);
        generalDoc.setTaxesTypes(taxes);

        try {
           generalDocumentationRepository.editGeneralDoc(generalDoc);

            if (attachedFile != null) {

                GeneralDocByAttachment newGeneralDocByAttach = generalDocByAttachmentRepository.findByGeneralDocId(generalDoc.getId());

                newGeneralDocByAttach.setGeneralDoc(generalDoc);
                newGeneralDocByAttach.setAttachedUrl("Doc_Url");
                newGeneralDocByAttach.setAttachedName(attachedFile.getOriginalFilename());
                newGeneralDocByAttach.setAttachedContentType(attachedFile.getContentType());

                // Borramos el documento anterior del servidor
                commonService.deleteDocumentServer(workCenterId, generalDoc.getId(), GENERAL_DOCUMENTS);

                GeneralDocByAttachment generalDocByAttach = this.generalDocByAttachmentRepository.save(newGeneralDocByAttach);

                String url = null;
                // Guardamos documento en el server
                url = commonService.saveDocumentServer(workCenterId, generalDoc.getId(), attachedFile, GENERAL_DOCUMENTS);

                // Actualizamos la ruta del documento guardado
                if (url != null) {
                    this.generalDocByAttachmentRepository.updateGeneralDocByAttachmentUrl(generalDocByAttach.getId(), url);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public GeneralDocumentation getGeneralDocById(int generalDocId) {
        return generalDocumentationRepository.findGeneralDocumentationById(generalDocId);
    }

    @Override
    public ResponseEntity<?> downloadGeneralDoc(HttpServletRequest request, int generalDocAttachId) {

        GeneralDocByAttachment gda = null;

        File file = null;
        byte[] content=null;

        try {

            //TODO this method neet to be change
            gda = this.generalDocByAttachmentRepository.findByGeneralDocId(generalDocAttachId);

            file = new File(gda.getAttachedUrl());
            if (file.exists()) {
                content = Files.readAllBytes(file.toPath());
            }else{
                return new ResponseEntity<>("File not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Uknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }


}
