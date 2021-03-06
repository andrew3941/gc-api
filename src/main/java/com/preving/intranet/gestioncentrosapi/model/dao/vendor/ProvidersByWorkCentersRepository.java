package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByWorkCenters;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvidersByWorkCentersRepository extends JpaRepository<ProvidersByWorkCenters, Integer> {

    List<ProvidersByWorkCenters> findAllByProvider (Provider provider);

    ProvidersByWorkCenters findByProvider_IdAndWorkCenter_Id(int providerId, int workCenterId);

    ProvidersByWorkCenters findAllByWorkCenter(WorkCenter workCenter);
    ProvidersByWorkCenters findByWorkCenterAndProvider(int workCenterId, int providerId);

    List<ProvidersByWorkCenters> findAllByProviderAndWorkCenter(Provider provider, WorkCenter workCenter);

    void deleteAllById(int provByWorkCenterId);

    void deleteAllByProvider_Id(int providerId);

}
