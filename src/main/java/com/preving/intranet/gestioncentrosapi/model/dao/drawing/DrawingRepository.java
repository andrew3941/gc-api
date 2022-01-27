package com.preving.intranet.gestioncentrosapi.model.dao.drawing;

import com.preving.intranet.gestioncentrosapi.model.domain.Drawing;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DrawingRepository extends JpaRepository<Drawing, Integer> {

    List<Drawing> findAllByWorkCenterIdAndDeletedIsNullOrderByCreatedDesc(int workCenterId);

    Drawing findDrawingById(int drawingId);

    @Modifying
    @Transactional
    @Query("update Drawing d set  borrado=CURRENT_TIMESTAMP, borrado_por=:deleted_by where id=:drawing_id and delegacion_id=:workCenterId")
    void drawingLogicDelete(@Param("deleted_by") int deleted_by, @Param("drawing_id") int drawing_id, @Param("workCenterId") int workCenterId);

    void deleteByWorkCenter(WorkCenter workCenter);

    @Modifying
    @Transactional
    @Query("update Drawing d set d.name=:#{#drawing.name}, " +
            "d.modified=CURRENT_TIMESTAMP, d.modifiedBy=:#{#drawing.modifiedBy} " +
            "where d.id=:#{#drawing.id} ")
    void  editWorkCenterDrawing(@Param("drawing") Drawing drawing);


//    @Modifying
//    @Transactional
//    @Query("update Drawing d set d.docUrl=:drawingDocUrl where d.id=:drawingId")
//    void  updateDrawingDocUrl(@Param("drawingId") int drawingId, @Param("drawingDocUrl")String drawingDocUrl);

}
