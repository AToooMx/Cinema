package com.clevercinema.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Seance {

	private int id;
	private double price;
	private Date timeStart;
	// private String formatTimeStart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
		return (calendar.get(Calendar.HOUR_OF_DAY) <= 9 ? "0" : "") + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ (calendar.get(Calendar.MINUTE) <= 9 ? "0" : "") + calendar.get(Calendar.MINUTE);

	}
	
	public String getDayFormatTimeStart() {
		
		String[] weekday = { "Неділя", "Понеділок", "Вівторок", "Середа", "Четверг", "П'ятниця", "Субота" };
		String pattern = "yyyy-MM-dd";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeStart);
		int d = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return weekday[d] + " (" + simpleDateFormat.format(timeStart) + ")";
	}

	@Override
	public String toString() {
		return "Seance [id=" + id + ", price=" + price + ", timeStart=" + timeStart + "]";
	}

}
