package com.loupgarou.model;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Loup")

public class Loup extends Villageois{
	
	private String role = "Loup";
	
	public Loup()
	{
		
	}
	
	public Loup(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}

	//lireMessage()
	
	//envoyerMessageCaché()
}
