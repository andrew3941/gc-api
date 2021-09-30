package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByWorkCenters;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProvidersByWorkCentersRepository extends JpaRepository<ProvidersByWorkCenters, Integer> {
}
