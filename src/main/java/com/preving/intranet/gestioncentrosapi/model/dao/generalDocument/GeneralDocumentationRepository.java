package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDocumentationRepository extends JpaRepository<GeneralDocumentation,Integer> {

//List<GeneralDocumentation> getGeneralDocumentation(Integer workCenterId);

}
