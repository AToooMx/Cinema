package com.clevercinema.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.AllArgsConstructor;
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
import com.clevercinema.dto.UserDto;
import com.clevercinema.entity.Users;
import com.clevercinema.repository.UserRepository;
import com.clevercinema.services.UserService;

@RequestMapping("/admin/users")
@Controller
@AllArgsConstructor
public class AdminUserController {

	private final UserService userService;
	private final UserRepository userRepository;


	@GetMapping
	public String showAllUsers(Model model) {

		List<UserDto> users = userService.findAllUsersAndHesTickets();
		model.addAttribute("users", users);

		return "admin-users-page";
	}

	@GetMapping("{id}/roles")
	public String showAllUserRoles(@PathVariable("id") int id, Model model) {

		Users user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		model.addAttribute("authority", new AuthorityDto());

		return "admin-user-roles-page";
	}

	@GetMapping("{id}/roles/{roleId}/delete")
	public String deleteRoleProcess(@PathVariable("id") int id, @PathVariable("roleId") int roleId, Model model) {

		userService.deleteRoleById(roleId);

		return "redirect:/admin/users/" + id + "/roles";
	}

	@PostMapping("{id}/roles/add")
	public String addRoleProcess(@PathVariable("id") int id, @ModelAttribute("authority") AuthorityDto authorityDto) {
		System.out.println(authorityDto);
		userService.addRoleByUserId(authorityDto.getRole(), id);

		return "redirect:/admin/users/" + id + "/roles";
	}

	@GetMapping("{id}/delete/")
	public String deleteUserProcess(@PathVariable("id") int id) {

		userService.deleteUserById(id);

		return "redirect:/admin/users";
	}

	
	
}
