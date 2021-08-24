package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.*;

import java.util.List;

public interface ProviderService {

    List<Provider> getproviders(int workCenterId, ProviderFilter providerFilter);

    List<ProviderTypes> getProviderTypes(int workCenterId);

    List<ProviderArea> getProviderArea(int workCenterId);

    List<ProviderEvaluationTypes> getProviderEvaluationTypes(int workCenterId);

    List<ExpenditurePeriod> getExpenditurePeriod(int workCenterId);

}
