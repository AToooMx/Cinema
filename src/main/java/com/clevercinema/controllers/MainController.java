package com.clevercinema.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.clevercinema.entity.Faq;
import com.clevercinema.model.Movie;
import com.clevercinema.services.FAQService;
import com.clevercinema.services.MovieService;

@Controller
public class MainController {
	@Autowired
	private MovieService movieService;
	@Autowired
	private FAQService FAQService;

	@GetMapping("")
	public String showPage() {

		return "redirect:/movies";
	}

	@GetMapping("/")
	public String showHomePage(Authentication authentication) {

		return "redirect:movies";
	}

	@GetMapping("/movies")
	public String showMoviesPage(Model model) {
		
		List<Movie> movies = movieService.getCurrentlyMoviesStreaming();
		System.out.println(movies);
		model.addAttribute("movies", movies);

		return "movies-page";
	}

	@GetMapping("/soon")
	public String showSoonPage(Model model) {

		List<Movie> movies = movieService.getSoonMovieList();
		model.addAttribute("movies", movies);

		return "movies-page";
	}

	@GetMapping("/help")
	public String showHelpPage(Model model) {

		List<Faq> FAQs = new ArrayList<Faq>();
		FAQService.findAll().forEach(FAQs::add);
		// System.out.println(FAQs);
		model.addAttribute("FAQs", FAQs);

		return "help-page";
	}

}
