import { Component, OnInit } from '@angular/core';

import { Utilisateur } from '../utilisateur';

@Component({
  selector: 'app-crud',
  templateUrl: './crud.component.html',
  styleUrls: ['./crud.component.css']
})
export class CrudComponent implements OnInit {

	private utilisateurs: Array<Utilisateur> = new Array<Utilisateur>();
	private utilisateur: Utilisateur = new Utilisateur() ;

  constructor() { }

  ngOnInit() {
  }

}
