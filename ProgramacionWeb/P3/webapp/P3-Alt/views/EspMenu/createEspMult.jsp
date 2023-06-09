<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ page import="java.time.LocalDateTime"%>
<title>Creación de espectáculo múltiple</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	body{
		background: #9aafc7;
	   	font-family: 'Roboto', sans-serif;
	   	color: #fdfdfd;
	}
	legend{
		font-size: 1.8em;
		text-align: center;
		margin-bottom: 1em;
	}
	
	.container {
    position:fixed;
    font-size: 1.2 em;
    text-align: center;
    vertical-align: center;
    top: 55%;
    left: 50%;
    width:50em;
    height:40em;
    margin-left: -25em; /*set to a negative number 1/2 of your width*/
    margin-top: -20em; /*set to a negative number 1/2 of your height*/
    border: 3px dotted #fdfdfd;
	}
	fieldset {
	    border: 2px dotted #fdfdfd;
	    text-align: center;
   	   	width:30em;
		height:32.8em;
		overflow: auto;
    	margin-left: 10em; /*set to a negative number 1/2 of your width*/
    	margin-top: 3em; /*set to a negative number 1/2 of your height*/
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
	/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px #c2cfdd; 
  border-radius: 4px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #8a9db3; 
  border-radius: 4px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #b30000; 
}

</style>
</head>
<body>
    <jsp:include page="../headerNavAdmin.jsp"></jsp:include>
    	<%
		int numFechas = Integer.parseInt(request.getParameter("number"));
		%>
	<div class="container">
	<form method="post" action="../../../../P3-Alt/NewMultServlet">
				<fieldset>
					<legend>Creación de Espectáculo Múltiple</legend>
					<input type="text" name="titulo" id="title"placeholder="Título" required>
					<input type="text" name="descripcion" id="desc" placeholder="Descripción" required>
					<input type="number" name="locVenta" id="localidades" min ="0" placeholder="Localidades a la venta" required>
					<input type="text" name="categoria" id="categoriaa" placeholder="Categoría" required>
					<%for(int i=1; i<=numFechas; i++){%>
					<p>Fecha<%=i%>:</p>
					<input type="datetime-local" id="dateInput" name="fecha<%=i%>" placeholder="Fecha<%=i%>" class="fecha" required>
					<%}%>
					<br>
					<input type="submit" value="Crear">
				</fieldset>
				 <input type="hidden" name="number" value='${param.number}'/>
			</form>
		</div>
<script src="../../js/fechasController.js"></script>
</body>
</html>