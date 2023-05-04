package com.clevercinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clevercinema.dao.CountryDao;
import com.clevercinema.dao.GenreDao;
import com.clevercinema.dao.MovieDao;
import com.clevercinema.dao.ProducerDao;
import com.clevercinema.dao.StudioDao;
import com.clevercinema.model.Age;
import com.clevercinema.model.Genre;
import com.clevercinema.model.Language;
import com.clevercinema.model.Movie;
import com.clevercinema.model.Studio;

@Transactional
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	@Autowired
	private GenreDao genreDao;
	@Autowired
	private ProducerDao producerDao;
	@Autowired
	private StudioDao studioDao;
	@Autowired
	private CountryDao countryDao;

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

		movie.setCountries(countryDao.findAllCountryByMovieId(id));
		movie.setStudios(studioDao.findAllStudioByMovieId(id));
		movie.setProducers(producerDao.findAllProducerByMovieId(id));
		movie.setGenres(genreDao.findAllGenreByMovieId(id));

		return movie;
	}

	@Override
	public List<Genre> getAllGenre() {
		return genreDao.findAllGenre();
	}

	@Override
	public List<Movie> findAllMovies() {

		return movieDao.findAllMovie();
	}

	@Override
	public List<Movie> findMoviesByTitle(String title) {
		title = "%" + title + "%";

		return movieDao.findMoviesByTitle(title);
	}

	@Override
	public List<Age> getAllAgeLimit() {

		return movieDao.getAllAgeLimit();
	}

	@Override
	public List<Language> getAllLanguage() {

		return movieDao.getAllLanguage();
	}

	@Override
	public void deleteFilmGenreById(int genreId) {
		genreDao.deleteFilmGenreById(genreId);
	}

	@Override
	public void addGenreForMovie(int id, int genreId) {
		genreDao.addGenreForMovie(id, genreId);

	}

	@Override
	public void deleteFilmStudioById(int studioId) {
		studioDao.deleteFilmStudioById(studioId);

	}

	@Override
	public void addStudioForMovie(int id, String name) {

		if (studioDao.existsStudioByName(name)) {
			Studio studio = studioDao.findStudioByName(name);
			studioDao.addStudioForMovie(id, studio.getId());
		} else {
			studioDao.addStudio(name);
			Studio studio = studioDao.findStudioByName(name);
			studioDao.addStudioForMovie(id, studio.getId());
		}

	}

}
