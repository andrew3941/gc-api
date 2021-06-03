package com.preving.intranet.gestioncentrosapi.model.dao.zona;

import com.preving.intranet.gestioncentrosapi.model.domain.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer> {

    @Modifying
    @Transactional
    @Query("update Zona z set z.denomination=:#{#zona.denomination}, " +
            "z.name=:#{#zona.name}, " +
            "z.telephone=:#{#zona.telephone}, " +
            "z.email=:#{#zona.email}, " +
            "z.address=:#{#zona.address}, " +
            "z.codPostal=:#{#zona.codPostal}, "+
            "z.poblacion=:#{#zona.poblacion} "+
            "where z.codZona=:#{#zona.codZona} ")
    void  editWorkCenter(@Param("zona") Zona zona);

}
