package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProviderDetailsRepository extends JpaRepository<ProviderDetail, Integer> {

}
