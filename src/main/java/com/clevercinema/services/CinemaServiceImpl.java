package com.clevercinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.CinemaDao;
import com.clevercinema.model.Cinema;

@Service
public class CinemaServiceImpl implements CinemaService {
	@Autowired
	private CinemaDao cinemaDao;

	@Override
	public Cinema findCinemaByHallId(int hallId) {

		return cinemaDao.findCinemaByHallId(hallId);
	}

}
