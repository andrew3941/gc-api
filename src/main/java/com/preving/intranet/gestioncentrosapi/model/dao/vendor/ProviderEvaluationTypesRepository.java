package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderEvaluationTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderEvaluationTypesRepository extends JpaRepository<ProviderEvaluationTypes, Integer> {
    List<ProviderEvaluationTypes> findAll();
}
