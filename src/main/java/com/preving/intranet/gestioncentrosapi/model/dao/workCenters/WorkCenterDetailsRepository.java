package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;

public interface WorkCenterDetailsRepository {

    WorkCenterDetails findWorkCenterDetails(int workCenterId);
}
