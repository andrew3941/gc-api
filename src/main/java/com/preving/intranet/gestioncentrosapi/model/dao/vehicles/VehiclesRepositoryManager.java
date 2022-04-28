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
public class VehiclesRepositoryManager implements VehiclesCustomRepository {

    @PersistenceContext
    private EntityManager manager;



    @Override
    public List<Vehicles> getVehiclesFiltered(Integer workCenterId, VehiclesFilter vehiclesFilter, UsuarioWithRoles user) {

        String sql = "" +
                "SELECT VE.ID, " +
                "VE.MATRICULA, " +
                "VE.MARCA_ID, " +
                "VE.MODELO, " +
                "VE.MODO_COMPRA, " +
                "VE.RESPONSABLE_ID, " +
                "VE.FECHA_COMPRA," +
                " VE.FECHA_VENCIMIENTO, " +
                "VE.CUOTA_MENSUAL, " +
                "VE.ACTIVO, " +
                "MAR.NOMBRE AS MARCA, " +
                "U.NOMBRE AS RESPONSABLE_FIRST_NAME, " +
                "U.APELLIDOS AS RESPONSABLE_LAST_NAME " +

                "FROM SAC.VE_VEHICULOS VE, " +
                " SAC.VE_MARCAS MAR, " +
                " GC2006_RELEASE.PC_USUARIOS U ";

        sql += "WHERE VE.MARCA_ID = MAR.ID " +
                " AND VE.RESPONSABLE_ID = U.ID ";

        if (vehiclesFilter != null && vehiclesFilter.getVehicleBrandTypes().size() != 0) {
            sql += "AND VE.MARCA_ID = :brands ";
        }

        if (vehiclesFilter != null && !vehiclesFilter.getCard().equals("")) {
            sql += "AND LOWER(TRANSLATE(VE.MATRICULA, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:card, '������������', 'aeiounAEIOUN')) ";
        }




        if(vehiclesFilter != null && vehiclesFilter.getVehiclesStatus() == 1) {
            sql += " AND VE.FECHA_VENCIMIENTO IS NULL ";
        } else if (vehiclesFilter.getVehiclesStatus() == 0) {
            sql += " AND VE.FECHA_VENCIMIENTO IS NOT NULL ";
        }



        if(workCenterId != 0){
            sql += "AND VE.DELEGACION_ID = :workCenterId ";
        }

        sql += "ORDER BY VE.FECHA_COMPRA DESC ";

        Query query = manager.createNativeQuery(sql, "VehiclesMapping");

        if (vehiclesFilter != null && vehiclesFilter.getVehicleBrandTypes().size() != 0 && vehiclesFilter.getVehicleBrandTypes() != null) {
            query.setParameter("brands", vehiclesFilter.getVehicleBrandTypes());
        }

        if (vehiclesFilter != null && !vehiclesFilter.getCard().equals("")) {
            query.setParameter("card", "%" + vehiclesFilter.getCard() + "%");
        }

        if(workCenterId != 0){
            query.setParameter("workCenterId", workCenterId);
        }

        List<Vehicles> vehicleResults = query.getResultList();

        return vehicleResults;
    }
}
