package com.preving.intranet.gestioncentrosapi.model.cities;

import com.preving.intranet.gestioncentrosapi.model.domain.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesRepositoryManager implements CitiesRepository {

    @Override
    public List<City> findCitiesByProvince(int provinceCod, String criterion) {
        return null;
    }
}
