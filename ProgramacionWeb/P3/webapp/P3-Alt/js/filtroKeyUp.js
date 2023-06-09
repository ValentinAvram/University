document.getElementById("buscador").addEventListener("keyup", buscador_interno);
let buscador = document.getElementById("buscador");
function buscador_interno()
{
	let filter = buscador.value.toUpperCase();
	let li= document.getElementsByClassName("critica");
	let count =0;
	let a = 0;
	while(true)
	{
		if(!li[a])
		{
			break;
		}
		else
		{
			count++;
			a++;
		}
	}
	for(i = 0; i<count;i++)
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

