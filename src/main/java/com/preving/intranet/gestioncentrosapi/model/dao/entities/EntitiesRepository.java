package com.preving.intranet.gestioncentrosapi.model.dao.entities;

import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntitiesRepository extends JpaRepository<Entity, Integer> {

    List<Entity> findAllByOrderByName();

   // List<Entity> findAllByActiveTrue();

}
