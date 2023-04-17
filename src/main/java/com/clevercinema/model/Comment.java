package com.clevercinema.model;

import javax.validation.constraints.NotEmpty;

public class Comment {

	private int id;
	private int idMovie;
	private int idUser;
	@NotEmpty
	private String info;
	private String publicationDate;
	private boolean isHidden = false;
	private String name;

	public Comment() {

	}

	public Comment(int id, int idMovie, int idUser, String info, String publicationDate, boolean isHidden, String name) {
		this.id = id;
		this.idMovie = idMovie;
		this.idUser = idUser;
		this.info = info;
		this.publicationDate = publicationDate;
		this.isHidden = isHidden;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUsers) {
		this.idUser = idUsers;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", idMovie=" + idMovie + ", idUser=" + idUser + ", info=" + info
				+ ", publicationDate=" + publicationDate + ", isHidden=" + isHidden + ", name=" + name + "]";
	}

}
