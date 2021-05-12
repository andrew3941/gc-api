package com.preving.intranet.gestioncentrosapi.model.cities;

import com.preving.intranet.gestioncentrosapi.model.domain.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository  {

    List<City> findCitiesByProvince(int provinceCod, String criterion);

}
