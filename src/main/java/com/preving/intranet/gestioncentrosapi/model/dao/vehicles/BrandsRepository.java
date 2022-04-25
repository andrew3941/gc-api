package com.preving.intranet.gestioncentrosapi.model.dao.vehicles;

import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Integer> {

    List<Brands> findAllByOrderByName();
}
