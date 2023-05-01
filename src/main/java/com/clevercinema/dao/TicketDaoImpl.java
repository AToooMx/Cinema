package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.dto.TicketDto;
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
		Object[] args = { seanceId, placeId };
		return template.queryForObject(sql, Integer.class, args) != 0;
	}

	@Override
	public void save(int userId, PickPlaceDto p) {

		String sql = "INSERT INTO Ticket (ID_Seance, ID_User, ID_Place, Cost) VALUES (?, ?, ?, ?)";
		
		Object[] args = { p.getSeanceId(), userId, p.getPlaceId(), p.getPrice()};
		
		template.update(sql, args);
	}
	
	public List<TicketDto> findAllTicketsByUserId(int id){
		
		String sql = "SELECT TicketID, ID_Seance, Ticket.Cost, PurchaseDate, MovieID, TimeStart, Movie_Title as 'movieTitle', Photo_Name as 'photoName', row, number  "
				+ "FROM Ticket inner join Seance on Ticket.ID_Seance = Seance.SeanceID inner join movie on Movie.MovieID = Seance.ID_Movie inner join Places on Ticket.ID_Place = Places.PlaceID "
				+ "WHERE ID_USER = ? order by purchaseDate desc";
		
		List<TicketDto> tickets = template.query(sql, new BeanPropertyRowMapper<TicketDto>(TicketDto.class), id);
		
		return tickets;
		
	}

}
