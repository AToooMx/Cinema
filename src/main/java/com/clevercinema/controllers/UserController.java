package com.clevercinema.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clevercinema.dto.ChangePasswordDto;
import com.clevercinema.entity.Users;
import com.clevercinema.repository.UserRepository;

@Controller
@RequestMapping("/profile")
public class UserController {

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String showAccountPage() {
		return "account-page";
	}
	
	
	@GetMapping("/changePassword")
	public String showChangePasswordPage(Model model) {

		model.addAttribute("changePassword", new ChangePasswordDto());
		
		return "change-password-page";
	}

	@PostMapping("/save-password")
	public String changePassword(@Valid @ModelAttribute("changePassword") ChangePasswordDto changePasswordDto, BindingResult result,
			Authentication authentication) {

		if(result.hasErrors()) {
			
			return "change-password-page";
		}
		
		if (changePasswordDto.getNewPassword().equals(changePasswordDto.getReNewPassword())) {

			Users user = userRepository.findByEmail(authentication.getName());
			boolean isMatches = encoder.matches(changePasswordDto.getOldPassword(), user.getPassword());

			if (isMatches) {

				String passwordEncode = encoder.encode(changePasswordDto.getNewPassword());
				user.setPassword(passwordEncode);
				userRepository.save(user);

				return "redirect:/account/changePassword?success";
			} else {
				return "redirect:/account/changePassword?oldPasswordInvalid";
			}

		} else {
			return "redirect:/account/changePassword?passwordMismatch";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "oldPassword", editor);
		binder.registerCustomEditor(String.class, "newPassword", editor);
		binder.registerCustomEditor(String.class, "reNewPassword", editor);
	}
}
