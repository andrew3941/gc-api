package com.preving.intranet.gestioncentrosapi.model.domain.vehicles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(schema = "SAC", name ="VE_VEHICULOS")
@SqlResultSetMapping(
        name = "VehiclesMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Vehicles.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "MATRICULA", type = String.class),
                                @ColumnResult(name = "MARCA_ID", type = Integer.class),
                                @ColumnResult(name = "MODELO", type = String.class),
                                @ColumnResult(name = "MODO_COMPRA", type = String.class),
                                @ColumnResult(name = "RESPONSABLE_ID", type = Integer.class),
                                @ColumnResult(name = "FECHA_COMPRA", type = Date.class),
                                @ColumnResult(name = "FECHA_VENCIMIENTO", type = Date.class),
                                @ColumnResult(name = "CUOTA_MENSUAL", type = Integer.class),
                                @ColumnResult(name = "ACTIVO", type = Integer.class),
                                @ColumnResult(name = "MARCA", type = String.class),
                                @ColumnResult(name = "RESPONSABLE_FIRST_NAME", type = String.class),
                                @ColumnResult(name = "RESPONSABLE_LAST_NAME", type = String.class)

                        }
                )
        }
)



public class Vehicles implements Serializable {

    private int id;
    private String enrollment;
    private Brands brands = new Brands();
    private String model;
    private String purchaseMode;
    private Date purchaseDate;
    private Date expirationDate;
    private int price ;
    private String card;
    private Date created = new Date();
    private User createdBy = new User();
    private Date modified;
    private User modifiedBy = new User();
    private WorkCenter workCenter = new WorkCenter();
    private int monthlyFee;
    private User responsibleId = new User();
    private int kilometersMaximum;
    private int excessPrice;
    private int defaultPrice;
    private int active;
    private Date lowDate;
    private int userUnsubscribe;
    private String observationsLow;
    private int profitabilityCoefficient;
    private int inThePropiety;
    private int bail;
    private com.preving.intranet.gestioncentrosapi.model.domain.Entity entityId = new com.preving.intranet.gestioncentrosapi.model.domain.Entity();
    private Date adrUpdated;
    private Date qdrCreated;

    public Vehicles() {
    }


    public Vehicles(int id, String enrollment, Brands brands, String model, String purchaseMode, Date purchaseDate, Date expirationDate, int price, String card, Date created, User createdBy, Date modified, User modifiedBy, WorkCenter delegationId, int monthlyFee, User responsibleId, int kilometersMaximum, int excessPrice, int defaultPrice, int active, Date lowDate, int userUnsubscribe, String observationsLow, int profitabilityCoefficient, int inThePropiety, int bail, com.preving.intranet.gestioncentrosapi.model.domain.Entity entityId, Date adrUpdated, Date qdrCreated) {
        this.id = id;
        this.enrollment = enrollment;
        this.brands = brands;
        this.model = model;
        this.purchaseMode = purchaseMode;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.card = card;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.monthlyFee = monthlyFee;
        this.responsibleId = responsibleId;
        this.kilometersMaximum = kilometersMaximum;
        this.excessPrice = excessPrice;
        this.defaultPrice = defaultPrice;
        this.active = active;
        this.lowDate = lowDate;
        this.userUnsubscribe = userUnsubscribe;
        this.observationsLow = observationsLow;
        this.profitabilityCoefficient = profitabilityCoefficient;
        this.inThePropiety = inThePropiety;
        this.bail = bail;
        this.entityId = entityId;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
    }

    public Vehicles(
            int id,
            String enrollment,
            Integer brand_Id,
            String model,
            String purchaseMode,
            Integer responsibleId,
            Date purchaseDate,
            Date expirationDate,
            int monthlyFee,
            int active,
            String brandName,
            String responsibleFirstName,
            String responsibleLastName) {
        this.id = id;
        this.enrollment = enrollment;
        this.brands.setId(brand_Id);
        this.model = model;
        this.purchaseMode = purchaseMode;
        this.getResponsibleId().setId(Long.valueOf(responsibleId));
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
        this.monthlyFee = monthlyFee;
        this.active = active;
        this.brands.setName(brandName);
        this.getResponsibleId().setFirstname(responsibleFirstName);
        this.getResponsibleId().setLastname(responsibleLastName);
    }

    @Id
    @Column(name = "ID", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @SequenceGenerator(name = "VE_VEHICULOS_SEQ", sequenceName = "VE_VEHICULOS_SEQ", schema = "SAC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VE_VEHICULOS_SEQ")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Basic
    @Column(name = "MATRICULA")
    public String getEnrollment() {return enrollment;}
    public void setEnrollment(String enrollment) {this.enrollment = enrollment;}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MARCA_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public Brands getBrands() {return brands;}
    public void setBrands(Brands brands) {this.brands = brands;}

    @Basic
    @Column(name = "MODELO")
    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    @Basic
    @Column(name = "MODO_COMPRA")
    public String getPurchaseMode() {return purchaseMode;}
    public void setPurchaseMode(String purchaseMode) {this.purchaseMode = purchaseMode;}

    @Basic
    @Column(name = "FECHA_COMPRA")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getPurchaseDate() {return purchaseDate;}
    public void setPurchaseDate(Date purchaseDate) {this.purchaseDate = purchaseDate;}

    @Basic
    @Column(name = "FECHA_VENCIMIENTO")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getExpirationDate() {return expirationDate;}
    public void setExpirationDate(Date expirationDate) {this.expirationDate = expirationDate;}

    @Basic
    @Column(name = "PRECIO")
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    @Basic
    @Column(name = "TARJETA")
    public String getCard() {return card;}
    public void setCard(String card) {this.card = card;}

    @Basic
    @Column(name = "CREADO")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CREADO_POR", referencedColumnName = "ID")
    public User getCreatedBy() {return createdBy;}
    public void setCreatedBy(User createdBy) {this.createdBy = createdBy;}

    @Basic
    @Column(name = "MODIFICADO")
    public Date getModified() {return modified;}
    public void setModified(Date modified) {this.modified = modified;}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODIFICADO_POR", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public User getModifiedBy() {return modifiedBy;}
    public void setModifiedBy(User modifiedBy) {this.modifiedBy = modifiedBy;}


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELEGACION_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public WorkCenter getworkCenter() {return workCenter;}
    public void setworkCenter(WorkCenter workCenter) {this.workCenter = workCenter;}


    @Basic
    @Column(name = "CUOTA_MENSUAL")
    public int getMonthlyFee() {return monthlyFee;}
    public void setMonthlyFee(int monthlyFee) {this.monthlyFee = monthlyFee;}

    //TODO its is a relationship
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESPONSABLE_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public User getResponsibleId() {return responsibleId;}
    public void setResponsibleId(User responsibleId) {this.responsibleId = responsibleId;}

    @Basic
    @Column(name = "KILOMETROS_MAXIMO")
    public int getKilometersMaximum() {return kilometersMaximum;}
    public void setKilometersMaximum(int kilometersMaximum) {this.kilometersMaximum = kilometersMaximum;}

    @Basic
    @Column(name = "PRECIO_EXCESO")
    public int getExcessPrice() {return excessPrice;}
    public void setExcessPrice(int excessPrice) {this.excessPrice = excessPrice;}

    @Basic
    @Column(name = "PRECIO_DEFECTO")
    public int getDefaultPrice() {return defaultPrice;}
    public void setDefaultPrice(int defaultPrice) {this.defaultPrice = defaultPrice;}

    @Basic
    @Column(name = "ACTIVO")
    public int getActive() {return active;}
    public void setActive(int active) {this.active = active;}

    @Basic
    @Column(name = "FECHA_BAJA")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getLowDate() {return lowDate;}
    public void setLowDate(Date lowDate) {this.lowDate = lowDate;}

    @Basic
    @Column(name = "USUARIO_BAJA")
    public int getUserUnsubscribe() {return userUnsubscribe;}
    public void setUserUnsubscribe(int userUnsubscribe) {this.userUnsubscribe = userUnsubscribe;}

    @Basic
    @Column(name = "OBSERVACIONES_BAJA")
    public String getObservationsLow() {return observationsLow;}
    public void setObservationsLow(String observationsLow) {this.observationsLow = observationsLow;}

    @Basic
    @Column(name = "COEFICIENTE_RENTABILIDAD")
    public int getProfitabilityCoefficient() {return profitabilityCoefficient;}
    public void setProfitabilityCoefficient(int profitabilityCoefficient) {
        this.profitabilityCoefficient = profitabilityCoefficient;}

    @Basic
    @Column(name = "EN_PROPIEDAD")
    public int getInThePropiety() {return inThePropiety;}
    public void setInThePropiety(int inThePropiety) {this.inThePropiety = inThePropiety;}

    @Basic
    @Column(name = "FIANZA")
    public int getBail() {return bail;}
    public void setBail(int bail) {this.bail = bail;}

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ENTIDAD_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public com.preving.intranet.gestioncentrosapi.model.domain.Entity getEntityId() {
        return entityId;}
    public void setEntityId(com.preving.intranet.gestioncentrosapi.model.domain.Entity entityId) {
        this.entityId = entityId;}

    @Basic
    @Column(name = "ADR_UPDATED")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getAdrUpdated() {return adrUpdated;}
    public void setAdrUpdated(Date adrUpdated) {this.adrUpdated = adrUpdated;}

    @Basic
    @Column(name = "QDR_CREATED")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    public Date getQdrCreated() {return qdrCreated;}
    public void setQdrCreated(Date qdrCreated) {this.qdrCreated = qdrCreated;}
}
