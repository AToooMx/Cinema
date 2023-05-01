package com.clevercinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.TicketDao;
import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.dto.TicketDto;

@Service
public class TickectServiceImpl implements TicketService{

	@Autowired
	private TicketDao ticketDao;
	
	@Override
	public void saveTicket(int userId, List<PickPlaceDto> places) {
		
		for(PickPlaceDto p:places) {
			ticketDao.save(userId, p);
		}
		
	}
	
	public List<TicketDto> findAllTicketsByUserId(int id){
		
		return ticketDao.findAllTicketsByUserId(id);
	}

}
