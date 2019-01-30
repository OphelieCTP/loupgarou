package com.loupgarou.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loupgarou.dao.IDAOPartie;
import com.loupgarou.dao.IDAOVillageois;
import com.loupgarou.model.Chat;
import com.loupgarou.model.Partie;
import com.loupgarou.model.Utilisateur;
import com.loupgarou.model.Villageois;
import com.loupgarou.security.UtilisateurPrincipal;

@Controller
@RequestMapping("/rejoindrePartie")

public class RejoindrePartieController {
	
	@Autowired 
	IDAOPartie daoPartie;
	
	@Autowired
	IDAOVillageois daoVillageois;
	
	@GetMapping
	public String afficheJeu(Principal principal, HttpSession session)
	{
		Villageois currPlayer = (Villageois)session.getAttribute("currentPlayer");
		if(currPlayer != null)
		{
			//L'utilsiateur a déjà rejoint une partie
			return "jeu";
		}
		UtilisateurPrincipal currUserP = (UtilisateurPrincipal)((UsernamePasswordAuthenticationToken)principal).getPrincipal();		
		Utilisateur currUser = currUserP.getUtilisateur();
		
		//Création d'une nouvelle partie
		Partie p = new Partie();
		p = daoPartie.save(p);
		
		//Ajout de l'utilisateur courrant à la partie
		Villageois v = new Villageois(currUser, "Loup", p);
		v.setVote(0);
		p.getJoueurs().add(v);
		daoVillageois.updateRole(v);
		System.out.println("OK ##################################################################");
		System.out.println(v);
		daoVillageois.save(v);
		
		System.out.println(currUser.getUserName() + " : " + currUser.getEmail());
		return "jeu";
	}
	
}
