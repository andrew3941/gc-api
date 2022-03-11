package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {


    List<Maintenance> findMaintenanceByDeletedIsNullOrderByCreatedDesc();
    Maintenance findMaintenanceById(int maintenanceId);
    Maintenance findById(int id);

//    void editMaintenance(Maintenance maintenance);
@Modifying
@Transactional
@Query("update Maintenance m set  m.deleted=CURRENT_TIMESTAMP, m.deletedBy=:deleted_by where m.id=:id")
void maintenanceLogicDeleted(@Param("deleted_by") long deleted_by, @Param("id") int id);

}
