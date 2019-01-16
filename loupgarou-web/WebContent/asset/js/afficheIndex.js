var event = function()
{
	var lienChoisi = $(this).attr('href');
	var content;
	
	$('section').hide();
	$(lienChoisi).show();
	return false;	

}



$('section').hide();
$('#identification').show();

$('#identification a').bind('click', event);