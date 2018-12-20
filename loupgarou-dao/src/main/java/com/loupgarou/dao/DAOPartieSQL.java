package com.loupgarou.dao;

import java.util.List;


import java.util.ArrayList;
import java.sql.*;

import com.loupgarou.divers.fonctions;
import com.loupgarou.model.*;

public class DAOPartieSQL implements IDAOPartie {
	private Connection connection; 
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void connect() throws SQLException {
		if (this.connection==null) {
			try {
				this.connection = DriverManager.getConnection("jdbc:mysql://localhost/loupGarou?serverTimezone=UTC", "root", "");
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public Partie map(ResultSet result) throws SQLException {
		Partie part = new Partie(); 
		
		part.setNbVillageois(result.getInt("PART_NB_VILLAGEOIS"));
		part.setNbLoups(result.getInt("PART_NB_LOUP"));
		part.setEtat(result.getBoolean("PART_ETAT"));
		part.setId(result.getInt("PART_ID"));
		part.setDateCreation(fonctions.stringToDate(result.getString("PART_DATE_CREAT")));
		
		Chat tchat = new Chat(); 
		tchat.setChatID(result.getInt("PART_ID"));
		part.setChat(tchat);
		return part;
	}

	@Override
	public List<Partie> findAll() {
		List<Partie> entity = new ArrayList<Partie>() ;
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			String myQuery = "SELECT * FROM partie p LEFT JOIN tchat c ON c.TCHAT_ID_PARTIE = p.PART_ID";
			ResultSet myResult = myStatement.executeQuery(myQuery);
			while (myResult.next()) {entity.add(this.map(myResult)); }
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return entity; 
	}

	@Override
	public Partie findById(int id) {
		Partie entity = null ;
		try {
			this.connect();
			PreparedStatement myStatement = this.connection.prepareStatement("SELECT * FROM partie WHERE PART_ID = ?");
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();
			if (myResult.next()) {
				entity = new Partie() ;
				entity = this.map(myResult) ;
				}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally { return entity; }
	}

	@Override
	public Partie save(Partie entity) {
		
		try {
			this.connect();
			String myQuery = "";
			if(entity.getId() == 0) {
				myQuery = "INSERT INTO partie (PART_NB_VILLAGEOIS, PART_NB_LOUP, PART_ETAT, PART_DATE_CREAT) VALUES (?, ?, ?, ?)";
				
			}
			else {
				myQuery = "UPDATE villageois SET PART_NB_VILLAGEOIS = ?, PART_NB_LOUP  = ?, PART_ETAT = ?, PART_DATE_CREAT = ?,"
						+ ", WHERE PART_ID = ?";
			}
			PreparedStatement myStatement =this.connection.prepareStatement(myQuery);
			myStatement.setInt(1, entity.getNbVillageois());
			myStatement.setInt(2, entity.getNbLoups());
			myStatement.setBoolean(3, entity.getEtat());
			myStatement.setString(4, fonctions.dateToString(entity.getDateCreation()));
			if (entity.getId() > 0) {
				myStatement.setInt(5, entity.getId()); }
			myStatement.execute();
			
			//Si insertion, récupération du nouvel id
			if(entity.getId() == 0)
			{
				String mySelectQuery = "SELECT PART_ID from partie WHERE PART_DATE_CREAT = ?";
				PreparedStatement mySelectStatement = this.connection.prepareStatement(mySelectQuery);
				mySelectStatement.setString(1, fonctions.dateToString(entity.getDateCreation()));
				ResultSet mySelectResult = mySelectStatement.executeQuery();
				if(mySelectResult.next())
				{
					System.out.println("update id");
					entity.setId(mySelectResult.getInt("PART_ID"));
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally { return entity;  }
	}

	@Override
	public void delete(Partie entity) {
		if(entity.getId()!=0) {
			this.deleteById(entity.getId());
		}
	}

	@Override
	public void deleteById(int id) {
		try {
			this.connect();
			PreparedStatement newStatement =
					this.connection.prepareStatement("DELETE FROM partie WHERE PART_ID = ?");
			newStatement.setInt(1, id);
			newStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}
