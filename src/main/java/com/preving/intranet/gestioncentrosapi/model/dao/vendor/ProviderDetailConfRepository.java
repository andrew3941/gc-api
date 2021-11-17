package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetailConf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderDetailConfRepository extends JpaRepository<ProviderDetailConf, Integer> {

    List<ProviderDetailConf> findAllByProviderType(ProviderTypes providerType);

}
