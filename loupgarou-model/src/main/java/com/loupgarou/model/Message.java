package com.loupgarou.model;

public class Message {
	private String Joueur;
	private String contenu;
	private int dateCreation;
	private Visible visible;
	private int idPartie;
	
	public Message()
	{
		
	}
	
	
	public String getJoueur() {
		return Joueur;
	}
	public void setJoueur(String joueur) {
		Joueur = joueur;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(int dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Visible getVisible() {
		return visible;
	}
	public void setVisible(Visible visible) {
		this.visible = visible;
	}
	public int getIdPartie() {
		return idPartie;
	}
	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}
	
	
}
