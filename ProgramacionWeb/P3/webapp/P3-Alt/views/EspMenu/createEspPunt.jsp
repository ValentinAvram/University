<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creación de espectáculo puntual</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	body{
		background: #9aafc7;
	   	font-family: 'Roboto', sans-serif;
	   	color: #fdfdfd;
	}
	h1{
		font-size: 2.4em;
		text-align: center;
	}
	legend{
		font-size: 1.8em;
		text-align: center;
	}
	
	.container {
    position:fixed;
    font-size: 1.2 em;
    text-align: center;
    vertical-align: center;
    top: 50%;
    left: 50%;
    width:50em;
    height:32em;
    margin-left: -25em; /*set to a negative number 1/2 of your width*/
    margin-top: -16em; /*set to a negative number 1/2 of your height*/
    border: 3px dotted #fdfdfd;
	}
	fieldset {
	    border: 2px dotted #fdfdfd;
	    text-align: center;
   	   	width:30em;
		height:22em;
    	margin-left: 10em; /*set to a negative number 1/2 of your width*/
    	margin-top: 5em; /*set to a negative number 1/2 of your height*/
	}
	input{
	text-align: center;
	}
	 form input{
	 display: block;
	 appearance: none;
	 outline: 0;
	 border: 1px solid rgba(255,255,255,0.4);
	 background-color: rgba(255,255,255,0.2);
	 width: 70%;
	 border-radius: 2px;
	 padding: 4px 7px;
	 margin: 0 auto 10px auto;
	 text-align: center;
	 font-size: 100%;
	 color: white;
	 transition-duration: 0.25s;
	 font-weight: 320;
}
 form input:hover {
	 background-color: rgba(255,255,255,0.4);
}
 form input:focus {
	 background-color: white;
	 width: 300px;
	 color: #859c74;
}
form button {
	 appearance: none;
	 outline: 0;
	 background-color: white;
	 border: 0;
	 padding: 10px 15px;
	 color: #9aafc7; 
	 border-radius: 3px;
	 width: 25%;
	 cursor: pointer;
	 font-size: 90%;
	  transition: transform .2s;
}
 form button:hover {
	 background-color: #f5f7f9;
	 transform: scale(1.1);
}  

</style>
</head>
<body>
    <jsp:include page="../headerNavAdmin.jsp"></jsp:include>
	<h1>Creación de espectáculos</h1>
	<div class="container">
	<form method="post" action="../../../../P3-Alt/NewPuntServlet">
				<fieldset>
					<legend>Nuevo espectáculo puntual</legend>
					<input type="text" name="titulo" placeholder="Título" required> <br>
					<input type="text" name="descripcion" placeholder="Descripción" required> <br>
					<input type="datetime-local" id="dateInput" name="fecha" placeholder="Fecha" required class="fecha"><br>
					<input type="number" name="locVenta" min ="0" placeholder="Localidades A la Venta" required><br>
					<input type="text" name="categoria" id="categoriaa" placeholder="Categoría" >
					<button type="submit" value="Crear">Crear</button>
				</fieldset>
			</form>
			</div>
<script src="../../js/fechasController.js"></script>
</body>
</html>