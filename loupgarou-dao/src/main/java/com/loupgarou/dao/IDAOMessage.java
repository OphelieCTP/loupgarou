package com.loupgarou.dao;

import java.util.List;

import com.loupgarou.divers.*;
import com.loupgarou.model.*;

public interface IDAOMessage extends IDAO<Message> {

	@Override
	default List<Message> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Message findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Message save(Message entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default void delete(Message entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}
 
}
