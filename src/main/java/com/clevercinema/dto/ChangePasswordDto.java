package com.clevercinema.dto;

import javax.validation.constraints.NotBlank;

public class ChangePasswordDto {

	@NotBlank(message = "Вкажіть старий пароль")
	private String oldPassword;
	@NotBlank(message = "Вкажіть новий пароль")
	private String newPassword;
	@NotBlank(message = "Повторіть новий пароль")
	private String reNewPassword;

	public ChangePasswordDto(String oldPassword, String newPassword, String reNewPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.reNewPassword = reNewPassword;
	}

	public ChangePasswordDto() {

	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordDto [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", reNewPassword="
				+ reNewPassword + "]";
	}

}
