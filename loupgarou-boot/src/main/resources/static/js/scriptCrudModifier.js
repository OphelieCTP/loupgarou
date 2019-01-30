$('section[id=addUser]').hide();

$('div[id=passWord]').hide();

$('input[id=add]').bind('click',function(){
	$('section[id=addUser]').show();
	return false;
})

$('input[id=display]').bind('click',function(){
	$('div[id=passWord]').show();
	return false;
})

$('input[id=hide]').bind('click',function(){
	$('div[id=passWord]').hide();
	return false;
})