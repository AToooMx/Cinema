package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Studio;

public interface StudioDao {

	List<Studio> findAllStudioByMovieId(int id);

	void deleteFilmStudioById(int studioId);
	
	Studio findStudioByName(String name);

	void addStudio(String name);
	
	void addStudioForMovie(int id, int studioId);

	boolean existsStudioByName(String name);

}
