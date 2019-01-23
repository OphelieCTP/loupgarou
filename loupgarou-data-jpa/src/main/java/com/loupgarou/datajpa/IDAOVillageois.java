package com.loupgarou.datajpa;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loupgarou.model.*;

public interface IDAOVillageois extends JpaRepository<Villageois, Integer>  {
	
	@Modifying
	@Transactional
	@Query("update Utilisateur u set u.role = :#{#v.role}, u.partie = :#{#v.partie} where u.userID = :#{#v.userID}")
	public void updateRole(@Param("v") Villageois v);
	
	@Query("select v from Villageois v where v.role = :role and v.partie.id = :id")
	public Villageois findByPartieIDAndRole(@Param("role") String role, @Param("id") Integer id);
	

}
