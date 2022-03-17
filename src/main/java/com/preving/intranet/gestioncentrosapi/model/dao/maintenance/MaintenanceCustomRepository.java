package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MaintenanceCustomRepository {

    List<Maintenance> getMaintenanceFiltered(Integer workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);

    String findDocUrlByMaintenanceType(int maintenanceType, int workCenterId);

    boolean checkProviderCIf(String providerCif);

   String findDocUrlByMaintenanceId(int maintenanceId, int workCenterId);
}
