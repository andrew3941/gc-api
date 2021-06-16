package com.preving.intranet.gestioncentrosapi.model.dao.cities;

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
                "   WHERE LOC_PRV_COD LIKE :provinceCod ";

        if(criterion != null && !criterion.equals("")) {
            sql += "AND LOWER(TRANSLATE(LOC_NOMBRE, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) " +
                    "LIKE LOWER(TRANSLATE(:criterion, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) ";
        }

        sql += "ORDER BY LOC_NOMBRE ASC) " +
                "AS rs";

        if(Integer.parseInt(provinceCod) < 10) {
            provinceCod = "0" + provinceCod;
        }

        Query query = manager.createNativeQuery(sql).setParameter("provinceCod", provinceCod);

        if(criterion != null && !criterion.equals("")) {
            query.setParameter("criterion", "%" + criterion + "%");
        }

        return mappingCities(query.getResultList());
    }

    private List<City> mappingCities(List<Object[]> localities) {

        List<City> mappedCities = new ArrayList<>();

        localities.stream().forEach((record) -> {
            City city = new City(((BigDecimal) record[0]).intValue(),
                    ((int) record[1]),
                    (String) record[2]);
            mappedCities.add(city);
        });

        return mappedCities;

    }
}
