package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import java.util.ArrayList;
import java.util.List;

public class ProviderFilter  {

    private String providerName;
    private int providerStatus;
    private List<ProviderTypes> providerTypes = new ArrayList<>();

    public ProviderFilter() {
    }

    public ProviderFilter(String providerName, int providerStatus, List<ProviderTypes> providerTypes) {
        this.providerName = providerName;
        this.providerStatus = providerStatus;
        this.providerTypes = providerTypes;
    }

    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public int getProviderStatus() { return providerStatus; }
    public void setProviderStatus(int providerStatus) { this.providerStatus = providerStatus; }

    public List<ProviderTypes> getProviderTypes() { return providerTypes; }
    public void setProviderTypes(List<ProviderTypes> providerTypes) { this.providerTypes = providerTypes; }

}
