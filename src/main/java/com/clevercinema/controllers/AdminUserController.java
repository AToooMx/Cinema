package com.clevercinema.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AdminController {

	private final UserService userService;
	private final UserRepository userRepository;


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
