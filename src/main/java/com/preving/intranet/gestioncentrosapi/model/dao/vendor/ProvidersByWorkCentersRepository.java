package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByWorkCenters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProvidersByWorkCentersRepository extends JpaRepository<ProvidersByWorkCenters, Integer> {

    List<ProvidersByWorkCenters> findAllByProvider (Provider provider);

    void deleteByProviderId(int providerId);
}
