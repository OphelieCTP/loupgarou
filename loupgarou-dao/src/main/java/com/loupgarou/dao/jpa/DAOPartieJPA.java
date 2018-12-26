package com.loupgarou.dao.jpa;


import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import com.loupgarou.model.*;
import com.loupgarou.dao.*;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


public class DAOPartieJPA implements IDAOPartie {
	private EntityManager em;
	
	public DAOPartieJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Partie> findAll() {
		return this.em.createQuery("select p from Partie p", Partie.class)
				.getResultList();
	}

	@Override
	public Partie findById(int id) {
		return this.em.find(Partie.class, id);
	}
	

	@Override
	public Partie save(Partie entity) {
		this.em.getTransaction().begin();
		if (entity.getId() == 0) {
			this.em.persist(entity);
		}
		else {
			entity = this.em.merge(entity);
		}
		this.em.getTransaction().commit();
		return entity;
	}
	
	@Override
	public void delete(Partie entity) {
		this.em.getTransaction().begin();
		this.em.remove(em.merge(entity));
		this.em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Partie element = new Partie();
		element.setId(id);
		this.delete(element);
	}

}
