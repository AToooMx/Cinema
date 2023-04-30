package com.clevercinema.services;

import java.util.List;

import com.clevercinema.dto.PlaceDto;
import com.clevercinema.model.Place;
import com.clevercinema.model.PlaceRank;

public interface PlaceService {

	List<PlaceRank> findAllPlaceRank();

	void pickPlace(String sessionId, int placeId, int seanceId);

	List<PlaceDto> findAllPickPlaceBySeanceId(String sessionId, int seanceId);

	Place findPlaceByPlaceId(int placeId);
	
	void cleanSessionTable();

	void cleanSessionTableBySessionId(String id);

}
