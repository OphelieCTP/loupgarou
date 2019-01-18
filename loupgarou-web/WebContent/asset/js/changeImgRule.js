// target icones

$('div[id=ruleZone]')
//$('div[class=divRole] img[class=carteJ]')
.on('mouseenter', function() {
	var newPath = $(this).find('img').attr('src');
	// console.log(newPath);
	var img = $('<div id="statJoueur" class="col-md-11"> <div class="container-fluid" id="statJTitle"> Résumé </div> <br/> <div class="carte"> <img id="carteJ" src="'+newPath+'"> </div> </div> </div>');
    $('div[id=page-left]').append(img);
    
}).on('mouseleave', function() {
	var newPath = $(this).attr('src');
	// console.log("leave : "+newPath);
	$('div[id=statJoueur]').remove();
})
