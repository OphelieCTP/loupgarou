package com.loupgarou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		List<Villageois> vills = daoVillageois.findByPartieID(1); 
		model.addAttribute("listVills", vills);
		
		return "jeu";
	}
	
	@PostMapping("/jeu/vote")
	public String EnregistreVote(@RequestParam Integer vote )
	{
		
		return "jeu";
	}
}
