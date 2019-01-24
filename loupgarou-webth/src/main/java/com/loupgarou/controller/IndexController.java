package com.loupgarou.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.loupgarou.datajpa.*;
import com.loupgarou.model.*;

@Controller
public class IndexController {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String produits(Model model) {
		model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		//daoUtilisateur.findByUserName(username);
		return "index";
	}
	
	
	@PostMapping("/index/connexion")
	public RedirectView connexion(Model model, HttpSession session,
			@RequestParam(value="username", required=true) String name,
			@RequestParam(value="password", required=true) String pw
			) {
		if(daoUtilisateur.findByUserName(name) != null) {
			if(daoUtilisateur.findByUserName(name).getPassWord().equals(pw)) {
				if(daoUtilisateur.findByUserName(name).getIsBanni() == true) {
					System.out.println("utilisateur banni");
					RedirectView redirect = new RedirectView("/loupgarou-webth/index#connexion");
					redirect.setExposeModelAttributes(false);
					return redirect;
				}
				Utilisateur currentUser = daoUtilisateur.findByUserName(name);
				session.setAttribute("currentUser", currentUser);
				//System.out.println("Bienvenue "+name);
				return new RedirectView("/loupgarou-webth/rules");
			}
			else {
				model.addAttribute("wrongPW", true);
				System.out.println("erreur WP");
				RedirectView redirect = new RedirectView("/loupgarou-webth/index#connexion");
				redirect.setExposeModelAttributes(false);
				return redirect;
			}
		}
		else {
			System.out.println("utilisateur non présent dans la DB");
			return new RedirectView("/loupgarou-webth/index#inscription");
		}
	}
	
	@PostMapping("/index/inscription")
	public RedirectView inscription(Model model, HttpSession session,
			//@RequestParam(value="pass1", required=true) String pass1,
			@RequestParam(value="pass2", required=true) String pass2,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss,
			@Valid @ModelAttribute Utilisateur newUser, BindingResult result
			) {
			System.out.println(datenaiss);
		if(newUser.getPassWord().equals(pass2)) {
			newUser.setDateNaissance(datenaiss);
			System.out.println(newUser.getDateNaissance());
			
			if(result.hasErrors()) {
				System.out.println("User invalide");
				return new RedirectView("/loupgarou-webth/index#inscription");
			}
			else {
				System.out.println("User ok");
				daoUtilisateur.save(newUser);
				session.setAttribute("currentUser", newUser);
				model.addAttribute("utilisateurs", daoUtilisateur.findAll());
			    return new RedirectView("/loupgarou-webth/rules");
			}	
		}
		else {
			System.out.println("WP not corresp");
			return new RedirectView("/loupgarou-webth/index#inscription");
			
		}
		
	}
	
	
	// creer deux attributs : util inscrit et util non inscrit ??? 
	// newUser 
	// oldUser
	// currentUser

}
