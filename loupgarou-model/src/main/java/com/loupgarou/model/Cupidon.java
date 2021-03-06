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
	
	public Cupidon(Villageois v)
	{
		super(v);
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
		System.out.println("Choisissez le premier amoureux.");
		Villageois vi = chercherAmoureux(this.getPartie().getJoueurs(), fonctions.lireChaine());
		while(vi==null) {
			System.out.println("Ce villageois n'est pas pr�sent.");
			System.out.println("Choisissez le premier amoureux.");
			vi = chercherAmoureux(this.getPartie().getJoueurs(), fonctions.lireChaine());
		}
		String c1 = vi.getUserName();
		vi = null;
		System.out.println("Choisissez le second amoureux.");
		String choix2 = fonctions.lireChaine();
		while (c1 == choix2 || vi == null) {
			System.out.println("Choisissez un joueur different.");
			choix2 = fonctions.lireChaine();
			vi = chercherAmoureux(this.getPartie().getJoueurs(), choix2);
		}
		
	}
	/// PRB !!! ne renvoit rien, doit pouvoir renvoyer chaque villageois pour etre modifi�
	
}
