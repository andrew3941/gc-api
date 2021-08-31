package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderTypesRepository extends JpaRepository<ProviderTypes, Integer> {
    List<ProviderTypes> findAll();
}
