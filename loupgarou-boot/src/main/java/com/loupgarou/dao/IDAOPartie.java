package com.loupgarou.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loupgarou.model.Partie;

public interface IDAOPartie extends JpaRepository<Partie, Integer> {

}
