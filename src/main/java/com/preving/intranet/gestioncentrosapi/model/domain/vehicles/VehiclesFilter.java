package com.preving.intranet.gestioncentrosapi.model.domain.vehicles;



import java.util.ArrayList;
import java.util.List;

public class VehiclesFilter {
    private List<Brands> vehicleBrandTypes = new ArrayList<>();;
    private String card;

    public VehiclesFilter() {
    }

    public VehiclesFilter(List<Brands> vehicleBrandTypes, String card) {
        this.vehicleBrandTypes = vehicleBrandTypes;
        this.card = card;
    }

    public List<Brands> getVehicleBrandTypes() {
        return vehicleBrandTypes;
    }
    public void setVehicleBrandTypes(List<Brands> vehicleBrandTypes) {
        this.vehicleBrandTypes = vehicleBrandTypes;
    }

    public String getCard() {
        return card;
    }
    public void setCard(String card) {
        this.card = card;
    }
}

