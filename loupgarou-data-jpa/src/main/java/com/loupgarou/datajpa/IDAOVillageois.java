package com.loupgarou.datajpa;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loupgarou.model.*;

public interface IDAOVillageois extends JpaRepository<Villageois, Integer>  {
	
	@Query("update Utilisateur u set u.role = :#{#v.role} where u.id = :#{#v.id}")
	public void updateRole(@Param("v") Villageois v);

}
