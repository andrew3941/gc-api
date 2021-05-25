package com.preving.intranet.gestioncentrosapi.model.dao.provinces;

import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvincesRepository  {

    List<Province> findAllByOrderByName();

    Province findById(int id);
}
