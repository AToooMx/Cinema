package com.clevercinema.model;

public class Hall {
	private int id;
	private String name;
	private int numberOfSeat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	@Override
	public String toString() {
		return "Hall [id=" + id + ", name=" + name + ", numberOfSeat=" + numberOfSeat + "]";
	}

}
