package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderDetailsRepository extends JpaRepository<ProviderDetail, Integer> {

    List<ProviderDetail> findByProvidersByWorkCentersIdAndActiveIsTrue(int providerByWorkcentersId);

    void deleteAllByProvidersByWorkCentersId(int provByWorkCentersId);

}
