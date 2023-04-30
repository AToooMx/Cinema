package com.clevercinema.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clevercinema.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Comment> findAllByIdMovie(int idMovie) {

		String sql = "SELECT id, id_movie as 'idMovie', id_User as 'idUser', info, publication_date as publicationDate, is_hidden as 'isHidden', name FROM Comment inner join users on user_id = id_user WHERE id_movie = ? and is_hidden = 0 order by publication_date desc";

		List<Comment> comments = new ArrayList<>();

		comments = template.query(sql, new BeanPropertyRowMapper<Comment>(Comment.class), idMovie);

		return comments;
	}

	@Transactional
	@Override
	public void saveComment(Comment comment) {

		String sql = "INSERT INTO Comment (id_Movie, id_User, info, is_hidden) VALUES (?, ?, ?, ?)";

		Object[] args = { comment.getIdMovie(), comment.getIdUser(), comment.getInfo(), comment.isHidden() };

		template.update(sql, args);

	}

	@Transactional
	@Override
	public void removeCommentById(int id) {
		String sql = "DELETE FROM Comment WHERE id = ?";
		
		template.update(sql, id);
		
	}

}
