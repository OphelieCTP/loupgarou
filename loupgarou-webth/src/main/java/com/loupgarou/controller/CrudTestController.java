package com.loupgarou.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
public class CrudTestController {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@Autowired
	private IDAOVillageois daoVillageois;
	
	@RequestMapping(value="/crudTest", method=RequestMethod.GET)
	public String produits(Model model) {
		model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		return "crudTest";
	}
	
//	@GetMapping({"/crudTest"})
//	public String redirAjoutUser(Model model) {
//		return "crudTest";
//	}
	
	@PostMapping("/crudTest")
	public String ajouterUser(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result, Model model, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss) { // possibilité de fabriquer les element a partir des parametres recuperes 
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		Date date = sdf.parse(utilisateur.getDateNaissance());
//		System.out.println(datenaiss);
//		utilisateur.setDateNaissance(datenaiss);
		if(result.hasErrors()) {
			System.out.println("User invalide");
			return "crudTest";
		}
		else {
			System.out.println("User ok");
			daoUtilisateur.save(utilisateur);
			model.addAttribute("utilisateurs", daoUtilisateur.findAll());
			return "crudTest";
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
