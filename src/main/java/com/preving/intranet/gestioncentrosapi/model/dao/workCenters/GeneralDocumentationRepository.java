package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.GeneralDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralDocumentationRepository extends JpaRepository<GeneralDocumentation,Integer> {

    List<GeneralDocumentation> findGeneralDocumentationBy(int workCenterId);

}
