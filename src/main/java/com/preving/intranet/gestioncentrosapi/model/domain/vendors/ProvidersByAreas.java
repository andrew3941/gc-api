package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.preving.intranet.gestioncentrosapi.model.domain.Entity;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(schema = "GESTION_CENTROS" , name = "PROVEEDORES_X_AREA")
public class ProvidersByAreas implements Serializable {

    private int id;
    private Provider provider = new Provider();
    private ProviderArea providerArea = new ProviderArea();

    public ProvidersByAreas() {
    }

    public ProvidersByAreas(int id, Provider provider, ProviderArea providerArea) {
        this.id = id;
        this.provider = provider;
        this.providerArea = providerArea;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PROVEEDORES_X_AREA_SEQ", sequenceName = "PROVEEDORES_X_AREA_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_X_AREA_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVEEDOR_ID", referencedColumnName = "ID")
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "AREA_ID", referencedColumnName = "ID")
    public ProviderArea getProviderArea() {
        return providerArea;
    }
    public void setProviderArea(ProviderArea providerArea) {
        this.providerArea = providerArea;
    }
}
