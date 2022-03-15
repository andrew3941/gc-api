package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocByAttachment;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface  MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    List<Maintenance> findMaintenancesByDeletedByIsNullOrderByCreatedDesc();
    Maintenance findMaintenanceById(int maintenanceId);
    //   maintenance to download
    Maintenance findById(int id);

    @Modifying
    @Transactional
    @Query("update Maintenance ma set  ma.deleted=CURRENT_TIMESTAMP, ma.deletedBy=:deleted_by where ma.id=:maintenanceId ")
    void maintenanceLogicDeleted(@Param("deleted_by") long deleted_by, @Param("maintenanceId") int maintenanceId);

}
