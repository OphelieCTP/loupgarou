import { Injectable } from '@angular/core';
import { Villageois } from './villageois';
import { AppConfigService } from './app-config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JoueurService {
  public villAsync : any = null;

  constructor(private appConfig : AppConfigService, private httpClient : HttpClient) { }

  findAllAsync() {
         if(this.villAsync == null)
         {
              this.villAsync = this.httpClient.get("http://localhost:8080/api/jeu"); //Version un traitmeent
              // this.httpClient.get("http://localhost-8080/api/produit").subscribe(resp => { console.log(resp);this.produits = resp; } ) //Accolades plusieurs traintements
         }
         return this.villAsync;
    }

    findById(id : number)
    {
          return this.httpClient.get("http://localhost:8080/api/jeu/"+id);
    }
}
