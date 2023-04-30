package com.clevercinema.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.model.Hall;
import com.clevercinema.model.Place;
import com.clevercinema.model.Seance;

public interface SeanceService {

	LinkedHashMap<String, List<Seance>> getSeancesByMovieId(int id);

	Seance findSeanceById(int id);

	Hall findHallBySeanceId(int id);

	Map<Integer, List<Place>> findAllPlacesByHallAndSeanceId(int seanceId, int hallId, String sessionId);

	List<PickPlaceDto> findAllPickPlacesBySeanceAndSessionId(int seanceId, String sessionId);
	
}
