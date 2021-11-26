package com.preving.intranet.gestioncentrosapi.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "GESTION_CENTROS", name = "PC_DELEGACIONES_X_PLANOS")
public class Drawing implements Serializable {
   private int id;
   private WorkCenter workCenter = new WorkCenter();
   private String name;
   private String docUrl;
   private String docName;
   private String docContentType;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
   private Date created = new Date();
   private User createdBy = new User();
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
   private Date modified;
   private User modifiedBy;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
   private Date deleted;
   private User deletedBy;


    public Drawing() {}

    public Drawing(int id, WorkCenter workCenter, String name, String docUrl, String docName, String docContentType, Date created, User createdBy, Date modified, User modifiedBy, Date deleted, User deletedBy) {
        this.id = id;
        this.workCenter = workCenter;
        this.name = name;
        this.docUrl = docUrl;
        this.docName = docName;
        this.docContentType = docContentType;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.deleted = deleted;
        this.deletedBy = deletedBy;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PC_DELEGACIONES_X_PLANOS_SEQ", sequenceName = "PC_DELEGACIONES_X_PLANOS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_DELEGACIONES_X_PLANOS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    public WorkCenter getWorkCenter() {
        return workCenter;
    }
    public void setWorkCenter(WorkCenter workCenter) {
        this.workCenter = workCenter;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DOC_URL")
    public String getDocUrl() {
        return docUrl;
    }
    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    @Basic
    @Column(name = "DOC_NOMBRE")
    public String getDocName() {
        return docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Basic
    @Column(name = "DOC_CONTENT_TYPE")
    public String getDocContentType() {
        return docContentType;
    }
    public void setDocContentType(String docContentType) {
        this.docContentType = docContentType;
    }

    @Basic
    @Column(name = "CREADO")
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "MODIFICADO")
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Basic
    @Column(name = "BORRADO")
    public Date getDeleted() {
        return deleted;
    }
    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BORRADO_POR", referencedColumnName = "ID")
    public User getDeletedBy() {
        return deletedBy;
    }
    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }
}