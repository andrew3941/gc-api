package com.preving.intranet.gestioncentrosapi.model.domain.vehicles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehiclesFilter {
    private Provider vehicleProvider;
    private List<Brands> Brands = new ArrayList<>();
    public int vehicleStatus;
    private String card;


    public VehiclesFilter() {
    }

    public VehiclesFilter(Provider vehicleProvider, List<com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands> brands, int vehicleStatus, String card) {
        this.vehicleProvider = vehicleProvider;
        Brands = brands;
        this.vehicleStatus = vehicleStatus;
        this.card = card;
    }

    public Provider getVehicleProvider() {
        return vehicleProvider;
    }

    public void setVehicleProvider(Provider vehicleProvider) {
        this.vehicleProvider = vehicleProvider;
    }

    public List<com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands> getBrands() {
        return Brands;
    }

    public void setBrands(List<com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands> brands) {
        Brands = brands;
    }

    public int getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(int vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}

