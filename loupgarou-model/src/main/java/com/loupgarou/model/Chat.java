package com.loupgarou.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TCHAT_ID")
	private int chatID;
	
	@OneToMany(mappedBy="chat")
	private List<Message> messages;
	
	@OneToMany(mappedBy="chat")
	private List<Utilisateur> utilisateurs;
	
	@OneToOne(mappedBy="chat")
	private Partie partie;
	
	public Chat()
	{
		
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatId) {
		this.chatID = chatId;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
}
