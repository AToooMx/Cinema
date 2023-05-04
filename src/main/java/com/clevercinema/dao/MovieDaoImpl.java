package com.clevercinema.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Age;
import com.clevercinema.model.Language;
import com.clevercinema.model.Movie;
import com.clevercinema.rowmapper.MovieRowMapper;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Movie> getCurrentlyMoviesStreaming() {

		Date today = new Date();
		String sql = "SELECT MovieID as 'id', Movie_Title as 'title', Photo_Name as 'photoName' FROM Movie WHERE End_Rental >= ? and Start_Rental<= ? order by Movie_Title";
		Object[] args = { today, today };

		List<Movie> movies = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class), args);

		return movies;
	}

	@Override
	public List<Movie> getSoonMovieList() {

		Date today = new Date();
		String sql = "SELECT MovieID as 'id', movie_title as 'title', Photo_Name as 'photoName', description, start_rental as 'startRental' FROM Movie WHERE Start_Rental > ? order by start_rental";
		Object[] args = { today };

		List<Movie> movies = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class), args);

		return movies;
	}

	@Override
	public Movie findMovieById(int id) {

		String sql = "SELECT MovieID, Movie_Title, Original_Title, filmagelimit.AgeId, filmagelimit.age,filmlanguage.languageid, filmlanguage.language, Start_Rental, End_Rental, duration, photo_name, description FROM movie inner join filmlanguage on ID_Language = LanguageID inner join filmagelimit on id_age = ageId WHERE MovieID = ?";

		Movie movie = template.queryForObject(sql, new MovieRowMapper(), id);

		return movie;
	}

	@Override
	public List<Movie> findAllMovie() {

		String sql = "SELECT MovieID as 'id', Movie_Title as 'title'FROM Movie";

		List<Movie> movies = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class));

		return movies;
	}

	@Override
	public List<Movie> findMoviesByTitle(String title) {
		String sql = "SELECT MovieID as 'id', Movie_Title as 'title', Original_title as 'originalTitle' FROM Movie WHERE Movie_Title like ? or Original_Title like ?";

		List<Movie> movies = template.query(sql, new BeanPropertyRowMapper<Movie>(Movie.class), title, title);

		return movies;
	}

	@Override
	public List<Age> getAllAgeLimit() {
		String sql = "SELECT AgeID as 'id', Age FROM filmagelimit";

		List<Age> ageList = template.query(sql, new BeanPropertyRowMapper<Age>(Age.class));

		return ageList;
	}

	@Override
	public List<Language> getAllLanguage() {
		String sql = "SELECT LanguageID as 'id', Language FROM filmlanguage";

		List<Language> languageList = template.query(sql, new BeanPropertyRowMapper<Language>(Language.class));

		return languageList;
	}

}
