package com.loupgarou.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loupgarou.dao.IDAOVillageois;
import com.loupgarou.model.Villageois;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class JeuRestController {
	@Autowired
	IDAOVillageois daoVillageois;
	
	@GetMapping("/jeu")
	public List<Villageois> getJoueurs() {
		List<Villageois> mesVillageois = daoVillageois.findByPartieID(1);
		return mesVillageois;
	}
	
	
	//attendre l'identifiant du joueur a modifier
	@PostMapping("/jeu/vote")
	public Villageois EnregistreVote(@RequestParam Integer vote, HttpSession session)
	{
		Villageois currPlayer = (Villageois)session.getAttribute("currentPlayer");
		currPlayer.setVote(vote);
		System.out.println(currPlayer.getEndormit());
		daoVillageois.save(currPlayer);
		return currPlayer;
	}
	
	@GetMapping("jeu/sauver/{sauverId}")
	public String sauverVillageois(@PathVariable Integer sauverId)
	{
		Villageois aSauver = daoVillageois.findById(sauverId).get();
		aSauver.setVivant(true);
		daoVillageois.save(aSauver);
		return "redirect:/jeu";
	}
	
	@PostMapping("jeu/tuer")
	public String tuerVillageois(@RequestParam Integer atuer)
	{
		Villageois victime = daoVillageois.findById(atuer).get();
		victime.setVivant(false);
		daoVillageois.save(victime);
		return "redirect:/jeu";
	}
	
	@PostMapping("jeu/voir")
	public String voirVillageois(@RequestParam Integer avoir, HttpSession session)
	{
		Villageois vu = daoVillageois.findById(avoir).get();
		List<Villageois> joueurVu = (List<Villageois>)session.getAttribute("joueurVu");
		if(joueurVu == null)
		{
			joueurVu = new ArrayList<Villageois>();
		}
		joueurVu.add(vu);
		session.setAttribute("joueurVu", joueurVu);
		return "redirect:/jeu";
	}
	
	@PostMapping("jeu/couple")
	public String formerCouple(@RequestParam Integer lover1Id, @RequestParam Integer lover2Id, HttpSession session)
	{
		if(lover1Id != lover2Id)
		{
			Villageois lover1 = daoVillageois.findById(lover1Id).get();
			lover1.setAmoureux(true);
			daoVillageois.save(lover1);
			Villageois lover2 = daoVillageois.findById(lover2Id).get();
			lover2.setAmoureux(true);
			daoVillageois.save(lover2);
			Boolean cupidonPouv = (Boolean)session.getAttribute("cupidonPouv");
			cupidonPouv = false;
			session.setAttribute("cupidonPouv", cupidonPouv);
		}
		return "redirect:/jeu";
	}
}
