package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaintenanceCustomRepository {
    List<Maintenance> getMaintenance(Integer workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user);

    String findDocUrlByMaintenanceType(int maintenanceType, int workCenterId);

    boolean checkProviderCIf(String providerCif);
   String findDocUrlByMaintenanceId(int maintenanceId, int workCenterId);
}
