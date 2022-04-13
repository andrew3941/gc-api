package com.preving.intranet.gestioncentrosapi.model.dao.vehicles;

import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.VehiclesFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class VehiclesRepositoryManager implements VehiclesCustomRepository{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Vehicles> getVehiclesFiltered(Integer workCenterId, VehiclesFilter vehiclesFilter, UsuarioWithRoles user) {

        String sql = "" +
                "SELECT DISTINCT   V.ID, V.MODELO, V.TARJETA " +

                "FROM SAC.VE_MARCAS M " +

                " SAC.VE_VEHICULOS V ";

        sql += "WHERE V._ID = M.ID " +
                "       AND M.ID = V.ID ";


        if (vehiclesFilter != null && vehiclesFilter.getBrands().size() != 0) {
            sql += "AND M.ID = :brands ";
        }


         if (vehiclesFilter != null && vehiclesFilter.getCard() != null) {
            sql += "AND V.TARJETA >= : card";
        }

        Query query = manager.createNativeQuery(sql, "VehiclesMapping");

        if (vehiclesFilter != null && vehiclesFilter.getBrands().size() != 0 && vehiclesFilter.getBrands() != null) {
            query.setParameter("brands", vehiclesFilter.getBrands());
        }

        if (vehiclesFilter != null && vehiclesFilter.getCard() != null) {
            query.setParameter("card",  vehiclesFilter.getCard());
        }

        if(workCenterId != 0){
            query.setParameter("workCenterId", workCenterId);
        }

        List<Vehicles> vehiclesResults = query.getResultList();

        return vehiclesResults;
    }
}
