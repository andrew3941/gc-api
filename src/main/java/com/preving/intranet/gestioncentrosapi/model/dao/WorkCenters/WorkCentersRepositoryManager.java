package com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters;

import com.preving.intranet.gestioncentrosapi.model.cities.CitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.services.WorkCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class WorkCentersRepositoryManager implements WorkCenterService {

    @Autowired
    private WorkCentersRepository workCentersRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    @Override
    public void addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {
        workCentersRepository.save(newWorkCenter);
    }

    @Override
    public void editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {

    }

    @Override
    public List<City> findCitiesByProvince(int provinceCod, String criterion) {
        return citiesRepository.findCitiesByProvince(provinceCod, criterion);
    }


}
