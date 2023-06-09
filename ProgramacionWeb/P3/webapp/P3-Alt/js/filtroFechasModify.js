let event = document.getElementById("buscador").addEventListener("change", buscador_interno);
let buscador = document.getElementById("buscador");
let d = document;

var elem = document.getElementById("buscador");
var evento = new Event('change');
elem.dispatchEvent(evento);
function buscador_interno()
{
	let filter = buscador.value.toUpperCase();
	let li= document.getElementsByClassName("critica");
	for(i = 0; i<3;i++)
	{
		 let a = li[i].getElementsByTagName("h2")[0];
		 let textValue = a.textContent || a.innerText;
		 if(textValue.toUpperCase().indexOf(filter) > -1)
		 {
			 li[i].style.display = "";
		 }
		 else
		 {
			 li[i].style.display ="none";
		 }
	}
	
}