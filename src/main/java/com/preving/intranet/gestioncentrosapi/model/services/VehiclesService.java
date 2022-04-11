package com.preving.intranet.gestioncentrosapi.model.services;


import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface VehiclesService {

    List<Brands> getAllBrandTypes();
//    GET ALL VEHICLES LIST
    List<Vehicles> findAllVehicles();

}
