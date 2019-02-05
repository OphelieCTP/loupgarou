import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

import { Message } from '../message';

import { MessageService } from '../message.service';

@Component({
  selector: 'app-chat,[app-chat]',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
	public message: Message = new Message("joueur", "...", new Date(), "Villageois");

	public messages: Array<Message> = new Array<Message>();

  constructor(private messageService: MessageService) { 
  	this.messages = this.messageService.messAsync; }

  ngOnInit() {
  }

  postMessage(message: Message){
    this.message.dateCreation = new Date();
    console.log(this.message.dateCreation);
  	this.messageService.save(this.message);
  }

  exit() {
    window.location.reload();
  }

}
