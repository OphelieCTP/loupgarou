package com.loupgarou.dao;


import com.loupgarou.model.*;

public interface IDAOUtilisateur extends IDAO<Utilisateur> {
	public Utilisateur authentification(String userName, String password);
	public Utilisateur findByUsername(String userName);
}
