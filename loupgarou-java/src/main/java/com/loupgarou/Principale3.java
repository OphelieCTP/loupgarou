package com.loupgarou;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.loupgarou.model.*;
import com.loupgarou.divers.*;
import com.loupgarou.dao.*;

public class Principale3 {
			
	
	public static int menu() {
		System.out.println("  ##########     MENU    ##########  ");
		System.out.println("1- Actualiser informations compte utilisateur.");
		System.out.println("2- Voir les autres joueurs connectés.");
		System.out.println("3- Démarrer une partie.");
		System.out.println("4- Rejoindre une partie.");
		System.out.println("5- Voir les parties disponibles.");
		System.out.println("6- Supprimer compte utilisateur");
		System.out.println("-----------------");
		System.out.println("7- Ajouter partie");
		System.out.println("8- Ajouter villageois");
		System.out.println("9- Lire partie");
		System.out.println("10- Lire villageois");
		System.out.println("11- Update partie");
		System.out.println("12- Update villageois");
		System.out.println("13- Delete partie");
		System.out.println("14- Delete villageois");
		System.out.println("0- End");
		
		return fonctions.lireEntier();
		//
	}
	
	public static void supprimerCompteUtilisateur(Utilisateur u) {
		System.out.println(" Vous êtes certains de vouloir supprimer votre compte ? [Y/N]");
		if(fonctions.lireChaine()=="Y") {
			IDAOUtilisateur daoUtilisateur = new DAOUtilisateurSQL(); 
			daoUtilisateur.deleteById(u.getUserID());
			//deconnexion();
		}
	}
	
	public static void actualiserCompteUtilisateur(Utilisateur u) {
		System.out.println("");
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurSQL(); 
		System.out.println("Que voulez-vous actualiser ?");
		System.out.println("1- Mot de passe.");
		System.out.println("2- Email.");
		System.out.println("3- Date naissance.");
		System.out.println("4- Nom utilisateur.");
		System.out.println("5- Pas de modifications.");
		int choix = fonctions.lireEntier();
		while (choix != 5) {
			if(choix == 1) {
				System.out.println("Nouvelle valeur ?");
				u.setPassWord(fonctions.lireChaine());
			}
			if(choix == 2) {
				System.out.println("Nouvelle valeur ?");
				u.setEmail(fonctions.lireChaine());
			}
			if(choix == 3) {
				System.out.println("Nouvelle valeur ?");
				// u.setDateNaissance(fonctions.lireChaine());
				System.out.println("Pas encore fonctionnel ");
			}
			if(choix == 4) {
				System.out.println("Nouvelle valeur ?");
				u.setUserName(fonctions.lireChaine());
			}
		}
		daoUtilisateur.save(u);
		}
	
	public static void voirJoueursConnectes() {
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurSQL(); 
		List<Utilisateur> user = daoUtilisateur.findAll();
		System.out.println("Joueurs connectés :");
		for (Utilisateur u : user) {
			if(u.getIsConnected()==true) {
				System.out.println("-----------------");
				System.out.println("Utilisateur "+u.getUserID()+" "+u.getUserName());
				}
		}
	}
	
	public static void afficherPartie() {
		IDAOPartie daoPartie = new DAOPartieSQL();
		List<Partie> parties = daoPartie.findAll();
		System.out.println("  Actuellement, "+parties.size()+" parties sont en cours : ");
		for (Partie p : parties) {
			System.out.println("-----------------");
			System.out.println("La partie "+p.getId());
			System.out.println("Avec : "+p.getNbVillageois()+" villageois et "+p.getNbLoups()+" loups");
			String etatPartie = "";
			if(p.getEtat()==false) { etatPartie = "fermée, vous ne pouvez la rejoindre."; }
			else { etatPartie = "ouverte, vous pouvez la rejoindre"; }
			System.out.println("Elle est actuellement : "+etatPartie);
		}
	}
	
	public static void afficherVillageois() {
		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
		List<Villageois> villageois = daoVillageois.findAll();
		System.out.println("  Actuellement, il y a "+villageois.size()+" villageois : ");
		for (Villageois v : villageois) {
			System.out.println("-----------------");
			System.out.println("Villageois "+v.getVillID()+" "+v.getRole()+" dans la partie "+ v.getPartie().getId() +"Il est : vivant = "+v.getVivant());
		}
	}
	
	public static void ajouterPartie() {
		IDAOPartie daoPartie = new DAOPartieSQL();
		Partie p = new Partie();
		p.setId(0);
		daoPartie.save(p);
	}
	
	public static void ajouterVillageois(Utilisateur u, String role, Partie p) {
		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
		System.out.println(" getUserID "+u.getUserID());
		Villageois v = new Villageois(u.getUserID(), u.getUserName(), u.getPassWord(), role, p);
		
		v.setVillID(0);
		daoVillageois.save(v);
	}
	
	public static void supprimerVillageois(Villageois v) {
		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
		daoVillageois.deleteById(v.getVillID());
	}
	
	public static void supprimerPartie(Partie p) {
		IDAOPartie daoPartie = new DAOPartieSQL();
		daoPartie.deleteById(p.getId());
	}
	
	public static String roleRandom() {
		List<String> roles = new ArrayList<String>();
		roles.add("Villageois");
		roles.add("Voyante");
		roles.add("Sorciere");
		roles.add("Petite Fille");
		roles.add("Cupidon");
		roles.add("Chasseur");
		roles.add("Loup Garou");
		int limiteMax = roles.size()-1;
		Random randomNum = new Random();
		String role = roles.get(0+randomNum.nextInt(limiteMax));
		return role;
	}
	
	public static int menuBienvenue()
	{
		System.out.println(" ########## MENU ##########");
		System.out.println("1- Se connecter");
		System.out.println("2- S'inscrire");
		
		return fonctions.lireEntier();
	}
	
	// fonction fournit nom utilisateur et renvoit villageois ??
			

	public static void main(String[] args) {
		

		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurSQL(); 
		
		int choixMenuLogin = -1;
		Utilisateur currentUser = null;
		while(choixMenuLogin != 0)
		{
			choixMenuLogin = menuBienvenue();
			
			switch(choixMenuLogin)
			{
			case 1 :
				System.out.println("Veuillez saisir votre nom d'utilisateur :");
				String userName = fonctions.lireChaine();
				System.out.println("Veuillez saisir votre mot de passe :");
				String password = fonctions.lireChaine();
				currentUser = daoUtilisateur.authentification(userName, password); 
				if(currentUser != null)
				{
					System.out.println("Bienvenue " + userName + " !");
					currentUser.setIsConnected(true);
					daoUtilisateur.save(currentUser);
					choixMenuLogin = 0;
					int choix = 1;
					while (choix != 0) {
						choix = menu();
						if(choix == 1) { actualiserCompteUtilisateur(currentUser); }
						if(choix == 2) { voirJoueursConnectes(); }
						if(choix == 3) { 
							IDAOPartie daoPartie = new DAOPartieSQL();
							Partie p = new Partie();
							p.setId(0);
							p = daoPartie.save(p);
							ajouterVillageois(currentUser, roleRandom(), p);
							afficherPartie();
							afficherVillageois();
							}
						
						if(choix == 4) {
							afficherPartie();
							System.out.println(" rejoindre quelle partie ? ");
							afficherVillageois();
							int selected = fonctions.lireEntier();
							IDAOPartie daoPartie = new DAOPartieSQL();
							Partie p = daoPartie.findById(selected);
							ajouterVillageois(currentUser, roleRandom(), p);
							afficherVillageois();
							}
						if(choix == 5) { afficherPartie(); }
						if(choix == 6) { supprimerCompteUtilisateur(currentUser); }
						if(choix == 7) { ajouterPartie(); }
						if(choix == 9) { afficherPartie(); }
						if(choix == 10) { afficherVillageois(); }
						
						if(choix == 8) {
							afficherPartie();
							System.out.println(" rejoindre quelle partie ? ");
							IDAOPartie daoPartie = new DAOPartieSQL();
							Partie p = daoPartie.findById(fonctions.lireEntier());
							System.out.println(" getUserID init "+currentUser.getUserID());
							System.out.println(" getUserName init "+currentUser.getUserName());
							System.out.println("  avec quel role ?  ");
							ajouterVillageois(currentUser, roleRandom(), p);
							afficherVillageois();
						}
					
					}
				}
				else
				{
					System.out.println("Utilisateur introuvable !");
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
		if(currentUser != null)
		{
			currentUser.setIsConnected(false);
			daoUtilisateur.save(currentUser);
			System.out.println("Vous etes deconnecté !");
		}
	}
}
