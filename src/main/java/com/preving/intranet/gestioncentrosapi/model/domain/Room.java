package com.preving.intranet.gestioncentrosapi.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "GESTION_CENTROS", name = "PC_DELEGACIONES_X_SALAS")
public class Room implements Serializable {
    private int id;
    private WorkCenter workCenter = new WorkCenter();
    private String name;
//    private RoomTypes type;
    private int surface;
    private String observation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date created = new Date();
    private User createdBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date modified;
    private User modifiedBy;
    private Date deleted;
    private User deletedBy;

    public Room(){

    }

    public Room(int id, WorkCenter workCenter, String name, int surface, String observation, Date created, User createdBy, Date modified, User modifiedBy, Date deleted, User deletedBy) {
        this.id = id;
        this.workCenter = workCenter;
        this.name = name;
//        this.type = type;
        this.surface = surface;
        this.observation = observation;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.deleted = deleted;
        this.deletedBy = deletedBy;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_SALAS_SEQ", sequenceName = "PC_DELEGACIONES_X_SALAS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_SALAS_SEQ")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() { return workCenter; }
    public void setWorkCenter(WorkCenter workCenter) { this.workCenter = workCenter; }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
//    public RoomTypes getType() {
//        return type;
//    }
//    public void setType(RoomTypes type) {
//        this.type = type;
//    }

    @Basic
    @Column(name = "SUPERFICIE")
    public int getSurface() { return surface; }
    public void setSurface(int surface) { this.surface = surface; }

    @Basic
    @Column(name = "OBSERVACIONES")
    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    @Basic
    @Column(name = "CREADO")
    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created;}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    @Basic
    @Column(name = "MODIFICADO")
    public Date getModified() { return modified; }
    public void setModified(Date modified) { this.modified = modified; }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(User modifiedBy) { this.modifiedBy = modifiedBy; }

    @Basic
    @Column(name = "BORRADO")
    public Date getDeleted() { return deleted;  }
    public void setDeleted(Date deleted) { this.deleted = deleted; }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BORRADO_POR", referencedColumnName = "ID")
    public User getDeletedBy() { return deletedBy; }
    public void setDeletedBy(User deletedBy) { this.deletedBy = deletedBy; }
}
