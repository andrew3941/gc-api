package com.preving.intranet.gestioncentrosapi.model.dao.provinces;

import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProvincesRepository  extends JpaRepository<Province, Integer> {

    List<Province> findAllByOrderByName();

    Province findById(int id);
}
