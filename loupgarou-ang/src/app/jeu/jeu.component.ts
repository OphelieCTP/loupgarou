import { Component, OnInit } from '@angular/core';
import { Villageois } from '../villageois';
import { JoueurService } from '../joueur.service';


@Component({
  selector: 'app-jeu',
  templateUrl: './jeu.component.html',
  styleUrls: ['./jeu.component.css']
})
export class JeuComponent implements OnInit {

  private mesVills: any;
  private bulletin : number;

  constructor(private villService: JoueurService) { }

  ngOnInit() {

  }




   // loadCurrentUser() {
   //        this.villService.findById();
   //   }

    voter() {
         // this.villService.currVillAsync.subscribe(resp => console.log(resp.userID));
         // console.log(this.bulletin);
         this.villService.currVillAsync.subscribe(resp => this.villService.sendVote(resp.userID, this.bulletin));
    }

}
