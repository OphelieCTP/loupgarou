package com.loupgarou.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.loupgarou.model.Message;

public abstract class DAOMessageSQL implements IDAOMessage {
	private Connection connection; 

	public Message findById(int i)
	{
		Message m = new Message();
		return m;
	}
	
	public List<Message> findAll()
	{
		List<Message> mesMessages = new ArrayList<Message>();
		return mesMessages;
	}
	
	public Message save(Message mes)
	{
		return mes;
	}
	
	public void delete(Message mes)
	{
		
	}
	
	public void deleteById(int id)
	{
		
	}
	
	
}
