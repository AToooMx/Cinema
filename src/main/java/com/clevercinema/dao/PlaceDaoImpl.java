package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.dto.PlaceDto;
import com.clevercinema.model.Place;
import com.clevercinema.model.PlaceRank;

@Repository
public class PlaceDaoImpl implements PlaceDao{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<PlaceRank> findAllPlaceRank() {
		String sql = "SELECT PlaceRankId as 'id', rankname, addictionalcost FROM placerank";
		
		List<PlaceRank> placeRankList = template.query(sql, new BeanPropertyRowMapper<PlaceRank>(PlaceRank.class));
		
		return placeRankList;
	}

	@Override
	public void addPlace(String sessionId, int placeId, int seanceId) {
		String sql = "INSERT INTO session (sessionId, id_place, id_seance) VALUE (?, ?, ?)";
		
		Object[]args = {sessionId, placeId, seanceId};
		
		template.update(sql, args);
		
	}
	
	@Override
	public List<PlaceDto> findAllPickPlaceBySeanceId(String sessionId, int seanceId){
		
		String sql = "SELECT sessionid, id_place as 'placeId', id_seance as 'seanceId' FROM session WHERE sessionid = ? and id_seance = ?";
		
		Object[]args = {sessionId, seanceId};
		
		List<PlaceDto>pickPlaces = template.query(sql, new BeanPropertyRowMapper<PlaceDto>(PlaceDto.class), args);
		
		return pickPlaces;
	}

	@Override
	public boolean existsPickPlacesBySessionAndSeanceAndPlaceId(String sessionId, int placeId, int seanceId) {
		String sql = "SELECT count(*) FROM Session WHERE sessionId = ? and ID_Seance = ? and ID_Place = ?";
		Object[] args  = {sessionId, seanceId, placeId};
		return template.queryForObject(sql, Integer.class, args)!=0;
	}
	
	@Override
	public void removePlace(String sessionId, int placeId, int seanceId) {
		String sql = "DELETE FROM session WHERE sessionId = ? and ID_Place = ? and ID_Seance = ?";
		
		Object[]args = {sessionId, placeId, seanceId};
		
		template.update(sql, args);
	}

	@Override
	public int countPickPlacesBySessionAndSeanceId(String sessionId, int seanceId) {
		String sql = "SELECT count(*) FROM Session WHERE sessionId = ? and ID_Seance = ?";
		
		Object[] args  = {sessionId, seanceId};
		
		return template.queryForObject(sql, Integer.class, args);
	}

	@Override
	public Place findByPlaceId(int placeId) {
		
		String sql = "SELECT PlaceID as 'id', row, number, ID_rank as 'idRank', RankName, addictionalCost FROM places inner join placerank on placerankid = id_rank WHERE PlaceId = ? order by row, number";
		
		Place places = template.queryForObject(sql, new BeanPropertyRowMapper<Place>(Place.class), placeId);
		
		return places;
	
	}

	@Override
	public void cleanSessionTable() {
		String sql = "DELETE FROM Session";
		
		template.update(sql);
	}

	@Override
	public void cleanSessionTableBySessionId(String sessionId) {
		String sql = "DELETE FROM Session WHERE sessionId = ?";
		
		template.update(sql, sessionId);
	}
	
}
