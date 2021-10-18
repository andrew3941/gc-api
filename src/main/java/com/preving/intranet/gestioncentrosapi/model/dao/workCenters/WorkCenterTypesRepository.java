package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkCenterTypesRepository extends JpaRepository<WorkCenterTypes, Integer> {
    List<WorkCenterTypes> findAll();
}
