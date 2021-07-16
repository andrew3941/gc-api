package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.preving.intranet.gestioncentrosapi.model.domain.Entity;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(schema = "GESTION_CENTROS" , name = "PC_DELEGACIONES_X_ENTIDADES")
public class WorkCentersByEntity implements Serializable {

    private int id;
    private WorkCenter workCenter = new WorkCenter();
    private Entity entity = new Entity();


    public WorkCentersByEntity() {
    }

    public WorkCentersByEntity(int id, WorkCenter workCenter, Entity entity) {
        this.id = id;
        this.workCenter = workCenter;
        this.entity = entity;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_ENTIDADES_SEQ", sequenceName = "PC_DELEGACIONES_X_ENTIDADES_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_ENTIDADES_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
//    @Required
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
//    @NotNull
    public WorkCenter getWorkCenter() { return workCenter; }
    public void setWorkCenter(WorkCenter workCenter) { this.workCenter = workCenter; }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
//    @Required
    @JoinColumn(name = "ENTIDAD_ID", referencedColumnName = "ID")
//    @NotNull
    public Entity getEntity() { return entity; }
    public void setEntity(Entity entity) { this.entity = entity; }

}
