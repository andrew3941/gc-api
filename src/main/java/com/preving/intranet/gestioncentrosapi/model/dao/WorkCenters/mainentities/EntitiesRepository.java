package com.preving.intranet.gestioncentrosapi.model.dao.WorkCenters.mainentities;

import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EntitiesRepository extends JpaRepository<Entity, Integer> {

    List<Entity> findAll();
    List<Entity> findAllByActiveTrue();

}
