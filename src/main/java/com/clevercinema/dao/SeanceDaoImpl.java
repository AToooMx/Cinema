package com.clevercinema.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
