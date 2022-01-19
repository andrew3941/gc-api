package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GeneralDocumentationRepository extends JpaRepository<GeneralDocumentation, Integer> {

    List<GeneralDocumentation>findGeneralDocumentationsByWorkCenterIdAndDeletedIsNullOrderByCreatedDesc(int workCenterId);
    GeneralDocumentation findGeneralDocumentationById(int generalDocId);

    @Modifying
    @Transactional
    @Query("update GeneralDocumentation d set  borrado=CURRENT_TIMESTAMP, borrado_por=:deleted_by where id=:generalDoc_id and delegacion_id=:workCenterId")
    void generalDocLogicDelete(@Param("deleted_by") int deleted_by, @Param("generalDoc_id") int generalDoc_id, @Param("workCenterId") int workCenterId);

    void deleteByWorkCenter(WorkCenter workCenter);

    @Modifying
    @Transactional
    @Query("update GeneralDocumentation g set g.documentName=:#{#generalDocumentation.documentName}, " +
            "g.generalDocTypes=:#{#generalDocumentation.generalDocTypes}, " +
            "g.modified=CURRENT_TIMESTAMP, g.modifiedBy=:#{#generalDocumentation.modifiedBy} " +
            "where g.id=:#{#generalDocumentation.id} ")
    void  editWorkCenterDrawing(@Param("generalDocumentation") GeneralDocumentation generalDocumentation);

//    @Modifying
//    @Transactional
//    @Query("update GeneralDocumentation g set g.annualImport=:annualImport where g.id=:generalDocId")
//    void  updateDrawingDocUrl(@Param("generalDocId") int generalDocId, @Param("annualImport")String annualImport);

}
