var databaseLink = "http://192.168.1.110/loupgarou-ajax/personnage";

class Joueur {
	constructor(id, userName)
	{
		this.id = id;
		this.userName = userName;
	}
}




var generateJoueur = function(j) {
	
	var divradio  = $("<div class=\"radio\" />");
	var label = $("<label />");
	var ligne = $("<input type=radio name=vote />");
	
	ligne.attr('value', j.id);
	
	
	label.append(ligne);
	label.append("     " + j.libelle);
	
	
	divradio.append(label);
		
	$('form[id=voteJ]').append(divradio);
}

//fonction .attr avec deux attribut --> modification valeur attribut

var generateForm = function(joueurs) {
	for(j of joueurs) {
		generateJoueur(j);
	}
	
	var divVote = $("<div id=\"centered-button\" />");
	var submitButton = $("<button type=\"submit\" class=\"btn btn-info\" />");
	submitButton.html("Voter !");
	divVote.append(submitButton);
	$('form[id=voteJ]').append(divVote);
}


var displayCarte = function(joueurs) {
	var wId = 27;
	for(p of joueurs)
	{
		if(p.id == wId)
		{
			var imgTag = $("<img id=\"carteJ\" />");
			var imgLegend = $("<div id=\"imgLegend\" />");
			
			if(p.pouvoir.libelle == "Villageois")
			{
				imgTag.attr('src', "img/vill.png");
				imgLegend.html("Villageois");
			}
			if(p.pouvoir.libelle == "Loup")
			{
				imgTag.attr('src', "img/loupg.png");
				imgLegend.html("Loup Garou");
			}
			$('.carte').append(imgTag);	
			$('.carte').append(imgLegend);	
		}
	}
}

$.ajax({
	method: 'GET',
	url: databaseLink,
	success: function(personnages) {
		console.log(personnages);
		generateForm(personnages);
		displayCarte(personnages);
		}
	}
)

//var j1 = new Joueur(1, "Skywalker");
//var j2 = new Joueur(2, "Kirikou");
//var j3 = new Joueur(3, "Hulk");
//
//var js = [j1, j2, j3];





	