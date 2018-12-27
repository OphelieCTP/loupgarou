package com.loupgarou.model;

import java.util.List;
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
	
	public void voirCarte() {
		System.out.println("Choisissez le joueur dont vous voulez voir la carte.");
		List<Villageois> village = new ArrayList<Villageois>();
		village = this.partie.getJoueurs();
		String name = fonctions.lireChaine();
		for (Villageois v : village) {
			if(v.getUserName()==name) {
				System.out.println("Carte "+v.getUserName() + " : " + v.getRole());
			}
		}
	}
}
