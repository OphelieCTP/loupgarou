package com.loupgarou.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.loupgarou.divers.fonctions;
import com.loupgarou.model.Administrateur;
import com.loupgarou.model.Utilisateur;

import java.sql.Connection;


public class DAOUtilisateurSQL implements IDAOUtilisateur {
	private Connection connection; 
	
	public void connect() throws SQLException
	{
		if(this.connection == null)
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loupgarou?serverTimezone=UTC", "root", "");
		}
	}
	
	public Utilisateur map(ResultSet result) throws SQLException
	{
		Utilisateur u = null;
		
		if(result.getBoolean("UTIL_ADMIN"))
		{
			u = new Administrateur();
		}
		else
		{
			u = new Utilisateur();
		}
		u.setUserID(result.getInt("UTIL_ID"));
		u.setUserName(result.getString("UTIL_USER_NAME"));		
		u.setPassWord(result.getString("UTIL_PASSWORD"));
		u.setEmail(result.getString("UTIL_EMAIL"));
		u.setNbPlaintes(result.getInt("UTIL_NB_PLAINTES"));
		u.setIsConnected(result.getBoolean("UTIL_IS_CONNECTED"));
		u.setIsBanni(result.getBoolean("UTIL_BANNI"));
		u.setDateNaissance(fonctions.stringToDate(result.getString("UTIL_DATE_NAiSS")));
		
		
		return u;
	}
	
	
	public List<Utilisateur> findAll()
	{
		List<Utilisateur> myUsers = new ArrayList<Utilisateur>();
		
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * from utilisateur");
			while(myResult.next())
			{
				Utilisateur u = this.map(myResult);
				myUsers.add(u);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connection à la base de données");
			e.printStackTrace();
		}
		
		return myUsers;
	}
	
	public Utilisateur findById(int id)
	{
		Utilisateur myUser = new Utilisateur();
		try
		{
			this.connect();
			PreparedStatement myStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE UTIL_ID = ?");
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();
			
			if(myResult.next())
			{
				myUser = this.map(myResult);
			}
		}
		catch (SQLException e)
		{
			
		}
		return myUser;
	}
	
	public Utilisateur save(Utilisateur user)
	{
		String myQuery = "";
		try
		{
			this.connect();			
			//L'objet a été crée pendant l'execution du programme : insertion 
			if(user.getUserID() == 0)
			{
				myQuery = "INSERT INTO utilisateur (UTIL_USER_NAME, UTIL_PASSWORD, UTIL_EMAIL ,UTIL_NB_PLAINTES ,UTIL_IS_CONNECTED, UTIL_BANNI, UTIL_DATE_NAISS, UTIL_ADMIN, UTIL_ID_TCHAT) VALUES (?,?,?,?,?,?,?,?,?)";
			}
			//Le produit est déjà dans la base - modification de son enregistrement
			else
			{
				myQuery = "UPDATE utilisateur SET UTIL_USER_NAME = ?, UTIL_PASSWORD = ?, UTIL_EMAIL = ?, UTIL_NB_PLAINTES = ?, UTIL_IS_CONNECTED = ?, UTIL_BANNI = ?, UTIL_DATE_NAISS = ?, UTIL_ADMIN = ?, UTIL_ID_TCHAT = ? WHERE UTIL_ID = ?";
			}
			PreparedStatement myStatement = connection.prepareStatement(myQuery);
			myStatement.setString(1,  user.getUserName());
			myStatement.setString(2, user.getPassWord());
			myStatement.setString(3,  user.getEmail());
			myStatement.setInt(4, user.getNbPlaintes());
			myStatement.setBoolean(5,  user.getIsConnected());
			myStatement.setBoolean(6, user.getIsBanni());
			myStatement.setString(7,  fonctions.dateToString(user.getDateNaissance()));
			if(user instanceof Administrateur)
			{
				myStatement.setBoolean(8, true);
			}
			else
			{
				myStatement.setBoolean(8, false);
			}
			myStatement.setInt(9,  user.getChat().getChatID());
			if(user.getUserID() > 0)
			{
				myStatement.setInt(10, user.getUserID());
			}
			
			myStatement.execute();
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connection à la base de données");
			e.printStackTrace();
		}
		return user;
	}
	
	public void delete(Utilisateur user)
	{
		this.deleteById(user.getUserID());
	}
	
	public void deleteById(int id)
	{
		try {
			this.connect();
			PreparedStatement myStatement = connection.prepareStatement("DELETE FROM utilisateur WHERE UTIL_ID = ?");
			
			myStatement.setInt(1, id);
			
			myStatement.execute();
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connection à la base de données");
			e.printStackTrace();
		}
	}
	
	public Utilisateur authentification(String username, String password)
	{
		Utilisateur u = null;
		try {
			this.connect();
			PreparedStatement myStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE UTIL_USER_NAME = ? AND UTIL_PASSWORD = ? AND UTIL_Banni = 0");
			myStatement.setString(1, username);
			myStatement.setString(2, password);
			ResultSet myResult = myStatement.executeQuery();
			if(myResult.next())
			{
				u = this.map(myResult);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connection à la base de données");
			e.printStackTrace();
		}
		return u;
	}
	
	public Utilisateur findByUsername(String username)
	{
		Utilisateur u = null;
		try {
			this.connect();
			PreparedStatement myStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE UTIL_USER_NAME = ?");
			myStatement.setString(1, username);
			ResultSet myResult = myStatement.executeQuery();
			if(myResult.next())
			{
				u = this.map(myResult);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur de connection à la base de données");
			e.printStackTrace();
		}
		return u;
	}
	
	
}
