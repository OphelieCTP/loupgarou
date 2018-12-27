package com.loupgarou.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Cupidon")


public class Cupidon extends Villageois{
	
	public Cupidon()
	{
		this.role = "Cupidon";
	}
	public Cupidon(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
	
	public Villageois chercherAmoureux(List<Villageois> village, String name) {
		for (Villageois v : village) {
			if(v.getUserName()==name) {
				v.setAmoureux(true);
				return v;
			}
		}
		return null;
	}
	
	public void formerCoupler() {
		System.out.println("Formez un couple.");
		List<Villageois> village = new ArrayList<Villageois>();
		village = this.partie.getJoueurs();
		System.out.println("Choisissez le premier amoureux.");
		Villageois vi = chercherAmoureux(village, fonctions.lireChaine());
		while(vi==null) {
			System.out.println("Ce villageois n'est pas présent.");
			System.out.println("Choisissez le premier amoureux.");
			vi = chercherAmoureux(village, fonctions.lireChaine());
		}
		System.out.println("Choisissez le second amoureux.");
		String choix2 = fonctions.lireChaine();
		while (vi.getUserName()==choix2) {
			System.out.println("Choisissez un joueur different.");
			choix2 = fonctions.lireChaine();
		}
		chercherAmoureux(village, choix2);
	}
	
}
