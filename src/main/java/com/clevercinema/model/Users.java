package com.clevercinema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID")
	private int id;
	@Column(name = "Email")
	@NotEmpty(message = "Поле не може бути пустим")
	private String email;
	@Column(name = "Password")
	@NotEmpty(message = "Поле не може бути пустим")
	private String password;
	@Column(name = "Roles")
	private String role;
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

	public Users() {

	}

	public Users(int id, String email, String password, String role, Date dateChange, String name, String surname,
			int bonuse, Long phone) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", enabled="
				+ enabled + ", dateChange=" + dateChange + ", name=" + name + ", surname=" + surname + ", bonuse="
				+ bonuse + ", phone=" + phone + "]";
	}

}
