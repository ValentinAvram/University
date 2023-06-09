<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/P3-Alt/P3-Alt/css/tableModEsps.css"/>

<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:100,200,300,400,500);
</style>
<meta charset="UTF-8">
<jsp:useBean  id="allEspsBean" scope="session" class="beans.AllEspsBean"></jsp:useBean>
<%@ page import="beans.AllEspsBean"%>
<%@ page import="business.EspectaculoPuntDTO"%>
<%@ page import="business.EspectaculoMultDTO"%>
<%@ page import="business.EspectaculoTempDTO"%>
<%@ page import="business.FechasDTO"%>
<%@ page import="business.PasesDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>

<title>Añadir Fechas</title>
<link rel="stylesheet" href="../../css/loadModifyFechas.css">
</head>
<body class = "hidden">
<div class ="centrado" id="onload">
	<div class="lds-roller">
		<div></div>
		<div></div>
		<div></div>
		<div></div>
		<div></div>
		<div></div>
		<div></div>
		<div></div>
	</div>
</div>
    <jsp:include page="../headerNavAdmin.jsp"></jsp:include>
<% 
request.getSession().getAttribute("allEspsBean");
if(allEspsBean.getAllMult() == null)
{
	String redirectURL = "../../../GetMultTempServlet";
	response.sendRedirect(redirectURL);
}
else
{
	if (allEspsBean != null) {
		%>
		<section>
		<h1>Añadir Fechas</h1>
		  <div class = "box">
			  	<select name="select" id="buscador" class="selectEsp">
					  <option value="Multiple" selected>Múltiple</option>
					  <option value="Temporada">Temporada</option>
				</select>
			</div>


		<div class ="critica">
			<h2>ESPECTÁCULOS MULTIPLES</h2>
			  <div class="tbl-header" id="1">	
			<table>
		      <thead>
		        <tr>
		          <th>Titulo</th>
		          <th>Fecha</th>
		        </tr>
		      </thead>
		    </table>
		  </div>
		    <div class="tbl-content">
		    <table>
		      <tbody>
			<%
			
				for (EspectaculoMultDTO mult : allEspsBean.getAllMult()){
			%>
			<tr>
				<td><%= mult.getTitulo()%></td>
				
				<td>
			        <table>
				<%
				for(FechasDTO fechas : mult.getFechas()){
				%>
							<tr>                
							<td><%= fechas.getFechaString()%></td>
							</tr>
				<%
				}
				%>
					</table>
				<form method="Post" action="../../../../P3-Alt/AddFechaMult">
				    <input type="datetime-local" name="fecha" class ="fecha" required>
				    <input type="submit" value="Añadir Fecha" class="button">
				    <input type="hidden" name="idesp" value= "<%=mult.getID() %>">
				</form>
				</td>
			</tr>
			<%
				}
			%>
				</tbody>
			</table>
			</div>
			</div>
			
		 <div class="critica" id="temp"> 
		  <h2>Espectaculos de temporada</h2>
		  <div class="tbl-header">
		    <table>
		      <thead>
		        <tr>
		          <th>Titulo</th>
		          <th>Fecha inicio</th>
		          <th>Fecha final</th>
		          <th>D&iacutea</th>
		          <th>Añadir Pase</th>
		        </tr>
		      </thead>
		    </table>
		  </div>
		<div class="tbl-content">
		    <table>
		      <tbody>
			<%
				for (EspectaculoTempDTO temp : allEspsBean.getAllTemp()){
			%>
			<tr>
				<td><%= temp.getTitulo()%></td>
					<td>
			        <table>
				<%
				for(PasesDTO pases : temp.getPases()){
				%>
						<tr>
						<td><%=pases.getFechaInicioString()%></td>
						</tr>
				<%
				}
				%>
					</table>
					</td>
					
					<td>
			        <table>
				<%
				for(PasesDTO pases : temp.getPases()){
				%>
						<tr>
						<td><%=pases.getFechaFinalString()%></td>
						</tr>
				<%
				}
				%>
					</table>
					</td>
				
					<td>
			        <table>
				<%
				for(PasesDTO pases : temp.getPases()){
				%>
						<tr>
						<td><%=pases.getDiaSemana()%></td>
						</tr>
				<%
				}
				%>
					</table>
					</td>
					<td>
					<form method="Post" action="../../../../P3-Alt/AddPasesTemp">
					<label for="fechainicio">Fecha de Inicio</label>
				    <input type="datetime-local" name="fechai" id="fechainicio" class ="fecha" required>
				    <label for="fechafinal">Fecha de Finalización</label>
				    <input type="datetime-local" name="fechaf" id="fechafinal" class ="fecha" required>
				    <label for="dia">Día</label>
					<select name="diasemana" id="dia">
						<option value="Lunes">Lunes</option>
						<option value="Martes">Martes</option>
						<option value="Miercoles">Miércoles</option>
						<option value="Jueves">Jueves</option>
						<option value="Viernes">Viernes</option>
						<option value="Sabado">Sábado</option>
						<option value="Domingo">Domingo</option>
					</select>
				    <input type="submit" value="Añadir Pase"class="button">
				    <input type="hidden" name="idesp" value= "<%=temp.getID() %>">
				</form>
				</td>
			</tr>
			<%
				}
			%>
			</tbody>
			</table>
			</div>
			</div>
			
		</section>
		<%
		}
}
session.setAttribute("allEspsBean", null);
%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"> </script>
<script src="../../js/filtroFechasModify.js"> </script>
<script src="../../js/loadPage.js"> </script>
<script src="../../js/fechasController.js"></script>
</body>
</html>