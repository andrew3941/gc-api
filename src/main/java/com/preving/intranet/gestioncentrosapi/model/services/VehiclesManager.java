package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vehicles.BrandsRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.vehicles.VehiclesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclesManager implements VehiclesService {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private BrandsRepository brandsRepository;


//    @Autowired
//    public VehiclesManager(VehiclesRepository vehiclesRepository) {
//        this.vehiclesRepository = vehiclesRepository;
//    }

    @Override
    public List<Brands> getAllBrandTypes() {
        return brandsRepository.findAll();
    }

    @Override
    public List<Vehicles> findAllVehicles() {
        return vehiclesRepository.findAll();
    }
}
