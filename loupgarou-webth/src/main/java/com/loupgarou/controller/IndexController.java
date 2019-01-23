package com.loupgarou.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.loupgarou.datajpa.*;
import com.loupgarou.model.*;

@Controller
public class IndexController {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String produits(Model model) {
		model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		return "index";
	}
	
//	@GetMapping({"/crudTest"})
//	public String redirAjoutUser(Model model) {
//		return "crudTest";
//	}
	
	@PostMapping("/index")
	public String ajouterUser(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result, Model model) { // possibilité de fabriquer les element a partir des parametres recuperes 
		System.out.println(utilisateur.getUserID());
		System.out.println(utilisateur.getUserName());
		System.out.println(utilisateur.getPassWord());
		System.out.println(utilisateur.getNbPlaintes());
		System.out.println(utilisateur.getDateNaissance());
		System.out.println(utilisateur.getIsBanni());
		System.out.println(utilisateur.getIsConnected());
		System.out.println("User invalide");
		if(result.hasErrors()) {
			System.out.println("User invalide");
			return "crudTest";
		}
		else {
			System.out.println("User ok");
			daoUtilisateur.save(utilisateur);
			model.addAttribute("utilisateurs", daoUtilisateur.findAll());
			return "monCompte";
		}		
	}
	
//	@GetMapping("/crudTest")
//	public String deleteUser(@RequestParam(value="id", required=false, defaultValue="0") Integer id, Model model) {
//		daoUtilisateur.deleteById(id);
//		return "crudTest";
//	}
//	
//	

}
