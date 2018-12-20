package com.loupgarou.dao;

import java.util.List;
import com.loupgarou.divers.*;
import com.loupgarou.model.*;

public interface IDAO<T> {
	
	public List<T> findAll();
	public T findById(int id);
	public T save(T entity);
	public void delete(T entity);
	public void deleteById(int id);

}
