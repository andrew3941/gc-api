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
                "SELECT DISTINCT   VE.MARCA_ID,VE.TARJETA, VE.MATRICULA, VE.MODELO, VE.MODO_COMPRA, VE.RESPONSABLE_ID,VE.FECHA_COMPRA, VE.FECHA_VENCIMIENTO, VE.CUOTA_MENSUAL, VE.ACTIVO"+
                "                 MAR.NOMBRE AS MARCA" +

                "FROM SAC.VE_VEHICULOS VEH " +
                "        SAC.VE_MARCAS MAR, " +
                "       GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES MXD ";;

        sql += "WHERE VE.MARCA_ID = MAR.ID " +
                "      AND  VE.VE_MARCAS.ID = VE.ID ";

        if (vehiclesFilter != null && vehiclesFilter.getBrands().size() != 0) {
            sql += "AND VE.MARCA_ID = :brands ";
        }

        if (vehiclesFilter != null && vehiclesFilter.getCard() != null) {
            sql += "AND VE.TARJETA >= :card ";
        }

        if(workCenterId != 0){
            sql += "AND MXD.DELEGACION_ID = :workCenterId ";
        }

        sql += " ORDER BY VE.FECHA_COMPRA DESC ";

        Query query = manager.createNativeQuery(sql, "VehiclesMapping");

        if (vehiclesFilter != null && vehiclesFilter.getBrands().size() != 0 && vehiclesFilter.getBrands() != null) {
            query.setParameter("brands", vehiclesFilter.getBrands());
        }

        if (vehiclesFilter != null && vehiclesFilter.getCard() != null) {
            query.setParameter("card", vehiclesFilter.getCard());
        }

        if(workCenterId != 0){
            query.setParameter("workCenterId", workCenterId);
        }

        List<Vehicles> vehicleResults = query.getResultList();

        return vehicleResults;
    }
}
