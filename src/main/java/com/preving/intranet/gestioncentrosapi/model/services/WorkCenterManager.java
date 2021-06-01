package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.users.UserCustomRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.users.UserRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersCustomizeRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.cities.CitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.entities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Override
    public void addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {
        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        // Seteamos los valores necesarios para hacer el insert
        // TODO verificar si el alta viene con fecha de baja
        newWorkCenter.setActive(1);
        newWorkCenter.setCreated(new Date());
        newWorkCenter.getCreatedBy().setId(userId);

        // Insertamos delegación
        workCentersRepository.save(newWorkCenter);
    }


    @Override
    public void editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {
        workCentersRepository.editWorkCenter(workCenterId, newWorkCenter, this.jwtTokenUtil.getUserWithRolesFromToken(request).getId());
    }


    @Override
    public List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter) {
        return this.workCentersCustomizeRepository.getWorkCenters(workCenterFilter);
    }

    @Override
    public WorkCenter getWorkCenterById(int workId) {
        return this.workCentersRepository.findWorkCenterById(workId);
    }

    @Override
    public List<City> findCitiesByProvince(String provinceCod, String criterion) {
        return citiesRepository.findCitiesByProvince(provinceCod, criterion);
    }

    @Override
    public List<User> findUsersByCriterion(String criterion) {
        return userCustomRepository.findUserByCriterion(criterion);
    }


}

