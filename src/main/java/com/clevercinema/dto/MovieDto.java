package com.clevercinema.dto;

import java.util.Date;

import com.clevercinema.model.Age;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
	private int id;
	private String title;
	private String originalTitle;
	private Age age;
	private String language;
	private Date startRental;
	private Date endRental;
	private String rentalPeriod;
	private int duration;
	private String photoName;
	private String description;
}
