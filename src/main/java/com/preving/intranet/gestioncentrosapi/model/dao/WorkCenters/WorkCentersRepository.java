package com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkCentersRepository extends JpaRepository<WorkCenter, Integer> {

}
