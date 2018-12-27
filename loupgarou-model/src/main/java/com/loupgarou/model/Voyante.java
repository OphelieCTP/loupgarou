package com.loupgarou.model;

import java.util.ArrayList;


import com.loupgarou.divers.*;


import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@DiscriminatorValue("Voyante")


public class Voyante extends Villageois{
	
	public Voyante()
	{
		this.role = "Voyante";
	}
	
	public Voyante(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
	
//	public void demanderJoueur()
//	{
//		int index = -1;
//		while(index == -1)
//		{
//			System.out.println("Quelle est le nom de la personne dont vous voulez voir la carte ?");
//			String choix = fonctions.lireChaine();
//			index = this.partie.getVillageoisIndex(choix);
//		}
//		this.voirCarte(this.partie.getJoueurs().get(index));
//	}
	
//	public void voirCarte() {
//		System.out.println("Choisissez le joueur dont vous voulez voir la carte.");
//		ArrayList<Villageois> village = new ArrayList<Villageois>();
//		village = this.partie.getListJoueurs(this.partie.getJoueurs());
//		String name = fonctions.lireChaine();
//		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
//		for (Villageois v : village) {
//			if(v.getUserName()==name) {
//				System.out.println("Carte "+v.getUserName() + " : " + v.getRole());
//			}
//		}
//	}
}
