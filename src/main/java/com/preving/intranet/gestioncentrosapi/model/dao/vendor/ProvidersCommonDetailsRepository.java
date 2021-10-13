package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersCommonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProvidersCommonDetailsRepository extends JpaRepository<ProvidersCommonDetails, Integer> {

    List<ProvidersCommonDetails> findAllByProvDelegacionId(int provDelegacionId);

    @Modifying
    @Transactional
    @Query("update ProvidersCommonDetails p set p.docUrl=:providerDocUrl where p.id=:providerId")
    void  updateProviderDocUrl(@Param("providerId") int providerId, @Param("providerDocUrl")String providerDocUrl);

}
