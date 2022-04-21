package com.preving.intranet.gestioncentrosapi.model.domain.maintenance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaintenanceFilter {
    private Provider maintenanceProvider;
    private List<MaintenanceTypes> maintenanceTypes = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date maintenanceStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date maintenanceEndDate;
    public int maintenanceStatus;

    public MaintenanceFilter() {
    }

    public MaintenanceFilter(Provider maintenanceProvider, List<com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes> maintenanceTypes, Date maintenanceStartDate, Date maintenanceEndDate, int maintenanceStatus) {
        this.maintenanceProvider = maintenanceProvider;
      this.maintenanceTypes = maintenanceTypes;
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
        return maintenanceTypes;
    }

    public void setMaintenanceTypes(List<com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceTypes> maintenanceTypes) {
        maintenanceTypes = maintenanceTypes;
    }

    public Date getMaintenanceStartDate() {
        return maintenanceStartDate;
    }

    public void setMaintenanceStartDate(Date maintenanceStartDate) {
        this.maintenanceStartDate = maintenanceStartDate;
    }

    public Date getMaintenanceEndDate() {
        return maintenanceEndDate;
    }

    public void setMaintenanceEndDate(Date maintenanceEndDate) {
        this.maintenanceEndDate = maintenanceEndDate;
    }

    public int getMaintenanceStatus() {return maintenanceStatus;}
    public void setMaintenanceStatus(int maintenanceStatus) {this.maintenanceStatus = maintenanceStatus;}
}
