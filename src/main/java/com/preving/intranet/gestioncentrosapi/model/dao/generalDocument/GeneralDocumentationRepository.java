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
    @Query("update GeneralDocumentation gDoc set  borrado=CURRENT_TIMESTAMP, borrado_por=:deleted_by where id=:generalDoc_id and delegacion_id=:workCenterId")
    void generalDocLogicDelete(@Param("deleted_by") int deleted_by, @Param("generalDoc_id") int generalDoc_id, @Param("workCenterId") int workCenterId);

    void deleteByWorkCenter(WorkCenter workCenter);

    @Modifying
    @Transactional
    @Query("update GeneralDocumentation gDoc set gDoc.generalDocTypes=:#{#generalDoc.generalDocTypes}, " +
            "gDoc.documentName=:#{#generalDoc.documentName}, gDoc.documentImport=:#{#generalDoc.documentImport}, " +
            "gDoc.documentStartDate=:#{#generalDoc.documentStartDate}, gDoc.documentEndDate=:#{#generalDoc.documentEndDate}, " +
            "gDoc.observations=:#{#generalDoc.observations}, gDoc.insurerName=:#{#generalDoc.insurerName}, gDoc.policeNumber=:#{#generalDoc.policeNumber}, "+
            "gDoc.mediator=:#{#generalDoc.mediator}, gDoc.telephone=:#{#generalDoc.telephone}, gDoc.email=:#{#generalDoc.email}, "+
            "gDoc.annualImport=:#{#generalDoc.annualImport}, gDoc.periodicity=:#{#generalDoc.periodicity}, gDoc.deposit=:#{#generalDoc.deposit}, "+
            "gDoc.certificateTypes=:#{#generalDoc.certificateTypes}, gDoc.taxesTypes=:#{#generalDoc.taxesTypes}, gDoc.created=:#{#generalDoc.created}, "+
            "gDoc.modified=CURRENT_TIMESTAMP, gDoc.modifiedBy=:#{#generalDoc.modifiedBy}, gDoc.workCenter=:#{#generalDoc.workCenter} " +
            "where gDoc.id=:#{#generalDoc.id}")
    void  editGeneralDoc(@Param("generalDoc") GeneralDocumentation generalDoc);



}
