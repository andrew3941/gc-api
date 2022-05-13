package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WorkCenterDetailsRepository extends JpaRepository<WorkCenterDetails, Integer> {

    WorkCenterDetails findWorkCenterDetailsByWorkCenter(WorkCenter workCenter);

    WorkCenterDetails findWorkCenterDetailsByWorkCenterId(int workCenter);

    @Modifying
    @Transactional
    @Query("UPDATE WorkCenterDetails wcd SET wcd.totalArea=:#{#wcd.totalArea}, wcd.jobAvailable=:#{#wcd.jobAvailable}, " +
            "wcd.accesibility=:#{#wcd.accesibility}, wcd.parking=:#{#wcd.parking}, wcd.parkingPlace=:#{#wcd.parkingPlace}, " +
            "wcd.description=:#{#wcd.description}, wcd.communityAmount=:#{#wcd.communityAmount}, wcd.stealingAlarm=:#{#wcd.stealingAlarm}," +
            "wcd.fireAlarm=:#{#wcd.fireAlarm}, wcd.umParkingPlaces=:#{#wcd.umParkingPlaces}, " +
            "wcd.administrator=:#{#wcd.administrator}, wcd.email=:#{#wcd.email}, wcd.phone=:#{#wcd.phone}," +
            " wcd.allDepartment=:#{#wcd.allDepartment}, wcd.cadastralRef=:#{#wcd.cadastralRef}, " +
            "wcd.modified = CURRENT_TIMESTAMP, wcd.modifiedBy=:#{#wcd.modifiedBy} WHERE wcd.id=:#{#wcd.id}")
    void updateWorkCenterDetails(@Param("wcd") WorkCenterDetails wcd);

}
