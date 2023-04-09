package com.clevercinema.services;

import java.util.List;

import com.clevercinema.model.Movie;

public interface MovieService{

	List<Movie> getCurrentlyMoviesStreaming();

	List<Movie> getSoonMovieList();
	
	Movie findMovieById(int id);

}
