package com.clevercinema.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.clevercinema.entity.Faq;
import com.clevercinema.model.Contact;
import com.clevercinema.model.Movie;
import com.clevercinema.services.EmailService;
import com.clevercinema.services.FAQService;
import com.clevercinema.services.MovieService;

@Controller
public class MainController {
	@Autowired
	private MovieService movieService;
	@Autowired
	private FAQService FAQService;
	@Autowired
	private EmailService emailService;

	@GetMapping("")
	public String showPage() {

		return "redirect:/movies";
	}

	@GetMapping("/")
	public String showHomePage() {
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
	
	@GetMapping("/contact")
	public String showContactPage(Model model) {
		
		model.addAttribute("contact", new Contact());
		
		return "contact-page";
	}
	
	@PostMapping("/contact/process-send-message")
	public String sendMessage(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
	
		if(result.hasErrors()) {
			System.out.println(result.toString());
			return "contact-page";
		}
		/*
		 * String message = "Ім'я: " + contact.getName() + ". Повідомлення: " + contact.getMessage();
		 * emailService.sendSimpleMessage(contact.getEmail(), contact.getTitle(),
		 * message);
		 */
		
		return "redirect:/contact?sendMessageSuccess";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "name", editor);
		binder.registerCustomEditor(String.class, "email", editor);
		binder.registerCustomEditor(String.class, "title", editor);
		binder.registerCustomEditor(String.class, "message", editor);
	}
	
}
