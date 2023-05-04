package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Age;
import com.clevercinema.model.Language;
import com.clevercinema.model.Movie;

public interface MovieDao {

	List<Movie> getCurrentlyMoviesStreaming();

	List<Movie> getSoonMovieList();

	Movie findMovieById(int id);
	
	List<Movie> findAllMovie();
	
	List<Movie> findMoviesByTitle(String title);

	List<Age> getAllAgeLimit();

	List<Language> getAllLanguage();
}
