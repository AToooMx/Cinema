package com.clevercinema.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clevercinema.dto.BonuseDto;
import com.clevercinema.dto.PaymentDto;
import com.clevercinema.dto.PickPlaceDto;
import com.clevercinema.entity.Users;
import com.clevercinema.model.Comment;
import com.clevercinema.model.Hall;
import com.clevercinema.model.Movie;
import com.clevercinema.model.Place;
import com.clevercinema.model.PlaceRank;
import com.clevercinema.model.Seance;
import com.clevercinema.repository.UserRepository;
import com.clevercinema.services.CinemaService;
import com.clevercinema.services.CommentService;
import com.clevercinema.services.MovieService;
import com.clevercinema.services.PlaceService;
import com.clevercinema.services.SeanceService;
import com.clevercinema.services.TicketService;

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
	@Autowired
	private PlaceService placeService;
	@Autowired
	private CinemaService cinemaService;
	@Autowired
	private TicketService ticketService;

	@GetMapping("/{id}")
	public String showMovie(@PathVariable("id") int id, Model model) {

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

	@PostMapping("/{id}/add-comment-process")
	public String addComment(@PathVariable("id") int id, @Valid Comment comment, BindingResult result,
			Authentication authentication) {

		if (result.hasErrors()) {
			return "redirect:/movies/" + comment.getIdMovie() + "?emptyTextArea";
		}

		// System.out.println(comment.getInfo());
		Users user = userRepository.findByEmail(authentication.getName());
		comment.setIdUser(user.getId());
		commentService.saveComment(comment);

		return "redirect:/movies/" + comment.getIdMovie();
	}

	@PostMapping("/{id}/delete-comment/{commentId}")
	public String deleteComment(@PathVariable("commentId") int commentId, @PathVariable("id") int id) {

		commentService.removeCommentById(commentId);

		return "redirect:/movies/" + id;
	}

	@GetMapping("/{id}/seance/{seanceId}")
	public String showSeancePage(@PathVariable("seanceId") int seanceId, @PathVariable("id") int id, Model model,
			HttpSession session, Authentication authentication) {

		Movie movie = movieService.findMovieById(id);
		Seance seance = seanceService.findSeanceById(seanceId);
		Hall hall = seanceService.findHallBySeanceId(seanceId);
		Map<Integer, List<Place>> places = seanceService.findAllPlacesByHallAndSeanceId(seanceId, hall.getId(),
				session.getId());
		List<PlaceRank> placeRank = placeService.findAllPlaceRank();
		List<PickPlaceDto> pickPlaces = seanceService.findAllPickPlacesBySeanceAndSessionId(seanceId, session.getId());

		if(authentication!=null) {
			Users user = userRepository.findByEmail(authentication.getName());
			model.addAttribute("bonusDto", new BonuseDto());
			model.addAttribute("user", user);
		}
		
		model.addAttribute("movie", movie);
		model.addAttribute("seance", seance);
		model.addAttribute("hall", hall);
		model.addAttribute("places", places);
		model.addAttribute("placeRank", placeRank);
		model.addAttribute("pickPlace", pickPlaces);
		model.addAttribute("sumAllTickets", getSumAllTickets(pickPlaces));

		return "seance-page";
	}

	@PostMapping("/{id}/seance/{seanceId}/pick-seat")
	public String pickSeat(@PathVariable("seanceId") int seanceId, @PathVariable("id") int id,
			@RequestParam("seatId") int seatId, HttpSession session) {

		placeService.pickPlace(session.getId(), seatId, seanceId);
		
		return "redirect:/movies/" + id + "/seance/" + seanceId;
	}

	@GetMapping("/{id}/seance/{seanceId}/buy-ticket")
    public String showBuyTicketPage(@PathVariable("seanceId") int seanceId, @PathVariable("id") int id, @ModelAttribute("bonuseDto") BonuseDto bonuse, HttpSession session, Model model,  Authentication authentication) {
    	List<PickPlaceDto>pickPlaces = seanceService.findAllPickPlacesBySeanceAndSessionId(seanceId, session.getId());
    	
    	if(pickPlaces.size() == 0) {
    		
    		return "redirect:/movies/"+id+"/seance/"+seanceId + "?notSeatSelect";
    	}
    	Movie movie = movieService.findMovieById(id);
    	Seance seance = seanceService.findSeanceById(seanceId);
    	Hall hall = seanceService.findHallBySeanceId(seanceId);
    	Users user = userRepository.findByEmail(authentication.getName());
    	double sumAllTickets =  getSumAllTickets(pickPlaces);
    	PaymentDto payment = new PaymentDto();
    	
    	if(bonuse.isConfirm()) {
    		sumAllTickets-=user.getBonuse();
    		pickPlaces.get(0).setPrice(pickPlaces.get(0).getPrice()-user.getBonuse());
    		payment.setUseBonuse(true);
    	}else {
    		payment.setUseBonuse(false);
    	}
    	
    	model.addAttribute("movie", movie);
    	model.addAttribute("pickPlace", pickPlaces);
    	model.addAttribute("seance", seance);
    	model.addAttribute("hall", hall);
    	model.addAttribute("cinema", cinemaService.findCinemaByHallId(hall.getId()));
    	model.addAttribute("sumAllTickets", sumAllTickets);
    	model.addAttribute("payment", payment);
    	
        return "buy-ticket-page";
    }
	
	@PostMapping("/{id}/seance/{seanceId}/buy-ticket/process-buy-ticket")
	public String processBuyTicket(@PathVariable("seanceId") int seanceId, @PathVariable("id") int id, @Valid @ModelAttribute("payment") PaymentDto payment, BindingResult result, HttpSession session, Authentication authentication) {
		
		if(result.hasErrors()) {
			return "redirect:/movies/"+id+"/seance/"+seanceId+"/buy-ticket";
		}
		
		List<PickPlaceDto>pickPlaces = seanceService.findAllPickPlacesBySeanceAndSessionId(seanceId, session.getId());
		Users user = userRepository.findByEmail(authentication.getName());
		double sumAllTickets;
		
		if(payment.isUseBonuse()) {
			pickPlaces.get(0).setPrice(pickPlaces.get(0).getPrice()-user.getBonuse());
			sumAllTickets =  getSumAllTickets(pickPlaces);
			user.setBonuse((int)(sumAllTickets/10));
		}else {
			sumAllTickets =  getSumAllTickets(pickPlaces);
			user.setBonuse(user.getBonuse() + (int)(sumAllTickets/10));
			
		}
		
		userRepository.save(user);
		
		int userId = user.getId();
		
		ticketService.saveTicket(userId, pickPlaces);
		placeService.cleanSessionTableBySessionId(session.getId());
		
		return "success-buy-ticket";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "info", editor);
	}

	public double getSumAllTickets(List<PickPlaceDto> ticketList) {
		double sum = 0;

		for (int i = 0; i < ticketList.size(); i++) {
			sum += ticketList.get(i).getPrice();
		}

		return sum;
	}
}
