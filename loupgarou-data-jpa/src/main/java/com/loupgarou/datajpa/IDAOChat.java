package com.loupgarou.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loupgarou.model.Chat;

public interface IDAOChat extends JpaRepository<Chat, Integer>{

}
