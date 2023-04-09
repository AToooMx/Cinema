package com.clevercinema.dto;

public class ChangePasswordDto {

	private String oldPassword;
	private String newPassword;
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
