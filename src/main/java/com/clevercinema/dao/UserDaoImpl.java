package com.clevercinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clevercinema.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<UserDto> findAllUsersAndHesTickets() {
		
		String sql = "SELECT User_ID as 'id', Email, name, surname, bonuses, count(TicketID) as 'countTickets', SUM(Cost) as 'sumSpendMoney' FROM users left join ticket on ID_User = User_ID group by User_ID";
		
		List<UserDto>usersList = template.query(sql, new BeanPropertyRowMapper<UserDto>(UserDto.class));
		
		return usersList;
	}

	@Override
	public void deleteUserById(int id) {
		String sql = "DELETE FROM authorities WHERE ID_User = ?";
		template.update(sql, id);
		sql = "DELETE FROM comment WHERE ID_User = ?";
		template.update(sql, id);
		sql = "DELETE FROM users WHERE User_ID = ?";
		template.update(sql, id);
	}

	@Override
	public void deleteRoleById(int id) {
		String sql = "DELETE FROM authorities WHERE id = ?";
		template.update(sql, id);
	}

	@Override
	public void addRoleByUserId(String authority, int id) {
		String sql = "INSERT INTO Authorities (authority, id_user) VALUES (?, ?)";
		template.update(sql, authority, id);
	}

}
