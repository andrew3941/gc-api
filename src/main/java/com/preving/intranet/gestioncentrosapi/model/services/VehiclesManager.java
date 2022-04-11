package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vehicles.BrandsRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehiclesManager implements VehiclesService{

    @Autowired
    private BrandsRepository brandsRepository;

    @Override
    public List<Brands> getAllBrandTypes() {
        return brandsRepository.findAll();
   }
}

