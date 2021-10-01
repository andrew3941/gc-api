package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import java.util.ArrayList;
import java.util.List;

public class ProviderFilter  {

    private String providerName;
    private List<ProviderTypes> providerTypes = new ArrayList<>();
    private List<ProviderArea> areaTypes = new ArrayList<>();
    private List<Province> provinces = new ArrayList<>();
    private List<WorkCenter> centers = new ArrayList<>();
    private int providerStatus;

    public ProviderFilter() {
    }

    public ProviderFilter(String providerName, List<ProviderTypes> providerTypes, List<ProviderArea> areaTypes, List<Province> provinces, List<WorkCenter> centers, int providerStatus) {
        this.providerName = providerName;
        this.providerTypes = providerTypes;
        this.areaTypes = areaTypes;
        this.provinces = provinces;
        this.centers = centers;
        this.providerStatus = providerStatus;
    }

    public String getProviderName() {
        return providerName;
    }
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<ProviderTypes> getProviderTypes() {
        return providerTypes;
    }
    public void setProviderTypes(List<ProviderTypes> providerTypes) {
        this.providerTypes = providerTypes;
    }

    public List<ProviderArea> getAreaTypes() {
        return areaTypes;
    }
    public void setAreaTypes(List<ProviderArea> areaTypes) {
        this.areaTypes = areaTypes;
    }

    public List<Province> getProvinces() {
        return provinces;
    }
    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<WorkCenter> getCenters() {
        return centers;
    }
    public void setCenters(List<WorkCenter> centers) {
        this.centers = centers;
    }

    public int getProviderStatus() {
        return providerStatus;
    }
    public void setProviderStatus(int providerStatus) {
        this.providerStatus = providerStatus;
    }

}
