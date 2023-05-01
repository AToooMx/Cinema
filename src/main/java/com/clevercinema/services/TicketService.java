package com.clevercinema.services;

import java.util.List;

import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.dto.TicketDto;

public interface TicketService {

	void saveTicket(int userId, List<PickPlaceDto> places);
	
	List<TicketDto> findAllTicketsByUserId(int id);
}
