package com.preving.intranet.gestioncentrosapi.model.dao.entities;

import com.preving.intranet.gestioncentrosapi.model.domain.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntitiesRepository extends JpaRepository<Entities, Integer> {

    List<Entities> findAll();
    List<Entities> findAllByActiveTrue();

}
