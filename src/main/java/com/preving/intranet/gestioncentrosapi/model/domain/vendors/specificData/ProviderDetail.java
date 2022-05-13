package com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "PROVEEDORES_DETALLES")
public class ProviderDetail implements Serializable {

    private int id;
    private int providersByWorkCentersId;
    private ProviderDetailConf providerDetailConf = new ProviderDetailConf();
    private String providerDetailValue;
    private boolean active;

    public ProviderDetail() { }

    public ProviderDetail(int id, int providersByWorkCentersId, ProviderDetailConf providerDetailConf,
                          String providerDetailValue) {
        this.id = id;
        this.providersByWorkCentersId = providersByWorkCentersId;
        this.providerDetailConf = providerDetailConf;
        this.providerDetailValue = providerDetailValue;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PROVEEDORES_DETALLES_SEQ", sequenceName = "PROVEEDORES_DETALLES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_DETALLES_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "PROV_DEL_ID")
    public int getProvidersByWorkCentersId() { return providersByWorkCentersId; }
    public void setProvidersByWorkCentersId(int providersByWorkCentersId) { this.providersByWorkCentersId = providersByWorkCentersId; }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONF_PROV_DETALLE_ID", referencedColumnName = "ID")
    public ProviderDetailConf getProviderDetailConf() { return providerDetailConf; }
    public void setProviderDetailConf(ProviderDetailConf providerDetailConf) { this.providerDetailConf = providerDetailConf; }

    @Basic
    @Column(name = "CONF_PROV_DETALLE_VALOR")
    public String getProviderDetailValue() { return providerDetailValue; }
    public void setProviderDetailValue(String providerDetailValue) { this.providerDetailValue = providerDetailValue; }

    @Column(name = "activo")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProviderDetail{" +
                "id=" + id +
                ", providersByWorkCentersId=" + providersByWorkCentersId +
                ", providerDetailConf=" + providerDetailConf +
                ", providerDetailValue='" + providerDetailValue + '\'' +
                '}';
    }

}
