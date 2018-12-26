package com.loupgarou.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tachat")
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TCHAT_ID")
	private int chatID;
	
	@OneToMany(mappedBy="chat")
	private ArrayList<Message> messages = new ArrayList<Message>();
	
	@OneToMany(mappedBy="chat")
	private ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	@OneToOne(mappedBy="PART_CHAT")
	private Partie partie;
	
	public Chat()
	{
		
	}
	
	public ArrayList<Message> getMessages() {
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

	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
}
