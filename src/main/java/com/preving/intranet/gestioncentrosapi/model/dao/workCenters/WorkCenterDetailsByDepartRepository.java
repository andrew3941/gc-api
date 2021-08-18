package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetailsByDepart;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCenterDetailsByDepartRepository extends JpaRepository<WorkCenterDetailsByDepart, Integer> {

    void deleteByWorkCenterDetails(WorkCenterDetails workCenterDetails);

    void deleteByWorkCenterDetailsId(int workCenterDetailsId);

}
