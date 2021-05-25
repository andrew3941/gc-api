package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.Entities;
import com.preving.intranet.gestioncentrosapi.model.domain.Province;

import java.util.List;


public interface CommonService {

    List<Province> findAllProvinces();

    List<Entities> findAll();
}
