package com.clevercinema.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.SeanceDao;
import com.clevercinema.dao.TicketDao;
import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.dto.PlaceDto;
import com.clevercinema.model.Hall;
import com.clevercinema.model.Place;
import com.clevercinema.model.Seance;
import com.clevercinema.model.Ticket;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceDao seanceDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private PlaceService placeService;

	@Override
	public LinkedHashMap<String, List<Seance>> getSeancesByMovieId(int id) {

		List<Seance> seances = seanceDao.getSeancesByMovieId(id);

		LinkedHashMap<String, List<Seance>> seanceDay = new LinkedHashMap<>();

		String[] weekday = { "Неділя", "Понеділок", "Вівторок", "Середа", "Четверг", "П'ятниця", "Субота" };
		Calendar calendar = Calendar.getInstance();
		String day = "";
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		for (int i = 0; i < seances.size(); i++) {

			calendar.setTime(seances.get(i).getTimeStart());
			int d = calendar.get(Calendar.DAY_OF_WEEK) - 1;

			day = weekday[d] + " (" + simpleDateFormat.format(seances.get(i).getTimeStart()) + ")";

			List<Seance> seanceList = seanceDay.get(day);

			if (seanceList == null) {
				List<Seance> seanceDayList = new ArrayList<>();
				seanceDayList.add(seances.get(i));

				seanceDay.put(day, seanceDayList);
			} else {
				seanceList.add(seances.get(i));
			}

		}
		return seanceDay;
	}

	@Override
	public Seance findSeanceById(int id) {
		Seance seance = seanceDao.findSeanceById(id);

		return seance;
	}

	@Override
	public Hall findHallBySeanceId(int id) {
		Hall hall = seanceDao.findHallBySeanceId(id);
		return hall;
	}
	
	

	@Override
	public Map<Integer, List<Place>> findAllPlacesByHallAndSeanceId(int seanceId, int hallId, String sessionId) {
		List<Place> placeList = seanceDao.findAllPlacesByHallId(hallId);
		List<Ticket> ticketList = ticketDao.findAllTicketBySeanceId(seanceId);
		List<PlaceDto> pickPlaces = placeService.findAllPickPlaceBySeanceId(sessionId, seanceId);
		
		for (int i = 0; i < placeList.size(); i++) {
			for (int j = 0; j < ticketList.size(); j++) {

				if (placeList.get(i).getId() == ticketList.get(j).getPlaceId()) {
					placeList.get(i).setSeatStatus(1);
				}

			}
		}
		
		for (int i = 0; i < placeList.size(); i++) {
			for (int j = 0; j < pickPlaces.size(); j++) {

				if (placeList.get(i).getId() == pickPlaces.get(j).getPlaceId()) {
					placeList.get(i).setSeatStatus(2);
				}

			}
		}
		

		Map<Integer, List<Place>> places = new HashMap<>();

		for (int i = 0; i < placeList.size(); i++) {
			Integer row = placeList.get(i).getRow();
			List<Place> allPlaceByRow = places.get(row);

			if (allPlaceByRow == null) {
				List<Place> allPlace = new ArrayList<>();
				allPlace.add(placeList.get(i));

				places.put(row, allPlace);
			} else {
				allPlaceByRow.add(placeList.get(i));
			}

		}

		return places;
	}

	@Override
	public List<PickPlaceDto> findAllPickPlacesBySeanceAndSessionId(int seanceId, String sessionId) {
		List<PlaceDto> place = placeService.findAllPickPlaceBySeanceId(sessionId, seanceId);
		List<Place> places = new ArrayList<Place>();
		for(int i = 0; i < place.size(); i++) {
			places.add(placeService.findPlaceByPlaceId(place.get(i).getPlaceId()));
		}
		
		Seance seance = seanceDao.findSeanceById(seanceId);
		List<PickPlaceDto> pickPlace = new ArrayList<PickPlaceDto>();
		
		for(Place p : places) {
			
			System.out.println("P = " + p);
			PickPlaceDto pick = new PickPlaceDto();
			pick.setPlaceId(p.getId());
			pick.setSeanceId(seance.getId());
			pick.setPrice(p.getAddictionalCost() + seance.getPrice());
			pick.setRow(p.getRow());
			pick.setNumber(p.getNumber());
			pick.setPlaceRank(p.getRankName());
			
			pickPlace.add(pick);
		}
		
		return pickPlace;
	}

}
