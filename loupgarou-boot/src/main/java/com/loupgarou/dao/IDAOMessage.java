package com.loupgarou.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loupgarou.model.*;

public interface IDAOMessage extends JpaRepository<Message, Integer>  {

}
