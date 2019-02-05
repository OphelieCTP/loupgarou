import { Injectable } from '@angular/core';

import { Message } from './message';

import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';

import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
	public url:string = "localhost:8080/api/jeu";

	public messages: Array<Message> = new Array<Message>();
	public messAsync : any = null;

	constructor(
    private appConfig : AppConfigService, 
    private httpClient : HttpClient) { }

	findAllAsync(id: number){ 
  	/** manipule promesse qui aura qq chose et chargera autre entités de se démerder et pas manip direct list produit ; specifie ainsi que l'async = promesse de retour */

  	/** ENTÊTE HTTP */
  	//let myHeaders: HttpHeaders = new HttpHeaders();

  	/**APPLIQUE ID A ENTETE */
  	//myHeaders = myHeaders.append('Authorization', "Basic "+ btoa("hades.enfers@monde_souterrain.com:123456")); 
  	/** active pour connexion sans passer par formulaire de connexion ; btoa =  convertion base 64 */

  	/** OPTIONS HTTP */
  	//let myOptions = { headers: myHeaders }

  	if (this.messAsync == null){
  	  	this.messAsync = this.httpClient.get<Message[]>("http://localhost:8080/api/chat");  		
  	}
  	return this.messAsync;
  }

  save(message: Message){
    this.httpClient.post("http://localhost:8080/api/chat", message).subscribe(resp => this.refresh());
  }

  refresh(){this.messAsync = null}


}
