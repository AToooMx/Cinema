package com.clevercinema.services;

import java.util.List;

import com.clevercinema.model.Age;
import com.clevercinema.model.Genre;
import com.clevercinema.model.Language;
import com.clevercinema.model.Movie;

public interface MovieService{

	List<Movie> getCurrentlyMoviesStreaming();

	List<Movie> getSoonMovieList();
	
	Movie findMovieById(int id);

	List<Genre> getAllGenre();

	List<Movie> findAllMovies();
	
	List<Movie> findMoviesByTitle(String title);
	
	List<Age> getAllAgeLimit();
	
	List<Language> getAllLanguage();

	void deleteFilmGenreById(int genreId);

	void addGenreForMovie(int id, int genreId);

	void deleteFilmStudioById(int studioId);

	void addStudioForMovie(int id, String name);

}
