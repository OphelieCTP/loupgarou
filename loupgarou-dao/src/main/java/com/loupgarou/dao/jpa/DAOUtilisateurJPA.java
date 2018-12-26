package com.loupgarou.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.loupgarou.dao.IDAOUtilisateur;
import com.loupgarou.model.Utilisateur;
import com.loupgarou.model.Villageois;

public class DAOUtilisateurJPA implements IDAOUtilisateur{

	private EntityManager em;
	
	public void initializeEM(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
		
	}

	public void closeEM() {
		this.em.close();
	}

	public List<Utilisateur> findAll() {
		return this.em.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
	}

	public Utilisateur findById(int id) {
		return this.em.find(Utilisateur.class, id);
	}

	public Utilisateur save(Utilisateur util) {
		this.em.getTransaction().begin();
		if (util.getUserID() == 0) {
			this.em.persist(util);
		}
		else {
			util = this.em.merge(util);
		}
		this.em.getTransaction().commit();
		return util;
	}

	public void delete(Utilisateur util) {
		this.em.getTransaction().begin();
		this.em.remove(em.merge(util));
		this.em.getTransaction().commit();	
	}

	@Override
	public void deleteById(int id) {
		Villageois element = new Villageois();
		element.setVillID(id);
		this.delete(element);
	}

	@Override
	public Utilisateur authentification(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur findByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
