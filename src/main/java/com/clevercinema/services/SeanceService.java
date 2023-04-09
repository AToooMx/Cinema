package com.clevercinema.services;

import java.util.LinkedHashMap;
import java.util.List;

import com.clevercinema.model.Seance;

public interface SeanceService {

	LinkedHashMap<String, List<Seance>> getSeancesByMovieId(int id);
	
}
