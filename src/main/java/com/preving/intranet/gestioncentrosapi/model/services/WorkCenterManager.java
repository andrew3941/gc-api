package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class WorkCenterManager implements WorkCenterService{

    @Autowired
    private ProvincesRepository provincesRepository;

    @Autowired
    private WorkCentersRepository workCentersRepository;

    @Override
    public void addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {

    }

    @Override
    public void editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {

    }

    @Override
    public List<City> findCitiesByProvince(int provinceCod, String criterion) {
        return null;
    }
}
