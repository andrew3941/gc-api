package com.preving.intranet.gestioncentrosapi.model.services;


import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.VehiclesFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.http.ResponseEntity;

import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service
public interface VehiclesService {

    //    Filter Vehicles
    List<Vehicles> getFilteredVehicles(int workCenterId, VehiclesFilter vehicleFilter, UsuarioWithRoles user);

    //    Export Vehicles
    ResponseEntity<?> exportVehicle(int workCenterId, VehiclesFilter vehicleFilter, HttpServletResponse response, UsuarioWithRoles user);

    //    List all vehicles brand types
    List<Brands> getAllBrandTypes();

    //    Get all vehicle list
    List<Vehicles> findAllVehicles();

}
