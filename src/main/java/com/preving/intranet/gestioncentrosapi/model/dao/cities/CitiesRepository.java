package com.preving.intranet.gestioncentrosapi.model.dao.cities;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository  {

    List findCitiesByProvince(String provinceCod, String criterion);

}
