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
    @Query("update Provider p set p.doc_Url=:providerDocUrl where p.id=:providerId")
    void  updateProviderDocUrl(@Param("providerId") int providerId, @Param("providerDocUrl")String providerDocUrl);

}
