package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.entities.EntitiesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.provinces.ProvincesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.Entities;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonManager implements CommonService {

    @Autowired
    private ProvincesRepository provincesRepository;

   // @Autowired
   // private EntitiesRepository entitiesRepository;

    @Override
    public List<Province> findAllProvinces(){
        return provincesRepository.findAllByOrderByName();

    }

    @Override
    public List<Entities> findAll() {
        return null;
    }


}

