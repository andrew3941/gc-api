package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ProviderRepositoryManager implements ProviderCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Provider> getProviders( Integer workCenterId, ProviderFilter providerFilter) {

        String sql = "" +
                "SELECT DISTINCT P.ID, P.DELEGACION_ID, WC.NOMBRE AS WORK_CENTER, P.NOMBRE, P.CIF, " +
                "P.DOC_NOMBRE, P.DOC_CONTENT_TYPE, P.ACTIVO, " +
                "P.TIPO_ID, P.AREA_ID, P.LOCALIDAD_ID, L.LOC_NOMBRE,V.PRV_NOMBRE, " +
                "PT.DENOMINACION AS PROVIDER_TYPE,  PA.DENOMINACION AS PROVIDERAREA_TYPE, " +
                "P.TIPO_EVALUACION_ID, PET.DENOMINACION AS PROVIDEREVALUATION_TYPE, " +
                "P.FECHA_INICIO_SERVICIO " +
                "FROM GESTION_CENTROS.PROVEEDORES P, " +
                "GC2006_RELEASE.PC_DELEGACIONES WC, " +
                "GESTION_CENTROS.TM_PROVEEDORES_TIPOS PT, " +
                "GESTION_CENTROS.TM_PROVEEDORES_AREAS PA, " +
                "GESTION_CENTROS.TM_PERIODICIDAD_GASTO PG, " +
                "GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO PET, "+
                "VIG_SALUD.LOCALIDADES L, "+
                "VIG_SALUD.PROVINCIAS V "+
                "WHERE P.DELEGACION_ID = WC.ID " +
                "AND WC.ID= :workCenterId " +
                "AND P.TIPO_ID = PT.ID "+
                "AND P.AREA_ID = PA.ID "+
                "AND P.TIPO_EVALUACION_ID = PET.ID "+
                "AND P.LOCALIDAD_ID = L.LOC_ID " +
                "AND L.LOC_PRV_COD = V.PRV_COD ";

        if(providerFilter != null && providerFilter.getProviderTypes().size() != 0){
           sql += "AND PT.ID =:providerTypes ";
        }

        if(providerFilter != null && providerFilter.getAreaTypes().size() != 0){
            sql += "AND PA.ID =:areaTypes ";
        }

        if(providerFilter != null && providerFilter.getCenters().size() != 0){
            sql += "AND WC.ID =:workCenterId ";
        }
        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != ""){
            sql += " AND LOWER(TRANSLATE(P.NOMBRE, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:providerName, '������������', 'aeiounAEIOUN')) ";
        }

        if(providerFilter != null && providerFilter.getProviderStatus() != 2) {
            sql += " AND P.ACTIVO = :providerStatus ";
        }

        sql += " ORDER BY P.NOMBRE ";

        Query query = manager.createNativeQuery(sql, "ProviderMapping").setParameter("workCenterId", workCenterId);

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != "") {
            query.setParameter("providerName", "%" + providerFilter.getProviderName() + "%");
        }

        if(providerFilter != null && providerFilter.getProviderTypes().size() != 0 && providerFilter.getProviderTypes() != null){
            query.setParameter("providerTypes", providerFilter.getProviderTypes());
        }

        if(providerFilter != null && providerFilter.getAreaTypes().size() != 0 && providerFilter.getAreaTypes() != null){
            query.setParameter("areaTypes", providerFilter.getAreaTypes());
        }

        if(providerFilter != null && providerFilter.getCenters().size() != 0 && providerFilter.getCenters() != null){
            query.setParameter("workCenterId", providerFilter.getCenters());
        }

        if(providerFilter != null && providerFilter.getProviderStatus() != 2) {
            query.setParameter("providerStatus", providerFilter.getProviderStatus() == 1);
        }

        List<Provider> providerResults = query.getResultList();

        return providerResults;
    }

}
