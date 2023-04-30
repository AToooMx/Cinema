package com.clevercinema.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Contact {
	@NotBlank(message = "Вкажіть ваше ім'я")
	private String name;
	@NotBlank(message = "Вкажіть ваш Email")
	@Email(message = "Вкажіть коректний Email")
	private String email;
	private String phone;
	@NotBlank(message = "Вкажіть тему повідомлення")
	private String title;
	@NotBlank(message = "Введіть повідомлення")
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
