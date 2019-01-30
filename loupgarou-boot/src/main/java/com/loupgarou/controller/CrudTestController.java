package com.loupgarou.controller;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.loupgarou.dao.IDAOUtilisateur;
import com.loupgarou.model.*;

import com.loupgarou.security.annotation.IsAdmin;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class CrudTestController {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@RequestMapping(value="/crudTest", method=RequestMethod.GET)
	public String users(Model model, @RequestParam(value="idEd", required=false) Integer id,
			@RequestParam(value="ban", required=false) Integer ban ) {
		if(id!=null) {
			if(ban!=null) { 
				Utilisateur user =  daoUtilisateur.findById(id).get(); 
				if(ban==1) {
					user.setIsBanni(true);
					daoUtilisateur.save(user);
					}
				if(ban==0) {
					user.setIsBanni(false);
					daoUtilisateur.save(user);
					}
				}
			model.addAttribute("utilisateur", daoUtilisateur.findById(id).get());
			model.addAttribute("idEd", id);
			}
		model.addAttribute("utilisateurs", daoUtilisateur.findAll());
		return "crudTest";
	}
	
	@IsAdmin
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
		else {
			utilisateur.setPassWord(new BCryptPasswordEncoder().encode(utilisateur.getPassWord()));
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

	@IsAdmin
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(value="idSpr", required=true, defaultValue="0") Integer id, Model model) {
		daoUtilisateur.deleteById(id);
		return "redirect:/crudTest";
	}

}
