package com.clevercinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeanceController {

	@GetMapping("/seance")
	public String showSeancePage(@RequestParam("id") int id) {
		
		
		
		return "seance-page";
	}
	
}
