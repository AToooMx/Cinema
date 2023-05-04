package com.clevercinema.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clevercinema.dto.AuthorityDto;
import com.clevercinema.dto.SearchDto;
import com.clevercinema.dto.UserDto;
import com.clevercinema.entity.Users;
import com.clevercinema.model.Country;
import com.clevercinema.model.Genre;
import com.clevercinema.model.Movie;
import com.clevercinema.model.Producer;
import com.clevercinema.model.Studio;
import com.clevercinema.repository.UserRepository;
import com.clevercinema.services.MovieService;
import com.clevercinema.services.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = { "/", "" })
	public String showMainAdminPage() {

		return "admin-main-page";
	}

	@GetMapping("/movies")
	public String showMovies(@ModelAttribute("searchDto") SearchDto search, Model model) {
		
		List<Movie> movies = new ArrayList<>();
		if (search.getTitle() == null) {
			movies = movieService.findAllMovies();
		} else {
			movies = movieService.findMoviesByTitle(search.getTitle());
		}
		model.addAttribute("movies", movies);

		return "admin-movies-page";
	}

	@GetMapping("/movies/{id}")
	public String showMovieInfoPage(@PathVariable("id") int id, Model model) {

		Movie movie = movieService.findMovieById(id);
		
		model.addAttribute("movie", movie);
		model.addAttribute("genreDto", new Genre());
		model.addAttribute("studioDto", new Studio());
		model.addAttribute("producerDto", new Producer());
		model.addAttribute("countryDto", new Country());
		
		model.addAttribute("allAgeLimit", movieService.getAllAgeLimit());
		model.addAttribute("allLanguage", movieService.getAllLanguage());
		model.addAttribute("allGenre", movieService.getAllGenre());

		return "admin-movie-page";
	}
	

	@PostMapping("/movies/{id}/save")
	public String saveMovieInfo(@PathVariable("id") int id, @ModelAttribute("movie") Movie movie) {

		System.out.println(movie);

		return "redirect:/admin/movies/" + id;
	}
	
	@GetMapping("/movies/{id}/delete-genre-process/{genreId}")
	public String deleteGenreProcess(@PathVariable("id") int id, @PathVariable("genreId") int genreId) {

		movieService.deleteFilmGenreById(genreId);

		return "redirect:/admin/movies/" + id;
	}
	
	@PostMapping("/movies/{id}/add-genre-process")
	public String addGenreProcess(@PathVariable("id") int id, @ModelAttribute("genreDto") Genre genre) {

		movieService.addGenreForMovie(id, genre.getId());

		return "redirect:/admin/movies/" + id;
	}

	@GetMapping("/movies/{id}/delete-studio-process/{studioId}")
	public String deleteStudioProcess(@PathVariable("id") int id, @PathVariable("studioId") int studioId) {

		movieService.deleteFilmStudioById(studioId);

		return "redirect:/admin/movies/" + id;
	}
	
	@PostMapping("/movies/{id}/add-studio-process")
	public String addStudioProcess(@PathVariable("id") int id, @ModelAttribute("studioDto") Studio studio) {
		movieService.addStudioForMovie(id, studio.getName());

		return "redirect:/admin/movies/" + id;
	}
	
	@GetMapping("/movies/{id}/delete-producer-process/{producerId}")
	public String deleteProducerProcess(@PathVariable("id") int id, @PathVariable("producerId") int producerId) {

		movieService.deleteFilmStudioById(producerId);

		return "redirect:/admin/movies/" + id;
	}
	
	@PostMapping("/movies/{id}/add-producer-process")
	public String addProducerProcess(@PathVariable("id") int id, @ModelAttribute("producerDto") Producer producer) {
		//movieService.addStudioForMovie(id, producer.getName());

		return "redirect:/admin/movies/" + id;
	}
	
	@GetMapping("/seances")
	public String showAddSeacnesPage(Model model) {

		List<Movie> movies = movieService.getCurrentlyMoviesStreaming();
		model.addAttribute("movies", movies);

		return "admin-seances-page";
	}

	@GetMapping("/users")
	public String showAllUsers(Model model) {

		List<UserDto> users = userService.findAllUsersAndHesTickets();
		model.addAttribute("users", users);

		return "admin-users-page";
	}

	@GetMapping("/users/{id}/roles")
	public String showAllUserRoles(@PathVariable("id") int id, Model model) {

		Users user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		model.addAttribute("authority", new AuthorityDto());

		return "admin-user-roles-page";
	}

	@GetMapping("/users/{id}/roles/{roleId}/delete")
	public String deleteRoleProcess(@PathVariable("id") int id, @PathVariable("roleId") int roleId, Model model) {

		userService.deleteRoleById(roleId);

		return "redirect:/admin/users/" + id + "/roles";
	}

	@PostMapping("/users/{id}/roles/add")
	public String addRoleProcess(@PathVariable("id") int id, @ModelAttribute("authority") AuthorityDto authorityDto) {
		System.out.println(authorityDto);
		userService.addRoleByUserId(authorityDto.getRole(), id);

		return "redirect:/admin/users/" + id + "/roles";
	}

	@GetMapping("/users/{id}/delete/")
	public String deleteUserProcess(@PathVariable("id") int id) {

		userService.deleteUserById(id);

		return "redirect:/admin/users";
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "title", editor);
		binder.registerCustomEditor(String.class, "originalTitle", editor);
		binder.registerCustomEditor(String.class, "duration", editor);
		binder.registerCustomEditor(String.class, "photoName", editor);
		binder.registerCustomEditor(String.class, "description", editor);
		binder.registerCustomEditor(String.class, "name", editor);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, "startRental", customDateEditor);
		binder.registerCustomEditor(Date.class, "endRental", customDateEditor);
	}
	
	
}
