package com.loupgarou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.loupgarou.datajpa.IDAOVillageois;
import com.loupgarou.model.Villageois;

@Controller
public class JeuController {
	
	@Autowired
	IDAOVillageois daoVillageois;
	
	@GetMapping("/jeu")
	public String afficherJeu(Model model) {
		Villageois currPlayer = daoVillageois.findById(7).get();
		model.addAttribute("currentUser", currPlayer);
		System.out.println(currPlayer.getRole());
		return "jeu";
	}
}
