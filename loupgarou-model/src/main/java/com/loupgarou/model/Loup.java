package com.loupgarou.model;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Loup")

public class Loup extends Villageois{
	
	public Loup()
	{
		this.role = "Loup";
	}
	
	public Loup(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
	
	public Loup(Villageois v)
	{
		super(v);
	}

	//lireMessage()
	
	//envoyerMessageCaché()
}
