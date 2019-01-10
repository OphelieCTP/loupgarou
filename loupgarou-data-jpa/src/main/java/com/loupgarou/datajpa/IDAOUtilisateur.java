package com.loupgarou.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loupgarou.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer>{
	public Utilisateur findByUserNameAndPassWordAndIsBanni(String userName, String password, Boolean banni);
	public Utilisateur findByUserName(String username);

}
