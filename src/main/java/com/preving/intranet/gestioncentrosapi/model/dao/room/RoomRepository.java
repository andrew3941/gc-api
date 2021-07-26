package com.preving.intranet.gestioncentrosapi.model.dao.room;

import com.preving.intranet.gestioncentrosapi.model.domain.Room;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findRoomByWorkCenter(Room room);

    void deleteByWorkCenter(WorkCenter workCenter);
}
