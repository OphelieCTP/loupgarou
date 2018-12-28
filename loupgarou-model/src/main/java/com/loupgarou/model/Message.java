package com.loupgarou.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MESS_ID")
	private int idMessage;

	@Column(name="MESS_USERNAME", length=50, nullable=false)
	@NotEmpty
	@NotNull
	@Size(max=50)
	private String joueur;
	
	@Column(name="MESS_description")
	@Lob
	private String contenu;
	
	@Column(name="MESS_DATE_ENVOI")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column(name="MESS_VISIBLE")
	private Visible visible;

	@ManyToOne
	@JoinColumn(name="MESS_ID_CHAT")
	private Chat chat; 
	
	public Message()
	{
		
	}
	
	public Message(String contenu)
	{
		this.contenu = contenu;
	}
	
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Visible getVisible() {
		return visible;
	}
	public void setVisible(Visible visible) {
		this.visible = visible;
	}
	public Chat getChat() {
		return chat;
	}
	
	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getJoueur() {
		return joueur;
	}

	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}
	
}
