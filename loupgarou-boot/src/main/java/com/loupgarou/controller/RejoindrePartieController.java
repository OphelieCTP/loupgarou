package com.loupgarou.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loupgarou.model.Utilisateur;
import com.loupgarou.security.UtilisateurPrincipal;

@Controller
@RequestMapping("/rejoindrePartie")

public class RejoindrePartieController {
	
	@GetMapping
	public String afficheJeu(Principal principal)
	{
		
		System.out.println(principal.getClass());
		UtilisateurPrincipal currUserP = (UtilisateurPrincipal)((UsernamePasswordAuthenticationToken)principal).getPrincipal();		
		Utilisateur currUser = currUserP.getUtilisateur();
		System.out.println(currUser.getUserName() + " : " + currUser.getEmail());
		return "jeu";
	}
	
}
