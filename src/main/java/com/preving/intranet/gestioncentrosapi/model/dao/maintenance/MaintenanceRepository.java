package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface  MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    List<Maintenance> findMaintenancesByDeletedByIsNullOrderByCreatedDesc();

    Maintenance findMaintenanceById(int maintenanceId);

    @Modifying
    @Transactional
    @Query("update Maintenance ma set  borrado=CURRENT_TIMESTAMP, borrado_por=:deleted_by where id=:maintenanceId")
    void maintenanceLogicDeleted(@Param("deleted_by") int deleted_by, @Param("maintenanceId") int maintenanceId);

    @Modifying
    @Transactional
    @Query("update Maintenance ma set ma.maintenanceTypes=:#{#maintenance.maintenanceTypes}, ma.provider=:#{#maintenance.provider}, " +
            "ma.billNumber=:#{#maintenance.billNumber}, ma.expenditurePeriod=:#{#maintenance.expenditurePeriod}, " +
            "ma.amount=:#{#maintenance.amount}, ma.date=:#{#maintenance.date}, ma.observations=:#{#maintenance.observations}, " +
            "ma.created=:#{#maintenance.created}, " +
            "ma.modified=CURRENT_TIMESTAMP, ma.modifiedBy=:#{#maintenance.modifiedBy} " +
            "where ma.id=:#{#maintenance.id}")
    void editMaintenance(@Param("maintenance") Maintenance maintenance);

}
