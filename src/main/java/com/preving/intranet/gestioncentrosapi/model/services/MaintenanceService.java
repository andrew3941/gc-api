package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;

import java.util.List;



import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;



public interface MaintenanceService {
    List<Maintenance>getMaintenance(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);

//METHOD FROM THE WORK CENTERS CONTROLLER
    List<Maintenance> findAllMaintenance();


}
