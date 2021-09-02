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
                "SELECT DISTINCT P.ID, P.DELEGACION_ID, P.NOMBRE, P.PROVEEDOR_CENTRALIZADO, P.CIF," +
                " P.TIPO_ID, P.AREA_ID, " +
                "P.TIPO_EVALUACION_ID, " +
                "P.FECHA_INICIO_SERVICIO, P.FECHA_FIN_SERVICIO, P.PERIODICIDAD_GASTO_ID " +
                "FROM GESTION_CENTROS.PROVEEDORES P, " +
                "GC2006_RELEASE.PC_DELEGACIONES WC, " +
                "GESTION_CENTROS.TM_PROVEEDORES_TIPOS PT " +
                "WHERE P.DELEGACION_ID = WC.ID " +
                "AND WC.ID= :workCenterId ";

        if(providerFilter.getProviderTypes().size() > 0){
            sql += " AND PT.ID =:providerTypes ";
        }

//        if(providerFilter != null && providerFilter.getProviderStatus() != 2){
//            sql += "AND P.ACTIVO = :providerStatus ";
//        }

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != ""){
            sql += " AND LOWER(TRANSLATE(P.NOMBRE, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:providerName, '������������', 'aeiounAEIOUN')) ";
        }

        sql += " ORDER BY P.NOMBRE ";

        Query query = manager.createNativeQuery(sql, "ProviderMapping").setParameter("workCenterId", workCenterId);

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != "") {
            query.setParameter("providerName", "%" + providerFilter.getProviderName() + "%");
        }

        if(providerFilter != null && providerFilter.getProviderTypes().size() > 0 && providerFilter.getProviderTypes() != null){
            query.setParameter("providerTypes", providerFilter.getProviderTypes());
        }

//        if(providerFilter != null && providerFilter.getProviderStatus() != 2){
//            query.setParameter("providerStatus", providerFilter.getProviderStatus());
//        }

        List<Provider> providerResults = query.getResultList();

        return providerResults;
    }

}
