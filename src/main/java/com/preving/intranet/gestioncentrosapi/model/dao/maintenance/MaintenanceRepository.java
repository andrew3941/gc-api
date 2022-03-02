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


    //   maintenance to download

    Maintenance findById(int id);

//    void editMaintenance(Maintenance maintenance);
    @Modifying
    @Transactional
    @Query("update Maintenance d set  borrado=CURRENT_TIMESTAMP, borrado_por=:deleted_by where id=:maintenance_id and delegacion_id=:workCenterId")
    void maintenanceLogicDelete(@Param("deleted_by") int deleted_by, @Param("maintenance_id") int maintenance_id, @Param("workCenterId") int workCenterId);
   // void deleteByWorkCenter(WorkCenter workCenter);
}
