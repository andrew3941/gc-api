package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkCentersCustomizeRepository {

    List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter);

    int getTotalEmployee(int workCenterId );
}
