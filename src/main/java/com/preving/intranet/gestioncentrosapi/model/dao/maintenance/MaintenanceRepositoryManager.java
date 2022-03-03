package com.preving.intranet.gestioncentrosapi.model.dao.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import com.preving.security.domain.UsuarioWithRoles;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class MaintenanceRepositoryManager implements MaintenanceCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    private final static String GC_ADMINISTRATOR_ROL_NAME = "44-25102";
    private final static String GC_MANAGER_ROL_NAME = "44-25103";
    private final static String GC_READING_ROL_NAME = "44-25104";

    @Override
    public List<Maintenance> getMaintenance(Integer workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user) {

        String sql = "" +
                "SELECT DISTINCT M.ID, M.CUANTIA, M.FECHA " +
                "M.TIPO_ID, M.PROVEEDOR_ID, M.PERIODICIDAD_ID, " +
                "MT.DENOMINACION AS MAINTENANCE_TYPE, " +
                "FROM GESTION_CENTROS.MANTENIMIENTOS M, " +
                "GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS MT, " +
                "GESTION_CENTROS.PROVEEDORES P, " +
                "GESTION_CENTROS.TM_PERIODICIDAD_GASTO, ";


        //TODO
        sql += "WHERE M.TIPO_ID = M.ID " +
                "AND M.ID = M.PROVEEDOR_ID " +
                "MT.DENOMINACION = M.ID" +
                "AND M.FECHA = M.CUANTIA " +
                "AND M.ID = M.PERIODICIDAD_ID" +
                "AND M.ID= MT.DENOMINACION ";


        //TODO CHECK THE IF CONDITIONS WELL
        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceTypes().size() != 0) {
            sql += "AND MT.ID =:maintenanceType ";
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceProvider() != "") {
            sql += "AND M.PERIODICIDAD_ID =:maintenanceProvider ";
        }
        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceStartDate() != "") {
            sql += "AND M.FECHA=:maintenaceStartDate";
        }


        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceEndDate() != "") {
            sql += "AND M.FECHA=:maintenaceEndDate";
        }

        if (workCenterId != 0) {
            sql += "AND PW.DELEGACION_ID =:workCenterId ";
        }


        sql += " ORDER BY M.FECHA DESC";

        Query query = manager.createNativeQuery(sql, "MaintenanceMapping");

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceTypes().size() != 0 && maintenanceFilter.getMaintenanceTypes() != null) {
            query.setParameter("maintenanceType", maintenanceFilter.getMaintenanceTypes());
        }

        if (maintenanceFilter != null && maintenanceFilter.getMaintenanceProvider() != "" && maintenanceFilter.getMaintenanceProvider() != null) {
            query.setParameter("maintenanceProvider", maintenanceFilter.getMaintenanceProvider());
        }

        if (workCenterId != 0) {
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
    public boolean checkProviderCIf(String providerCif) {
        return false;
    }


}

