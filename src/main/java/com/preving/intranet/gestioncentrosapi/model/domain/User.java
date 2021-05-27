package com.preving.intranet.gestioncentrosapi.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(schema = "GC2006_RELEASE", name = "PC_USUARIOS")
public class User {

    public User(Long id, String username, String firstname, String lastname, String email, String password,
                Boolean enabled, Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public User() {
    }

    private Long id;
    private String username;
    private String salto;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean enabled;
    private Date lastPasswordResetDate;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USUARIO", length = 50, unique = true)
    @NotNull
    @JsonIgnore
    @Size(min = 4, max = 50)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "SALTO", length = 100)
    @NotNull
    @JsonIgnore
    @Size(min = 4, max = 100)
    public String getSalto() {
        return salto;
    }
    public void setSalto(String salto) {
        this.salto = salto;
    }

    @Column(name = "HASHEDPWD", length = 100)
    @NotNull
    @JsonIgnore
    @Size(min = 4, max = 100)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "NOMBRE", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "APELLIDOS", length = 200)
    @NotNull
    @Size(min = 4, max = 50)
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ACTIVO")
    @NotNull
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    @Column(name = "FCH_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
    public void setLastPasswordResetDate(Date lastPasswordResetDate) { this.lastPasswordResetDate = lastPasswordResetDate; }

}
