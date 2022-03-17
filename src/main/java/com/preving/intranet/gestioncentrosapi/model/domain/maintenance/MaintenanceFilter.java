package com.preving.intranet.gestioncentrosapi.model.domain.maintenance;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceFilter {
    private Provider maintenanceProvider;
    private List<MaintenanceTypes> MaintenanceTypes = new ArrayList<>();
    private String maintenanceStartDate;
    private String maintenanceEndDate;

    public MaintenanceFilter() {
    }

    public MaintenanceFilter(Provider maintenanceProvider, List<com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes> maintenanceTypes, String maintenanceStartDate, String maintenanceEndDate) {
        this.maintenanceProvider = maintenanceProvider;
        MaintenanceTypes = maintenanceTypes;
        this.maintenanceStartDate = maintenanceStartDate;
        this.maintenanceEndDate = maintenanceEndDate;
    }

    public Provider getMaintenanceProvider() {
        return maintenanceProvider;
    }
    public void setMaintenanceProvider(Provider maintenanceProvider) {
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
