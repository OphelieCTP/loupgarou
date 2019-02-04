import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';

import { AppComponent } from './app.component';
import { AppConfigService } from './app-config.service';
import { RulesComponent } from './rules/rules.component';
import { HomeComponent } from './home/home.component';
import { CrudComponent } from './crud/crud.component';
import { JeuComponent } from './jeu/jeu.component';
import { MonCompteComponent } from './mon-compte/mon-compte.component';


//Configuration des routes
const routes: Routes = [
	{ path: 'home', component: HomeComponent},
	{ path: 'crud', component: CrudComponent},
	{ path: 'jeu', component: JeuComponent},
	{ path: 'rules', component: RulesComponent},
	{ path: 'monCompte', component: MonCompteComponent}
	//{ path: 'produit/:id', component: ProduitDetailComponent },
	//{ path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    RulesComponent,
    HomeComponent,
    CrudComponent,
    JeuComponent,
    MonCompteComponent
  ],
  imports: [
    BrowserModule, 
    FormsModule, 
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [AppConfigService],
  bootstrap: [AppComponent]
})
export class AppModule { }
