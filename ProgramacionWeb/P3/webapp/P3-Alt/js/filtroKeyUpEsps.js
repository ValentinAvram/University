//BUSCADOR TITULO
document.getElementById("buscadorEspsTitulo").addEventListener("keyup", buscador_interno_EspsTitulo);
let buscadorEspsTitulo = document.getElementById("buscadorEspsTitulo");
function buscador_interno_EspsTitulo()
{
	let filter = buscadorEspsTitulo.value.toUpperCase();
	let li= document.getElementsByClassName("Espectaculo");
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
		 let a = li[i].getElementsByTagName("h3")[0];
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
//BUSCADOR CATEGORIA
document.getElementById("buscadorEspsCategoria").addEventListener("keyup", buscador_interno_EspsCategoria);
let buscadorEspsCategoria = document.getElementById("buscadorEspsCategoria");
function buscador_interno_EspsCategoria()
{
	let filter = buscadorEspsCategoria.value.toUpperCase();
	let li= document.getElementsByClassName("Espectaculo");
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
		 let a = li[i].getElementsByClassName("categoria")[0];
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

