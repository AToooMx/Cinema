package com.clevercinema.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.model.Hall;
import com.clevercinema.model.Place;
import com.clevercinema.model.Seance;

@Repository
public class SeanceDaoImpl implements SeanceDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Seance> getSeancesByMovieId(int id) {

		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		String today = simpleDateFormat.format(date);
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.DAY_OF_WEEK, 7);
		date = instance.getTime();
		String todayPlusWeek = simpleDateFormat.format(date);

		String sql = "SELECT SeanceID as 'id', Cost as 'price', TimeStart FROM Seance WHERE ID_Movie = ? and TimeStart >= ? and TimeStart<= ? order by timestart asc";

		List<Seance> seances = template.query(sql, new BeanPropertyRowMapper<Seance>(Seance.class), id, today,
				todayPlusWeek);

		return seances;
	}

	@Override
	public Seance findSeanceById(int id) {
		
		String sql = "SELECT SeanceID as 'id', Cost as 'price', TimeStart FROM Seance WHERE SeanceId = ?";
		
		Seance seance = template.queryForObject(sql, new BeanPropertyRowMapper<Seance>(Seance.class), id);
		
		return seance;
	}

	@Override
	public Hall findHallBySeanceId(int id) {
		
		String sql = "SELECT HallId as 'id', HallName as 'name', count(PlaceId) as 'numberOfSeat' FROM Hall inner join Seance on ID_Hall = HallID inner join places on Places.ID_Hall = Hall.HallID WHERE SeanceID = ? group by HallId, HallName";
		
		Hall hall = template.queryForObject(sql, new BeanPropertyRowMapper<Hall>(Hall.class), id);
		//System.out.println(hall);
		return hall;
	}

	@Override
	public List<Place> findAllPlacesByHallId(int hallId) {
		
		String sql = "SELECT PlaceID as 'id', row, number, ID_rank as 'idRank', RankName, addictionalCost FROM places inner join placerank on placerankid = id_rank WHERE ID_Hall = ? order by row, number";
		
		List<Place> places = template.query(sql, new BeanPropertyRowMapper<Place>(Place.class), hallId);
		
		return places;
	}

}
