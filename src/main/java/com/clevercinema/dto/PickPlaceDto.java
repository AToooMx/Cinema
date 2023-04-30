package com.clevercinema.dto;

public class PickPlaceDto {

	private int placeId;
	private int seanceId;
	private double price;
	private int row;
	private int number;
	private String placeRank;

	public PickPlaceDto() {

	}

	public PickPlaceDto(int placeId, int seanceId, double price, int row, int number, String placeRank) {
		this.placeId = placeId;
		this.seanceId = seanceId;
		this.price = price;
		this.row = row;
		this.number = number;
		this.placeRank = placeRank;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getSeanceId() {
		return seanceId;
	}

	public void setSeanceId(int seanceId) {
		this.seanceId = seanceId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getPlaceRank() {
		return placeRank;
	}

	public void setPlaceRank(String placeRank) {
		this.placeRank = placeRank;
	}

	@Override
	public String toString() {
		return "PickPlaceDto [placeId=" + placeId + ", seanceId=" + seanceId + ", price=" + price + ", row=" + row
				+ ", number=" + number + ", placeRank=" + placeRank + "]";
	}

}
