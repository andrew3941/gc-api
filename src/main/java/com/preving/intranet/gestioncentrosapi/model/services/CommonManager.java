package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters.mainentities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommonManager implements CommonService {

    @Autowired
    private ProvincesRepository provincesRepository;

    @Autowired
    EntitiesRepository entitiesRepository;

    @Override
    public List<Province> findAllProvinces(){
        return provincesRepository.findAllByOrderByName();
    }

    @Override
    public List<Entity> findAllEntities() {
        return entitiesRepository.findAllByActiveTrue();
    }


}

