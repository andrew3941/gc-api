package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.users.UserRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersCustomizeRepository;
import com.preving.intranet.gestioncentrosapi.model.cities.CitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.entities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.security.JwtTokenUtil;
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

   @Autowired
   private WorkCentersCustomizeRepository workCentersCustomizeRepository;

    @Autowired
    EntitiesRepository entitiesRepository;

    @Autowired
    CitiesRepository citiesRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {
//        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();#

        workCentersRepository.save(newWorkCenter);
    }

    @Override
    public void editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {

    }

    @Override
    public List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter) {
        return this.workCentersCustomizeRepository.getWorkCenters(workCenterFilter);
    }

    @Override
    public List<Province> findAllProvinces() {
        return provincesRepository.findAllByOrderByName();
    }

    @Override
    public List<Entities> findAll() {
        return entitiesRepository.findAll() ;
    }

    @Override
    public List<City> findCitiesByProvince(int provinceCod, String criterion) {
        return citiesRepository.findCitiesByProvince(provinceCod, criterion);
    }

    @Override
    public List<User> getUsers(String criterion) {
      return  null;
    }
}
