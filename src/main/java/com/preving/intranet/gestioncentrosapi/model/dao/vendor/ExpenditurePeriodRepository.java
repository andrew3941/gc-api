package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ExpenditurePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenditurePeriodRepository extends JpaRepository<ExpenditurePeriod, Integer> {
 List<ExpenditurePeriod> findAll();
}
