package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCenterDetailsRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkCenterDetailsManager implements WorkCenterDetailsService {

    @Autowired
    private WorkCenterDetailsRepository workCenterDetailsRepository;

    @Override
    public WorkCenterDetails getWorkCenterDetails(int workCenterId) {

        WorkCenterDetails workCenterDetails = this.workCenterDetailsRepository.findWorkCenterDetails(workCenterId);

        return workCenterDetails;
    }
}
