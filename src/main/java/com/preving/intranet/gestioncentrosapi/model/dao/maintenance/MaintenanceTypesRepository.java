package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceTypesRepository extends JpaRepository<MaintenanceTypes, Integer> {
List<MaintenanceTypes> findAll();
}
