package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkCenterDetailsRepository extends JpaRepository<WorkCenterDetails, Integer> {

    WorkCenterDetails findByWorkCenter(WorkCenter workCenter);

}
