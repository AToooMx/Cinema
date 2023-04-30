package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.dto.PlaceDto;
import com.clevercinema.model.Place;
import com.clevercinema.model.PlaceRank;

public interface PlaceDao {
	
	List<PlaceRank> findAllPlaceRank();
	
	Place findByPlaceId(int placeId);
	
	void addPlace(String sessionId, int placeId, int seanceId);
	
	List<PlaceDto> findAllPickPlaceBySeanceId(String sessionId, int seanceId);

	boolean existsPickPlacesBySessionAndSeanceAndPlaceId(String sessionId, int placeId, int seanceId);

	void removePlace(String sessionId, int placeId, int seanceId);

	int countPickPlacesBySessionAndSeanceId(String sessionId, int seanceId);
	
	void cleanSessionTable();

	void cleanSessionTableBySessionId(String sessionId);
}
