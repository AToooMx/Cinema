package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Ticket;

public interface TicketDao {

	List<Ticket> findAllTicketBySeanceId(int seanceId);
	
	boolean existsTicketBySeanceAndPlaceId(int seanceId, int placeId);

}
