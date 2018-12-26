package com.loupgarou.dao;


import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import com.loupgarou.model.*;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


public class DAOVillageoisJPA implements IDAO<Villageois> {
	private EntityManager em;
	
	public DAOVillageoisJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Villageois> findAll() {
		return this.em.createQuery("select v from Villageois v", Villageois.class)
				.getResultList();
	}

	@Override
	public Villageois findById(int id) {
		return this.em.find(Villageois.class, id);
	}
	

	@Override
	public Villageois save(Villageois entity) {
		this.em.getTransaction().begin();
		if (entity.getVillID() == 0) {
			this.em.persist(entity);
		}
		else {
			entity = this.em.merge(entity);
		}
		this.em.getTransaction().commit();
		return entity;
	}
	
	@Override
	public void delete(Villageois entity) {
		this.em.getTransaction().begin();
		this.em.remove(em.merge(entity));
		this.em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Villageois element = new Villageois();
		element.setVillID(id);
		this.delete(element);
	}
	

}