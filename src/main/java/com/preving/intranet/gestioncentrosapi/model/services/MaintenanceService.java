package com.preving.intranet.gestioncentrosapi.model.services;


import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;

import java.util.List;

public interface MaintenanceService {
    List<Maintenance>getMaintenance(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);


}
