package com.loupgarou.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.loupgarou.dao.IDAOUtilisateur;
import com.loupgarou.model.Utilisateur;
import com.loupgarou.security.UtilisateurPrincipal;
import com.loupgarou.security.annotation.IsAdmin;

@Controller
@RequestMapping("/monCompte")
public class MonCompteController {
	
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@GetMapping
	public String afficherMonCompte(Model model, HttpSession session, Principal principal) 
	{
		UtilisateurPrincipal currUserP = (UtilisateurPrincipal)((UsernamePasswordAuthenticationToken)principal).getPrincipal();		
		Utilisateur currUser = currUserP.getUtilisateur();
		model.addAttribute("currentUser", currUser);
		return "monCompte";
	}


	@PostMapping
	public String edit(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result, Model model,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss) {
		
		model.addAttribute("utilisateur", daoUtilisateur.findById(utilisateur.getUserID()).get());
		
		if(utilisateur.getPassWord()!=daoUtilisateur.findById(utilisateur.getUserID()).get().getPassWord()) {
			utilisateur.setPassWord(new BCryptPasswordEncoder().encode(utilisateur.getPassWord()));
		}
		
		utilisateur.setDateNaissance(datenaiss);
		
		if(result.hasErrors()) {
			System.out.println("User invalide");
			return "monCompte";
		}
		else {
			System.out.println("User ok");
			daoUtilisateur.save(utilisateur);
			model.addAttribute("currentUser", utilisateur);
			model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		    return "monCompte";
		}		
	}
	
}
