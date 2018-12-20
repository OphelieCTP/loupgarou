package com.loupgarou;

import java.util.ArrayList; 
import java.util.Scanner;
import com.loupgarou.model.Utilisateur;
import com.loupgarou.model.Partie;
import com.loupgarou.divers.*;

public class Principale {

	public static void main(String[] args) {
//		//Création et stockage des utilisateurs. 
//		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
//		ArrayList<Partie> parties = new ArrayList<Partie>(); 
//		Boolean access = false;
//		String user;
//		Utilisateur currentUser = new Utilisateur("","");
//		
//		utilisateurs.add(new Utilisateur("Adrien", "12345"));
//		utilisateurs.add(new Utilisateur("Ophelie", "98765"));
//		utilisateurs.add(new Utilisateur("Jeremy", "45632"));
//		utilisateurs.add(new Utilisateur("Clement", "45682"));
//		utilisateurs.add(new Utilisateur("Benjamin", "14598"));
//		utilisateurs.add(new Utilisateur("Vanessa", "78764"));
//		utilisateurs.add(new Utilisateur("Marie", "95468"));
//		utilisateurs.add(new Utilisateur("Ivan", "45968"));
//		
//		//Connection au programme
//		while(access == false)
//		{
//			System.out.println("Veuillez entrer votre nom d'utilisateur");
//			user = fonctions.lireChaine();
//			for(Utilisateur u : utilisateurs)
//			{
//				if(u.getUserName().equals(user));
//				{
//					access = u.seConneecter();
//				}
//				currentUser = u;
//				break;
//			}
//		}
//		
//		//Menu principal
//		int menu = -1;
//		
//		while(menu != 4)
//		{
//			System.out.println("  ##########  MENU  ##########  ");
//			System.out.println("1- Voir les autres joueurs connectés.");
//			System.out.println("2- Démarrer une partie.");
//			System.out.println("3- Rejoindre une partie.");
//			System.out.println("4- Deconnexion");
//			
//			menu = fonctions.lireEntier();
//			
//			switch (menu)
//			{
//				case 1 :
//					for(Utilisateur u : utilisateurs)
//					{
//						u.afficherUtilisateur();
//					}
//					break;
//				case 2 :
//					parties.add(currentUser.creerPartie());
//					
//					//Ajout de joueurs
//					utilisateurs.get(1).rejoindrePartie(parties, 0);
//					utilisateurs.get(3).rejoindrePartie(parties, 0);
//					utilisateurs.get(4).rejoindrePartie(parties, 0);
//					utilisateurs.get(6).rejoindrePartie(parties, 0);
//					
//					//Affichage des participants + contrôle nb joueurs.
//					parties.get(0).initiatePartie();
//					
//					//Distribution des rôles
//					parties.get(0).distribuerRole();
//					
//					break;
//				case 3 :
//					currentUser.rejoindrePartie(parties, 0);
//					break;
//				case 4 :
//					currentUser.seDeconnecter();
//					break;
//				default :
//					menu = -1;
//					break;
//			}
//		}
//		
//		
//		
//		
	}
	


}
