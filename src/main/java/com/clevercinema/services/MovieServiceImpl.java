package com.clevercinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.MovieDao;
import com.clevercinema.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Override
	public List<Movie> getCurrentlyMoviesStreaming() {

		return movieDao.getCurrentlyMoviesStreaming();
	}

	@Override
	public List<Movie> getSoonMovieList() {

		return movieDao.getSoonMovieList();
	}

	@Override
	public Movie findMovieById(int id) {

		Movie movie = movieDao.findMovieById(id);

		String startRental = movie.getStartRental().toString();
		String endRental = movie.getEndRental().toString();
		movie.setRentalPeriod(startRental + " - " + endRental);

		movie.setCountries(movieDao.findAllCountryByMovieId(id));
		movie.setStudios(movieDao.findAllStudioByMovieId(id));
		movie.setProducers(movieDao.findAllProducerByMovieId(id));
		movie.setGenres(movieDao.findAllGenreByMovieId(id));
		
		return movie;
	}

}
