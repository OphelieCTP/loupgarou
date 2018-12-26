package com.loupgarou.dao.jpa;


import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import com.loupgarou.model.*;
import com.loupgarou.dao.*;


import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


public class DAOChatJPA implements IDAOChat {
	private EntityManager em;
	
	public DAOChatJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Chat> findAll() {
		return this.em.createQuery("select c from Chat c", Chat.class)
				.getResultList();
	}

	@Override
	public Chat findById(int id) {
		return this.em.find(Chat.class, id);
	}
	

	@Override
	public Chat save(Chat entity) {
		this.em.getTransaction().begin();
		if (entity.getChatID() == 0) {
			this.em.persist(entity);
		}
		else {
			entity = this.em.merge(entity);
		}
		this.em.getTransaction().commit();
		return entity;
	}
	
	@Override
	public void delete(Chat entity) {
		this.em.getTransaction().begin();
		this.em.remove(em.merge(entity));
		this.em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Chat element = new Chat();
		element.setChatID(id);
		this.delete(element);
	}
	

}
