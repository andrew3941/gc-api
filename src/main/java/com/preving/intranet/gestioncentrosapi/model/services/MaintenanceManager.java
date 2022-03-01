package com.preving.intranet.gestioncentrosapi.model.services;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceByAttachmentRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;


import java.util.List;




@Service
public class MaintenanceManager implements MaintenanceService {
    private MaintenanceManager maintenanceCustomRepository;

    // autowired the maintenance repo
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MaintenanceByAttachmentRepository maintenanceByAttachmentRepository;

    @Override
    public ResponseEntity<?> deleteMaintenance(HttpServletRequest request, int workCenterId, int maintenanceId) {
        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();
        Maintenance maintenance = this.maintenanceRepository.findMaintenanceById(maintenanceId);
        if (maintenance==null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        try {
            this.maintenanceRepository.maintenanceLogicDelete((int) uId,maintenance.getId(), workCenterId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> downloadMaintenanceDoc(HttpServletRequest request, int generalMaintenanceId) {
        MaintenanceByAttachement mba = null;

        File file = null;
        byte[] content = null;

//        try {
//
//            mba = this.maintenanceByAttachmentRepository.findByGeneralMainId(generalMaintenanceId);
//
//            file = new File(mba.getDocumentUrl());
//            if (file.exists()) {
//                content = Files.readAllBytes(file.toPath());
//            }else{
//                return new ResponseEntity<>("File not found",HttpStatus.NOT_FOUND);
//            }
//        }catch (Exception ex) {
//            ex.printStackTrace();
//            return new ResponseEntity<>("Uknown error",HttpStatus.INTERNAL_SERVER_ERROR);
//        }

      return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }


//    @Autowired
//    private MaintenanceRepository maintenanceRepository;
//
//
//
//    @Override
//    public List<Maintenance> findAllMaintenance() {
//        return maintenanceRepository.findAllMaintenance();
//    }


    @Autowired
    public MaintenanceManager(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> findAllMaintenance(){
        return maintenanceRepository.findAll();
    }



    @Override
    public List<Maintenance> getMaintenance(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user) {
        return maintenanceCustomRepository.getMaintenance(workCenterId, maintenanceFilter, user);
    }

}

