package com.preving.intranet.gestioncentrosapi.model.dao.vehicles;


import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.VehiclesFilter;
import com.preving.security.domain.UsuarioWithRoles;

import java.util.List;

public interface VehiclesCustomRepository {
    List<Vehicles> getVehiclesFiltered(Integer workCenterId, VehiclesFilter vehiclesFilter, UsuarioWithRoles user);

}
