package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralDocumentationRepository extends JpaRepository<GeneralDocumentation, Integer> {

    List<GeneralDocumentation> findGeneralDocumentationsByWorkCenterId(int workCenterId);

}
