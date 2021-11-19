package com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData;

import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProvidersByWorkCenters;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "PROVEEDORES_DETALLES")
public class ProviderDetail implements Serializable {

    private int id;
    private ProvidersByWorkCenters providersByWorkCenters = new ProvidersByWorkCenters();
    private ProviderDetailConf providerDetailConf = new ProviderDetailConf();
    private String providerDetailValue;

    public ProviderDetail() { }

    public ProviderDetail(int id, ProvidersByWorkCenters providersByWorkCenters, ProviderDetailConf providerDetailConf,
                          String providerDetailValue) {
        this.id = id;
        this.providersByWorkCenters = providersByWorkCenters;
        this.providerDetailConf = providerDetailConf;
        this.providerDetailValue = providerDetailValue;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PROVEEDORES_DETALLES_SEQ", sequenceName = "PROVEEDORES_DETALLES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_DETALLES_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROV_DEL_ID", referencedColumnName = "ID")
    public ProvidersByWorkCenters getProvidersByWorkCenters() { return providersByWorkCenters; }
    public void setProvidersByWorkCenters(ProvidersByWorkCenters providersByWorkCenters) { this.providersByWorkCenters = providersByWorkCenters; }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONF_PROV_DETALLE_ID", referencedColumnName = "ID")
    public ProviderDetailConf getProviderDetailConf() { return providerDetailConf; }
    public void setProviderDetailConf(ProviderDetailConf providerDetailConf) { this.providerDetailConf = providerDetailConf; }

    @Basic
    @Column(name = "CONF_PROV_DETALLE_VALOR")
    public String getProviderDetailValue() { return providerDetailValue; }
    public void setProviderDetailValue(String providerDetailValue) { this.providerDetailValue = providerDetailValue; }

    @Override
    public String toString() {
        return "ProviderDetail{" +
                "id=" + id +
                ", providersByWorkCenters=" + providersByWorkCenters +
                ", providerDetailConf=" + providerDetailConf +
                ", providerDetailValue='" + providerDetailValue + '\'' +
                '}';
    }

}
