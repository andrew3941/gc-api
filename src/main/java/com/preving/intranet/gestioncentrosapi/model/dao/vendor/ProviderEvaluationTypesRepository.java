package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderEvaluationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderEvaluationTypesRepository extends JpaRepository<ProviderEvaluationTypes, Integer> {
    List<ProviderEvaluationTypes> findAll();
}
