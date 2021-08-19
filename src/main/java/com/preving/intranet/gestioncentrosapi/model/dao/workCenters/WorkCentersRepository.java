package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface WorkCentersRepository extends JpaRepository<WorkCenter, Integer> {

    WorkCenter findWorkCenterById(int workCenterId);

    @Modifying
    @Transactional
    @Query("update WorkCenter w set w.name=:#{#newWorkCenter.name}, " +
            "w.city=:#{#newWorkCenter.city}, " +
            "w.navisionCode=:#{#newWorkCenter.navisionCode}, " +
            "w.address=:#{#newWorkCenter.address}, " +
            "w.postalCode=:#{#newWorkCenter.postalCode}, " +
            "w.phoneNumber=:#{#newWorkCenter.phoneNumber}, " +
            "w.email=:#{#newWorkCenter.email}, " +
            "w.headPerson=:#{#newWorkCenter.headPerson}, " +
            "w.startDate=:#{#newWorkCenter.startDate}, " +
            "w.endDate=:#{#newWorkCenter.endDate}, " +
            "w.modified= CURRENT_TIMESTAMP, modificado_por=:editBy " +
            "where w.id=:workCenterId ")
    void  editWorkCenter(@Param("workCenterId") int workCenterId, @Param("newWorkCenter") WorkCenter newWorkCenter, @Param("editBy") int editBy);

}
