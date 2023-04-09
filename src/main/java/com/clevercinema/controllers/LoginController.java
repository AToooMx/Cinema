package com.clevercinema.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.clevercinema.model.Users;
import com.clevercinema.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/login")
	public String showLoginPage(Authentication authentication) {

		return authentication != null ? "redirect:/movies" : "login-page";

	}

	@GetMapping("/registration")
	public String showRegistrationPage(Authentication authentication) {

		return authentication != null ? "redirect:/movies" : "registration-page";
	}

	@PostMapping("/process-registration")
	public String processRegistration(@Valid Users user, BindingResult result, Authentication authentication) {

		if (authentication != null) {
			return "redirect:/movies";
		}

		if (result.hasErrors()) {

			return "redirect:/registration?formError";
		}

		if (userRepository.findByEmail(user.getEmail()) != null) {
			return "redirect:/registration?emailError";
		}

		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setPassword(encoder.encode(user.getPassword()));
		user.setDateChange(new Date());
		System.out.println(user);
		userRepository.save(user);

		return "redirect:/login?registerSuccess";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "name", editor);
		binder.registerCustomEditor(String.class, "surname", editor);
	}

}
