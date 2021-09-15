package com.preving.intranet.gestioncentrosapi.model.dao.room;

import com.preving.intranet.gestioncentrosapi.model.domain.RoomByTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomByTypesRepository extends JpaRepository<RoomByTypes, Integer> {

    void deleteByRoomId(int roomId);
}
