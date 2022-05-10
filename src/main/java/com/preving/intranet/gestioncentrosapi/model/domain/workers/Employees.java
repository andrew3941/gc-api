package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "RRHH", name ="EMPLEADOS")
public class Employees implements Serializable {

    private int id;
    private String nif;
    private String nss;
    private String name;
    private String surnames;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date dateBirth;
    private String ccc;
    private String image;
    private int haveImage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date oldDate;
    private User insertBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date insertDate;
    private User updateBy= new  User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date updateDate;
    private String cv;
    private String cvContentType;
    private int cvHas;
    private int variable;
    private RolesEmployees rolesEmployees = new RolesEmployees();
    private String qualificationSerpa;
    private String sex;
    private String theyWerePre;
    private Integer lastYearCongratulations;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date updateIbanFch;
    private int mileageValue;
    private int disability;
    private int socialExclusion;
    private Integer disabilityPct;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date adrUpdated;
    private Date qdrCreated;
    private EmpContacto empContacto;
    private List<EmpLabHistory> empLabHistory;

    public Employees() {
    }

    public Employees(int id, String nif, String nss, String name, String surnames, Date dateBirth, String ccc, String image, int haveImage, Date oldDate, User insertBy, Date insertDate, User updateBy, Date updateDate, String cv, String cvContentType, int cvHas, int variable, RolesEmployees rolesEmployees, String qualificationSerpa, String sex, String theyWerePre, int lastYearCongratulations, Date updateIbanFch, int mileageValue, Integer disability, int socialExclusion, int disabilityPct, Date adrUpdated, Date qdrCreated, EmpContacto empContacto, List<EmpLabHistory> empLabHistory) {
        this.id = id;
        this.nif = nif;
        this.nss = nss;
        this.name = name;
        this.surnames = surnames;
        this.dateBirth = dateBirth;
        this.ccc = ccc;
        this.image = image;
        this.haveImage = haveImage;
        this.oldDate = oldDate;
        this.insertBy = insertBy;
        this.insertDate = insertDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.cv = cv;
        this.cvContentType = cvContentType;
        this.cvHas = cvHas;
        this.variable = variable;
        this.rolesEmployees = rolesEmployees;
        this.qualificationSerpa = qualificationSerpa;
        this.sex = sex;
        this.theyWerePre = theyWerePre;
        this.lastYearCongratulations = lastYearCongratulations;
        this.updateIbanFch = updateIbanFch;
        this.mileageValue = mileageValue;
        this.disability = disability;
        this.socialExclusion = socialExclusion;
        this.disabilityPct = disabilityPct;
        this.adrUpdated = adrUpdated;
        this.qdrCreated = qdrCreated;
        this.empContacto = empContacto;
        this.empLabHistory = empLabHistory;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "EMPLEADOS", sequenceName = "EMPLEADOS", schema = "RRHH", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLEADOS")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Basic
    @Column( name= "NIF")
    public String getNif() {return nif;}
    public void setNif(String nif) {this.nif = nif;}

    @Basic
    @Column(name= "NSS")
    public String getNss() {return nss;}
    public void setNss(String nss) {this.nss = nss;}

    @Basic
    @Column(name= "NOMBRE")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Basic
    @Column(name = "APELLIDOS")
    public String getSurnames() {return surnames;}
    public void setSurnames(String surnames) {this.surnames = surnames;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name = "FCH_NACIMIENTO")
    public Date getDateBirth() {return dateBirth;}
    public void setDateBirth(Date dateBirth) {this.dateBirth = dateBirth;}

    @Basic
    @Column(name = "CCC")
    @JsonIgnore
    public String getCcc() {return ccc;}
    public void setCcc(String ccc) {this.ccc = ccc;}

    @Basic
    @Column(name = "IMAGEN")
    @JsonIgnore
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "TIENE_IMAGEN")
    public int getHaveImage() {return haveImage;}
    public void setHaveImage(int haveImage) {this.haveImage = haveImage;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name = "FCH_ANTIGUEDAD")
    public Date getOldDate() {return oldDate;}
    public void setOldDate(Date oldDate) {this.oldDate = oldDate;}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INSERT_BY", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public User getInsertBy() {return insertBy;}
    public void setInsertBy(User insertBy) {this.insertBy = insertBy;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name= "INSERT_FCH")
    public Date getInsertFch() {return insertDate;}
    public void setInsertFch(Date insertFch) {this.insertDate = insertFch;}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public User getUpdateBy() {return updateBy;}
    public void setUpdateBy(User updateBy) {this.updateBy = updateBy;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name = " UPDATE_FCH")
    public Date getUpdateFch() {return updateDate;}
    public void setUpdateFch(Date updateFch) {this.updateDate = updateFch;}

    @Basic
    @Column(name = "CV")
    @JsonIgnore
    public String getCv() {
        return cv;
    }
    public void setCv(String cv) {
        this.cv = cv;
    }

    @Basic
    @Column(name = "CV_CONTENT_TYPE")
    public String getCvContentType() {return cvContentType;}
    public void setCvContentType(String cvContentType) {this.cvContentType = cvContentType;}

    @Basic
    @Column(name = "CV_TIENE")
    public int getCvHas() {return cvHas;}
    public void setCvHas(int cvHas) {this.cvHas = cvHas;}

    @Basic
    @Column(name = "VARIABLE")
    public int getVariable() {return variable;}
    public void setVariable(int variable) {this.variable = variable;}

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RC_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public RolesEmployees getRolesEmployees() {return rolesEmployees;}
    public void setRolesEmployees(RolesEmployees rolesEmployees) {this.rolesEmployees = rolesEmployees;
    }

    @Basic
    @Column(name = "CUALIFICACION_SERPA")
    public String getQualificationSerpa() {return qualificationSerpa;}
    public void setQualificationSerpa(String qualificationSerpa) {this.qualificationSerpa = qualificationSerpa;}

    @Basic
    @Column(name = "SEXO")
    public String getSex() {return sex;}
    public void setSex(String sex) {this.sex = sex;}


    @Basic
    @Column(name = "IBAN_PRE")
    public String getTheyWerePre() {return theyWerePre;}
    public void setTheyWerePre(String theyWerePre) {this.theyWerePre = theyWerePre;}

    @Basic
    @Column(name = "ULTIMO_ANIO_FELICITACION")
    public Integer getLastYearCongratulations() {return lastYearCongratulations;}
    public void setLastYearCongratulations(Integer lastYearCongratulations) {this.lastYearCongratulations = lastYearCongratulations;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name = "UPDATE_IBAN_FCH")
    public Date getUpdateIbanFch() {return updateIbanFch;}
    public void setUpdateIbanFch(Date updateIbanFch) {this.updateIbanFch = updateIbanFch;}

    @Basic
    @Column(name = "VALOR_KILOMETRAJE")
    public Integer getMileageValue() {
        return mileageValue;
    }
    public void setMileageValue(Integer mileageValue) {
        this.mileageValue = mileageValue;
    }

    @Basic
    @Column(name = "DISCAPACIDAD")
    public int getDisability() {return disability;}
    public void setDisability(int disability) {this.disability = disability;}

    @Basic
    @Column(name = "EXCLUSION_SOCIAL")
    public int getSocialExclusion() {return socialExclusion;}
    public void setSocialExclusion(int socialExclusion) {this.socialExclusion = socialExclusion;}

    @Basic
    @Column(name ="DISCAPACIDAD_PCT")
    public Integer getDisabilityPct() {return disabilityPct;}
    public void setDisabilityPct(Integer disabilityPct) {this.disabilityPct = disabilityPct;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name = "ADR_UPDATED")
    public Date getAdrUpdated() {return adrUpdated;}
    public void setAdrUpdated(Date adrUpdated) {this.adrUpdated = adrUpdated;}

    @Basic
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    @Column(name = "QDR_CREATED")
    public Date getQdrCreated() {
        return qdrCreated;
    }
    public void setQdrCreated(Date qdrCreated) {
        this.qdrCreated = qdrCreated;
    }

    @OneToOne(mappedBy = "employee")
    public EmpContacto getEmpContacto() {
        return empContacto;
    }

    public void setEmpContacto(EmpContacto empContacto) {
        this.empContacto = empContacto;
    }

    @OneToMany(mappedBy = "employee")
    public List<EmpLabHistory> getEmpLabHistory() {
        return empLabHistory;
    }

    public void setEmpLabHistory(List<EmpLabHistory> empLabHistory) {
        this.empLabHistory = empLabHistory;
    }

}
