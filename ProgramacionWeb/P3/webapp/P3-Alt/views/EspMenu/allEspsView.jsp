<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean  id="allEspsBean" scope="session" class="beans.AllEspsBean"></jsp:useBean>
<%@ page import="beans.AllUsersBean"%>
<%@ page import="business.EspectaculoPuntDTO"%>
<%@ page import="business.EspectaculoMultDTO"%>
<%@ page import="business.EspectaculoTempDTO"%>
<%@ page import="business.FechasDTO"%>
<%@ page import="business.PasesDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>
<link rel="stylesheet" href="../../css/allEspsView.css">
<title>Busqueda de Espectaculos</title>
</head>
<body>
    <jsp:include page="../headerNav.jsp"></jsp:include>
<% 
request.getSession().getAttribute("allEspsBean");
if(allEspsBean.getAllPunt() == null)
{
	String redirectURL = "../../../SearchAllEspsUser";
	response.sendRedirect(redirectURL);
}
else
{
	if (allEspsBean != null) {
		%>
		<div class="container">
		<div class="sticky">
		<h1>Búsqueda de Espectáculos:</h1>
		<input type="text" placeholder="Título" id= "buscadorEspsTitulo">
		<input type="text" placeholder="Categoría" id = "buscadorEspsCategoria">
		</div>
		<%
			if (allEspsBean.getAllPunt().size() > 0){
				%>
			<hr class="b">
			<hr class="a">
			<h2>Espectaculos puntuales:</h2>
			<hr class="a">
			<hr class="b">
			<%
				for (EspectaculoPuntDTO punt : allEspsBean.getAllPunt()){
		%>

		<div class="Espectaculo">
			<h3>Título : <%= punt.getTitulo()%></h3>
			<p class="categoria">Categoría : <%= punt.getCategoria()%></p>
			<p>Descripción :<%= punt.getDescripcion()%></p>
			<p>Localidades en Venta : <%= punt.getLocalidadesVenta()%></p>
			<p>Localidades Vendidas : <%= punt.getLocalidadesVendidas()%></p>
			<p>Fecha : <%= punt.getHoraFechaString()%></p>
			<hr class="b">
			</div>
			
			<%
				}
			%>
			<hr class="a">
			<%
			}
		%>

		<%
			if (allEspsBean.getAllMult().size() > 0){
				%>
				<h2>Espect&aacuteculos M&uacuteltiples:</h2>
				<hr class="a">
				<hr class="b">
			<%
			for (EspectaculoMultDTO mult : allEspsBean.getAllMult()){
		%>
			<div class="Espectaculo">
				<h3>Título : <%= mult.getTitulo()%></h3>
					<p class="categoria">Categoría : <%= mult.getCategoria()%></p>
					<p>Descripción :<%= mult.getDescripcion()%></p>
					<p>Localidades en Venta :<%= mult.getLocalidadesVenta()%></p>
					<p>Localidades Vendidas : <%= mult.getLocalidadesVendidas()%></p>
					<p>Fechas:</p>
				<div class="side">
				<%
				for(FechasDTO fechas : mult.getFechas()){
				%>			
				<a><%= fechas.getFechaString()%></a>
				<%
				}
				%>
				</div>
					<hr class="b">
			</div>
			<%
					}
			%>
			<hr class="a">	
			<%	
			}
			%>

		<%
			if (allEspsBean.getAllTemp().size() > 0){
				%>
				<h2>Espectáculos de Temporada:</h2>
				<hr class="a">
				<hr class="b">
			<%
			for (EspectaculoTempDTO temp : allEspsBean.getAllTemp()){
		%>
		<div class="Espectaculo">
			<h3>Título : <%= temp.getTitulo()%></h3>
			<p class="categoria">Categoría : <%= temp.getCategoria()%></p>
			<p>Descripción : <%= temp.getDescripcion()%></p>
			<p>Localidades en Venta :<%= temp.getLocalidadesVenta()%></p>
			<p>Localidades Vendidas : <%= temp.getLocalidadesVendidas()%></p>
			<p>Pases:</p>
			<%
			for(PasesDTO pases : temp.getPases()){
			%>
			<div class="side2">
				<a>Fecha Inicio: <%=pases.getFechaInicioString()%></a>
				<a>Fecha Final: <%=pases.getFechaFinalString() %></a>
				<a>Dia de la semana: <%=pases.getDiaSemana()%></a>
			</div>
				
			<%
				}
			%>
			<hr class="b">
		</div>
			<%
			}
			%>
					<hr class="a">
		<%
			}
		%>
			</div>
		<%
		}
}
session.setAttribute("allEspsBean", null);
%>
<script src="../../js/filtroKeyUpEsps.js"></script>
</body>
</html>