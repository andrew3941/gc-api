package com.preving.intranet.gestioncentrosapi.model.domain.vehicles;



import java.util.ArrayList;
import java.util.List;

public class VehiclesFilter {
    private List<Brands> vehicleBrandTypes = new ArrayList<>();;
    private String card;
    private String model;
    private int vehiclesStatus;

    public VehiclesFilter() {
    }


    public VehiclesFilter(List<Brands> vehicleBrandTypes, String card, String model, int vehiclesStatus) {
        this.vehicleBrandTypes = vehicleBrandTypes;
        this.card = card;
        this.model = model;
        this.vehiclesStatus = vehiclesStatus;
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

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    public int getVehiclesStatus() {return vehiclesStatus;}
    public void setVehiclesStatus(int vehiclesStatus) {this.vehiclesStatus = vehiclesStatus;}
}

