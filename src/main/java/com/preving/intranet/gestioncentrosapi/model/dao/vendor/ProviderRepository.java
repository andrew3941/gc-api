package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    @Modifying
    @Transactional
    @Query("update Provider p set p.docUrl=:providerDocUrl where p.id=:providerId")
    void  updateProviderDocUrl(@Param("providerId") int providerId, @Param("providerDocUrl")String providerDocUrl);

    Provider findProviderByWorkCenterIdAndId(int workCenterId, int providerId);

    @Modifying
    @Transactional
    @Query("update Provider p set p.name=:#{#provider.name}, " +
            "p.centralProvider=:#{#provider.centralProvider}, " +
            "p.cif=:#{#provider.cif}, p.providerTypes=:#{#provider.providerTypes}, p.providerArea=:#{#provider.providerArea}, " +
            "p.evaluationTypes=:#{#provider.evaluationTypes}, p.expenditurePeriod=:#{#provider.expenditurePeriod}, " +
            "p.email=:#{#provider.email}, p.address=:#{#provider.address}, p.telephone=:#{#provider.telephone}, " +
            "p.serviceDetails=:#{#provider.serviceDetails}, p.spending=:#{#provider.spending}, " +
            "p.serviceStartDate=:#{#provider.serviceStartDate}, p.serviceEndDate=:#{#provider.serviceEndDate}," +
            "p.modified=CURRENT_TIMESTAMP, p.modifiedBy=:#{#provider.modifiedBy} " +
            "where p.id=:#{#provider.id} ")
    void  editProvider(@Param("provider") Provider provider);

}
