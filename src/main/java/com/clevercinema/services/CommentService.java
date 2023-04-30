package com.clevercinema.services;

import java.util.List;

import com.clevercinema.model.Comment;

public interface CommentService {

	List<Comment> findAllByIdMovie(int idMovie);

	void saveComment(Comment comment);

	void removeCommentById(int id);
}
