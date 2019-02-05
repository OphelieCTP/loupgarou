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
import com.loupgarou.dao.*;
import com.loupgarou.model.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CrudRestController {
	@Autowired
	IDAOUtilisateur daoUtilisateur;
	
	@GetMapping("/crud")
	@JsonView(Views.Utilisateur.class)
	public List<Utilisateur> getUser() {
		List<Utilisateur> utilisateurs = daoUtilisateur.findAll();
		return utilisateurs;
	}
	

}
