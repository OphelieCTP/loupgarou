var databaseLink = "http://192.168.1.110/loupgarou-ajax/personnage";
//var eventSource = new EventSource("http://192.168.1.110/loupgarou-ajax/personnage/listen-new");

$('section[id=addUser]').hide();

$('input[id=add]').bind('click',function(){
	// alert("declencheur");
	$('section[id=addUser]').show();
	return false;
})

$('input[id=addDone]').bind('click',function(){
	// alert("declencheur");
	$('section[id=addUser]').hide();
	return false;
})

// si temps pour jouer avec svg
var pursuerPathD = "";
var runnerPathD = "";
var pursuerPathNodetype = "";
var runnerPathNodetype = "";

//console.log($("svg id='runner' path d"))
//
//console.log("tentative");
//jQuery.get('http://localhost/loupgarou-web/', function(data){console.log(data)});

// get Personnages

function createRowUser(user){
	var newTr = $("<tr id='"+user.id+"' />");
	var col1 = $("<td class='align-middle' id='ID' />"); // ID
	var col2 = $("<td class='align-middle' id='user' />"); // userName
	var col3 = $("<td class='align-middle' id='wp' />"); // wp
	var col4 = $("<td class='align-middle' id='connexion' />"); // connexion
	var col5 = $("<td class='align-middle' id='bani' />"); // banni
	var col6 = $("<td class='align-middle' id='plaintes' />"); // plaintes
	var col7 = $("<td class='align-middle' id='naissance' />"); // naissance
	var col8 = $("<td class='align-middle' id='actions' />"); // actions possibles
	
	var newactDivSupp = $("<div />");
	var newactDivSub = $("<div class='col-xs-3' />");
	var newactinputModif = $("<input type='submit' class='btn btn-primary btn-md btn-block' value = 'Modifier' onclick='crudModifierJS("+user.id+"); return false;'>");
	var newactinputSpace = $("<p> </p>") ;
	var newactinputBanir = $("<input type='submit' class='btn btn-primary btn-md btn-block' value = 'Banir' onclick='crudBanirJS("+user.id+"); return false;'>");
	var newactinputSupprimer = $("<input type='submit' class='btn btn-primary btn-md btn-block' value = 'Supprimer' onclick='crudSupprimerJS("+user.id+"); return false;'>");
	
	newactDivSub.append(newactinputModif);
	newactDivSub.append(newactinputSpace);
	newactDivSub.append(newactinputBanir);
	newactDivSub.append(newactinputSpace);
	newactDivSub.append(newactinputSupprimer);
	
	newactDivSupp.append(newactDivSub);
	col8.append(newactDivSupp);
	
	col1.html(user.id);
	col2.html(user.libelle);
	col3.html("WP");
	col4.html(true);
	col5.html(false);
	col6.html(0);
	col7.html("0000-00-00");
	
	newTr.append(col1);
	newTr.append(col2);
	newTr.append(col3);
	newTr.append(col4);
	newTr.append(col5);
	newTr.append(col6);
	newTr.append(col7);
	newTr.append(col8);
	$("table tbody").append(newTr);
}


$.ajax({
	method: 'GET',
	url: databaseLink,
	success: function(personnages) {
		console.log(personnages);
		for (let personnage of personnages){
			createRowUser(personnage);
		}
	}
})

var crudAjouterJS = function(){
	alert("ajouter joueur");
	var nouveauJoueur = {
			//id:10,
			libelle: $('input[id="nom"]').val(),
			// wp: $('input[id="wp"]').val(),
			// connexion:false,
			// banni:false,
			// plaintes:0,
			// naissance: $('input[id="naissance"]').val(),
			pouvoir: {
				id:1,
				libelle:"Loup"
			}
	}
	
	console.log(nouveauJoueur)
	
	var personnages = $.ajax({
		method: 'POST',
		url: databaseLink,
		contentType: "application/json",
		data: JSON.stringify(nouveauJoueur),
		success: function(personnage) {
			createRowUser(personnage);
		}
	})
}


var crudSupprimerJS = function(id){
	$("table tbody tr[id='"+id+"'] ").remove();
	alert("supprimer joueur");
	var cible =  $.ajax({
			method: 'DELETE',
			url: databaseLink+"/"+id,
			success: function(personnages) {
				console.log(personnages);
			}
		})
}


var crudModifierJS = function(id){
	alert("modification joueur "+id);
	
	var nouveauJoueur = $.ajax({
		method: 'GET',
		url: databaseLink,
		success: function(personnages) {
			console.log(personnages);
			for (let personnage of personnages){
				if(personnage.id===id){
					var cible = parseInt(prompt("Modifier : 1- Joueur 2-Pouvoir ? "));
					var att = prompt("Attributs modifiables disponibles : id, libelle. Modifier lequel ? ");
					var result = prompt("Nouvelle valeur ? ");
					console.log(typeof parseInt(cible));
					switch (cible){
					case 1: 
						console.log(att);
						if(att=="id"){personnage.id=result;}
						else{personnage.libelle=result;}
						break;
					case 2: 
						if(att=="id"){personnage.pouvoir.id=result;}
						else{personnage.pouvoir.libelle=result;}
						break;
					}
					console.log(personnage);
					alert(personnage);
					
					$.ajax({
						method: 'PUT',
						url: databaseLink+"/"+id+"/",
						contentType: "application/json",
						data: JSON.stringify(personnage),
						success: function(personnage) {
							alert("perso change");
						}
					})
					$("table tbody tr[id='"+id+"'] ").remove();
					createRowUser(personnage);
				}
					
			}
		}
	})
}


var crudBanirJS = function(){
	alert("banir joueur");
}



