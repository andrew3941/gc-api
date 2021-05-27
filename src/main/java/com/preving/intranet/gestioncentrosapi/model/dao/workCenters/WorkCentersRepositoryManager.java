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
                "SELECT ID, LOCALIDAD_ID, NOMBRE, COD_IN_NAV, DIRECCION, C_POSTAL, TFNO, MAIL, FECHA_ALTA, FECHA_BAJA " +
                "FROM " +
                "GC2006_RELEASE.PC_DELEGACIONES WC " +
                "WHERE 1 = 1 ";

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 0){
            sql+=" AND WC.ACTIVO = :workCenterStatus";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != ""){

            sql += " AND LOWER(TRANSLATE(WC.NOMBRE, 'áéíóúñÁÉÍÓÚÑ', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:workCenterName, 'áéíóúñÁÉÍÓÚÑ', 'aeiounAEIOUN'))";
        }

//        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){
//            sql += " AND WC.LOCALIDAD_ID = :workCenterProvince";
//        }

        Query query = manager.createNativeQuery(sql, "WorkCenterMapping");

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != "") {
            query.setParameter("workCenterName", "%" + workCenterFilter.getWorkCenterName() + "%");
        }

//        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getId() != 0){
//            query.setParameter("workCenterProvince", workCenterFilter.getWorkCenterProvince());
//        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 0){
            query.setParameter("workCenterStatus", workCenterFilter.getWorkCenterStatus());
        }

        List<WorkCenter>  allWorkCenters = query.getResultList();

        return allWorkCenters;
    }


    @Override
    public WorkCenter findById(int workId) {

        String sqlGetWorkCenter = "" +
                "SELECT ID, LOCALIDAD_ID, NOMBRE, COD_IN_NAV, DIRECCION, C_POSTAL, TFNO, MAIL, FECHA_ALTA, FECHA_BAJA " +
                "FROM " +
                "GC2006_RELEASE.PC_DELEGACIONES WHERE ID =:workId" ;

        Query query = manager.createNativeQuery(sqlGetWorkCenter,"WorkCenterMapping").setParameter("workId",workId);
        return (WorkCenter) query.getSingleResult();
    }


}
