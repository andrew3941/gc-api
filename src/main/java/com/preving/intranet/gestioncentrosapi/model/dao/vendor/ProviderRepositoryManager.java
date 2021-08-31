package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
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

    @Override
    public List<Provider> getProviders( int workCenterId, ProviderFilter providerFilter) {

        String sql = "" +
                "SELECT DISTINCT P.ID, P.DELEGACION_ID, P.NOMBRE, P.PROVEEDOR_CENTRALIZADO, P.CIF, P.TIPO_ID, P.AREA_ID, P.TIPO_EVALUACION_ID, " +
                "   P.FECHA_INICIO_SERVICIO, P.FIN_INICIO_SERVICIO, P.PERIODICADAD_GASTO_ID " +
                "FROM GESTION_CENTROS.PROVEEDORES P, " +
               "GC2006_RELEASE.PC_DELEGACIONES WC " +
                "AND P.DELEGACION_ID = WC.ID ";

        if(providerFilter.getProviderTypes().size() > 0){
            sql += "GESTION_CENTROS.PROVEEDORES.TM_PROVEEDORES_TIPOS PT";
        }

        if(providerFilter.getProviderTypes().size() > 0) {
            String providerTypes = providerFilter.getProviderTypes().stream().map(pt -> String.valueOf(pt.getId())).collect(Collectors.joining(","));
            sql += "AND P.ID = PT.PROVIDER_ID" +
                    "AND PT_ID IN (" + providerTypes + ")";
        }

        if(providerFilter != null && providerFilter.getProviderStatus() != 2){
            sql += "AND P.ACTIVO = :providerStatus ";
        }

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != ""){
            sql += " AND LOWER(TRANSLATE(WC.NOMBRE, '������������', 'aeiounAEIOUN')) LIKE LOWER(TRANSLATE(:providerName, '������������', 'aeiounAEIOUN')) ";
        }

        sql += "ORDER BY P.NOMBRE";

        Query query = manager.createNativeQuery(sql, "ProviderMapping");

        if(providerFilter != null && providerFilter.getProviderName() != null && providerFilter.getProviderName() != "") {
            query.setParameter("providerName", "%" + providerFilter.getProviderName() + "%");
        }

        if(providerFilter != null && providerFilter.getProviderTypes() != null){
            query.setParameter("providerTypes", providerFilter.getProviderTypes());
        }

        if(providerFilter != null && providerFilter.getProviderStatus() != 2){
            query.setParameter("providerStatus", providerFilter.getProviderStatus());
        }

        List<Provider> providerResults = query.getResultList();

        return providerResults;
    }

}
