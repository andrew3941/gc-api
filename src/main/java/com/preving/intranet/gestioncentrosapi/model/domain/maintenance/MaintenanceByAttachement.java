package com.preving.intranet.gestioncentrosapi.model.domain.maintenance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "GESTION_CENTROS", name = "MANTENIMIENTOS_X_ADJUNTOS")
public class MaintenanceByAttachement implements Serializable {
    private int id;
    private Maintenance maintenance;
    private String documentUrl;
    private String docName;
    private String documentContentType;

    public MaintenanceByAttachement() {
    }


    public MaintenanceByAttachement(int id, Maintenance maintenance, String documentUrl, String docName, String documentContentType) {
        this.id = id;
        this.maintenance = maintenance;
        this.documentUrl = documentUrl;
        this.docName = docName;
        this.documentContentType = documentContentType;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "MANTENIMIENTOS_X_ADJUNTOS_SEQ", sequenceName = "MANTENIMIENTOS_X_ADJUNTOS_SEQ", schema = "GESTION_CENTROS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANTENIMIENTOS_X_ADJUNTOS_SEQ")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}


    @Basic
    @Column(name = "DOC_URL")
    public String getDocumentUrl() {
        return documentUrl;
    }
    public void setDocumentUrl(String documentUrl) {this.documentUrl = documentUrl;
    }

    @Basic
    @Column(name = "DOC_NOMBRE")
    public String getDocName() {return docName;}
    public void setDocName(String docName) {this.docName = docName;}

    @Basic
    @Column(name = "DOC_CONTENT_TYPE")
    public String getDocumentContentType() {return documentContentType;}
    public void setDocumentContentType(String documentContentType) {this.documentContentType = documentContentType;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MANTENIMIENTO_ID", referencedColumnName="id")
    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }
}
