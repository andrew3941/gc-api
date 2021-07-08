package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.CenterDetailsDepart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterDetailsDepartRepository extends JpaRepository<CenterDetailsDepart, Integer> {
}
