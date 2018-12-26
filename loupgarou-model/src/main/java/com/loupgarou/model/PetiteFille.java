package com.loupgarou.model;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("PetiteFille")

public class PetiteFille extends Villageois{
	
	private String role = "PetiteFille";

	public PetiteFille()
	{
	
	}
	
	public PetiteFille(int userId, String userName, String passWord, String role, Partie partie)
	{
		super(userId, userName, passWord, role, partie);
	}
}
