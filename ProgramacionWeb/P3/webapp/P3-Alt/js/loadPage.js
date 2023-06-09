$('body').removeClass('hidden');
$('#onload').fadeOut();
	
var myFunction = function()
{
	var dataArray = $("form").serializeArray();
	dataObj = {};
	$(dataArray).each(function(i, field){
		if(field.value!=='')
		{
			dataObj[field.name] = field.value;
		}
	});
	let stringfecha = String(dataObj['fecha']);
	if(stringfecha!== 'undefined')
	{
		$('#onload').fadeIn();
		window.onload=function()
		{
			$('#onload').fadeOut();
		}	
	}	
}


var elements = document.getElementsByClassName("button");

Array.from(elements).forEach(function(element) {
      element.addEventListener('click', myFunction);
    });






