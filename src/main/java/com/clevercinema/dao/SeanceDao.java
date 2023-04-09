package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Seance;

public interface SeanceDao {

	List<Seance> getSeancesByMovieId(int id);

}
