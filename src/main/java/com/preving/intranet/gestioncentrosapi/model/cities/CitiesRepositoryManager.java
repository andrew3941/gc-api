package com.preving.intranet.gestioncentrosapi.model.cities;

import com.preving.intranet.gestioncentrosapi.model.domain.City;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesRepositoryManager implements CitiesRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> findCitiesByProvince(String provinceCod, String criterion) {

        String sql = "" +
                "SELECT * " +
                "FROM ( " +
                "   SELECT LOC_ID, CAST(LOC_PRV_COD AS INTEGER), LOC_NOMBRE FROM VIG_SALUD.LOCALIDADES " +
                "   WHERE LOC_PRV_COD=:provinceCod " +
                "   AND LOWER(TRANSLATE(LOC_NOMBRE, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) " +
                "   LIKE LOWER(TRANSLATE(:criterion, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) " +
                "   ORDER BY LOC_NOMBRE ASC) " +
                "AS rs " +
                "LIMIT 10";

        Query query = manager.createNativeQuery(sql).setParameter("provinceCod", provinceCod)
                .setParameter("criterion", "%" + criterion + "%");

        return mappingCities(query.getResultList());
    }

    private List<City> mappingCities(List<Object[]> services) {

        List<City> mappedCities = new ArrayList<>();

        services.stream().forEach((record) -> {
            City city = new City(((BigDecimal) record[0]).intValue(),
                    ((int) record[1]),
                    (String) record[2]);
            mappedCities.add(city);
        });

        return mappedCities;

    }
}
