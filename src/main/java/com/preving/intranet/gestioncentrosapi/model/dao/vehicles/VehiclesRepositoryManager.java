package com.preving.intranet.gestioncentrosapi.model.dao.vehicles;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.VehiclesFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class VehiclesRepositoryManager implements VehiclesCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Vehicles> getVehiclesFiltered(Integer workCenterId, VehiclesFilter vehiclesFilter, UsuarioWithRoles user) {

        String sql = "" +
                "SELECT DISTINCT   V.ID, V.MATRICULA, V.MODELO, V.MODO_COMPRA, V.FECHA_COMPRA, V.FECHA_VENCIMIENTO, V.PRECIO, V.TARJETA, V.CREADO " +
                "                 M.NOMBRE AS MARCA" +

                "FROM SAC.VE_VEHICULOS V " +
                "        SAC.VE_MARCAS M, " +
                "       GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES MXD ";

        sql += "WHERE V.MARCA_ID = M.ID " +
                "      AND  V.VE_MARCAS.ID = V.ID " +
                "       AND V.ID = MXD.MANTENIMIENTO_ID ";

        if (vehiclesFilter != null && vehiclesFilter.getBrands().size() != 0) {
            sql += "AND M.ID = :brands ";
        }

        if (vehiclesFilter != null && vehiclesFilter.getCard() != null) {
            sql += "AND V.TARJETA >= :card ";
        }

        if (workCenterId != 0) {
            sql += "AND MXD.DELEGACION_ID = :workCenterId ";
        }


        sql += " ORDER BY V.CREADO DESC ";

        Query query = manager.createNativeQuery(sql, "MaintenanceMapping");

        if (vehiclesFilter != null && vehiclesFilter.getBrands().size() != 0 && vehiclesFilter.getBrands() != null) {
            query.setParameter("brands", vehiclesFilter.getBrands());
        }


        if (vehiclesFilter != null && vehiclesFilter.getCard() != null) {
            query.setParameter("card", vehiclesFilter.getCard());
        }


        if (workCenterId != 0) {
            query.setParameter("workCenterId", workCenterId);
        }

        List<Vehicles> vehicleResults = query.getResultList();

        return vehicleResults;
    }
}
