package com.loupgarou.model;
import java.util.ArrayList;


import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@DiscriminatorValue("Villageois")


public class Villageois extends Utilisateur{
//	@Column(name="VILL_ID")
//	protected int villID;
	
	@Column(name="VILL_AMOUREUX")
	protected Boolean amoureux;
	
	@Column(name="VILL_VIVANT")
	protected Boolean vivant;
	
	@Column(name="VILL_BULLETIN")
	@NotNull
	protected Integer vote;
	
	@Column(name="VILL_PEUT_VOTER")
	protected Boolean peutVoter = false;
	
	@Column(name="VILL_A_VOTE")
	protected Boolean aVote = false;
	
	@Column(name="VILL_ENDORMIT")
	protected Boolean endormit;
	
	@Column(name="VILL_CAPITAINE")
	protected Boolean capitaine;
	
	@ManyToOne
	@JoinColumn(name="VILL_PARTIE_ID")
	protected Partie partie;
	
//	public int getVillID() {
//		return villID;
//	}
//
//
//
//	public void setVillID(int villID) {
//		this.villID = villID;
//	}



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
		this.role = "Villageois";
	}
	
	public Villageois(int user_id, String userName, String passWord, String role, Partie partie)
	{
		super(userName, passWord, user_id);
		this.isConnected = true;
		this.role = role;
		this.partie = partie;		
		this.amoureux = false;
		this.vivant = true;
		this.endormit = false;
		this.capitaine = false;

	}
	
	
	public Villageois(Utilisateur util, String role, Partie partie)
	{
		super(util.getUserName(), util.getPassWord(), util.getUserID(), util.getNbPlaintes(), util.getIsBanni(), util.getIsConnected(), util.getEmail(), util.getDateNaissance());
		this.chat = partie.getChat();
		this.role = role;
		this.partie = partie;		
		this.amoureux = false;
		this.vivant = true;
		this.endormit = false;
		this.capitaine = false;
	}
	
	public Villageois(Villageois v)
	{
		super(v.getUserName(), v.getPassWord(), v.getUserID(), v.getNbPlaintes(), v.getIsBanni(), v.getIsConnected(), v.getEmail(), v.getDateNaissance());
		//this.role = v.getRole();
		this.chat = v.getChat();
		this.partie = v.getPartie();	
		this.amoureux = v.getAmoureux();
		this.vivant = v.getVivant();
		this.vote = v.getVote();
		this.peutVoter = v.getPeutVoter();
		this.aVote = v.getaVote();
		this.endormit = v.getEndormit();
		this.capitaine = v.getCapitaine();
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
	
	public Villageois voter(){
		while(this.aVote==false) {
			System.out.println("Pour quel joueur souhaitez vous voter ?");
			String cible = fonctions.lireChaine();
			for (Villageois v : this.getPartie().getJoueurs()) {
				if(v.getUserName()==cible) {
					if(v.getVivant()==true && v.getPeutVoter() == true) { 
						this.setaVote(true); 
						this.setVote(v.getUserID());
						System.out.println(this.userName + " a vot� contre "+v.getUserName());
						return v;
						}
					else { System.out.println("Vous ne pouvez voter contre ce joueur, il est mort"); }
					break;
				}
			}
		}
		return null;
	}
	
	public Villageois mourrir() {
		this.setVivant(false);
		Message annonce = new Message(this.getUserName() + " a �t� tu� !", this.getChat());
		annonce.setVisible(Visible.Villageois);
		if(this.getAmoureux() == true)
		{
			for(Villageois v : this.getPartie().getJoueurs())
			{
				if(v.getAmoureux() == true && v.getVivant() == true)
				{
					v.setVivant(false);
					System.out.println(this.getUserName() + " a �galement �t� tu� ! Il �tait : " + this.getRole());
				}
			}
		}
		return this;
	}
	
	public Message creerMessage(Message message) {
		Message post = new Message(fonctions.lireChaine(), this.getChat());
		post.setVisible(Visible.Villageois);
		if(this.role=="Loup") { post.setVisible(Visible.Loup); }
		if(this.vivant==false) { post.setVisible(Visible.Mort); }
		return post;
	}
	
	public void lireMessages(Message message) {
		if(message.getVisible()!=Visible.Villageois) {
			if(this.role=="Loup" || this.vivant==false) {
				System.out.println(message.getContenu());
			}
		}
		else {
			System.out.println(message.getContenu());
		}
	}
	
	public void quitterPartie()
	{
		this.setPartie(null);
	}
	
	

//	public void tuerEtSeFaireTuer(){ // 3 cas a g�rer : par vote, loups, chasseur et amoureux
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
//			// PRB : ne doit tuer que la premi�re fois qu'il est consid�r� comme mort
//		}
//		
//	}
	
}
