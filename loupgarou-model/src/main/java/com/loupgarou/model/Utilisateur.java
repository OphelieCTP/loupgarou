package com.loupgarou.model;

import java.util.ArrayList;



import java.util.Date;

import com.loupgarou.divers.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="utilisateur")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="UTIL_ROLE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Utilisateur")

public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UTIL_ID")
	protected int userID = 0;
	
	@Column(name="UTIL_USERNAME", length=50, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=50)
	protected String userName = "";
	
	@Column(name="UTIL_PASSWORD", length=250, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=250)
	protected String passWord = "";
	
	@Column(name="UTIL_NB_PLAINTES")
	protected int nbPlaintes;
	
	@Column(name="UTIL_IS_CONNECTED")
	protected Boolean isConnected = false;
	
	@Column(name="UTIL_BANNI")
	protected Boolean isBanni = false;
	
	@Column(name="UTIL_EMAIL", length=100, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=100)
	protected String email;
	
	@Column(name="UTIL_DATE_NAISS")
	@Temporal(TemporalType.DATE)
	protected Date dateNaissance;
	
	@ManyToOne
	@JoinColumn(name="UTIL_ID_CHAT")
	protected Chat chat; 
	
	@Column(name="UTIL_ROLE", length = 50 , insertable=false, updatable=false)	
	@NotEmpty
	protected String role = "";
	
	public Utilisateur()
	{
		this.chat = new Chat();
		this.getChat().setChatID(1);
		this.role = "Utilisateur";
	}
	
	public Utilisateur(String userName, String passWord, int userID)
	{
		this.chat = new Chat();
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.nbPlaintes = 0;
		this.isConnected = false;
		this.getChat().setChatID(1);
	}

	public Utilisateur(String userName, String passWord, int userID, int nbPlaintes, Boolean isBanni, Boolean isConnected, String email, Date dateNaissance)
	{
		this.chat = new Chat();
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.nbPlaintes = nbPlaintes;
		this.isConnected = isConnected;
		this.isBanni = isBanni;
		this.email = email;
		this.dateNaissance = dateNaissance;
	}
	
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getNbPlaintes() {
		return nbPlaintes;
	}

	public void setNbPlaintes(int nbPlaintes) {
		this.nbPlaintes = nbPlaintes;
	}
	
	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}

	public Boolean getIsBanni() {
		return isBanni;
	}

	public void setIsBanni(Boolean isBanni) {
		this.isBanni = isBanni;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Boolean seConneecter()
	{
		System.out.println("Veuillez saisir votre mot de passe : ");
		String mdp = fonctions.lireChaine();
		if(mdp.equals(this.passWord))
		{
			System.out.println("Bienvenue " + this.userName);
			this.isConnected = true;
			return this.isConnected;
		}
		return isConnected;
	}
	
	public void seDeconnecter()
	{
		this.isConnected = false;
		System.out.println("Vous êtes maintenant déconnecté !\nAu revoir !");
	}
	
	public void afficherUtilisateur()
	{
		if(this.isConnected)
			System.out.println(this.userName + " : " + "Connecté.");
		else
		{
			System.out.println(this.userName + " : " + "Deconnecté.");
		}
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
//	public Partie creerPartie()
//	{
//		Partie partie = new Partie();
//		partie.ajouterJoueur(this);
//		System.out.println("Partie crée - identifiant : " + partie.getId());
//		return partie;
//	}
//	
//	public Boolean rejoindrePartie(ArrayList<Partie> parties, int id)
//	{
//		//int id;
//		//System.out.println("Quel est l'identifiant de la partie à rejoindre ?");
//		//id = fonctions.lireEntier();
//		
//		for(Partie p : parties)
//		{
//			if(p.getId() == id)
//			{
//				if(p.getEtat() == true)
//				{
//					p.ajouterJoueur(this);
//					return true;
//				}
//				else
//				{
//					System.out.println("Partie déjà commencée.");
//					return false;
//				}
//			}
//		}
//		System.out.println("Partie introuvable.");
//		return false;
//	}
	
	//creerPartie;
	
	//rejoindrePartie;
}
