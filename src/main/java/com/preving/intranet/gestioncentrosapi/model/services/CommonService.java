package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {

    List<Province> findAllProvinces();

    List<Entity> findAllEntities();
}
