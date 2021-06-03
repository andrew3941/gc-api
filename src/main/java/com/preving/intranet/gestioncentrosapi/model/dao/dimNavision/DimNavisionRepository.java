package com.preving.intranet.gestioncentrosapi.model.dao.dimNavision;

import com.preving.intranet.gestioncentrosapi.model.domain.DimNavision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DimNavisionRepository extends JpaRepository<DimNavision, Integer> {


    @Modifying
    @Transactional
    @Query("update DimNavision dm set dm.name=:#{#dimNavision.name}, " +
            "dm.provinceCod=:#{#dimNavision.provinceCod} " +
            "where dm.id=:#{#dimNavision.id} ")
    void  editWorkCenter(@Param("dimNavision") DimNavision dimNavision);

}
