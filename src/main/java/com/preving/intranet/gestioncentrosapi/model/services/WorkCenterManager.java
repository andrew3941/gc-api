package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.users.UserCustomRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.users.UserRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersCustomizeRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.cities.CitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.entities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.zona.ZonaRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Transactional
    public ResponseEntity<?> addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {

        // Obtenemos el usuario creador mediante el token
        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        // Seteamos los valores necesarios para hacer el insert
        // TODO verificar si el alta viene con fecha de baja
        newWorkCenter.setActive(1);
        newWorkCenter.setCreated(new Date());
        newWorkCenter.getCreatedBy().setId(userId);

        // Insertamos delegación en GC2006_RELEASE.PC_DELEGACIONES
        workCentersRepository.save(newWorkCenter);

        // Construimos el objeto zona
        Zona zona = seteamosZona(newWorkCenter);

        // Insertamos delegación en MP2.ZONA
        zonaRepository.save(zona);


        // TODO verificar dim_navision



        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Zona seteamosZona(WorkCenter newWorkCenter) {

        Zona zona = new Zona();

        zona.setCodZona(newWorkCenter.getId());
        zona.setDenomination(newWorkCenter.getName());
        zona.setName(newWorkCenter.getName());
        zona.setTelephone(newWorkCenter.getPhoneNumber());
        zona.setEmail(newWorkCenter.getEmail());
        zona.setAddress(newWorkCenter.getAddress());
        zona.setCodPostal(newWorkCenter.getPostalCode());
        zona.setPoblacion(newWorkCenter.getCity().getName());

        return zona;
    }


    @Transactional
    public ResponseEntity<?> editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {
        workCentersRepository.editWorkCenter(workCenterId, newWorkCenter, this.jwtTokenUtil.getUserWithRolesFromToken(request).getId());

        return new ResponseEntity<>(HttpStatus.OK);
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

