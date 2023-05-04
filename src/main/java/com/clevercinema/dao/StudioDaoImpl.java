package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Studio;

@Repository
public class StudioDaoImpl implements StudioDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Studio> findAllStudioByMovieId(int id) {
		String sql = "SELECT filmstudio.filmstudioid as id, Studio as name FROM studio inner join filmstudio on StudioID = ID_Studio WHERE ID_Movie = ?";

		List<Studio> studios = template.query(sql, new BeanPropertyRowMapper<Studio>(Studio.class), id);

		return studios;
	}

	@Override
	public void deleteFilmStudioById(int studioId) {
		String sql = "DELETE FROM filmstudio WHERE FilmStudioId = ?";

		template.update(sql, studioId);

	}
	
	@Override
	public boolean existsStudioByName(String name) {
		String sql = "SELECT count(*) FROM Studio WHERE Studio = ?";
		return template.queryForObject(sql, Integer.class, name)!=0;
	}
	
	@Override
	public Studio findStudioByName(String name) {
		String sql = "SELECT StudioID as id, Studio as 'name' FROM Studio WHERE Studio = ?";
		Studio studio = template.queryForObject(sql, new BeanPropertyRowMapper<Studio>(Studio.class), name);
		return studio;
	}

	@Override
	public void addStudio(String name) {
		String sql = "INSERT INTO Studio (Studio) VALUES (?)";
		template.update(sql, name);
	}
	
	@Override
	public void addStudioForMovie(int id, int studioId) {
		String sql = "INSERT INTO filmstudio (id_movie, id_studio) VALUES(?, ?)";
		template.update(sql, id, studioId);

	}


}
