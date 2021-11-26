package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    Provider findProviderById(int providerId);

    @Modifying
    @Transactional
    @Query("update Provider p set p.docUrl=:providerDocUrl where p.id=:providerId")
    void  updateProviderDocUrl(@Param("providerId") int providerId, @Param("providerDocUrl")String providerDocUrl);

    @Modifying
    @Transactional
    @Query("update Provider p set p.name=:#{#provider.name}, " +
            "p.docName=:#{#provider.docName}, p.docContentType=:#{#provider.docContentType}," +
            "p.cif=:#{#provider.cif}, p.providerTypes=:#{#provider.providerTypes}, " +
            "p.evaluationTypes=:#{#provider.evaluationTypes}, p.city=:#{#provider.city},p.postalCode=:#{#provider.postalCode}," +
            "p.email=:#{#provider.email}, p.address=:#{#provider.address}, p.telephone=:#{#provider.telephone}, " +
            "p.serviceDetails=:#{#provider.serviceDetails}, " +
            "p.serviceStartDate=:#{#provider.serviceStartDate}, p.serviceEndDate=:#{#provider.serviceEndDate}, " +
            "p.active=:#{#provider.active}, " +
            "p.modified=CURRENT_TIMESTAMP, p.modifiedBy=:#{#provider.modifiedBy} " +
            "where p.id=:#{#provider.id}")
    void  editProvider(@Param("provider") Provider provider);

    List<Provider> findProvidersByServiceStartDateEquals(Date date);

    List<Provider> findProvidersByServiceEndDateEquals(Date date);

    @Modifying
    @Transactional
    @Query("update Provider p set p.active = false where p.id = :providerId")
    void setInactiveProvider(@Param("providerId") int providerId);

    @Modifying
    @Transactional
    @Query("update Provider p set p.active = true where p.id = :providerId")
    void setActiveProvider(@Param("providerId") int providerId);

}
