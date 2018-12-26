package com.loupgarou.dao.jpa;


import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import com.loupgarou.model.*;
import com.loupgarou.dao.*;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


public class DAOMessageJPA implements IDAO<Message> {
	private EntityManager em;
	
	public DAOMessageJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Message> findAll() {
		return this.em.createQuery("select m from Message m", Message.class)
				.getResultList();
	}

	@Override
	public Message findById(int id) {
		return this.em.find(Message.class, id);
	}
	

	@Override
	public Message save(Message entity) {
		this.em.getTransaction().begin();
		if (entity.getIdMessage() == 0) {
			this.em.persist(entity);
		}
		else {
			entity = this.em.merge(entity);
		}
		this.em.getTransaction().commit();
		return entity;
	}
	
	@Override
	public void delete(Message entity) {
		this.em.getTransaction().begin();
		this.em.remove(em.merge(entity));
		this.em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Message element = new Message();
		element.setIdMessage(id);
		this.delete(element);
	}
	

}
