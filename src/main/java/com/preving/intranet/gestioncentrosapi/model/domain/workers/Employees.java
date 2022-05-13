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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date oldDate;
    private User insertBy = new User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date insertDate;
    private User updateBy= new  User();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date updateDate;
    private RolesEmployees rolesEmployees = new RolesEmployees();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
    private Date adrUpdated;
    private Date qdrCreated;
    private EmpContacto empContacto;
    private List<EmpLabHistory> empLabHistory;

    public Employees() {
    }

    public Employees(int id, String nif, String nss, String name, String surnames, Date dateBirth, Date oldDate, User insertBy, Date insertDate, User updateBy, Date updateDate, RolesEmployees rolesEmployees, Date adrUpdated, Date qdrCreated, EmpContacto empContacto, List<EmpLabHistory> empLabHistory) {
        this.id = id;
        this.nif = nif;
        this.nss = nss;
        this.name = name;
        this.surnames = surnames;
        this.dateBirth = dateBirth;
        this.oldDate = oldDate;
        this.insertBy = insertBy;
        this.insertDate = insertDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.rolesEmployees = rolesEmployees;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RC_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    public RolesEmployees getRolesEmployees() {return rolesEmployees;}
    public void setRolesEmployees(RolesEmployees rolesEmployees) {this.rolesEmployees = rolesEmployees;
    }

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
