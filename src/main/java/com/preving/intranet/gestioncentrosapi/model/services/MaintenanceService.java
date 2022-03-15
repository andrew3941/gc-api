package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;


import javax.servlet.http.HttpServletResponse;

@Service
public interface MaintenanceService {

    //getting a specific record by using the method getMaintenanceById() of maintenanceRepository
    Maintenance getMaintenanceById(HttpServletRequest request, int maintenanceId);


    List<MaintenanceTypes> getMaintenanceType(int workCenterId);

    ResponseEntity<?>  editMaintenance(int workCenterId, Maintenance maintenance, MultipartFile[] attachedFile, HttpServletRequest request);

    List<Maintenance>getMaintenance(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);

    ResponseEntity<?> exportMaintenance(MaintenanceFilter maintenanceFilter, HttpServletResponse response, UsuarioWithRoles user);

    ResponseEntity<?> deleteMaintenanced(HttpServletRequest request, int maintenanceId);

    ResponseEntity<?> downloadMaintenanceDoc(HttpServletRequest request, int generalMaintenanceId);

    //METHOD FROM THE WORK CENTERS CONTROLLER
    List<Maintenance> findAllMaintenance();

//    List<Maintenance> getAllMaintenance();

    // edit/update maintenance
    void saveOrUpdate(Maintenance maintenance);


    //Logic to Save New Maintenance
    ResponseEntity<?> saveNewMaintenance(int workCenterId, Maintenance newMaintenance, MultipartFile[] attachedFile, HttpServletRequest request);

    List<MaintenanceTypes> getAllMaintenanceTypes();
    // method for maintenanceTypes
    List<MaintenanceTypes> getMaintenanceTypes();

}
