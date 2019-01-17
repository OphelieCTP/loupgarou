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

var confirmMDP = document.querySelector('#passwordCheck');

confirmMDP.addEventListener('keyup', function(event){
	if($('input[name="password-1"]').val() !== $('input[name="password-2"]').val()) {
		//alert($('input[name="password-1"]').val() + " : " + $('input[name="password-2"]').val());
		var mySpan = $("<span id=\"errormdp\" class=\"help-block\" />");
		mySpan.html = "Les deux mots de passe ne corespondent pas !";
		$('.passwordCheck').append(mySpan);
	}
	
});