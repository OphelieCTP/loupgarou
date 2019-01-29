package com.loupgarou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monCompte")
public class MonCompteController {
	
	@GetMapping
	public String afficherMonCompte() 
	{
		return "monCompte";
	}
	
}
