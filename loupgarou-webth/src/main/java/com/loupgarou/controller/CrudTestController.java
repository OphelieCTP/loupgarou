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
	public String users(Model model) {
		model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		return "crudTest";
	}
	
	@PostMapping("/crudTest")
	public String ajouterUser(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result, Model model, 
			// @RequestParam(value="idSpr", required=false, defaultValue="0") Integer id,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss) { // possibilité de fabriquer les element a partir des parametres recuperes 
		utilisateur.setDateNaissance(datenaiss);
		if(result.hasErrors()) {
			System.out.println("User invalide");
			return "crudTest";
		}
		else {
			System.out.println("User ok");
//			if (id!=null) {
//				model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
//				utilisateur.setUserID(id);
//			}
			daoUtilisateur.save(utilisateur);
			model.addAttribute("utilisateurs", daoUtilisateur.findAll());
			return "crudTest";
		}		
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(value="idSpr", required=true, defaultValue="0") Integer id, Model model) {
		daoUtilisateur.deleteById(id);
		return "redirect:/crudTest";
	}

	@GetMapping({"/editUser"})
	public String redirEditProd(@RequestParam(value="idEd", required=false, defaultValue="0") Integer id, Model model) {
		model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
		return "editUser";
	}

	
	@PostMapping("/editUser")
	public String editUser(@RequestParam(value="idEd", required=true, defaultValue="0") int id, 
			@Valid @ModelAttribute Utilisateur utilisateur, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss) {
		utilisateur.setDateNaissance(datenaiss);
		utilisateur.setUserID(id);
		daoUtilisateur.save(utilisateur);
		return "redirect:/crudTest";
	}

}
