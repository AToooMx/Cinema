package com.clevercinema.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Movie {

	private int id;
	private String title;
	private String originalTitle;
	private Age age;
	private Language language;
	private Date startRental;
	private Date endRental;
	private String rentalPeriod;
	private int duration;
	private String photoName;
	private String description;
	private List<Genre> genres;
	private List<Producer> producers;
	private List<Studio> studios;
	private List<Country> countries;

	public Movie() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Date getStartRental() {
		return startRental;
	}

	public void setStartRental(Date startRental) {
		this.startRental = startRental;
	}

	public Date getEndRental() {
		return endRental;
	}

	public void setEndRental(Date endRental) {
		this.endRental = endRental;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getRentalPeriod() {
		return rentalPeriod;
	}

	public void setRentalPeriod(String rentalPeriod) {
		this.rentalPeriod = rentalPeriod;
	}


	public List<Producer> getProducers() {
		return producers;
	}

	public List<Studio> getStudios() {
		return studios;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public String getAllGenres() {

		return genres.toString().replace("[", "").replace("]", "");
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getAllProducers() {
		return producers.toString().replace("[", "").replace("]", "");
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public String getAllStudios() {
		return studios.toString().replace("[", "").replace("]", "");
	}

	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

	public String getAllCountries() {
		return countries.toString().replace("[", "").replace("]", "");
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", originalTitle=" + originalTitle + ", age=" + age
				+ ", language=" + language + ", startRental=" + startRental + ", endRental=" + endRental + ", duration="
				+ duration + ", photoName=" + photoName + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStratRentalDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(startRental);
	}

}
