package com.preving.intranet.gestioncentrosapi.model.dao.zona;

import com.preving.intranet.gestioncentrosapi.model.domain.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer> {

}
