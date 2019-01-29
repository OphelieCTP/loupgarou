var event = function()
{
	var lienChoisi = $(this).attr('href');
	var content;
	
	$('section').hide();
	$(lienChoisi).show();
	return false;	

}



$('section').hide();
$('#infoCompte').show();

$('#infoCompte a').bind('click', event);
$('#changeMDP-valid a').bind('click', event);
$('#changeEmail-valid a').bind('click', event);