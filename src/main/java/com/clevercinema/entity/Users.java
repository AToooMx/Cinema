package com.clevercinema.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private int id;
    @Column(name = "Email")
    @NotEmpty(message = "Поле не може бути пустим")
    private String email;
    @Column(name = "Password")
    @NotEmpty(message = "Поле не може бути пустим")
    private String password;
    @Column(name = "Enabled")
    private boolean enabled;
    @Column(name = "Date_Change")
    private Date dateChange;
    @Column(name = "Name")
    @NotEmpty(message = "Поле не може бути пустим")
    private String name;
    @Column(name = "Surname")
    @NotEmpty(message = "Поле не може бути пустим")
    private String surname;
    @Column(name = "Bonuses")
    private int bonuse;
    @Column(name = "Phone")
    private Long phone;
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Authorities> authorities = new HashSet<>();

    public Users() {

    }

    public Users(int id, String email, String password, Date dateChange, String name, String surname,
                 int bonuse, Long phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.dateChange = dateChange;
        this.name = name;
        this.surname = surname;
        this.bonuse = bonuse;
        this.phone = phone;
        this.enabled = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBonuse() {
        return bonuse;
    }

    public void setBonuse(int bonuse) {
        this.bonuse = bonuse;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

}
