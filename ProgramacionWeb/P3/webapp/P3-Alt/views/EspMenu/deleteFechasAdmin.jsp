<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:100,200,300,400,500);
body {
 	background: #9aafc7;
	font-family: 'Roboto', sans-serif;
}

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

<title>Busqueda de Espectaculos</title>
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
	String redirectURL = "../../../GetMultTempDeleteServlet";
	response.sendRedirect(redirectURL);
}
else
{
	if (allEspsBean != null) {
		%>
		<h1>Cancelar sesiones</h1>

		<div class = "box">
			  	<select name="select" id="buscador">
					  <option value="Multiple" selected>Múltiple</option>
					  <option value="Temporada">Temporada</option>
				</select>
			</div>

		<div class = "critica">
			<h2>ESPECTÁCULOS MULTIPLES</h2>
			<table border="1">
			<tr>
				<td>TÍTULO</td><td>FECHAS</td>
			</tr>
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
							<td><%= fechas.getFechaString()%>
							<form method="Post" action="../../../DeleteFechaMult">
				   			 <input type="submit" value="Cancelar fecha" class="button">
				   			 <input type="hidden" name="idEsp" value= "<%=mult.getID() %>">
				   			 <input type="hidden" name="idFecha" value= "<%=fechas.getID() %>">
							</form>
							</td>
							</tr>
				<%
				}
				%>
					</table>
				</td>
			</tr>
			<%
				}
			%>
			</table>
		</div>


		<div class = "critica">
			<h2>ESPECTÁCULOS DE TEMPORADA</h2>
			<table border="1">
			<tr>
				<td>TITULO</td><td>FECHA INICIO</td><td>FECHA FINAL</td><td>DÍA</td><td>BORRAR PASE</td>
			</tr>
			<%
			
				for (EspectaculoTempDTO temp : allEspsBean.getAllTemp()){
			%>
			<tr>
				<td>
				<%= temp.getTitulo()%>
				</td>
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
			        <table>
				        <%
						for(PasesDTO pases : temp.getPases()){
						%>
						<tr>
							<td>
							<form method="Post" action="../../../DeletePaseTemp">
				   			 <input type="submit" value="Borrar" class="button">
				   			 <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
				   			 <input type="hidden" name="idpase" value= "<%=pases.getID() %>">
							</form>
							</td>
						</tr>
						<%
						}
						%>
			        </table>
				</td>
				
			
			</tr>
			<%
				}
			%>
			</table>
		</div>
		<%
		}
}
session.setAttribute("allEspsBean", null);
%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"> </script>
<script src="../../js/filtroFechasModify.js"> </script>
<script src="../../js/loadPage.js"> </script>
</body>
</html>