package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.TaxesTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxesTypesRepository extends JpaRepository<TaxesTypes, Integer> {
    List<TaxesTypes> findAll();
}
