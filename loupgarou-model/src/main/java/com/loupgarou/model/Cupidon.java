package com.loupgarou.model;

import java.util.ArrayList;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Cupidon")


public class Cupidon extends Villageois{
	
	private String role = "Cupidon";
	
	public Cupidon()
	{
		
	}
	public Cupidon(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
	
//	public void tomberAmoureux(ArrayList<Villageois> village, String name) {
//		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
//		for (Villageois v : village) {
//			if(v.getUserName()==name) {
//				v.setAmoureux(true);
//				daoVillageois.save(v);
//			}
//		}
//	}
	
//	public void formerCoupler() {
//		System.out.println("Formez un couple.");
//		System.out.println("Choisissez le premier amoureux.");
//		ArrayList<Villageois> village = new ArrayList<Villageois>();
//		village = this.partie.getListJoueurs(this.partie.getJoueurs());
//		String choix1 = fonctions.lireChaine();
//		tomberAmoureux(village, choix1);
//		System.out.println("Choisissez le second amoureux.");
//		String choix2 = fonctions.lireChaine();
//		while (choix1==choix2) {
//			System.out.println("Choisissez un joueur different.");
//			choix2 = fonctions.lireChaine();
//		}
//		tomberAmoureux(village, choix2);
//	}
	
}
