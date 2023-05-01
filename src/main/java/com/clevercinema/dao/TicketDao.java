package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.dto.TicketDto;
import com.clevercinema.model.Ticket;

public interface TicketDao {

	List<Ticket> findAllTicketBySeanceId(int seanceId);
	
	boolean existsTicketBySeanceAndPlaceId(int seanceId, int placeId);

	void save(int userId, PickPlaceDto p);

	List<TicketDto> findAllTicketsByUserId(int id);
}
