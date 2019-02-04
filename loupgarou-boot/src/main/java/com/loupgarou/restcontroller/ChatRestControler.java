package com.loupgarou.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.loupgarou.dao.*;
import com.loupgarou.model.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ChatRestControler {
	
	@Autowired
	IDAOMessage daoMessage;
	
	@GetMapping("/message")
	@JsonView(Views.Message.class)
	public List<Message> getMessages() {
		List<Message> messages = daoMessage.findAll();
		return messages;
	}

}
