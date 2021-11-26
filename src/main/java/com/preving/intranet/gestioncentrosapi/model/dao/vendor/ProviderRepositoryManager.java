package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderRepositoryManager implements ProviderCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    private final static String GC_ADMINISTRATOR_ROL_NAME = "44-25102";
    private final static String GC_MANAGER_ROL_NAME = "44-25103";
    private final static String GC_READING_ROL_NAME = "44-25104";

    @Override
    public List<Provider> getProviders(Integer workCenterId, ProviderFilter providerFilter, UsuarioWithRoles user) {

        String sql = "" +
                "SELECT DISTINCT P.ID, P.NOMBRE, P.CIF, " +
                "P.DOC_NOMBRE, P.DOC_CONTENT_TYPE, P.ACTIVO, " +
                "P.TIPO_ID, P.AREA_ID, P.LOCALIDAD_ID, L.LOC_NOMBRE,V.PRV_NOMBRE, " +
                "PT.DENOMINACION AS PROVIDER_TYPE, PA.DENOMINACION AS PROVIDERAREA_TYPE, " +
                "P.TIPO_EVALUACION_ID, PET.DENOMINACION AS PROVIDEREVALUATION_TYPE, " +
                "P.FECHA_INICIO_SERVICIO " +
                "FROM GESTION_CENTROS.PROVEEDORES P, " +
                "GESTION_CENTROS.TM_PROVEEDORES_TIPOS PT, " +
                "GESTION_CENTROS.TM_PROVEEDORES_AREAS PA, " +
                "GESTION_CENTROS.TM_PERIODICIDAD_GASTO PG, " +
                "GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO PET, " +
                "GESTION_CENTROS.PROVEEDORES_X_DELEGACIONES PW, " +
                "GESTION_CENTROS.PROVEEDORES_DETALLES_COMUN PDC, " +
                "VIG_SALUD.LOCALIDADES L, "+
                "VIG_SALUD.PROVINCIAS V ";

        if(!user.hasRole(GC_ADMINISTRATOR_ROL_NAME)) {

            if(user.hasRole(GC_MANAGER_ROL_NAME)) {
                sql += " , GESTION_CENTROS.PROVEEDORES_X_DELEGACIONES PXD, " +
                        " GC2006_RELEASE.PC_DELEGACIONES DEL ";
            }
        }

        sql +=  "WHERE P.TIPO_ID = PT.ID " +
                "AND P.AREA_ID = PA.ID "+
                "AND P.TIPO_EVALUACION_ID = PET.ID "+
                "AND P.LOCALIDAD_ID = L.LOC_ID " +
                "AND L.LOC_PRV_COD = V.PRV_COD " +
                "AND P.ID = PW.PROVEEDOR_ID ";

        if(providerFilter != null && providerFilter.getProviderTypes().size() != 0){
            sql += "AND PT.ID =:providerTypes ";
        }

        if(providerFilter != null && providerFilter.getAreaTypes().size() != 0){
            sql += "AND PA.ID =:areaTypes ";
        }

        if(providerFilter != null && providerFilter.getProvinces().size() != 0){
            sql += "AND V.PRV_COD =:provinceCod ";
        }

        if(providerFilter != null && providerFilter.getCenters().size() > 0) {
            String centers = providerFilter.getCenters().stream().map(wce -> String.valueOf(wce.getId())).collect(Collectors.joining(","));
            sql += "AND PW.DELEGACION_ID IN (" + centers + ")";
        }

        if(workCenterId != 0){
            sql += "AND PW.DELEGACION_ID =:workCenterId ";
        }

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != ""){
            sql += " AND LOWER(TRANSLATE(P.NOMBRE, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:providerName, '������������', 'aeiounAEIOUN')) ";
        }

        if(providerFilter != null && providerFilter.getProviderStatus() != 2) {
            sql += " AND P.ACTIVO = :providerStatus ";
        }

        if(!user.hasRole(GC_ADMINISTRATOR_ROL_NAME)) {

            if(user.hasRole(GC_MANAGER_ROL_NAME)) {
                sql += " AND P.ID = PXD.PROVEEDOR_ID " +
                        " AND PXD.DELEGACION_ID = DEL.ID " +
                        " AND DEL.RESPONSABLE = :userId ";
            }

        }

        sql += " ORDER BY P.FECHA_INICIO_SERVICIO DESC";

        Query query = manager.createNativeQuery(sql, "ProviderMapping");

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != "") {
            query.setParameter("providerName", "%" + providerFilter.getProviderName() + "%");
        }

        if(providerFilter != null && providerFilter.getProviderTypes().size() != 0 && providerFilter.getProviderTypes() != null){
            query.setParameter("providerTypes", providerFilter.getProviderTypes());
        }

        if(providerFilter != null && providerFilter.getAreaTypes().size() != 0 && providerFilter.getAreaTypes() != null){
            query.setParameter("areaTypes", providerFilter.getAreaTypes());
        }

        if(providerFilter != null && providerFilter.getProvinces().size() != 0 && providerFilter.getProvinces() != null){
            query.setParameter("provinceCod", providerFilter.getProvinces());
        }

        if(workCenterId != 0){
            query.setParameter("workCenterId", workCenterId);
        }

        if(providerFilter != null && providerFilter.getProviderStatus() != 2) {
            query.setParameter("providerStatus", providerFilter.getProviderStatus() == 1);
        }

        if(!user.hasRole(GC_ADMINISTRATOR_ROL_NAME)) {
            if(user.hasRole(GC_MANAGER_ROL_NAME)) {
                query.setParameter("userId", user.getId());
            }
        }

        List<Provider> providerResults = query.getResultList();

        return providerResults;
    }


    @Override
    public String findDocUrlByProviderId(int providerId, int workCenterId) {

        String sql = "" +
                "SELECT DET.DOC_URL " +
                "FROM GESTION_CENTROS.PROVEEDORES_DETALLES_COMUN DET, " +
                "       GC2006_RELEASE.PC_DELEGACIONES DEL, " +
                "       GESTION_CENTROS.PROVEEDORES_X_DELEGACIONES PROV " +
                "WHERE DET.PROV_X_DELEGACION_ID = PROV.ID " +
                "       AND PROV.DELEGACION_ID = DEL.ID " +
                "AND PROV.PROVEEDOR_ID = :providerId " +
                "       AND PROV.DELEGACION_ID = :workCenterId ";

        Query query = manager.createNativeQuery(sql)
                .setParameter("providerId", providerId)
                .setParameter("workCenterId", workCenterId);

        String docUrl = query.getSingleResult().toString();

        return docUrl;

    }

    @Override
    public boolean checkProviderCIf(String providerCif) {

        String sql = "" +
                "SELECT COUNT(*)  " +
                "FROM GESTION_CENTROS.PROVEEDORES " +
                "       WHERE LOWER(cif) = LOWER(:providerCif) ";

        Query query = manager.createNativeQuery(sql)
                .setParameter("providerCif", providerCif);

        boolean providerExist = Integer.parseInt(query.getSingleResult().toString()) > 0;

        return providerExist;

    }

}
