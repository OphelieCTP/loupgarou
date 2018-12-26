package com.loupgarou.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Sorciere")

public class Sorciere extends Villageois{
	
	private String role = "Sorciere";
	
	public Sorciere()
	{
		
	}
	
	public Sorciere(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
}
