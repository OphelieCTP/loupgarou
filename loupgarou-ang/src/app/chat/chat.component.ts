import { Component, OnInit } from '@angular/core';

import { Message } from '../message';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
	public message: Message = new Message();

	public messages: Array<Message> = new Array<Message>();

  constructor(private messageService: MessageService) { 
  	this.messages = messageService.messAsync; }

  ngOnInit() {
  }

  postMessage(){
  	this.messageService.save(this.message);
  }

}
