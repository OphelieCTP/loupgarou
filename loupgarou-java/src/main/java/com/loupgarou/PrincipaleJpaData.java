package com.loupgarou;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import java.lang.Object;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.loupgarou.model.*;
import com.loupgarou.divers.*;
import com.loupgarou.config.*;
//import com.loupgarou.dao.*;
import com.loupgarou.datajpa.*;


public class PrincipaleJpaData {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@Autowired
	private IDAOPartie daoPartie;
	
	@Autowired
	private IDAOVillageois daoVillageois;
	
	@Autowired
	private IDAOMessage daoMessage;
	
	@Autowired
	private IDAOChat daoChat;
	
	public int menuBienvenue()
	{
		System.out.println(" ########## MENU ##########");
		System.out.println("1- Se connecter");
		System.out.println("2- S'inscrire");
		
		return fonctions.lireEntier();
	}
	
	
	public int menu() {
		System.out.println("  ##########     MENU    ##########  ");
		System.out.println("0- Deconnexion");
		System.out.println("1- Actualiser informations compte utilisateur.");
		System.out.println("2- Voir les autres joueurs connect�s.");
		System.out.println("3- D�marrer une partie.");
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
	
	public void supprimerCompteUtilisateur(Utilisateur u, IDAOUtilisateur daoUtilisateur) {
		System.out.println(" Vous �tes certains de vouloir supprimer votre compte ? [Y/N]");
		if(fonctions.lireChaine()=="Y") {
			daoUtilisateur.deleteById(u.getUserID());
			//deconnexion();
		}
	}
	
	public void actualiserCompteUtilisateur(Utilisateur u, IDAOUtilisateur daoUtilisateur) {
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
	
	public void voirJoueursConnectes(IDAOUtilisateur daoUtilisateur) {
		List<Utilisateur> user = daoUtilisateur.findAll();
		System.out.println("Joueurs connect�s :");
		for (Utilisateur u : user) {
			if(u.getIsConnected()==true) {
				System.out.println("-----------------");
				System.out.println("Utilisateur "+u.getUserID()+" "+u.getUserName());
				}
		}
	}
	
	public void voirParties(IDAOPartie daoPartie) {
		List<Partie> parties = daoPartie.findAll();
		System.out.println("  Actuellement, il y a "+parties.size()+" parties.");
		for (Partie p : parties) {
			System.out.println("-----------------");
			System.out.println("La partie "+p.getId());
			System.out.println("Avec : "+p.getJoueurs().size()+" joueurs actuellement.");
			String etatPartie = "";
			if(p.getEtat()==false) { etatPartie = "ferm�e, vous ne pouvez la rejoindre."; }
			else { etatPartie = "ouverte, vous pouvez la rejoindre"; }
			System.out.println("Elle est actuellement : "+etatPartie);
		}
	}
	
	public Partie creerPartie(IDAOPartie daoPartie, IDAOChat daoChat) {
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
	
	public Villageois ajouterVillageois(Utilisateur u, String role, Partie p, IDAOVillageois daoVillageois) {
		System.out.println(" getUserID "+u.getUserID());
		Villageois v = new Villageois(u, role, p);
		p.getJoueurs().add(v);
		//v.setVillID(0);
		System.out.println("ID du nouveau villageois : " + v.getUserID());
		daoVillageois.updateRole(v);
		daoVillageois.save(v);
		return v;
	}
	
	public void jouer(IDAOPartie daoPartie, Partie p) {
		
		//voir joueurs associes a la partie
		//distribuer roles
		//premier tour : intervention cupidon + reconnaissance des amoureux, voyante
		// while partie.NbVillageois !=0 et partie.NbLoup !=0
			// nuit
				// les loup votent
				// la petite fille voit
			// aube : intervention sorciere (apprend qui est le mort : sauver, tuer)
			// jour
				// un villageois est mort (is chasseur ? is amoureux ?)
				// si NbVilageois =! 0 : villagesois votent
				// un villageois meurt
		
		// PRB : � quel moment fait revelation plus de loup/plus de villageois non loups ?
		// PRB : intervention parametre endormit : condition vote de nuit ?
		System.out.println("en cours de param�trage"); 
	}
	
	public Partie recrutement(IDAOPartie daoPartie, IDAOMessage daoMessage, Partie p) {
		//Long timeLimit = p.getDateCreation().getTime()+60*5*1000;
		Long timeLimit = p.getDateCreation().getTime()+10*1000;
		Date times = new Date();
		while (times.getTime()<timeLimit) {
			p.setEtat(true);
			times = new Date();
		}
		p.setEtat(false);
		//Message annonce = new Message("Recrutement pour la partie "+p.getId()+" termin�.", p.getChat());
		//annonce.setVisible(Visible.Villageois);
		System.out.println("Recrutement pour la partie "+p.getId()+" termin�."); 
		//daoMessage.save(annonce);
		return daoPartie.save(p);
	}

	// retire tt static
	
	public void run(String[] args) {
		// faire le switch ici
	//public static void main(String[] args) {
		
		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(AppConfig.class);
		//myContext.getBeanFactory().createBean(AppListeProduit.class).run(args);
		
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
				
				
				currentUser = daoUtilisateur.findByUserNameAndPassWordAndIsBanni(userName, password, false).get(); 
				if(currentUser != null){
					System.out.println("Bienvenue " + userName + " !");
					currentUser.setIsConnected(true);
					daoUtilisateur.save(currentUser);
					choixMenuLogin = 0;
					int choix = 1;
					while (choix != 0) {
						choix = menu();
						switch(choix) {
						case 0 : 
							break;
						case 1 : actualiserCompteUtilisateur(currentUser, daoUtilisateur);
							break;
						case 2 : voirJoueursConnectes(daoUtilisateur);
							break;
						case 3 : Partie p = creerPartie(daoPartie, daoChat);
							System.out.println("-------------------");
							int tentative = 0;
							Villageois currentPlayer = ajouterVillageois(currentUser, "Villageois", p, daoVillageois);
							//test si le nombre de joueurs est sufissant
							do {
								p.setDateCreation(new Date());
								p = recrutement(daoPartie, daoMessage, p);
								if(p.getJoueurs().size() < 5) {
									System.out.println("Pas assez de joueurs, relancement du processus de recrutement.");
								}
								tentative++;
							}while(p.getJoueurs().size() < 5 && tentative < 3);
//							if(tentative == 3)
//							{
//								break;
//							}
							
							p.setEtat(true);
//							// d�marer la partie
							p.distribuerRole();
							for(Villageois v : p.getJoueurs())
							{
								daoVillageois.updateRole(v);
								System.out.println(v.getUserID());
								//A CORRIGER : COPIE DANS LA BASE DE DONNEES 
								//daoVillageois.save(v);								
							}
							currentPlayer = daoVillageois.findById(currentPlayer.getUserID()).get();
							
							break;
						case 4 : voirParties(daoPartie);
							System.out.println("Quelle partie voulez-vous rejoindre ? ");
							int selected = fonctions.lireEntier();
							Partie partie = daoPartie.findById(selected).get();
							break;
						case 5 : System.out.println("en cours de developpement"); //
							break;
							
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
					currentUser = daoUtilisateur.findByUserName(newName);
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
