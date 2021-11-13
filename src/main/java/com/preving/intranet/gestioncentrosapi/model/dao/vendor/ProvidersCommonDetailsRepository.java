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

    ProvidersCommonDetails findAllByProvDelegacionId(int provDelegacionId);

    @Modifying
    @Transactional
    @Query("update ProvidersCommonDetails p set p.docUrl=:providerDocUrl where p.id=:providerId")
    void  updateProviderDocUrl(@Param("providerId") int providerId, @Param("providerDocUrl")String providerDocUrl);

    ProvidersCommonDetails findProvidersCommonDetailsById(int proComDetails);
    void deleteAllById(int provByWorkCentersId);

    void deleteAllByProvDelegacionId(int provByWorkCentersId);

    @Modifying
    @Transactional
    @Query("update ProvidersCommonDetails pcd set pcd.provDelegacionId=:#{#providersCommonDetails.provDelegacionId}, " +
            "pcd.docUrl=:#{#providersCommonDetails.docUrl}, pcd.docName=:#{#providersCommonDetails.docName}, pcd.docContentType=:#{#providersCommonDetails.docContentType}, " +
            "pcd.serviceDetails=:#{#providersCommonDetails.serviceDetails}, pcd.spending=:#{#providersCommonDetails.spending}, pcd.anualSpending=:#{#providersCommonDetails.anualSpending}, " +
            "pcd.expenditurePeriod=:#{#providersCommonDetails.expenditurePeriod}, " +
            "pcd.serviceStartDate=:#{#providersCommonDetails.serviceStartDate}, pcd.serviceEndDate=:#{#providersCommonDetails.serviceEndDate}, " +
            "pcd.modified=CURRENT_TIMESTAMP, pcd.modifiedBy=:#{#providersCommonDetails.modifiedBy} " +
            "where pcd.id=:#{#providersCommonDetails.id}")
    void editProviderCommonDetails(@Param("providersCommonDetails") ProvidersCommonDetails providersCommonDetails);
}
