package com.clevercinema.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketDto {

	private int id;
	private int seanceId;
	private int movieId;
	private String movieTitle;
	private String photoName;
	private double cost;
	private Date timeStart;
	private Date purchaseDate;
	private int row;
	private int number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeanceId() {
		return seanceId;
	}

	public void setSeanceId(int seanceId) {
		this.seanceId = seanceId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "TicketDto [id=" + id + ", seanceId=" + seanceId + ", movieId=" + movieId + ", movieTitle=" + movieTitle
				+ ", photoName=" + photoName + ", cost=" + cost + ", purchaseDate=" + purchaseDate + ", row=" + row
				+ ", number=" + number + "]";
	}
	
	public String formatDate() {
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(purchaseDate);
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	
	
	public String getDayFormatTimeStart() {
		
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		return simpleDateFormat.format(timeStart);
	}

}
