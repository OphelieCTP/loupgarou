package com.loupgarou.model;

import java.util.ArrayList;

public class Chat {
	private ArrayList<Message> messages = new ArrayList<Message>();
	private int chatID;
	
	
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
	
	
	
	
}
