package com.clevercinema.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterDto {
	@NotBlank(message = "Вкажіть ваше ім'я")
	private String name;
	@NotBlank(message = "Вкажіть ваше прізвище")
	private String surname;
	@NotBlank(message = "Вкажіть ваш Email")
	@Email(message = "Вкажіть коректний Email")
	private String email;
	private Long phone;
	@NotBlank(message = "Вкажіть пароль")
	private String password;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
