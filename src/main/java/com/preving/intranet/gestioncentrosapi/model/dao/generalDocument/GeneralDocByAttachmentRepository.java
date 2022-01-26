package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocByAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GeneralDocByAttachmentRepository extends JpaRepository<GeneralDocByAttachment, Integer> {

    GeneralDocByAttachment findByGeneralDocId(int generalDocId);

    @Modifying
    @Transactional
    @Query("update GeneralDocByAttachment pa set pa.attachedUrl=:attachmentUrl where pa.id=:attachmentId")
    void  updateGeneralDocByAttachmentUrl(@Param("attachmentId") int attachmentId, @Param("attachmentUrl")String attachmentUrl);

}
