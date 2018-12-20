package com.loupgarou;

import com.loupgarou.dao.DAOPartieSQL;

import com.loupgarou.dao.DAOUtilisateurSQL;
import com.loupgarou.dao.IDAOPartie;
import com.loupgarou.dao.IDAOUtilisateur;
import com.loupgarou.divers.*;
import com.loupgarou.model.Utilisateur;

public class Principale2 {

	public static void main(String[] args) {
		IDAOUtilisateur daoUtilisateur = new DAOUtilisateurSQL();
		
		int choixMenuLogin = -1;
		Utilisateur currentUser = null;
		while(choixMenuLogin != 0)
		{
			System.out.println(" ########## MENU ##########");
			System.out.println("1- Se connecter");
			System.out.println("2- S'inscrire");
			
			choixMenuLogin = fonctions.lireEntier();
			
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
		//Deconnection
		if(currentUser != null)
		{
			currentUser.setIsConnected(false);
			daoUtilisateur.save(currentUser);
		}
	}
	


}
