package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface MaintenanceCustomRepository {

    List<Maintenance>getMaintenanceFiltered(Integer workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);

    String findDocUrlByMaintenanceType(int maintenanceType, int workCenterId);

    boolean checkProviderCIf(String providerCif);

   String findDocUrlByMaintenanceId(int maintenanceId, int workCenterId);
}
