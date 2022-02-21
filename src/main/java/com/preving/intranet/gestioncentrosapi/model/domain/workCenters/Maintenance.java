package com.preving.intranet.gestioncentrosapi.model.domain.workCenters;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.ExpenditurePeriod;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.Provider;


import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(schema = "", name = "")
public class Maintenance implements Serializable {


    private int id;
    private MaintenanceTypes maintenanceTypes =   new MaintenanceTypes();
    private Provider provider = new Provider();
    private String billNumber;
    private ExpenditurePeriod expenditurePeriod = new ExpenditurePeriod();
    private int amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date date;
    private String observations;
    private Date created = new Date();
    private User createdBy = new User();
    private Date modified;
    private User modifiedBy;
    private Date deleted;
    private User deletedBy;


    public Maintenance() {
    }


    public Maintenance(int id, MaintenanceTypes maintenanceTypes, Provider provider, String billNumber, ExpenditurePeriod expenditurePeriod, int amount, Date date, String observations, Date created, User createdBy, Date modified, User modifiedBy, Date deleted, User deletedBy) {
        this.id = id;
        this.maintenanceTypes = maintenanceTypes;
        this.provider = provider;
        this.billNumber = billNumber;
        this.expenditurePeriod = expenditurePeriod;
        this.amount = amount;
        this.date = date;
        this.observations = observations;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.deleted = deleted;
        this.deletedBy = deletedBy;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = " ", sequenceName = "", schema = "", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public MaintenanceTypes getMaintenanceTypes() {return maintenanceTypes;}
    public void setMaintenanceTypes(MaintenanceTypes maintenanceTypes) {
        this.maintenanceTypes = maintenanceTypes;}

    @ManyToOne
    @JoinColumn(name = "provider_id")
    public Provider getProvider() {return provider;}
    public void setProvider(Provider provider) {this.provider = provider;}

    public String getBillNumber() {return billNumber;}
    public void setBillNumber(String billNumber) {this.billNumber = billNumber;}

    @ManyToOne
    @JoinColumn(name = "expenditure_period_id")
    public ExpenditurePeriod getExpenditurePeriod() {return expenditurePeriod;}
    public void setExpenditurePeriod(ExpenditurePeriod expenditurePeriod) {this.expenditurePeriod = expenditurePeriod;}

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public String getObservations() {return observations;}
    public void setObservations(String observations) {this.observations = observations;}

    @Basic
    @Column(name = "CREADO")
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() {return createdBy;}
    public void setCreatedBy(User createdBy) {this.createdBy = createdBy;}

    @Basic
    @Column(name = "MODIFICADO")
    public Date getModified() {return modified;}
    public void setModified(Date modified) {this.modified = modified;}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    public User getModifiedBy() {return modifiedBy;}
    public void setModifiedBy(User modifiedBy) {this.modifiedBy = modifiedBy;}

    @Basic
    @Column(name = "BORRADO")
    public Date getDeleted() {return deleted;}
    public void setDeleted(Date deleted) {this.deleted = deleted;}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BORRADO_POR", referencedColumnName = "ID")
    public User getDeletedBy() {return deletedBy;}
    public void setDeletedBy(User deletedBy) {this.deletedBy = deletedBy;}

}