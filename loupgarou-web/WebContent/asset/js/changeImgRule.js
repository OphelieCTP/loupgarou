// juste une tentative pour le moment.

// cible les icones


$('div[class=divRole] img[class=carteJ]').on('mouseenter', function() {
	var newPath = $(this).attr('src');
	console.log(newPath);
	var img = $('<div id="statJoueur" class="col-md-11"> <div class="container-fluid" id="statJTitle"> Résumé </div> <br/> <div class="carte"> <img id="carteJ" src="'+newPath+'"> </div> </div> </div>');
    $('div[id=page-left]').append(img);
    
}).on('mouseleave', function() {
	var newPath = $(this).attr('src');
	console.log("leave : "+newPath);
	$('div[id=statJoueur]').remove();
})

// <div class="divRole"> <img class="carteJ" src="./img/loupg.png" width="42" id="loupIMG">
//
//$('div[id=statJoueur] div[class=carte]').on('mouseenter', function() {
//	//
//})

//
//<div id="statJoueur" class="col-md-11">
//				<div class="container-fluid" id="statJTitle">
//				Rôle
//				</div>
//				<br/>
//				
//				<div class="carte">
//					<!-- <img src="./img/loupg.png"> -->
//				</div>
//				
//			</div>