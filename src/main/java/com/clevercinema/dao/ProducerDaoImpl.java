package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Producer;

@Repository
public class ProducerDaoImpl implements ProducerDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Producer> findAllProducerByMovieId(int id) {
		String sql = "SELECT filmproducer.filmproducerid as id, Producer as name FROM producer inner join filmproducer on ProducerID = ID_Producer WHERE ID_Movie = ?";

		List<Producer> producers = template.query(sql, new BeanPropertyRowMapper<Producer>(Producer.class), id);

		return producers;
	}

	
}
