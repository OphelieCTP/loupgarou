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
  private currVill : Villageois = new Villageois();

  constructor(private villService: JoueurService) { }

  ngOnInit() {
       this.loadCurrentUser();
  }




  loadCurrentUser() {
         this.villService.findById(8).subscribe(resp => this.currVill = <Villageois>resp );
    }

    voter() {
         console.log(this.currVill.userName);
    }

}
