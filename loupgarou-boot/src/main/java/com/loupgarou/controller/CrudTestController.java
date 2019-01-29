package com.loupgarou.controller;

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
import org.springframework.web.servlet.view.RedirectView;

import com.loupgarou.dao.IDAOUtilisateur;
import com.loupgarou.model.*;

@Controller
public class CrudTestController {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@RequestMapping(value="/crudTest", method=RequestMethod.GET)
	public String users(Model model, 
			@RequestParam(value="idEd", required=false) Integer id,
			@RequestParam(value="ban", required=false) Integer ban
			) {
		//RedirectView redirect = new RedirectView("crudTest");
		if(ban!=null) { 
			if(ban==1) {
				model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
				Utilisateur user =  daoUtilisateur.findById(id).get(); 
				user.setIsBanni(true);
				daoUtilisateur.save(user);
				}
			if(ban==0) {
				model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
				Utilisateur user =  daoUtilisateur.findById(id).get(); 
				user.setIsBanni(false);
				daoUtilisateur.save(user);
				}
			}
		if(id!=null) { model.addAttribute("idEd", id); }
		model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		//redirect.setExposeModelAttributes(false);
		return "crudTest";
		//return redirect;
	}
	
	@PostMapping("/crudTest")
	public RedirectView editUser(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result, Model model, 
			@RequestParam(value="idEd", required=false) Integer id,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss) {
		RedirectView redirect = new RedirectView("crudTest");
		if(id!=null) {
			model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
			utilisateur.setUserID(id);
			model.addAttribute("idEd", id);
		}
		
		utilisateur.setDateNaissance(datenaiss);
		
		if(result.hasErrors()) {
			System.out.println("User invalide");
			return redirect;
		}
		else {
			System.out.println("User ok");
			daoUtilisateur.save(utilisateur);
			model.addAttribute("utilisateurs", daoUtilisateur.findAll());
			redirect.setExposeModelAttributes(false);
		    return redirect;
		}		
	}
	
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(value="idSpr", required=true, defaultValue="0") Integer id, Model model) {
		daoUtilisateur.deleteById(id);
		return "redirect:/crudTest";
	}
	
	/// VERSION FONCTIONNELLE EDITION EN PLUSIEURS PAGES : 
	
//	@GetMapping({"/editUser"})
//	public String redirEditProd(@RequestParam(value="idEd", required=false, defaultValue="0") Integer id, Model model) {
//		model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
//		return "editUser";
//	}
//
//	
//	@PostMapping("/editUser")
//	public String editUser(@RequestParam(value="idEd", required=true, defaultValue="0") int id, 
//			@Valid @ModelAttribute Utilisateur utilisateur, @DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaiss) {
//		utilisateur.setDateNaissance(datenaiss);
//		utilisateur.setUserID(id);
//		daoUtilisateur.save(utilisateur);
//		return "redirect:/crudTest";
//	}

}
