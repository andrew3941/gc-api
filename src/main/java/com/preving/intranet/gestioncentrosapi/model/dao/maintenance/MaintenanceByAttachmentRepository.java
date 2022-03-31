package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocByAttachment;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MaintenanceByAttachmentRepository extends JpaRepository<MaintenanceByAttachment, Integer> {


    List<MaintenanceByAttachment> findAllByMaintenance(Maintenance myMaintenance);

    MaintenanceByAttachment findById(int attachedId);

    void deleteById(int attachId);

    @Modifying
    @Transactional
    @Query("update MaintenanceByAttachment ma set ma.documentUrl=:attachmentUrl where ma.id=:attachmentId")
    void updateNewMaintenanceByAttachmentUrl(@Param("attachmentId") int id, @Param("attachmentUrl")String attachmentUrl);
    }
