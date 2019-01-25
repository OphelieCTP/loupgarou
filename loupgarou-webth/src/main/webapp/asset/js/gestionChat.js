var eventSource = new EventSource("http://192.168.1.129:8080/loupgarou-webth/sse");

eventSource.onmessage = function(evt) {
	let msg = evt.data;
	console.log(msg);
}



$('#edit form').submit(function() {
	
	//RQT AJAX VERS LA SERVEUSE
	var databaseLink = "http://192.168.1.129:8080/loupgarou-webth/envoyermessage";
	$.ajax({
	method: 'POST',
	url: databaseLink,
	success: function(personnages) {
		
		}
	}
)
	
	return false;
});


//var databaseLink = "http://192.168.1.129:8080/loupgarou-webth/envoyermessage";
//
//$.ajax({
//	method: 'GET',
//	url: databaseLink,
//	success: function(personnages) {
//		console.log(utilisateurAjoute);
//		}
//	}
//)
