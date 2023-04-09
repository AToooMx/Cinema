package com.clevercinema.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Comment")
public class CommentDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_Movie")
	private int idMovie;
	@Column(name = "id_User")
	private int idUser;
	@Column(name = "text")
	@NotEmpty(message = "Поле не може бути пустим")
	private String text;
	@Column(name = "publication_Date")
	private Date publicationDate;
	@Column(name = "is_Hidden")
	private boolean isHidden = false;

	public CommentDto(int id, int idMovie, int idUser, @NotEmpty(message = "Поле не може бути пустим") String text,
			Date publicationDate, boolean isHidden) {
		this.id = id;
		this.idMovie = idMovie;
		this.idUser = idUser;
		this.text = text;
		this.publicationDate = publicationDate;
		this.isHidden = isHidden;
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

}
