package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.generalDocument.*;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.*;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
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
    public ResponseEntity<?> saveGeneralDocumentation(int workCenterId, GeneralDocumentation newGeneralDoc, MultipartFile[] attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newGeneralDoc.setCreated(new Date());
        newGeneralDoc.getCreatedBy().setId(userId);
        newGeneralDoc.getWorkCenter().setId(workCenterId);

        try{

            GeneralDocumentation savedGeneralDoc = this.generalDocumentationRepository.save(newGeneralDoc);

            if (attachedFile.length > 0) {

                for (MultipartFile mpFile : attachedFile) {

                    GeneralDocByAttachment generalDocByAttachment = new GeneralDocByAttachment();

                    generalDocByAttachment.setGeneralDoc(savedGeneralDoc);
                    generalDocByAttachment.setAttachedUrl("Doc_Url");
                    generalDocByAttachment.setAttachedName(mpFile.getOriginalFilename());
                    generalDocByAttachment.setAttachedContentType(mpFile.getContentType());

                    this.generalDocByAttachmentRepository.save(generalDocByAttachment);

                    String url = null;

                    // Guardamos documento en el server
                    url = commonService.saveDocumentServer(workCenterId, savedGeneralDoc.getId(), mpFile, GENERAL_DOCUMENTS);

                    // Actualizamos la ruta del documento guardado
                    if (url != null) {
                        this.generalDocByAttachmentRepository.updateGeneralDocByAttachmentUrl(generalDocByAttachment.getId(), url);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editGeneralDoc(int workCenterId, GeneralDocumentation generalDoc, MultipartFile[] attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        generalDoc.setModifiedBy(new User());
        generalDoc.getModifiedBy().setId(userId);
        generalDoc.getWorkCenter().setId(workCenterId);

        try {

            generalDocumentationRepository.editGeneralDoc(generalDoc);

            for (GeneralDocByAttachment gda : generalDoc.getGeneralDocByAttachments()){
                // Borramos el documento anterior del servidor
                commonService.deleteDocumentServer(workCenterId, gda.getId(), GENERAL_DOCUMENTS);

                generalDocByAttachmentRepository.deleteById(gda.getId());
            }

            if (attachedFile.length > 0) {

                for (MultipartFile mpFile : attachedFile){

                    GeneralDocByAttachment newGeneralDocByAttach = generalDocByAttachmentRepository.findByGeneralDocId(generalDoc.getId());

                    newGeneralDocByAttach.setGeneralDoc(generalDoc);
                    newGeneralDocByAttach.setAttachedUrl("Doc_Url");
                    newGeneralDocByAttach.setAttachedName(mpFile.getOriginalFilename());
                    newGeneralDocByAttach.setAttachedContentType(mpFile.getContentType());

                    GeneralDocByAttachment generalDocByAttach = this.generalDocByAttachmentRepository.save(newGeneralDocByAttach);

                    String url = null;
                    // Guardamos documento en el server
                    url = commonService.saveDocumentServer(workCenterId, generalDoc.getId(), mpFile, GENERAL_DOCUMENTS);

                    // Actualizamos la ruta del documento guardado
                    if (url != null) {
                        this.generalDocByAttachmentRepository.updateGeneralDocByAttachmentUrl(generalDocByAttach.getId(), url);
                    }

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

        GeneralDocumentation generalDoc = generalDocumentationRepository.findGeneralDocumentationById(generalDocId);

        List<GeneralDocByAttachment>  gAttachDocuments = generalDocByAttachmentRepository.findAllByGeneralDoc(generalDoc);

        generalDoc.setGeneralDocByAttachments(gAttachDocuments);

        return generalDoc;
    }

    @Override
    public ResponseEntity<?> downloadGeneralDoc(HttpServletRequest request, int generalDocAttachId) {

        GeneralDocByAttachment gda = null;

        File file = null;
        byte[] content = null;

        try {
            
            gda = this.generalDocByAttachmentRepository.findByGeneralDoc_Id(generalDocAttachId);

            file = new File(gda.getAttachedUrl());
            if (file.exists()) {
                content = Files.readAllBytes(file.toPath());
            }else{
                return new ResponseEntity<>("File not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }


}
