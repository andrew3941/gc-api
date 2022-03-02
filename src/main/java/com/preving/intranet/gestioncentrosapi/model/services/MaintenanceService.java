package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;

import java.util.List;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;


import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface MaintenanceService {
    ResponseEntity<?> saveMaintenance(int workCenterId, Maintenance newMaintenance, MultipartFile[] attachedFile, HttpServletRequest request);
    //getting a specific record by using the method getMaintenanceById() of maintenanceRepository
    Maintenance getMaintenanceById(int maintenanceId);



    ResponseEntity<?>  editMaintenance(int workCenterId, Maintenance maintenance, MultipartFile[] attachedFile, HttpServletRequest request);

    List<Maintenance>getMaintenance(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);
    ResponseEntity<?> exportMaintenance(MaintenanceFilter maintenanceFilter, HttpServletResponse response,UsuarioWithRoles user);
    ResponseEntity<?> deleteMaintenance(HttpServletRequest request,int workCenterId, int maintenanceId);
      ResponseEntity<?> downloadMaintenanceDoc(HttpServletRequest request, int generalMaintenanceId);

//METHOD FROM THE WORK CENTERS CONTROLLER
    List<Maintenance> findAllMaintenance();

    List<Maintenance> getAllMaintenance();

// edit/update maintenance
    void saveOrUpdate(Maintenance maintenance);
    //Logic to Save New Maintenance
    ResponseEntity<?> saveNewMaintenance(int maintenanceId, Maintenance newMaintenance, MultipartFile[] attachedFile, HttpServletRequest request);
}
