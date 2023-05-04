package com.clevercinema.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.clevercinema.model.Age;
import com.clevercinema.model.Language;
import com.clevercinema.model.Movie;

public class MovieRowMapper implements RowMapper<Movie> {

	@Override
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {

		Movie movie = new Movie();
		if (rs != null) {
			movie.setId(rs.getInt("MovieID"));
			movie.setTitle(rs.getString("Movie_Title"));
			movie.setOriginalTitle(rs.getString("Original_Title"));
			Age age = new Age();
			age.setId(rs.getInt("ageId"));
			age.setAge(rs.getInt("age"));
			movie.setAge(age);
			Language language = new Language();
			language.setId(rs.getInt("LanguageID"));
			language.setLanguage(rs.getString("Language"));
			movie.setLanguage(language);
			movie.setStartRental(rs.getDate("Start_Rental"));
			movie.setEndRental(rs.getDate("End_Rental"));
			movie.setDuration(rs.getInt("duration"));
			movie.setPhotoName(rs.getString("photo_name"));
			movie.setDescription(rs.getString("description"));
			return movie;
		}
		// System.out.println(movie);

		return null;
	}

}
