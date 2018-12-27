package com.loupgarou;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.*;

import com.loupgarou.model.*;

import com.loupgarou.divers.*;
import com.loupgarou.dao.*;
import com.loupgarou.dao.jpa.*;


public class PrincipaleJPA {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoupGarouPU");
		EntityManager em = emf.createEntityManager();
		//IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA(emf);
		IDAOPartie daoPartie = new DAOPartieJPA(emf);
		IDAOVillageois daoVillageois = new DAOVillageoisJPA(emf);
		IDAOMessage daoMessage = new DAOMessageJPA(emf);
		IDAOChat daoChat = new DAOChatJPA(emf);
		
		List<Villageois> vill = daoVillageois.findAll();
		System.out.println("Test : list villageois ");
		for (Villageois v : vill) {
			System.out.println("-------------------");
			System.out.println(v.getRole());
		}
	}

}
