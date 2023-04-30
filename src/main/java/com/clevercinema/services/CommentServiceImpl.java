package com.clevercinema.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clevercinema.dao.CommentDao;
import com.clevercinema.model.Comment;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> findAllByIdMovie(int idMovie) {

		return commentDao.findAllByIdMovie(idMovie);
	}
	@Transactional
	@Override
	public void saveComment(Comment comment) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setPublicationDate(simpleDateFormat.format(new Date()));
		comment.setHidden(false);
		commentDao.saveComment(comment);

	}
	
	@Override
	public void removeCommentById(int id) {
		
		commentDao.removeCommentById(id);
	}

}
