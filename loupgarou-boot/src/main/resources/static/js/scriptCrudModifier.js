$('section[id=addUser]').hide();

$('input[id=add]').bind('click',function(){
	$('section[id=addUser]').show();
	return false;
})

$('input[id=addDone]').bind('click',function(){
	$('section[id=addUser]').hide();
	return false;
})


//$('tr[name=formMod]').hide();

//
//var allowModifierForm = function(id){
//	alert("fonctionne");
//	$('tr[id='+id+'F]').show();
//	return false;
//}
//
//var addModifierForm = function(id){
//	console.log("tentative"+id);
////	$('a[id=addModif]').bind('click',function(id){
////		alert("tentative")
//////		$("table tbody tr[id='"+id+"'] ").find('td[id="ID"]')
//////			.append($('<div class="form-group"><input type="text" class="form-control" id="ID"> </div>'));
////		//$('div[id=addModif]').show();
////		return false;
////	})
//}



