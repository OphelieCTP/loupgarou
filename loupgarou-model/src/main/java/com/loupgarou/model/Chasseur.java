package com.loupgarou.model;
import com.loupgarou.divers.fonctions;

public class Chasseur extends Villageois{
	
	
	public Chasseur()
	{
		
	}
	
	public Chasseur(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
	
	public String demanderVictime()
	{		
		System.out.println("Quelle personne voulez vous tuez ?");
		String victime = fonctions.lireChaine();
		return victime;
		
	}

//	@Override
//	public void tuer(Villageois victime) {
//			victime.mourrir(victime);
//		
//	}
	

}
