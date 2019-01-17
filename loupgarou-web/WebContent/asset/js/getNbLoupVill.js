var loups = 5;
var villageois = 10;

var newSpanLoup =  $("<span> "+loups+" </span>");
var newSpanVill =  $("<span> "+villageois+" </span>");
//
//$('statpart-zone span[id=loup]').remove();
//$('statpart-zone span[id=villageois]').remove();

$('#statpart-zone span[id=loups]').append(newSpanLoup);
$('#statpart-zone span[id=villageois]').append(newSpanVill);

console.log(toString($('statpart-zone span[id=loup]')));
