package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Producer;

public interface ProducerDao {

	List<Producer> findAllProducerByMovieId(int id);

}
