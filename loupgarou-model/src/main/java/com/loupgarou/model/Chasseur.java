package com.loupgarou.model;
import com.loupgarou.divers.fonctions;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Chasseur")


public class Chasseur extends Villageois{	
	
	public Chasseur()
	{
		this.role = "Chasseur";
	}
	
	public Chasseur(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
	
	public Chasseur(Villageois v)
	{
		super(v);
	}
	
	public void tuer()
	{	
		String victime;
		Boolean trouve = false;
		do {
			System.out.println("Quelle personne voulez-vous tuez ?");
			victime = fonctions.lireChaine();
		
		for(Villageois v : this.getPartie().getJoueurs())
		{
			if(v.getUserName().equals(victime))
			{
				v.mourrir();
				trouve = true;
				break;
			}
		}
		}while(trouve != true);
	}

//	@Override
//	public void tuer(Villageois victime) {
//			victime.mourrir(victime);
//		
//	}
	

}
