package com.loupgarou.model;

import java.util.ArrayList;

import java.util.Date;

import com.loupgarou.divers.*;

public class Utilisateur {
	protected int userID = 0;
	protected String userName = "";
	protected String passWord = "";
	protected int nbPlaintes;
	protected Boolean isConnected = false;
	protected Boolean isBanni = false;
	protected String email;
	protected Date dateNaissance;
	protected Chat chat; 
	
	public Utilisateur()
	{
		this.chat = new Chat();
		this.getChat().setChatID(1);
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
