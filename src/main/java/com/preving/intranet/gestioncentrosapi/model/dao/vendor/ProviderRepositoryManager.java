package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProviderRepositoryManager implements ProviderCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Provider> getProviders(ProviderFilter providerFilter) {
        return null;
    }
}
