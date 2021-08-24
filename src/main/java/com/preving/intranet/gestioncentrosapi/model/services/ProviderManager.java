package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vendor.ExpenditurePeriodRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.vendor.ProviderAreaRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.vendor.ProviderEvaluationTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.vendor.ProviderTypesRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderManager implements ProviderService {

   @Autowired
   private ProviderTypesRepository providerTypesRepository;

    @Autowired
    private ProviderAreaRepository providerAreaRepository;

    @Autowired
    private ProviderEvaluationTypesRepository providerEvaluationTypesRepository;

    @Autowired
    private ExpenditurePeriodRepository expenditurePeriodRepository;

   @Override
    public List<Provider> getproviders(int workCenterId, ProviderFilter providerFilter) {
    return null;
    }

    @Override
    public List<ProviderTypes> getProviderTypes(int workCenterId) {
    return providerTypesRepository.findAll(); }

    @Override
    public List<ProviderArea> getProviderArea(int workCenterId) {  return providerAreaRepository.findAll(); }

    @Override
    public List<ProviderEvaluationTypes> getProviderEvaluationTypes(int workCenterId) { return providerEvaluationTypesRepository.findAll(); }

    @Override
    public List<ExpenditurePeriod> getExpenditurePeriod(int workCenterId) { return expenditurePeriodRepository.findAll(); }


}
