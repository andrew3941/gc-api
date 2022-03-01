package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import org.springframework.data.jpa.repository.JpaRepository;




public interface MaintenanceByAttachmentRepository extends JpaRepository<MaintenanceByAttachement, Integer> {
//    MaintenanceByAttachement findByGeneralMainId(int id);

}
