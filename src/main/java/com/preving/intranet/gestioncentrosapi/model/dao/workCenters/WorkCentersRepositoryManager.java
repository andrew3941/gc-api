package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.services.ProviderManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkCentersRepositoryManager implements WorkCentersCustomizeRepository {

    @PersistenceContext
    private EntityManager manager;

    private ProviderManager providerManager;

    private WorkCentersCustomizeRepository workCentersCustomizeRepository;

    @Override
    public List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter) {

        String sql = "" +
                "SELECT DISTINCT WC.ID, WC.LOCALIDAD_ID, WC.NOMBRE, WC.COD_IN_NAV, WC.DIRECCION, WC.C_POSTAL, WC.TFNO, WC.MAIL, " +
                "   WC.FECHA_ALTA, WC.FECHA_BAJA, WC.ACTIVO, WC.TIPO_ID, LOC.LOC_NOMBRE LOCALIDAD_NOMBRE, " +
                "   LOC.LOC_PRV_COD PROVINCIA_COD, PRV.PRV_NOMBRE PROVINCIA_NOMBRE " +
                "FROM GC2006_RELEASE.PC_DELEGACIONES WC, ";

        if(workCenterFilter.getWorkCenterEntities().size() > 0) {
            sql += "GESTION_CENTROS.PC_DELEGACIONES_X_ENTIDADES WCE, ";
        }

        sql +=  "   VIG_SALUD.LOCALIDADES LOC, " +
                "   VIG_SALUD.PROVINCIAS PRV " +
                "WHERE WC.LOCALIDAD_ID = LOC.LOC_ID " +
                "   AND LOC.LOC_PRV_COD LIKE PRV.PRV_COD ";

        if (workCenterFilter.isAllEntitiesSelected()) {
                sql += "AND WC.ID = WCE.DELEGACION_ID ";
        } else {
            if(workCenterFilter.getWorkCenterEntities().size() > 0) {
                String entities = workCenterFilter.getWorkCenterEntities().stream().map(wce -> String.valueOf(wce.getId())).collect(Collectors.joining(","));
                sql += "AND WC.ID = WCE.DELEGACION_ID " +
                        "AND WCE.ENTIDAD_ID IN (" + entities + ")";
            }
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getCod() != null){
            sql += "AND LOC.LOC_PRV_COD = :workCenterProvince ";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 2){
            sql += " AND WC.ACTIVO = :workCenterStatus ";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterTypes().size() > 0){
            sql += " AND WC.TIPO_ID = :workCenterTypes ";
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != ""){
            sql += " AND LOWER(TRANSLATE(WC.NOMBRE, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:workCenterName, '������������', 'aeiounAEIOUN')) ";
        }

        sql += "ORDER BY WC.NOMBRE";

        Query query = manager.createNativeQuery(sql, "WorkCenterMapping");

        if(workCenterFilter != null && workCenterFilter.getWorkCenterName() != null && workCenterFilter.getWorkCenterName() != "") {
            query.setParameter("workCenterName", "%" + workCenterFilter.getWorkCenterName() + "%");
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterProvince().getCod() != null){
            query.setParameter("workCenterProvince", workCenterFilter.getWorkCenterProvince().getCod());
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterStatus() != 2){
            query.setParameter("workCenterStatus", workCenterFilter.getWorkCenterStatus());
        }

        if(workCenterFilter != null && workCenterFilter.getWorkCenterTypes().size() > 0){
            query.setParameter("workCenterTypes", workCenterFilter.getWorkCenterTypes());
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

    @Override
    public List<WorkCenter> findAllByActive() {

        String sql = "" +
                "SELECT WC.ID, WC.NOMBRE, WC.TIPO_ID FROM GC2006_RELEASE.PC_DELEGACIONES WC " +
                "WHERE WC.ACTIVO = 1 " +
                "ORDER BY NOMBRE ";

        Query query = manager.createNativeQuery(sql);

        return mappingWorkCenters(query.getResultList());
    }

    private List<WorkCenter> mappingWorkCenters(List<Object[]> workCenters) {

        List<WorkCenter> mappingWorkCenters = new ArrayList<>();

        workCenters.stream().forEach((record) -> {
            WorkCenter workCenter = new WorkCenter(((BigDecimal) record[0]).intValue(),
                    (String) record[1]);
            mappingWorkCenters.add(workCenter);
        });

        return mappingWorkCenters;

    }

}


