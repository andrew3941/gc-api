package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
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

    // getting a specific record by Id
    Maintenance getMaintenanceById(int maintenanceId);

    ResponseEntity<?>  editMaintenance(int workCenterId, Maintenance maintenance, MultipartFile[] attachedFile, HttpServletRequest request);

    List<Maintenance> getFilteredMaintenances(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);


    //    EXPORT
    ResponseEntity<?> exportMaintenance(MaintenanceFilter maintenanceFilter, HttpServletResponse response, UsuarioWithRoles user);

    //Get all Maintenance
    List<Maintenance> findAllMaintenance();

    // Save New Maintenance
    ResponseEntity<?> saveNewMaintenance(int workCenterId, Maintenance newMaintenance, MultipartFile[] attachedFile, HttpServletRequest request);

    List<MaintenanceTypes> getAllMaintenanceTypes();

    // method for delete maintenance
    ResponseEntity<?> deleteMaintenance(HttpServletRequest request,int workCenterId, int maintenanceId);


}
