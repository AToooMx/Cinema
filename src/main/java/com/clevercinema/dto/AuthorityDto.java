package com.clevercinema.dto;

public class AuthorityDto {
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AuthorityDto [role=" + role + "]";
	}

}
