package com.loupgarou.dao.jpa;


import java.util.List;

import java.util.ArrayList;
import java.sql.*;
import com.loupgarou.model.*;
import com.loupgarou.dao.*;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


public class DAOVillageoisJPA implements IDAOVillageois {
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
		if (entity.getUserID() == 0) {
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
		element.setUserID(id);
		this.delete(element);
	}
	
	public void updateRole(Villageois v)
	{
		this.em.getTransaction().begin();
		this.em.createQuery("update Utilisateur u set u.role = :role, u.villID = :villId where u.id = :id")
		.setParameter("role", v.getRole())
		.setParameter("villId", v.getVillID())
		.setParameter("id", v.getUserID())
		.executeUpdate();
		this.em.getTransaction().commit();
	}
	

}
