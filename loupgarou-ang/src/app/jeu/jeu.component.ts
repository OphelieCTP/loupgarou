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
       this.loadCurrentUser();
  }




   loadCurrentUser() {
          this.villService.findById(8);
     }

    voter() {
         this.villService.currVillAsync.subscribe(resp => console.log(resp.userID));
         console.log(this.bulletin);
    }

}
