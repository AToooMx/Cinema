package com.clevercinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.PlaceDao;
import com.clevercinema.dao.TicketDao;
import com.clevercinema.dto.PlaceDto;
import com.clevercinema.model.Place;
import com.clevercinema.model.PlaceRank;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	@Autowired
	private TicketDao ticketDao;

	@Override
	public List<PlaceRank> findAllPlaceRank() {

		return placeDao.findAllPlaceRank();
	}

	@Override
	public void pickPlace(String sessionId, int placeId, int seanceId) {
		if (!ticketDao.existsTicketBySeanceAndPlaceId(seanceId, placeId)) {
			if (!placeDao.existsPickPlacesBySessionAndSeanceAndPlaceId(sessionId, placeId, seanceId)) {
				if(placeDao.countPickPlacesBySessionAndSeanceId(sessionId, seanceId)<5)
				placeDao.addPlace(sessionId, placeId, seanceId);
			} else {
				placeDao.removePlace(sessionId, placeId, seanceId);
			}
		}

	}

	@Override
	public List<PlaceDto> findAllPickPlaceBySeanceId(String sessionId, int seanceId) {

		return placeDao.findAllPickPlaceBySeanceId(sessionId, seanceId);
	}

	@Override
	public Place findPlaceByPlaceId(int placeId) {
		
		return placeDao.findByPlaceId(placeId);
	}

	@Override
	public void cleanSessionTable() {
		placeDao.cleanSessionTable();
		
	}

	@Override
	public void cleanSessionTableBySessionId(String sessionId) {
		placeDao.cleanSessionTableBySessionId(sessionId);
		
	}

}
