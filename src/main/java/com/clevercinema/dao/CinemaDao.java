package com.clevercinema.dao;

import com.clevercinema.model.Cinema;

public interface CinemaDao {

	Cinema findCinemaByHallId(int hallId);
	
}
