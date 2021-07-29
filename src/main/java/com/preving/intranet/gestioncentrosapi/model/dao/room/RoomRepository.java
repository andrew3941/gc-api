package com.preving.intranet.gestioncentrosapi.model.dao.room;

import com.preving.intranet.gestioncentrosapi.model.domain.Room;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findRoomListByWorkCenter(Room room);

    Room findRoomById(int roomId);

    @Modifying
    @Transactional
    @Query("update Room d set  borrado=CURRENT_TIMESTAMP, borrado_por=:deleted_by where id=:room_id and delegacion_id=:workCenterId")
    void roomLogicDelete(@Param("deleted_by") int deleted_by, @Param("room_id") int type_id, @Param("workCenterId") int workCenterId);
}
