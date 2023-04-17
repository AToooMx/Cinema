package com.clevercinema.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.SeanceDao;
import com.clevercinema.model.Seance;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceDao seanceDao;

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
			// System.out.println(seances.get(i));

			calendar.setTime(seances.get(i).getTimeStart());
			int d = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			day = weekday[d] + " (" + simpleDateFormat.format(seances.get(i).getTimeStart()) + ")";

			List<Seance> seanceList = seanceDay.get(day); // Присваюиваю список всех сеансов по ключу
			// System.out.println(seances.get(i).getFormatTimeStart());

			if (seanceList == null) { // Если список по ключу пустой, то создаю его и добавляю в него елемент.
				List<Seance> seanceDayList = new ArrayList<>();
				seanceDayList.add(seances.get(i));

				seanceDay.put(day, seanceDayList);
			} else {
				// Если список не пустой, то добавляю в него елемент, который подходит по ключу,
				// и потому что он ссылкой связан с мапой данные в мапе автоматически
				// обновляються
				// System.out.println(seanceDay.get(day));
				seanceList.add(seances.get(i));
				// System.out.println(seanceDay.get(day));
			}

		}
		return seanceDay;
	}

}

//(calendar.get(Calendar.MINUTE) <= 9 ? 0 : "")
