package com.clevercinema.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PaymentDto {

	@NotNull
	@Min(value = 1000)
	@Max(value = 9999)
	private Integer firstFourDigit;
	@NotNull
	@Min(value = 1000)
	@Max(value = 9999)
	private Integer secondFourDigit;
	@NotNull
	@Min(value = 1000)
	@Max(value = 9999)
	private Integer thirdFourDigit;
	@NotNull
	@Min(value = 1000)
	@Max(value = 9999)
	private Integer lastFourDigit;
	@NotNull
	@Positive
	private Integer year;
	@NotNull
	@Positive
	private Integer month;
	@NotNull
	@Min(value = 100)
	@Max(value = 999)
	@Positive
	private Integer cvv;
	private boolean useBonuse;

	public Integer getFirstFourDigit() {
		return firstFourDigit;
	}

	public void setFirstFourDigit(Integer firstFourDigit) {
		this.firstFourDigit = firstFourDigit;
	}

	public Integer getSecondFourDigit() {
		return secondFourDigit;
	}

	public void setSecondFourDigit(Integer secondFourDigit) {
		this.secondFourDigit = secondFourDigit;
	}

	public Integer getThirdFourDigit() {
		return thirdFourDigit;
	}

	public void setThirdFourDigit(Integer thirdFourDigit) {
		this.thirdFourDigit = thirdFourDigit;
	}

	public Integer getLastFourDigit() {
		return lastFourDigit;
	}

	public void setLastFourDigit(Integer lastFourDigit) {
		this.lastFourDigit = lastFourDigit;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public boolean isUseBonuse() {
		return useBonuse;
	}

	public void setUseBonuse(boolean useBonuse) {
		this.useBonuse = useBonuse;
	}

	@Override
	public String toString() {
		return "PaymentDto [firstFourDigit=" + firstFourDigit + ", secondFourDigit=" + secondFourDigit
				+ ", thirdFourDigit=" + thirdFourDigit + ", lastFourDigit=" + lastFourDigit + ", year=" + year
				+ ", month=" + month + ", cvv=" + cvv + ", useBonuse=" + useBonuse + "]";
	}

}
