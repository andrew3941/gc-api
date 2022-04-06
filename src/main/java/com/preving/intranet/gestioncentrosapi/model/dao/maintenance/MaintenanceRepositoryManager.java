package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class MaintenanceRepositoryManager implements MaintenanceCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    private final static String GC_ADMINISTRATOR_ROL_NAME = "44-25102";
    private final static String GC_MANAGER_ROL_NAME = "44-25103";
    private final static String GC_READING_ROL_NAME = "44-25104";

    @Override
    public List<Maintenance> getMaintenanceFiltered(Integer workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user) {

        String sql = "" +
                "SELECT DISTINCT MA.ID, MA.CUANTIA, MA.REF_FACTURA, MA.FECHA, MA.OBSERVACIONES, MA.CONCEPTO, " +
                "               MT.DENOMINACION AS TIPO, PO.NOMBRE, P.DENOMINACION AS PERIODICIDAD " +
                "FROM GESTION_CENTROS.MANTENIMIENTOS MA, " +
                "       GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS MT, " +
                "       GESTION_CENTROS.PROVEEDORES PO, " +
                "       GESTION_CENTROS.TM_PERIODICIDAD_GASTO P, " +
                "       GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES MXD ";


        sql += "WHERE MA.TIPO_ID = MT.ID " +
                "       AND MA.PROVEEDOR_ID = PO.ID " +
                "       AND MA.PERIODICIDAD_ID = P.ID " +
                "       AND MA.ID = MXD.MANTENIMIENTO_ID " +
                "       AND MA.BORRADO IS NULL ";

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceTypes().size() != 0) {
            sql += "AND MT.ID = :maintenanceType ";
        }


        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceProvider().getId() != 0) {
            sql += "AND PO.ID = :maintenanceProvider ";
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceStartDate() != null) {
            sql += "AND MA.FECHA >= :maintStartDate ";
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceEndDate() != null) {
            sql += "AND MA.FECHA <= :maintEndDate ";
        }

        if(workCenterId != 0){
            sql += "AND MXD.DELEGACION_ID = :workCenterId ";
        }


        sql += " ORDER BY MA.FECHA DESC ";

        Query query = manager.createNativeQuery(sql, "MaintenanceMapping");

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceTypes().size() != 0 && maintenanceFilter.getMaintenanceTypes() != null) {
            query.setParameter("maintenanceType", maintenanceFilter.getMaintenanceTypes());
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceProvider().getId() != 0) {
            query.setParameter("maintenanceProvider", maintenanceFilter.getMaintenanceProvider());
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceStartDate() != null) {
            query.setParameter("maintStartDate",  maintenanceFilter.getMaintenanceStartDate());
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceEndDate() != null) {
            query.setParameter("maintEndDate", maintenanceFilter.getMaintenanceEndDate());
        }

        if(workCenterId != 0){
            query.setParameter("workCenterId", workCenterId);
        }

        List<Maintenance> maintenanceResults = query.getResultList();

        return maintenanceResults;
    }


    @Override
    public String findDocUrlByMaintenanceType(int maintenanceType, int workCenterId) {

        String sql = "" +
                "SELECT DET.DOC_URL " +
                "FROM GESTION_CENTROS.MANTENIMIENTOS M_COMUN DET, " +
                "       GC2006_RELEASE.PC_DELEGACIONES DEL, " +
                "       GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS MT " +
                "WHERE M.PROVEEDOR_ID = M.ID " +
                "       AND M.DELEGACION_ID = DEL.ID " +
                "AND M.PERIODICIDAD_ID= :maintenanceId " +
                "       AND M.DELEGACION_ID = :workCenterId ";

        Query query = manager.createNativeQuery(sql)
                .setParameter("workCenterId", workCenterId);

        String docUrl = query.getSingleResult().toString();
        return docUrl;

    }


    @Override
    public boolean checkProviderCIf(String maintenanceCif) {
        String sql = "" +
                "SELECT COUNT(*)  " +
                "FROM GESTION_CENTROS.PROVEEDORES " +
                "       WHERE LOWER(cif) = LOWER(:maintenanceCif) ";

        Query query = manager.createNativeQuery(sql)
                .setParameter("maintenanceCif", maintenanceCif);

        boolean maintenanceExist = Integer.parseInt(query.getSingleResult().toString()) > 0;

        return maintenanceExist;



    }

    @Override
    public String findDocUrlByMaintenanceId(int maintenanceId, int workCenterId) {
        String sql = "" +
                "SELECT DET.DOC_URL " +
                "FROM GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS DET, " +
                "       GC2006_RELEASE.PC_DELEGACIONES DEL, " +
                "       GESTION_CENTROS.MAINTENANCE_X_DELEGACIONES MAINS " +
                "WHERE DET.MANTENIMIENTO_ID = MAINS.ID " +
                "       AND MAINS.DELEGACION_ID = DEL.ID " +
                "AND MAINS.MAINTENANCE_ID = :maintenanceId " +
                "       AND MAINS.DELEGACION_ID = :workCenterId ";

        Query query = manager.createNativeQuery(sql)
                .setParameter("maintenanceId", maintenanceId)
                .setParameter("workCenterId", workCenterId);

        String docUrl = query.getSingleResult().toString();

        return docUrl;
    }


}

