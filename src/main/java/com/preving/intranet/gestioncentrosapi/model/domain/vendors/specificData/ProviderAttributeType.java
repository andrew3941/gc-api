package com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "TM_TIPOS_ATRIBUTOS")
public class ProviderAttributeType implements Serializable {

    private int id;
    private String component;
    private String type;
    private String dataType;

    public ProviderAttributeType() { }

    public ProviderAttributeType(int id, String component, String type, String dataType) {
        this.id = id;
        this.component = component;
        this.type = type;
        this.dataType = dataType;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Basic
    @Column(name = "COMPONENTE")
    public String getComponent() { return component; }
    public void setComponent(String component) { this.component = component; }

    @Basic
    @Column(name = "TIPO")
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Basic
    @Column(name = "TIPO_DATO")
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }

    @Override
    public String toString() {
        return "ProviderAttributeType{" +
                "id=" + id +
                ", component='" + component + '\'' +
                ", type='" + type + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }

}
