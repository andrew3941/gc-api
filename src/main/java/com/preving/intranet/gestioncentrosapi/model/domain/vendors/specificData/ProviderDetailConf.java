package com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ProviderTypes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "CONF_PROVEEDORES_DETALLES")
public class ProviderDetailConf implements Serializable {

    private int id;
    private ProviderTypes providerType = new ProviderTypes();
    private ProviderAttributeType attributeType = new ProviderAttributeType();
    private String label;

    public ProviderDetailConf() { }

    public ProviderDetailConf(int id, ProviderTypes providerType, ProviderAttributeType attributeType, String label) {
        this.id = id;
        this.providerType = providerType;
        this.attributeType = attributeType;
        this.label = label;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_PROVEEDOR_ID", referencedColumnName = "ID")
    @JsonIgnore
    public ProviderTypes getProviderType() { return providerType; }
    public void setProviderType(ProviderTypes providerType) { this.providerType = providerType; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ATRIBUTO_ID", referencedColumnName = "ID")
    public ProviderAttributeType getAttributeType() { return attributeType; }
    public void setAttributeType(ProviderAttributeType attributeType) { this.attributeType = attributeType; }

    @Basic
    @Column(name = "ETIQUETA")
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return "ProviderDetailConf{" +
                "id=" + id +
                ", providerType=" + providerType +
                ", attributeType=" + attributeType +
                ", label='" + label + '\'' +
                '}';
    }

}
