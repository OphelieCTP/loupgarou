package com.loupgarou;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import java.lang.Object;

import javax.persistence.*;

import com.loupgarou.model.*;

import com.loupgarou.divers.*;
import com.loupgarou.dao.*;
import com.loupgarou.dao.jpa.*;


public class PrincipaleJPA {
	
	public static int menuBienvenue()
	{
		System.out.println(" ########## MENU ##########");
		System.out.println("1- Se connecter");
		System.out.println("2- S'inscrire");
		
		return fonctions.lireEntier();
	}
	
	
	public static int menu() {
		System.out.println("  ##########     MENU    ##########  ");
		System.out.println("1- Actualiser informations compte utilisateur.");
		System.out.println("2- Voir les autres joueurs connectés.");
		System.out.println("3- Démarrer une partie.");
		System.out.println("4- Rejoindre une partie.");
//		System.out.println("5- Voir les parties disponibles.");
//		System.out.println("6- Supprimer compte utilisateur");
//		System.out.println("-----------------");
//		System.out.println("7- Ajouter partie");
//		System.out.println("8- Ajouter villageois");
//		System.out.println("9- Lire partie");
//		System.out.println("10- Lire villageois");
//		System.out.println("11- Update partie");
//		System.out.println("12- Update villageois");
//		System.out.println("13- Delete partie");
//		System.out.println("14- Delete villageois");
//		System.out.println("0- End");
		
		return fonctions.lireEntier();
	}
	
	public static void supprimerCompteUtilisateur(Utilisateur u, IDAOUtilisateur daoUtilisateur) {
		System.out.println(" Vous êtes certains de vouloir supprimer votre compte ? [Y/N]");
		if(fonctions.lireChaine()=="Y") {
			daoUtilisateur.deleteById(u.getUserID());
			//deconnexion();
		}
	}
	
	public static void actualiserCompteUtilisateur(Utilisateur u, IDAOUtilisateur daoUtilisateur) {
		System.out.println("");
		System.out.println("Que voulez-vous actualiser ?");
		System.out.println("1- Mot de passe.");
		System.out.println("2- Email.");
		System.out.println("3- Date naissance.");
		System.out.println("4- Nom utilisateur.");
		System.out.println("5- Pas de modifications.");
		int choix = fonctions.lireEntier();
		while (choix != 5) {
			System.out.println("Nouvelle valeur ?");
			switch(choix) {
			case 1 : u.setPassWord(fonctions.lireChaine());
				break;
			case 2 : u.setEmail(fonctions.lireChaine());
				break;
			case 3 : System.out.println("Pas encore fonctionnel ");
				break;
				//u.setDateNaissance(fonctions.lireChaine());
			case 4 : u.setUserName(fonctions.lireChaine());
				break;
			}
		}
		daoUtilisateur.save(u);
		}
	
	public static void voirJoueursConnectes(IDAOUtilisateur daoUtilisateur) {
		List<Utilisateur> user = daoUtilisateur.findAll();
		System.out.println("Joueurs connectés :");
		for (Utilisateur u : user) {
			if(u.getIsConnected()==true) {
				System.out.println("-----------------");
				System.out.println("Utilisateur "+u.getUserID()+" "+u.getUserName());
				}
		}
	}
	
	public static void voirParties(IDAOPartie daoPartie) {
		List<Partie> parties = daoPartie.findAll();
		System.out.println("  Actuellement, il y a "+parties.size()+" parties.");
		for (Partie p : parties) {
			System.out.println("-----------------");
			System.out.println("La partie "+p.getId());
			System.out.println("Avec : "+p.getJoueurs().size()+" joueurs actuellement.");
			String etatPartie = "";
			if(p.getEtat()==false) { etatPartie = "fermée, vous ne pouvez la rejoindre."; }
			else { etatPartie = "ouverte, vous pouvez la rejoindre"; }
			System.out.println("Elle est actuellement : "+etatPartie);
		}
	}
	
	public static Partie creerPartie(IDAOPartie daoPartie, IDAOChat daoChat) {
		Partie p = new Partie();
		p.setChat(new Chat());
		p.setId(0);
		daoChat.save(p.getChat());
		daoPartie.save(p);
		return p;
	}
	
	
	
//	public static List<Villageois> lireVillageoisPartie(Utilisateur u, String role, Partie p, IDAOVillageois daoVillageois) {
//		//
//		return v;
//	}
	
	public static Villageois ajouterVillageois(Utilisateur u, String role, Partie p, IDAOVillageois daoVillageois) {
		System.out.println(" getUserID "+u.getUserID());
		Villageois v = new Villageois(u, role, p);
		p.getJoueurs().add(v);
		v.setVillID(0);
		daoVillageois.save(v);
		return v;
	}
	
	public static void jouer(IDAOPartie daoPartie, Partie p) {
		System.out.println("en cours de paramétrage"); 
	}
	
	public static void controleOuverturePartie(IDAOPartie daoPartie, Partie p) {
		p.getDateCreation();
		System.out.println("en cours de paramétrage"); 
		System.out.println(p.getDateCreation()); 
	}

	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoupGarouPU");
		EntityManager em = emf.createEntityManager();
		
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurJPA(emf);
		IDAOPartie daoPartie = new DAOPartieJPA(emf);
		IDAOVillageois daoVillageois = new DAOVillageoisJPA(emf);
		IDAOMessage daoMessage = new DAOMessageJPA(emf);
		IDAOChat daoChat = new DAOChatJPA(emf);
		
		//Creation du chat par default
		Chat chatdefault = new Chat();
		daoChat.save(chatdefault);
		
		List<Villageois> vill = daoVillageois.findAll();
		System.out.println("Test : list villageois ");
		for (Villageois v : vill) {
			System.out.println("-------------------");
			System.out.println(v.getRole());
		}
		
		int choixMenuLogin = -1;
		Utilisateur currentUser = null;
		while(choixMenuLogin != 0) {
			choixMenuLogin = menuBienvenue();
			switch(choixMenuLogin) {
			case 1 :
				System.out.println("Veuillez saisir votre nom d'utilisateur :");
				String userName = fonctions.lireChaine();
				System.out.println("Veuillez saisir votre mot de passe :");
				String password = fonctions.lireChaine();
				currentUser = daoUtilisateur.authentification(userName, password); 
				if(currentUser != null){
					System.out.println("Bienvenue " + userName + " !");
					currentUser.setIsConnected(true);
					daoUtilisateur.save(currentUser);
					choixMenuLogin = 0;
					int choix = 1;
					while (choix != 0) {
						choix = menu();
						switch(choix) {
						case 1 : actualiserCompteUtilisateur(currentUser, daoUtilisateur);
							break;
						case 2 : voirJoueursConnectes(daoUtilisateur);
						case 3 : Partie p = creerPartie(daoPartie, daoChat);
							System.out.println("-------------------");
							controleOuverturePartie(daoPartie, p);
						
						// "timer" s'enclenche
//							p.setEtat(true);
//							ajouterVillageois(currentUser, "Villageois", p, daoVillageois);
//							//int i = p.getDateCreation().getMinutes()+5;
//							
//							Timer timer = new Timer();
//							TimerTask timers = new TimerTask() {
//								public void run() {
//									p.setEtat(false);
//								}
//							};
//							
//							timer.schedule(timers, (5*60*1000));
//							if(p.getEtat()==false) {
//								jouer(daoPartie, p);
//							}
//							
							
							
							//timeLimit.setMinutes(p.getDateCreation().getMinutes()+5);
							
//							Boolean timeOut = false;
//							while (new Date()<p.getDateCreation())
//							Timer timer = new Timer();
//							
//							// deux cas : fermeture manuelle et time limite
//						
//							Date timeLimit = new Date();
//							Date timeLim = DateUtils.addHours(timeLimit, 3);
//							p.getDateCreation();
//							// créer partie
//							// créer temps pendant lequel la partie sera ouverte
//							
//							// démarer la partie
						case 4 : voirParties(daoPartie);
						case 5 : System.out.println("en cours de developpement"); //
							
						}
					}
				}
			break;

			case 2 :
				//Code pour s'inscrire 
				boolean dispo = false;
				String newName;
				do
				{
					System.out.println("Veuillez saisir un nom d'utilisateur");
					newName = fonctions.lireChaine();
					currentUser = daoUtilisateur.findByUsername(newName);
					if(currentUser != null)
					{
						System.out.println("Le nom d'utilisateur n'est pas disponible.");
						currentUser = null;
					}
					else
					{
						dispo = true;
						currentUser = new Utilisateur();
						currentUser.setUserName(newName);
					}
				}while(dispo == false);
				System.out.println("Veuillez saisir un mot de passe :");
				currentUser.setPassWord(fonctions.lireChaine());
				System.out.println("Veuillez saisir votre date de naissance (JJ-MM-YYYY) :");
				currentUser.setDateNaissance(fonctions.stringToSQLDate(fonctions.lireChaine()));				
				System.out.println("Veuillez saisir votre email :");
				currentUser.setEmail(fonctions.lireChaine());
				daoUtilisateur.save(currentUser);
				break;
			}
			
		}

	}

}
