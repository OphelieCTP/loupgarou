$('section[id=addUser]').hide();

$('input[id=add]').bind('click',function(){
	$('section[id=addUser]').show();
	return false;
})

$('input[id=addDone]').bind('click',function(){
	$('section[id=addUser]').hide();
	return false;
})

$('tr[name=formMod]').hide();

var allowModifierForm = function(id){
	alert("fonctionne");
	$('tr[id='+id+'F]').show();
	return false;
}

var addModifierForm = function(id){
	
	console.log("tentative"+id);
//	$('a[id=addModif]').bind('click',function(id){
//		alert("tentative")
////		$("table tbody tr[id='"+id+"'] ").find('td[id="ID"]')
////			.append($('<div class="form-group"><input type="text" class="form-control" id="ID"> </div>'));
//		//$('div[id=addModif]').show();
//		return false;
//	})
}

//<div class="form-group" id="addModif"> 
//			      		<input type="text" class="form-control" name="userID" id="ID" th:text="${user.userID}" th:value="${user.userID}"> 
//			      	</div>

//var addModifierForm = function(id){
//	//$("<tr id='"+id+"' />").find('img');
//	//<input type="text" id="nom" th:value="${utilisateur?.userName}" name="userName" required/>
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="ID"]')
//		.append($('<div class="form-group"><input type="text" class="form-control" id="ID"> </div>'));
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="user"]')
//	.append($('<div class="form-group"><input type="text" class="form-control" th:value="${utilisateur?.userName}" name="userName" id="userName"> </div>'));
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="wp"]')
//	.append($('<div class="form-group"><input type="text" class="form-control" id="wp"> </div>'));
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="connexion"]')
//	.append($('<form><div class="form-group"><select multiple class="form-control" id="connexion"> <option>true</option> <option>false</option> </select></div></form>'));
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="bani"]')
//	.append($('<form><div class="form-group"><select multiple class="form-control" id="bani"> <option>true</option> <option>false</option> </select></div></form>'));
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="plaintes"]')
//	.append($('<div class="form-group"><input type="text" class="form-control" id="plaintes"> </div>'));
//	$("table tbody tr[id='"+id+"'] ").find('td[id="naissance"]')
//	.append($('<div class="form-group"><input type="text" class="form-control" id="naissance"> </div>'));
//	
//	$("table tbody tr[id='"+id+"'] ").find('td[id="actions"]')
//	.append($('<hr/> <div class="col-xs-3"> <input type="submit" class="btn btn-primary btn-md btn-block" value = "Submit" onclick="deleteModifierForm(0); return false;" > </div>'));
//	
//}


