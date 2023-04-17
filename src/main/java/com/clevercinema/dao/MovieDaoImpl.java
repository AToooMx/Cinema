package com.clevercinema.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Country;
import com.clevercinema.model.Genre;
import com.clevercinema.model.Movie;
import com.clevercinema.model.Producer;
import com.clevercinema.model.Studio;
import com.clevercinema.rowmapper.MovieRowMapper;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Movie> getCurrentlyMoviesStreaming() {

		Date today = new Date();
		String sql = "SELECT MovieID as 'id', Movie_Title as 'title', Photo_Name as 'photoName' FROM Movie WHERE End_Rental >= ? and Start_Rental<= ?";
		Object[] args = { today, today };

		List<Movie> movies = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class), args);

		return movies;
	}

	@Override
	public List<Movie> getSoonMovieList() {

		Date today = new Date();
		String sql = "SELECT MovieID as 'id', movie_title as 'title', Photo_Name as 'photoName', description FROM Movie WHERE Start_Rental > ?";
		Object[] args = { today };

		List<Movie> movies = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class), args);

		return movies;
	}

	@Override
	public Movie findMovieById(int id) {

		String sql = "SELECT MovieID, Movie_Title, Original_Title, filmagelimit.age, filmlanguage.language, Start_Rental, End_Rental, duration, photo_name, trailer, description FROM movie inner join filmlanguage on ID_Language = LanguageID inner join filmagelimit on id_age = ageId WHERE MovieID = ?";

		Movie movie = template.queryForObject(sql, new MovieRowMapper(), id);

		return movie;
	}

	@Override
	public List<Genre> findAllGenreByMovieId(int id) {

		String sql = "SELECT GenreID as id, GenreName as name FROM genre inner join filmgenre on GenreID = ID_Genre WHERE ID_Movie = ?";

		List<Genre> genres = template.query(sql, new BeanPropertyRowMapper<Genre>(Genre.class), id);

		return genres;
	}

	@Override
	public List<Producer> findAllProducerByMovieId(int id) {
		String sql = "SELECT ProducerID as id, Producer as name FROM producer inner join filmproducer on ProducerID = ID_Producer WHERE ID_Movie = ?";

		List<Producer> producers = template.query(sql, new BeanPropertyRowMapper<Producer>(Producer.class), id);

		return producers;
	}

	@Override
	public List<Country> findAllCountryByMovieId(int id) {
		String sql = "SELECT CountryID as id, Country as name FROM country inner join filmcountry on CountryID = ID_Country WHERE ID_Movie = ?";

		List<Country> countries = template.query(sql, new BeanPropertyRowMapper<Country>(Country.class), id);

		return countries;
	}

	@Override
	public List<Studio> findAllStudioByMovieId(int id) {
		String sql = "SELECT StudioID as id, Studio as name FROM studio inner join filmstudio on StudioID = ID_Studio WHERE ID_Movie = ?";

		List<Studio> studios = template.query(sql, new BeanPropertyRowMapper<Studio>(Studio.class), id);

		return studios;
	}

}
