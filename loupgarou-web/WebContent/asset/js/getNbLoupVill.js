var loups = 5;
var villageois = 10;

$.ajax({
	method: 'GET',
	url: databaseLink,
	success: function(personnages) {
		loups = 0;
		villageois = 10;
		console.log(personnages);
		console.log(personnages.length);
		if(personnages.length!==0){
			for (let personnage of personnages){
				console.log(personnage.pouvoir.id);
				if(personnage.pouvoir.id==1){
					loups=loups+1;
				}
				else {villageois=villageois+1}
			}
		}
		else {
			loups = "Partie non commencée";
			villageois = "Partie non commencée";
		}
		var newSpanLoup =  $("<span> "+loups+" </span>");
		var newSpanVill =  $("<span> "+villageois+" </span>");

		$('#statpart-zone span[id=loups]').append(newSpanLoup);
		$('#statpart-zone span[id=villageois]').append(newSpanVill);
		
	}
})

