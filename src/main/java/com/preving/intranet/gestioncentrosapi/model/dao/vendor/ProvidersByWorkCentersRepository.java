package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByWorkCenters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvidersByWorkCentersRepository extends JpaRepository<ProvidersByWorkCenters, Integer> {

    List<ProvidersByWorkCenters> findAllByProvider (Provider provider);

    void deleteAllByProvider(Provider provider);

}
