package com.preving.intranet.gestioncentrosapi.model.domain.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.Province;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderArea;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderTypes;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceFilter {
    private String maintenanceProvider;
    private List<MaintenanceTypes> MaintenanceTypes = new ArrayList<>();
    private String maintenanceStartDate;
    private String maintenanceEndDate;

    public MaintenanceFilter() {
    }

    public MaintenanceFilter(String maintenanceProvider, List<com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes> maintenanceTypes, String maintenanceStartDate, String maintenanceEndDateDate) {
        this.maintenanceProvider = maintenanceProvider;
        MaintenanceTypes = maintenanceTypes;
        this.maintenanceStartDate = maintenanceStartDate;
        this.maintenanceEndDate = maintenanceEndDateDate;
    }

    public String getMaintenanceProvider() {
        return maintenanceProvider;
    }

    public void setMaintenanceProvider(String maintenanceProvider) {
        this.maintenanceProvider = maintenanceProvider;
    }

    public List<com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes> getMaintenanceTypes() {
        return MaintenanceTypes;
    }

    public void setMaintenanceTypes(List<com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes> maintenanceTypes) {
        MaintenanceTypes = maintenanceTypes;
    }

    public String getMaintenanceStartDate() {
        return maintenanceStartDate;
    }

    public void setMaintenanceStartDate(String maintenanceStartDate) {
        this.maintenanceStartDate = maintenanceStartDate;
    }

    public String getMaintenanceEndDate() {
        return maintenanceEndDate;
    }

    public void setMaintenanceEndDate(String maintenanceEndDateDate) {
        this.maintenanceEndDate = maintenanceEndDateDate;
    }
}
