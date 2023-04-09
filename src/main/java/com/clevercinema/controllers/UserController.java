package com.clevercinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clevercinema.dto.ChangePasswordDto;
import com.clevercinema.model.Users;
import com.clevercinema.repository.UserRepository;

@Controller
@RequestMapping("/account")
public class UserController {

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/changePassword")
	public String showChangePasswordPage() {

		return "change-password-page";
	}

	@PostMapping("/save-password")
	public String changePassword(ChangePasswordDto changePasswordDto, Authentication authentication) {

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
}
