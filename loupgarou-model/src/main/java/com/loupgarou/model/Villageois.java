package com.loupgarou.model;
import java.util.ArrayList;

import java.util.List;


import com.loupgarou.divers.fonctions;

public class Villageois extends Utilisateur{
	
	protected int villID;
	protected String role = "";
	protected Boolean amoureux;
	protected Boolean vivant;
	protected Integer vote;
	protected Boolean peutVoter = false;
	protected Boolean aVote = false;
	protected Boolean endormit;
	protected Boolean capitaine;
	protected Partie partie;
	
	public String getRole() {
		return role;
	}
	
	
	
	public int getVillID() {
		return villID;
	}



	public void setVillID(int villID) {
		this.villID = villID;
	}



	public Boolean getPeutVoter() {
		return peutVoter;
	}



	public void setPeutVoter(Boolean peutVoter) {
		this.peutVoter = peutVoter;
	}



	public Boolean getaVote() {
		return aVote;
	}



	public void setaVote(Boolean aVote) {
		this.aVote = aVote;
	}



	public Partie getPartie() {
		return partie;
	}



	public void setPartie(Partie partie) {
		this.partie = partie;
	}



	public Villageois()
	{
		
	}
	
	public Villageois(int user_id, String userName, String passWord, String role, Partie partie)
	{
		super(userName, passWord, user_id);
		this.isConnected = true;
		this.role = role;
		this.amoureux = false;
		this.vivant = true;
		this.endormit = false;
		this.capitaine = false;
		this.partie = partie;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getAmoureux() {
		return amoureux;
	}
	public void setAmoureux(Boolean amoureux) {
		this.amoureux = amoureux;
	}
	public Boolean getVivant() {
		return vivant;
	}
	public void setVivant(Boolean vivant) {
		this.vivant = vivant;
	}
	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	public Boolean getEndormit() {
		return endormit;
	}
	public void setEndormit(Boolean endormit) {
		this.endormit = endormit;
	}
	public Boolean getCapitaine() {
		return capitaine;
	}
	public void setCapitaine(Boolean capitaine) {
		this.capitaine = capitaine;
	}
	
//	public void voter(){
//		ArrayList<Villageois> village = new ArrayList<Villageois>();
//		village = this.partie.getListJoueurs(this.partie.getJoueurs());
//		while(this.aVote==false) {
//			System.out.println("Pour quel joueur souhaitez vous voter ?");
//			Villageois v = this.partie.getVillageois(village, fonctions.lireChaine());
//			if(v.getVivant()==true) {
//				this.setaVote(true);
//			}
//			else {
//				System.out.println("Vous ne pouvez voter contre ce joueur, il est mort");
//			}
//		}
//		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
//		daoVillageois.save(this);
//		System.out.println(this.userName + " a voté contre " +daoVillageois.findById(this.getVote()));
//	}
//	
//	public Villageois mourrir(Villageois v) {
//		v.setVivant(false);
//		IDAOVillageois daoVillageois = new DAOVillageoisSQL();
//		daoVillageois.save(v);
//		return v;
//	}
	
//	public void tuerEtSeFaireTuer(){ // 3 cas a gérer : par vote, loups, chasseur et amoureux
//		ArrayList<Villageois> village = new ArrayList<Villageois>();
//		village = this.partie.getListJoueurs(this.partie.getJoueurs());
//		if (this.amoureux==true) {
//			for (Villageois v : village) {
//				if (v.getAmoureux()==true && v.getVivant()==false) {
//					mourrir(this);
//					System.out.println("Vous etes morts car votre partenaire est mort");
//				}
//			}
//		}
//		if (this.role=="Chasseur") {
//			System.out.println("Vous pouvez tuer quelqu'un");
//			
//			// PRB : ne doit tuer que la première fois qu'il est considéré comme mort
//		}
//		
//	}
	
}
