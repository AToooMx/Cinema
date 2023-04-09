package com.clevercinema.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clevercinema.model.Comment;
import com.clevercinema.model.Movie;
import com.clevercinema.model.Seance;
import com.clevercinema.model.Users;
import com.clevercinema.repository.UserRepository;
import com.clevercinema.services.CommentService;
import com.clevercinema.services.MovieService;
import com.clevercinema.services.SeanceService;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private SeanceService seanceService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentService commentService;

	@GetMapping("/movie")
	public String showMovie(@RequestParam("movieId") int id, Model model) {

		Movie movie = movieService.findMovieById(id);
		LinkedHashMap<String, List<Seance>> seances = seanceService.getSeancesByMovieId(id);
		List<Comment> allMovieComments = commentService.findAllByIdMovie(id);
		Comment comment = new Comment();

		System.out.println(allMovieComments);
		
		comment.setIdMovie(id);

		model.addAttribute("movie", movie);
		model.addAttribute("seances", seances);
		model.addAttribute("comment", comment);
		model.addAttribute("comments", allMovieComments);

		return "movie-page";

	}

	@PostMapping("/movie/add-comment-process")
	public String addComment(@Valid Comment comment, BindingResult result, Authentication authentication) {

		if (result.hasErrors()) {
			return "redirect:/movies/movie?movieId=" + comment.getIdMovie() + "&?emptyTextArea";
		}
		
		Users user = userRepository.findByEmail(authentication.getName());
		comment.setIdUser(user.getId());
		commentService.saveComment(comment);

		return "redirect:/movies/movie?movieId=" + comment.getIdMovie();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "text", editor);
	}
}
