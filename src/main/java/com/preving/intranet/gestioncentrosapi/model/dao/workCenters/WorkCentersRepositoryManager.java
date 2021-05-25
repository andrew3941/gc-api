package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class WorkCentersRepositoryManager implements WorkCentersCustomizeRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter) {

        //TODO: Update database query to match the table.

        String sql = "" +
                "SELECT ID, ESTADO_ID, ESTADO, FECHA, FECHA_ACEPTACION, PLAN_ID, PLAN, SERVICIO, OFERTA_ID, RENOVABLE, " +
                "CLIENTE_ID, CLIENTE, CLIENTE_CIF_NIF, ASIGNACIONES, IMPORTE," +
                "CASE WHEN DIAS_PREVISTOS IS NOT NULL THEN  DIAS_PREVISTOS ELSE 0 END DIAS_PREVISTOS," +
                "HORAS_STD, NUM_CENTROS, NUM_TRABAJADORES, URL_TEAMLEADER, FECHA_FIN_PREVISTA, FECHA_FIN_CONSULTOR, OBS_CONSULTOR, FECHA_FIN_REAL," +
                "OBS_COORDINADOR, CONTRATO_ACTIVO, VISITAS, HORAS_REALES " +
                "FROM " +
                "SSGG.PROYECTOS_SERVICIOS_VIEW PSV " +
                "WHERE 1 = 1 " ;


        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 0){
            sql+=" AND PSV.ESTADO_ID = :workCenterStatus";
        }


        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != ""){

            sql += " AND LOWER(TRANSLATE(PSV.CLIENTE, 'áéíóúñÁÉÍÓÚÑ', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:workCenterName, 'áéíóúñÁÉÍÓÚÑ', 'aeiounAEIOUN'))";
        }


        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){
            sql += " AND PSV.PLAN_ID = :workCenterProvince";
        }



        sql += " ORDER BY PSV.FECHA_FIN_PREVISTA ASC";  // sorting


        Query query = manager.createNativeQuery(sql, "WorkCenterMapping");


        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null &&workCenterFilter.getWorkCenterName() != "") {
            query.setParameter("workCenterName", "%" + workCenterFilter.getWorkCenterName() + "%");
        }


        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){
            query.setParameter("workCenterProvince", workCenterFilter.getWorkCenterProvince());
        }


        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 0){
            query.setParameter("workCenterStatus", workCenterFilter.getWorkCenterStatus());
        }


        List<WorkCenter>  allWorkCenters = query.getResultList();

        return allWorkCenters;
    }

}
