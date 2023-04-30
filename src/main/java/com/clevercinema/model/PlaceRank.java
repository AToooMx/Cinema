package com.clevercinema.model;

public class PlaceRank {

	private int id;
	private String rankName;
	private double addictionalCost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "PlaceRank [id=" + id + ", rankName=" + rankName + ", addictionalCost=" + addictionalCost + "]";
	}

	
}
