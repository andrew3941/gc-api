package com.preving.intranet.gestioncentrosapi.model.domain.workers;


import java.util.ArrayList;
import java.util.List;

public class WorkersFilter {
    private List<Employees> employees = new ArrayList<>();
    private String card;

    public WorkersFilter() {
    }

    public WorkersFilter(List<Employees> employees, String card) {
        this.employees = employees;
        this.card = card;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
