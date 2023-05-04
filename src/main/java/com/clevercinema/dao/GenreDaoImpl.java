package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Genre;

@Repository
public class GenreDaoImpl implements GenreDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Genre> findAllGenreByMovieId(int id) {

		String sql = "SELECT filmgenre.filmgenreid as id, GenreName as name FROM genre inner join filmgenre on GenreID = ID_Genre WHERE ID_Movie = ? order by GenreName";

		List<Genre> genres = template.query(sql, new BeanPropertyRowMapper<Genre>(Genre.class), id);

		return genres;
	}

	@Override
	public List<Genre> findAllGenre() {
		String sql = "SELECT GenreId as 'id', GenreName as 'name' FROM Genre ORDER BY GenreName";

		List<Genre> genres = template.query(sql, new BeanPropertyRowMapper<Genre>(Genre.class));

		return genres;
	}

	@Override
	public void deleteFilmGenreById(int genreId) {

		String sql = "DELETE From filmgenre WHERE FilmGenreId = ?";

		template.update(sql, genreId);

	}

	@Override
	public void addGenreForMovie(int id, int genreId) {
		String sql = "INSERT INTO FilmGenre (ID_Movie, ID_Genre) VALUES (?,?)";

		template.update(sql, id, genreId);

	}
}
