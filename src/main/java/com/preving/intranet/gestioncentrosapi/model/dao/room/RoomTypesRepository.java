package com.preving.intranet.gestioncentrosapi.model.dao.room;

import com.preving.intranet.gestioncentrosapi.model.domain.RoomTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypesRepository extends JpaRepository<RoomTypes, Integer> {
    List<RoomTypes> findAll();
}
