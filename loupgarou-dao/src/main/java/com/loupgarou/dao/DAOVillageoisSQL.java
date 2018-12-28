package com.loupgarou.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import com.loupgarou.model.*;

public class DAOVillageoisSQL implements IDAOVillageois {
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
				//url de connexion avec local host : là ou veut se connecter et dans cas présent, pas WP et root utilise
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public Villageois map(ResultSet result) throws SQLException
	{
			String role = result.getString("VILL_ROLE");
			Villageois vill = new Villageois(); 
			if(role == "Voyante") { vill = new Voyante();  }
			else {
				if(role == "Sorciere") { vill = new Sorciere();}
				else {
					if(role == "Petite Fille") { vill = new PetiteFille(); }
					else {
						if(role == "Cupidon") { vill = new Cupidon(); }
						else {
							if(role == "Chasseur") { vill = new Chasseur(); }
							else {
								if(role == "Loup Garou") { vill = new Loup(); }
							}
						}
					}
				}
			}
			vill.setVillID(result.getInt("VILL_ID"));
			vill.setRole(result.getString("VILL_ROLE"));
			vill.setVivant(result.getBoolean("VILL_VIVANT"));
			vill.setCapitaine(result.getBoolean("VILL_CAPITAINE"));
			vill.setAmoureux(result.getBoolean("VILL_AMOUREUX"));
			vill.setUserID(result.getInt("VILL_USER_ID"));
			vill.setaVote(result.getBoolean("PARTICIP_A_VOTE"));
			vill.setPeutVoter(result.getBoolean("PARTICIP_PEUT_VOTER"));
			vill.setVote(result.getInt("PARTICIP_BULLETIN"));
			IDAOPartie idaoPartie = new DAOPartieSQL();
			Partie p = new Partie();
			p = idaoPartie.findById(result.getInt("PARTICIP_PARTIE_ID"));
			vill.setPartie(p);
			return vill; 
		}
	

	@Override
	public List<Villageois> findAll() {
		List<Villageois> villageois = new ArrayList<Villageois>() ;
		try {
			this.connect();
			Statement myStatement = this.connection.createStatement();
			String myQuery = "SELECT * FROM villageois v JOIN participation p ON p.PARTICIP_VILLAGEOIS_ID = v.VILL_ID"; 
			ResultSet myResult = myStatement.executeQuery(myQuery);
			while (myResult.next()) {villageois.add(this.map(myResult)); }
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return villageois; 
	}

	@Override
	public Villageois findById(int id) {
		Villageois vill = null ;
		try {
			this.connect();
			String myQuery = "SELECT * FROM utilisateur u LEFT JOIN villageois v ON v.VILL_USER_ID = u.UTIL_ID LEFT JOIN participation p ON p.PARTICIP_VILLAGEOIS_ID = v.VILL_ID"; 
			PreparedStatement myStatement = this.connection.prepareStatement(myQuery+" WHERE VILL_ID = ?");
			myStatement.setInt(1, id);
			ResultSet myResult = myStatement.executeQuery();
			if (myResult.next()) {
				vill = new Villageois() ;
				vill = this.map(myResult) ;
				}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally { return vill; }
	}

		
	@Override
public Villageois save(Villageois entity) {
		
		try {
			this.connect();
			String myQuery = "";
			String myQueryParticipation = "";
			if(entity.getVillID() ==0) { // creer villageois + participation
				myQuery = "INSERT INTO villageois (VILL_ROLE, VILL_VIVANT, VILL_CAPITAINE, VILL_AMOUREUX, VILL_USER_ID)"
						+ "VALUES (?, ?, ?, ?, ?)";
				myQueryParticipation = "INSERT INTO participation ("
						+ "PARTICIP_PARTIE_ID, PARTICIP_VILLAGEOIS_ID, PARTICIP_A_VOTE, PARTICIP_PEUT_VOTER, PARTICIP_BULLETIN)"
						+ "VALUES (?, LAST_INSERT_ID(), ?, ?, ?)";
			}
			else {
				myQuery = "UPDATE villageois SET VILL_ROLE = ?, VILL_VIVANT  = ?, VILL_CAPITAINE = ?, VILL_AMOUREUX = ?, VILL_USER_ID = ?"
						+ ", WHERE VILL_ID = ?";
				myQueryParticipation = "UPDATE participation "
						+ "SET PARTICIP_PARTIE_ID = ?, PARTICIP_VILLAGEOIS_ID  = ?, PARTICIP_A_VOTE = ?, PARTICIP_PEUT_VOTER = ?, PARTICIP_BULLETIN = ?"
						+ ", PARTICIP_ID = ?";
			}
			
			// PRB : Participation à une partie !!! 
			
			PreparedStatement myStatement =this.connection.prepareStatement(myQuery);
			myStatement.setString(1, entity.getRole());
			myStatement.setBoolean(2, entity.getVivant());
			myStatement.setBoolean(3, entity.getCapitaine());
			myStatement.setBoolean(4, entity.getAmoureux());
			myStatement.setInt(5, entity.getUserID());
			if (entity.getVillID() > 0) {
				myStatement.setInt(6, entity.getVillID()); }
			myStatement.execute();
			
			PreparedStatement myStatementParticipation = this.connection.prepareStatement(myQueryParticipation);
			myStatementParticipation.setInt(1, entity.getPartie().getId());
//			myStatementParticipation.setInt(2, entity.getVillID());
			myStatementParticipation.setBoolean(2, entity.getaVote());
			myStatementParticipation.setBoolean(3, entity.getPeutVoter());
			myStatementParticipation.setObject(4, entity.getVote());
			
			if (entity.getVillID() > 0) {
				// get ID participation 
				// get participation correspondante 
				try {
					this.connect();
					Statement myStat = this.connection.createStatement();
					String newQuery = "SELECT PARTICIP_ID, PARTICIP_VILLAGEOIS_ID FROM participation WHERE"
							+ " PARTICIP_VILLAGEOIS_ID = " + entity.getVillID()
							+ " AND PARTICIP_PARTIE_ID = "+entity.getPartie().getId(); 
					ResultSet res = myStat.executeQuery(newQuery);
					int participID = 0 ;
					while (res.next()) {
						participID = res.getInt("PARTICIP_ID");
						}
					myStatementParticipation.setInt(5, participID); }
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			myStatementParticipation.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return entity;
	}

	@Override
	public void delete(Villageois entity) {
		if(entity.getVillID()!=0) {
			this.deleteById(entity.getVillID());
		}
	}

	@Override
	public void deleteById(int id) {
		try {
			this.connect();
			Villageois vill = this.findById(id);
			PreparedStatement newStatement =
					this.connection.prepareStatement("DELETE FROM villageois WHERE VILL_ID = ?");
			newStatement.setInt(1, id);
			newStatement.execute();
			
			PreparedStatement newStat =
					this.connection.prepareStatement("DELETE FROM participation WHERE PARTICIP_VILLAGEOIS_ID = ? AND PARTICIP_PARTIE_ID = ? ");
			newStat.setInt(1, id);
			newStat.setInt(2, vill.getPartie().getId());
			newStat.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		 
	}

	@Override
	public void updateRole(Villageois v) {
		// TODO Auto-generated method stub
		
	}
}
