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

        String sql = "" +
                "SELECT WC.ID, WC.LOCALIDAD_ID, WC.NOMBRE, WC.COD_IN_NAV, WC.DIRECCION, WC.C_POSTAL, WC.TFNO, WC.MAIL, WC.FECHA_ALTA, WC.FECHA_BAJA " +
                "FROM GC2006_RELEASE.PC_DELEGACIONES WC WHERE 1=1 ";

        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){

            sql += " AND WC.LOCALIDAD_ID IN (SELECT LOC_ID FROM VIG_SALUD.LOCALIDADES LOCALITY WHERE LOCALITY.LOC_PRV_COD = :workCenterProvince)";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 0){
            sql += " AND WC.ACTIVO = :workCenterStatus";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != ""){

            sql += " AND LOWER(TRANSLATE(WC.NOMBRE, '????????????', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:workCenterName, '????????????', 'aeiounAEIOUN'))";
        }

        Query query = manager.createNativeQuery(sql, "WorkCenterMapping");

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != "") {
            query.setParameter("workCenterName", "%" + workCenterFilter.getWorkCenterName() + "%");
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){
            query.setParameter("workCenterProvince",workCenterFilter.getWorkCenterProvince().getId());
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 0){
            query.setParameter("workCenterStatus", workCenterFilter.getWorkCenterStatus());
        }

        List<WorkCenter>  allWorkCenters = query.getResultList();

        return allWorkCenters;
    }
}
