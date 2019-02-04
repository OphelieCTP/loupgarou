import { Injectable } from '@angular/core';

import { HttpClientModule, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
	public url:string = "localhost:8080/api/jeu";

	constructor() { }
}
