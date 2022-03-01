package com.preving.intranet.gestioncentrosapi.model.services;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class MaintenanceManager implements MaintenanceService {
    private MaintenanceManager maintenanceCustomRepository;

    private final MaintenanceRepository maintenanceRepository;

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

