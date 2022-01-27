package com.preving.intranet.gestioncentrosapi.model.dao.drawingByAttachments;

import com.preving.intranet.gestioncentrosapi.model.domain.Drawing;
import com.preving.intranet.gestioncentrosapi.model.domain.DrawingsByAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface DrawingByAttachmentsRepository extends JpaRepository<DrawingsByAttachment, Integer> {

    List<DrawingsByAttachment> findAllByDrawing_Id(int drawingId);

    DrawingsByAttachment findById(int id);

    DrawingsByAttachment findByDrawingId(int drawindId);

//    void deleteByDrawing(Drawing drawing);

    @Modifying
    @Transactional
    @Query("update DrawingsByAttachment da set da.attachedUrl=:url where da.id=:drawingsByAttachmentId")
    void updateAttachedUrl(@Param("url") String url, @Param("drawingsByAttachmentId") int drawingsByAttachmentId);

}
