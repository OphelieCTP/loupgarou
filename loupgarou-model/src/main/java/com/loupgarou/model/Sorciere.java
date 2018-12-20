package com.loupgarou.model;

public class Sorciere extends Villageois{
	
	public Sorciere()
	{
		
	}
	
	public Sorciere(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
}
