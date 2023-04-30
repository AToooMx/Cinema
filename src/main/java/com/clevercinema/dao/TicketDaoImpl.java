package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Ticket> findAllTicketBySeanceId(int seanceId) {
		
		String sql = "SELECT TicketID as 'id', ID_Seance as 'seanceId', ID_Place as 'placeId' FROM Ticket inner join seance on ID_Seance = SeanceID WHERE seanceId = ? ";
		
		List<Ticket> tickets = template.query(sql, new BeanPropertyRowMapper<Ticket>(Ticket.class), seanceId);
		
		return tickets;
	}

	@Override
	public boolean existsTicketBySeanceAndPlaceId(int seanceId, int placeId) {
		String sql = "SELECT count(*) FROM Ticket WHERE ID_Seance = ? and ID_Place = ?";
		Object[] args  = {seanceId, placeId};
		return template.queryForObject(sql, Integer.class, args)!=0;
	}
	
	

}
