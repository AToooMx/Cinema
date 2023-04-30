package com.clevercinema.services;

import com.clevercinema.model.Cinema;

public interface CinemaService {
	Cinema findCinemaByHallId(int hallId);
}
