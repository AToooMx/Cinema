package com.clevercinema.dto;

public class BonuseDto {
	private boolean confirm;

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "BonuseDto [confirm=" + confirm + "]";
	}

}
