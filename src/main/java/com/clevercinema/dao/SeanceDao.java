package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Hall;
import com.clevercinema.model.Place;
import com.clevercinema.model.Seance;

public interface SeanceDao {

	List<Seance> getSeancesByMovieId(int id);

	Seance findSeanceById(int id);

	Hall findHallBySeanceId(int id);
	
	List<Place> findAllPlacesByHallId(int hallId);

}
