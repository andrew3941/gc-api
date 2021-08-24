package com.preving.intranet.gestioncentrosapi.model.dao.vendor;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderFilter;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface ProviderCustomRepository  {

    List<Provider> getProviders(ProviderFilter providerFilter);

}
