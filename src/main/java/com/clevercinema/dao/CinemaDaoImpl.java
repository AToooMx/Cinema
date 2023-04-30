package com.clevercinema.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Cinema;

@Repository
public class CinemaDaoImpl implements CinemaDao {
	@Autowired
	private JdbcTemplate template;

	@Override
	public Cinema findCinemaByHallId(int hallId) {
		
		String sql = "SELECT * FROM Cinema inner join Hall on ID_Cinema = CinemaID WHERE HallID = ?";
		
		Cinema cinema = template.queryForObject(sql, new BeanPropertyRowMapper<Cinema>(Cinema.class), hallId);
		
		return cinema;
	}

}
