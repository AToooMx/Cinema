package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Country;

public interface CountryDao {

	List<Country> findAllCountryByMovieId(int id);
	
}
