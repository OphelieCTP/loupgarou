//var databaseLink = "http://192.168.1.110/loupgarou-ajax/personnage";
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

$('div[id=addModif]').hide();

$('a[id=addModif]').bind('click',function(){
	$('div[id=addModif]').show();
	return false;
})

var addModifierForm = function(id){
	//$("<tr id='"+id+"' />").find('img');
	//<input type="text" id="nom" th:value="${utilisateur?.userName}" name="userName" required/>
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="ID"]')
		.append($('<div class="form-group"><input type="text" class="form-control" id="ID"> </div>'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="user"]')
	.append($('<div class="form-group"><input type="text" class="form-control" th:value="${utilisateur?.userName}" name="userName" id="userName"> </div>'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="wp"]')
	.append($('<div class="form-group"><input type="text" class="form-control" id="wp"> </div>'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="connexion"]')
	.append($('<form><div class="form-group"><select multiple class="form-control" id="connexion"> <option>true</option> <option>false</option> </select></div></form>'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="bani"]')
	.append($('<form><div class="form-group"><select multiple class="form-control" id="bani"> <option>true</option> <option>false</option> </select></div></form>'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="plaintes"]')
	.append($('<div class="form-group"><input type="text" class="form-control" id="plaintes"> </div>'));
	$("table tbody tr[id='"+id+"'] ").find('td[id="naissance"]')
	.append($('<div class="form-group"><input type="text" class="form-control" id="naissance"> </div>'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="actions"]')
	.append($('<hr/> <div class="col-xs-3"> <input type="submit" class="btn btn-primary btn-md btn-block" value = "Submit" onclick="deleteModifierForm(0); return false;" > </div>'));
	
}


var deleteModifierForm = function(id){
	//$("<tr id='"+id+"' />").find('img');
	//var newForm = 
	$("table tbody tr[id='"+id+"'] ").find('td[id="ID"]').remove($('.form-group'));
	$("table tbody tr[id='"+id+"'] ").find('td[id="user"]').remove($('.form-group'));
	$("table tbody tr[id='"+id+"'] ").find('td[id="wp"]').remove($('.form-group'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="connexion"]').remove($('.form-group'));	
	$("table tbody tr[id='"+id+"'] ").find('td[id="bani"]').remove($('.form-group'));
	
	$("table tbody tr[id='"+id+"'] ").find('td[id="plaintes"]').remove($('.form-group'));
	$("table tbody tr[id='"+id+"'] ").find('td[id="naissance"]').remove($('.form-group'));
}


var crudModifierJS = function(id){
	//passer plutot formulaire que alertes
//	console.log(this);
//	console.log("test");
	//alert("modification joueur "+id);
	$.ajax({
		method: 'GET',
		url: databaseLink,
		success: function(personnages) {
			console.log(personnages);
			for (let personnage of personnages){
				if(personnage.id===id){
					// .parent()
					//$("table tbody tr[id='"+id+"'] ").append();
					// 
					//var newForm = $('<div class="form-group"> <label for="usr">Name:</label> <input type="text" class="form-control" id="usr"> </div>')
					//$(this).append(newForm);
					//addModifierForm(id);
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
					// prendre ici, ajouter formulaire et adapter ??
					createRowUser(personnage);
				}
					
			}
		}
	})
}


