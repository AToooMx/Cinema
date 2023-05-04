package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Genre;

public interface GenreDao {

	List<Genre> findAllGenreByMovieId(int id);

	List<Genre> findAllGenre();

	void deleteFilmGenreById(int genreId);

	void addGenreForMovie(int id, int genreId);

}
