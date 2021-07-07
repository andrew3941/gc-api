package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;
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

        String sql = "" +
                "SELECT WC.ID, WC.LOCALIDAD_ID, WC.NOMBRE, WC.COD_IN_NAV, WC.DIRECCION, WC.C_POSTAL, WC.TFNO, WC.MAIL, " +
                "   WC.FECHA_ALTA, WC.FECHA_BAJA, WC.ACTIVO, LOC.LOC_NOMBRE LOCALIDAD_NOMBRE, " +
                "   LOC.LOC_PRV_COD PROVINCIA_COD, PRV.PRV_NOMBRE PROVINCIA_NOMBRE " +
                "FROM GC2006_RELEASE.PC_DELEGACIONES WC, " +
                "   VIG_SALUD.LOCALIDADES LOC, " +
                "   VIG_SALUD.PROVINCIAS PRV " +
                "WHERE WC.LOCALIDAD_ID = LOC.LOC_ID " +
                "   AND LOC.LOC_PRV_COD LIKE PRV.PRV_COD ";

        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){
            sql += "AND LOC.LOC_PRV_COD = :workCenterProvince ";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 2){
            sql += " AND WC.ACTIVO = :workCenterStatus ";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != ""){
            sql += " AND LOWER(TRANSLATE(WC.NOMBRE, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:workCenterName, '������������', 'aeiounAEIOUN')) ";
        }

        sql += "ORDER BY WC.NOMBRE";

        Query query = manager.createNativeQuery(sql, "WorkCenterMapping");

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != "") {
            query.setParameter("workCenterName", "%" + workCenterFilter.getWorkCenterName() + "%");
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){

            String prvCod = String.valueOf(workCenterFilter.getWorkCenterProvince().getId());
            if(workCenterFilter.getWorkCenterProvince().getId() < 10) {
                prvCod = "0" + prvCod;
            }

            query.setParameter("workCenterProvince", prvCod);

        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 2){
            query.setParameter("workCenterStatus", workCenterFilter.getWorkCenterStatus());
        }


        List<WorkCenter>  allWorkCenters = query.getResultList();

        return allWorkCenters;
    }

    @Override
    public int getTotalEmployee(int workCenterId) {

        String sql = "" +
                "SELECT COUNT(*) FROM GC2006_RELEASE.PC_USUARIOS_DELEGACION UD " +
                "WHERE DELEGACION_ID=:workCenterId";

        Query query = manager.createNativeQuery(sql).setParameter("workCenterId", workCenterId);
        int totalEmployee = Integer.parseInt(query.getSingleResult().toString());

        return totalEmployee;

    }
}


