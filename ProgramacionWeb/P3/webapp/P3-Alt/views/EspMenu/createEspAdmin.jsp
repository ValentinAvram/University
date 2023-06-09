<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dar de alta espectaculo</title>
<link rel="stylesheet" href="../../css/createEspAdmin.css">
</head>
<body>
<jsp:include page="../headerNavAdmin.jsp"></jsp:include>
    <div class="container">
			<form id="myform" method="post" action="../../../CreateEspServlet">
				<fieldset>
					<legend>Crear Espectáculo</legend>
					<select id ="location"name="tipo">
						<option value="Puntual">Puntual</option>
						<option value="Temporada">Temporada</option>
						<option value="Multiple">Múltiple</option>
					</select>
					<br><br>
					<div id="second"> 
					<label>Número de fechas/pases <input type="number" name="opcion" value="1" min=1 placeholder = "Número fechas/pases" required> <br><br></label> 
					</div>
					<button type="submit" value="Aceptar" class="button">Aceptar</button>
				</fieldset>
			</form>
			</div>
<script src="../../js/createEspAdmin.js"> </script>
<script src="../../js/loadPage.js"> </script>
</body>
</html>