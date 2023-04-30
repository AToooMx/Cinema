package com.clevercinema.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;

import com.clevercinema.dto.RegisterDto;
import com.clevercinema.services.PlaceService;
import com.clevercinema.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private PlaceService placeService;

	@GetMapping("/login")
	public String showLoginPage(Authentication authentication, HttpSession session) {

		placeService.cleanSessionTableBySessionId(session.getId());
		
		return authentication != null ? "redirect:/movies" : "login-page";

	}

	@GetMapping("/registration")
	public String showRegistrationPage(Model model, Authentication authentication) {

		model.addAttribute("user", new RegisterDto());

		return authentication != null ? "redirect:/movies" : "registration-page";
	}

	@PostMapping("/process-registration")
	public String processRegistration(@Valid @ModelAttribute("user") RegisterDto userDto, BindingResult result, Authentication authentication) {

		if (authentication != null) {
			return "redirect:/movies";
		}

		if (result.hasErrors()) {

			return "registration-page";
		}

		if (!userService.save(userDto)) {
			return "redirect:/registration?emailError";
		} else {
			return "redirect:/login?registerSuccess";
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "name", editor);
		binder.registerCustomEditor(String.class, "surname", editor);
	}

}
