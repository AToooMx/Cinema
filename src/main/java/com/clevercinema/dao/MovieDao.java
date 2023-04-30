package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Country;
import com.clevercinema.model.Genre;
import com.clevercinema.model.Movie;
import com.clevercinema.model.Producer;
import com.clevercinema.model.Studio;

public interface MovieDao {

	List<Movie> getCurrentlyMoviesStreaming();

	List<Movie> getSoonMovieList();

	Movie findMovieById(int id);

	List<Genre> findAllGenreByMovieId(int id);

	List<Producer> findAllProducerByMovieId(int id);

	List<Country> findAllCountryByMovieId(int id);

	List<Studio> findAllStudioByMovieId(int id);

	List<Genre> findAllGenre();
}
