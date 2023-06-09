//HORA ACTUAL
var now = new Date();
now.setHours(now.getHours()-1); 
now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
now.toISOString().slice(0,16);

let elementsArray = document.querySelectorAll(".fecha");

elementsArray.forEach(function(elem) {
elem.addEventListener("input", function() {  
var input_date = this.value;
var f = new Date(input_date);
if(f<now)
{
	alert("Está intentando poner una fecha anterior a la actual. Por favor, cambie la fecha e inténtelo de nuevo");
	elem.value = "";
}
    });
});

$("form" ).submit(function( event ) {
	var dataArray = $("form").serializeArray();
	dataObj = {};
	$(dataArray).each(function(i, field){
		if(field.value!=='')
		{
			dataObj[field.name] = field.value;
		}
	});
	let stringfechai = String(dataObj['fechai']);
	let stringfechaf = String(dataObj['fechaf']);
	var fi = new Date(stringfechai);
	var ff = new Date(stringfechaf);
	if(stringfechai !== 'undefined' && stringfechaf !== 'undefined')
	{
		
		if ( fi < ff)
		{
			$("form" ).submit();
		}
		else
		{
			alert('ERROR. La fecha inicial no puede ser posterior a la fecha final. Por favor, ingrese de nuevo y vuelva a intentarlo.');
			window.history.back();
			event.preventDefault();
		}
	}
});