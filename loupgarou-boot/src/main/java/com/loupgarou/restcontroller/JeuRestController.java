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

import com.fasterxml.jackson.annotation.JsonView;
import com.loupgarou.dao.IDAOVillageois;
import com.loupgarou.model.Views;
import com.loupgarou.model.Villageois;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class JeuRestController {
	@Autowired
	IDAOVillageois daoVillageois;
	
	@GetMapping("/jeu")
	@JsonView(Views.Villageois.class)
	public List<Villageois> getJoueurs() {
		List<Villageois> mesVillageois = daoVillageois.findByPartieID(1);
		return mesVillageois;
	}
	
	@GetMapping("/jeu/{askId}")
	@JsonView(Views.Villageois.class)
	public Villageois getJoueur(@PathVariable Integer askId) {
		Villageois vill = daoVillageois.findById(askId).get();
		return vill;
	}
	
	
	//attendre l'identifiant du joueur a modifier
	@PostMapping("/jeu/vote")
	@JsonView(Views.Villageois.class)
	public Villageois EnregistreVote(@RequestParam Integer vote, @RequestParam Integer id)
	{
		Villageois currPlayer = daoVillageois.findById(id).get();
		currPlayer.setVote(vote);
		System.out.println(currPlayer.getEndormit());
		daoVillageois.save(currPlayer);
		return currPlayer;
	}
	
	@PostMapping("jeu/sauver/{sauverId}")
	@JsonView(Views.Villageois.class)
	public Villageois sauverVillageois(@PathVariable Integer sauverId)
	{
		Villageois aSauver = daoVillageois.findById(sauverId).get();
		aSauver.setVivant(true);
		daoVillageois.save(aSauver);
		return aSauver;
	}
	
	@PostMapping("jeu/tuer")
	@JsonView(Views.Villageois.class)
	public Villageois tuerVillageois(@RequestParam Integer atuer)
	{
		Villageois victime = daoVillageois.findById(atuer).get();
		victime.setVivant(false);
		daoVillageois.save(victime);
		return victime;
	}
	
	@PostMapping("jeu/voir")
	@JsonView(Views.Villageois.class)
	public Villageois voirVillageois(@RequestParam Integer avoir, HttpSession session)
	{
		Villageois vu = daoVillageois.findById(avoir).get();
		List<Villageois> joueurVu = (List<Villageois>)session.getAttribute("joueurVu");
		if(joueurVu == null)
		{
			joueurVu = new ArrayList<Villageois>();
		}
		joueurVu.add(vu);
		session.setAttribute("joueurVu", joueurVu);
		return vu;
	}
	
	@PostMapping("jeu/couple")
	@JsonView(Views.Villageois.class)
	public List<Villageois> formerCouple(@RequestParam Integer lover1Id, @RequestParam Integer lover2Id, HttpSession session)
	{
		List<Villageois> couple = new ArrayList<Villageois>();
		if(lover1Id != lover2Id)
		{
			Villageois lover1 = daoVillageois.findById(lover1Id).get();
			lover1.setAmoureux(true);
			couple.add(daoVillageois.save(lover1));
			Villageois lover2 = daoVillageois.findById(lover2Id).get();
			lover2.setAmoureux(true);
			couple.add(daoVillageois.save(lover2));
			Boolean cupidonPouv = (Boolean)session.getAttribute("cupidonPouv");
			cupidonPouv = false;
			session.setAttribute("cupidonPouv", cupidonPouv);
		}
		return couple;
	}
}
