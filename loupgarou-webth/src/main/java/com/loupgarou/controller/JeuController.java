package com.loupgarou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String afficherJeu(Model model, HttpSession session) {
		Villageois currPlayer = daoVillageois.findById(7).get();
		session.setAttribute("currentPlayer", currPlayer);
		model.addAttribute("currentUser", currPlayer);
		
		List<Villageois> vills = daoVillageois.findByPartieID(1); 
		model.addAttribute("listVills", vills);
		
		return "jeu";
	}
	
	@PostMapping("/jeu/vote")
	public String EnregistreVote(@RequestParam Integer vote, HttpSession session)
	{
		Villageois currPlayer = (Villageois)session.getAttribute("currentPlayer");
		currPlayer.setVote(vote);
		System.out.println(currPlayer.getEndormit());
		daoVillageois.save(currPlayer);
		return "redirect:/jeu";
	}
}
