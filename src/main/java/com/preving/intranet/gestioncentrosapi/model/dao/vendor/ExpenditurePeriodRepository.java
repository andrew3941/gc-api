package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ExpenditurePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenditurePeriodRepository extends JpaRepository<ExpenditurePeriod, Integer> {
 List<ExpenditurePeriod> findAll();
}
