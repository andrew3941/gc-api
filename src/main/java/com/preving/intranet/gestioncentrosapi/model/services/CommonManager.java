package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.drawing.DrawingRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.entities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.Drawing;
import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CommonManager implements CommonService {

    @Autowired
    private ProvincesRepository provincesRepository;

    @Autowired
    private EntitiesRepository entitiesRepository;

    @Autowired
    private DrawingRepository drawingRepository;

    @Value("${url-drawing-documents}")
    private String urlDrawingDocuments;

    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String CONTENT_TYPE_ZIP = "application/x-zip-compressed";
    private static final String CONTENT_TYPE_DOC = "application/msword";
    private static final String CONTENT_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private static final String CONTENT_TYPE_ODT = "application/vnd.oasis.opendocument.text";
    private static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
    private static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String CONTENT_TYPE_ODS = "application/vnd.oasis.opendocument.spreadsheet";
    private static final String CONTENT_TYPE_JPG = "image/jpg";
    private static final String CONTENT_TYPE_JPEG = "image/jpeg";
    private static final String CONTENT_TYPE_PNG = "image/png";
    private static final String CONTENT_TYPE_RAR= "application/x-rar-compressed";


    @Override
    public List<Province> findAllProvinces(){
        return provincesRepository.findAllByOrderByName();

    }

    @Override
    public List<Entity> findAllEntities() {
        return entitiesRepository.findAllByOrderByName();
    }

    private String contentType(String contentType){
        String typeFile=null;
        switch (contentType){
            case CONTENT_TYPE_PDF:
                typeFile=".pdf";
                break;
            case  CONTENT_TYPE_ZIP:
                typeFile=".zip";
                break;
            case  CONTENT_TYPE_DOC:
                typeFile=".doc";
                break;
            case CONTENT_TYPE_DOCX:
                typeFile=".docx";
                break;
            case CONTENT_TYPE_ODT:
                typeFile=".odt";
                break;
            case CONTENT_TYPE_XLS:
                typeFile=".xls";
                break;
            case CONTENT_TYPE_XLSX:
                typeFile=".xlsx";
                break;
            case CONTENT_TYPE_ODS:
                typeFile=".ods";
                break;
            case CONTENT_TYPE_JPG:
                typeFile=".jpg";
                break;
            case CONTENT_TYPE_JPEG:
                typeFile=".jpg";
                break;
            case CONTENT_TYPE_PNG:
                typeFile=".png";
                break;
            case CONTENT_TYPE_RAR:
                typeFile=".rar";
                break;

        }
        return typeFile;

    }


    public String saveDocumentServer(int workCenterId, int drawingId, MultipartFile attachedFile) throws IOException {

        String path= null;
        String url = null;

        path = urlDrawingDocuments + "/" + workCenterId + "/"+ drawingId;
        url = urlDrawingDocuments + "/" + workCenterId + "/" + drawingId +"/" + attachedFile.getOriginalFilename();

        File file = new File(url);
        if(file.exists()){
            file.delete();
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

        } else {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        attachedFile.transferTo(file);
        return url;
    }

    public boolean deleteDocumentServer(int workCenterId, int drawingId) throws IOException {

        // Obtenemos la URL del plano para borrarlo del servidor
        Drawing drawing = drawingRepository.findDrawingById(drawingId);

        File file = new File(drawing.getDocUrl());
        Boolean borrado = null;
        if (file.exists()) {
            file.delete();

            return borrado = true;
        } else {
            borrado = false;
        }
        return borrado;
    }

}

