package com.clevercinema.model;

public class Place {
	private int id;
	private int row;
	private int number;
	private int idRank;
	private String rankName;
	private double addictionalCost;
	private int seatStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdRank() {
		return idRank;
	}

	public void setIdRank(int idRank) {
		this.idRank = idRank;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public double getAddictionalCost() {
		return addictionalCost;
	}

	public void setAddictionalCost(double addictionalCost) {
		this.addictionalCost = addictionalCost;
	}

	public int getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(int seatStatus) {
		this.seatStatus = seatStatus;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", row=" + row + ", number=" + number + ", idRank=" + idRank + ", rankName="
				+ rankName + ", addictionalCost=" + addictionalCost + ", seatStatus=" + seatStatus + "]";
	}

}
