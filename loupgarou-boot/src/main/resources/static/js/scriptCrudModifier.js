$('section[id=addUser]').hide();

$('input[id=add]').bind('click',function(){
	$('section[id=addUser]').show();
	return false;
})