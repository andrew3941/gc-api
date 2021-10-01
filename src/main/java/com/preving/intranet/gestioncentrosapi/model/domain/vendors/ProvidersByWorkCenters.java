package com.preving.intranet.gestioncentrosapi.model.domain.vendors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(schema = "GESTION_CENTROS" , name = "PROVEEDORES_X_DELEGACIONES")
public class ProvidersByWorkCenters implements Serializable {

    private int id;
    private Provider provider = new Provider();
    private WorkCenter workCenter = new WorkCenter();

    public ProvidersByWorkCenters() {
    }

    public ProvidersByWorkCenters(int id, Provider provider, WorkCenter workCenter) {
        this.id = id;
        this.provider = provider;
        this.workCenter = workCenter;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PROVEEDORES_X_DELEGACIONES_SEQ", sequenceName = "PROVEEDORES_X_DELEGACIONES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVEEDORES_X_DELEGACIONES_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVEEDOR_ID", referencedColumnName = "ID")
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() { return workCenter; }
    public void setWorkCenter(WorkCenter workCenter) { this.workCenter = workCenter; }

}
