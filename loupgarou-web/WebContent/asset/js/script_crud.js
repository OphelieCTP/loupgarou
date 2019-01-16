var databaseLink = "http://192.168.1.117/loupgarou-web/crud.html";

var eventSource = new EventSource("http://192.168.1.117/loupgarou-web/crud.html");

var pursuerPathD = "";
var runnerPathD = "";
var pursuerPathNodetype = "";
var runnerPathNodetype = "";

var crudModifierJS = function(){
	alert("modification joueur");
}

var crudBanirJS = function(){
	alert("banir joueur");
}

var crudSupprimerJS = function(){
	alert("supprimer joueur");
}


//
//
//console.log("test");
//
//
////navigator.geolocation.getCurrentPosition(function(position));
//
////http://maps.googleapis.com/maps/api/geocode/json?latlng=la_lat,la_lng&key=AIzaSyBiAmyKDEL6IhU7af_oCPzDWFDYzp0y9wg
//
//
//// cas des push
//
//function createRowProduit(produit){
//	var newTr = $("<tr />");
//	var col1 = $("<td />");
//	var col2 = $("<td />");
//	var col3 = $("<td />");
//	var col4 = $("<td />");
//	var col5 = $("<td />");
//	col1.html($('table tbody tr').length + 1);
//	col2.html(produit.modele);
//	col3.html(produit.description);
//	col4.html(produit.prix);
//	col5.html(produit.fournisseur.nom);
//	newTr.append(col1);
//	newTr.append(col2);
//	newTr.append(col3);
//	newTr.append(col4);
//	newTr.append(col5);
//	$("table tbody").append(newTr);
//	
//}
//
//
//
//
//
//// fonction utiles : onmessage qui se déclenche quand message reçut du serveur
//// close : fermer la connection
//eventSource.onmessage = function(evt){
//	//evt.data = donnée recue du serveur
//	//envoit produit ajouter par qq'un ici
//	var monProduitrecuDuServeur = JSON.parse(evt.data);
//	createRowProduit(monProduitrecuDuServeur)
//}