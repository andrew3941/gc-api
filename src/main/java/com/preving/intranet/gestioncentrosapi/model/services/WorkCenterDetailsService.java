package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;

public interface WorkCenterDetailsService {

    WorkCenterDetails getWorkCenterDetails(int workCenterId);
}
