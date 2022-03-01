package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaintenanceByAttachmentRepository extends JpaRepository<MaintenanceByAttachment, Integer> {

}
