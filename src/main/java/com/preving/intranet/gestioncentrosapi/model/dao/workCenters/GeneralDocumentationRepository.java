package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.GeneralDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDocumentationRepository extends JpaRepository<GeneralDocumentation,Integer> {

//    List<GeneralDocumentation> findGeneralDocumentationBy(int workCenterId);

}
