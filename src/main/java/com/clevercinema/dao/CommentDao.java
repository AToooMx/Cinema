package com.clevercinema.dao;

import java.util.List;

import com.clevercinema.model.Comment;

public interface CommentDao {

	List<Comment> findAllByIdMovie(int idMovie);
	
	void saveComment(Comment comment);

	void removeCommentById(int id);
	
}
