import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

import { Message } from '../message';

import { MessageService } from '../message.service';

import { JoueurService } from '../joueur.service';

import { JeuComponent } from '../jeu/jeu.component';
import { Villageois } from '../villageois';



@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
	public message: Message = new Message("joueur", "...", new Date(), "Villageois");

	public messages: Array<Message> = new Array<Message>();

  public author: Villageois = new Villageois();

  constructor(private messageService: MessageService, public jeuComp: JeuComponent) {
  	this.messages = this.messageService.messAsync; 
    console.log(this.jeuComp.currId);
    console.log(this.jeuComp.currUser);
    this.jeuComp.villService.findById(this.jeuComp.currId).subscribe(resp => this.author = resp);
  }

  getAuthor(){
    this.message.joueur = this.author.userName;
  }

  sendMessage(message: Message){
    this.getAuthor();
    console.log(this.jeuComp.currUser);
    this.postMessage(message);
  }

  postMessage(message: Message){
    this.getAuthor();
    this.message.dateCreation = new Date();
    //console.log(this.message.dateCreation);
    this.messageService.save(this.message);
  }

  exit() {
    window.location.reload();
  }



  ngOnInit() {
  }


}
