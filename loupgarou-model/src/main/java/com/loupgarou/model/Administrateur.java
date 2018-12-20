package com.loupgarou.model;

public class Administrateur extends Utilisateur{

	public Administrateur()
	{
		
	}
	
	public Administrateur(String userName, String passWord, int userId)
	{
		super(userName, passWord, userId);
	}
	
	public void bannir(Utilisateur user) {
		user.isBanni = true; 
	}
}
