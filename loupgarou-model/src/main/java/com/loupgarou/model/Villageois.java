package com.loupgarou.model;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.fonctions;


@Entity
@Table(name = "villageois")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="VILL_ROLE", discriminatorType = DiscriminatorType.STRING)

@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="VILL_USER_ID"))
})

public class Villageois extends Utilisateur{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VILL_ID")
	protected int villID;
	
	@Column(name="VILL_ROLE", length = 50)
	@NotEmpty
	protected String role = "";
	
	@Column(name="VILL_AMOUREUX")
	@NotEmpty
	protected Boolean amoureux;
	
	@Column(name="VILL_VIVANT")
	@NotEmpty
	protected Boolean vivant;
	
	@Column(name="VILL_BULLETIN")
	@NotNull
	protected Integer vote;
	
	@Column(name="VILL_PEUT_VOTER")
	@NotEmpty
	protected Boolean peutVoter = false;
	
	@Column(name="VILL_A_VOTE")
	@NotEmpty
	protected Boolean aVote = false;
	
	@Column(name="VILL_ENDORMIT")
	@NotEmpty
	protected Boolean endormit;
	
	@Column(name="VILL_CAPITAINE")
	@NotEmpty
	protected Boolean capitaine;
	
	@ManyToOne
	@JoinColumn(name="VILL_PARTIE_ID")
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
		this.partie = partie;		
		this.amoureux = false;
		this.vivant = true;
		this.endormit = false;
		this.capitaine = false;

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
