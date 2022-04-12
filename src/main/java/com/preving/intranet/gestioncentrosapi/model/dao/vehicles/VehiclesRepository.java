package com.preving.intranet.gestioncentrosapi.model.dao.vehicles;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehiclesRepository extends JpaRepository<Vehicles,Integer> {


}
