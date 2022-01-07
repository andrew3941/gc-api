package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.GeneralDocumentationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralDocTypesRepository extends JpaRepository<GeneralDocumentationTypes,Integer> {
    List<GeneralDocumentationTypes> findAll();
}
