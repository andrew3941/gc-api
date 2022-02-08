package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderByAreasRepository extends JpaRepository<ProvidersByAreas, Integer> {

    List<ProvidersByAreas> findAllByProvider (Provider provider);

    void deleteAllByProvider(Provider provider);
}
