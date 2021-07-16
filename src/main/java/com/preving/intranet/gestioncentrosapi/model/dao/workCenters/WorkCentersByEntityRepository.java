package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCentersByEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface WorkCentersByEntityRepository extends JpaRepository<WorkCentersByEntity, Integer> {

    List<WorkCentersByEntity> findByWorkCenter(WorkCenter workCenter);

    void deleteByWorkCenter(WorkCenter workCenter);

}
