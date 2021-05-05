package com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters.mainentities;

import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntitiesRepository extends JpaRepository <Entity, Integer> {
     List<Entity> findAll();

    List<Entity> findAllByActiveTrue();

}
