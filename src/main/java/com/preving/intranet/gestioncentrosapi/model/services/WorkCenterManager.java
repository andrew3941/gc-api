package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.department.DepartmentRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.dimNavision.DimNavisionRepository;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Autowired
    private DimNavisionRepository dimNavisionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public ResponseEntity<?> addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request) {

        // Obtenemos el usuario creador mediante el token
        long userId =  this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        // Construimos el objeto zona
        Zona zona = seteamosZona(newWorkCenter);

        // Insertamos delegaci�n en MP2.ZONA
        zonaRepository.save(zona);

        // Construimos el objeto dimNavision
        DimNavision dimNavision = seteamosDimNavision(newWorkCenter);

        // Insertamos delegaci�n en RRHH.TM_DIM_NAVISION
        dimNavisionRepository.save(dimNavision);

        // Seteamos los valores necesarios para hacer el insert
        // TODO verificar con fecha de baja
        newWorkCenter.setActive(1);
        newWorkCenter.setVisible(1);
        // Seteamos valores de creaci�n
        newWorkCenter.setCreated(new Date());
        newWorkCenter.getCreatedBy().setId(userId);
        // Seteamos las ids de las tablas secundarias
        newWorkCenter.setIdInMp2(zona.getCodZona());
        newWorkCenter.setLineId(dimNavision.getId());

        // Insertamos delegaci�n en GC2006_RELEASE.PC_DELEGACIONES
        workCentersRepository.save(newWorkCenter);


        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Zona seteamosZona(WorkCenter newWorkCenter) {

        Zona zona = new Zona();

        zona.setCodZona(newWorkCenter.getIdInMp2());
        zona.setDenomination(newWorkCenter.getName());
        zona.setName(newWorkCenter.getName());
        zona.setTelephone(newWorkCenter.getPhoneNumber());
        zona.setEmail(newWorkCenter.getEmail());
        zona.setAddress(newWorkCenter.getAddress());
        zona.setCodPostal(newWorkCenter.getPostalCode());
        zona.setPoblacion(newWorkCenter.getCity().getName());

        return zona;
    }

    private DimNavision seteamosDimNavision(WorkCenter newWorkCenter) {

        DimNavision dimNavision = new DimNavision();

        dimNavision.setId(newWorkCenter.getLineId());
        dimNavision.setType("GEO");
        dimNavision.setCod("pru");
        dimNavision.setName(newWorkCenter.getName());
        dimNavision.setActive(1);
        dimNavision.setOrder(444);
        dimNavision.setMcc_ln_mf("PT");
        String provinceCod = String.valueOf(newWorkCenter.getCity().getProvince().getId());
        dimNavision.setProvinceCod(provinceCod);

        return dimNavision;
    }


    @Transactional
    public ResponseEntity<?> editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request) {

        // Construimos el objeto zona
        Zona zona = seteamosZona(newWorkCenter);

        // Editamos la delegaci�n en la tabla MP2.ZONA
        zonaRepository.editWorkCenter(zona);

        // Construimos el objeto dimNavision
        DimNavision dimNavision = seteamosDimNavision(newWorkCenter);

        // Insertamos delegaci�n en RRHH.TM_DIM_NAVISION
        dimNavisionRepository.editWorkCenter(dimNavision);

        // Editamos la delegaci�n en la tabla GC2006_RELEASE.PC_DELEGACIONES
        workCentersRepository.editWorkCenter(workCenterId, newWorkCenter, this.jwtTokenUtil.getUserWithRolesFromToken(request).getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter) {
        return this.workCentersCustomizeRepository.getWorkCenters(workCenterFilter);
    }

    @Override
    public List<Department> getDepartments() {
        return this.departmentRepository.findAllByOrderByName();
    }

    @Override
    public WorkCenter getWorkCenterById(int workCenterId) {
        WorkCenter workCenter = this.workCentersRepository.findWorkCenterById(workCenterId);

        if (workCenter.getHeadPerson() != null) {
            workCenter.getHeadPerson().setCompleteName(workCenter.getHeadPerson().getLastname() + ", " + workCenter.getHeadPerson().getFirstname());
        }

        int totalEmployee = this.workCentersCustomizeRepository.getTotalEmployee(workCenterId);
        workCenter.setEmployee(totalEmployee);

        return workCenter;
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

