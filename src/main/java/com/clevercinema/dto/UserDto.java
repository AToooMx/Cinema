package com.clevercinema.dto;

public class UserDto {

	private int id;
	private String name;
	private String surname;
	private String email;
	private int bonuses;
	private Integer countTickets;
	private Double sumSpendMoney;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBonuses() {
		return bonuses;
	}

	public void setBonuses(int bonuses) {
		this.bonuses = bonuses;
	}

	public Integer getCountTickets() {
		return countTickets;
	}

	public void setCountTickets(Integer countTickets) {
		this.countTickets = countTickets;
	}

	public Double getSumSpendMoney() {
		return sumSpendMoney;
	}

	public void setSumSpendMoney(Double sumSpendMoney) {
		this.sumSpendMoney = sumSpendMoney;
	}

}
