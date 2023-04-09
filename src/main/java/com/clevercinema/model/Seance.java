package com.clevercinema.model;

import java.util.Calendar;
import java.util.Date;

public class Seance {

	private int id;
	private float price;
	private Date timeStart;
	//private String formatTimeStart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getTimeStart() {

		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public String getFormatTimeStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeStart);
		return (calendar.get(Calendar.HOUR_OF_DAY) <= 9 ? "0" : "") + calendar.get(Calendar.HOUR_OF_DAY) + ":" + (calendar.get(Calendar.MINUTE) <= 9 ? "0" : "") + calendar.get(Calendar.MINUTE);

	}

	@Override
	public String toString() {
		return "Seance [id=" + id + ", price=" + price + ", timeStart=" + timeStart + "]";
	}

}
