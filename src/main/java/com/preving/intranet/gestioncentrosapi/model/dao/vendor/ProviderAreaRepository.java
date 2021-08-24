package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderAreaRepository  extends JpaRepository<ProviderArea, Integer> {
    List<ProviderArea> findAll();
}
