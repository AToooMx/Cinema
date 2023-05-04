package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Country;

@Repository
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Country> findAllCountryByMovieId(int id) {
		String sql = "SELECT filmcountry.filmcountryid as id, Country as name FROM country inner join filmcountry on CountryID = ID_Country WHERE ID_Movie = ?";

		List<Country> countries = template.query(sql, new BeanPropertyRowMapper<Country>(Country.class), id);

		return countries;
	}
}
