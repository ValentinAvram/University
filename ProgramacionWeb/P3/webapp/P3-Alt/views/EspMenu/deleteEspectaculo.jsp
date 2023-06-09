<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<title>Eliminar Espectáculo</title>
<link rel="stylesheet" href="../../css/deleteEspectaculo.css">
</head>
<body>
<jsp:include page="../headerNavAdmin.jsp"></jsp:include>
<% 
request.getSession().getAttribute("allEspsBean");
if(allEspsBean.getAllPunt() == null)
{
	String redirectURL = "../../../GetAllToDelete";
	response.sendRedirect(redirectURL);
}
else
{
	if (allEspsBean != null) {
		%>
		<div class="container">
		<div class="sticky">
		<h1>Eliminar Espectáculo</h1>
		<input type="text" id="buscador" placeholder="Título">
		</div>
				<%
					for (EspectaculoPuntDTO punt : allEspsBean.getAllPunt()){
				%>
				<div class = "critica"> 
					<h2> <%= punt.getTitulo()%> </h2>
					<form method="Post" action="../../../DeleteEspectaculo">
					    <button type="submit" value="Eliminar" class="button">Eliminar</button>
					    <input type="hidden" name="idEsp" value= "<%=punt.getID() %>">
					    <input type="hidden" name="tipo" value= "<%= "punt" %>">
					</form>
				</div>
				<%
					}
				%>
				
				<%
				for (EspectaculoMultDTO mult : allEspsBean.getAllMult()){
				%>
				<div class = "critica"> 
					<h2 class = "titulo"> <%= mult.getTitulo()%> </h2>
					<form method="Post" action="../../../DeleteEspectaculo">
					    <button type="submit" value="Eliminar" class="button">Eliminar</button>
					    <input type="hidden" name="idEsp" value= "<%=mult.getID() %>">
					    <input type="hidden" name="tipo" value= "<%= "mult" %>">
					</form>
				</div>
			<%
				}
			%>
			
			<%
			
				for (EspectaculoTempDTO temp : allEspsBean.getAllTemp()){
			%>
				<div class = "critica"> 
					<h2 class = "titulo"><%= temp.getTitulo()%></h2>
					<form method="Post" action="../../../DeleteEspectaculo">
					    <button type="submit" value="Eliminar" class="button">Eliminar</button>
					    <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
					    <input type="hidden" name="tipo" value= "<%= "temp" %>">
					</form>
				</div>
			
			<%
				}
			%>
		</div>
		<%
		}
}
session.setAttribute("allEspsBean", null);

%>
<script src="../../js/filtroKeyUp.js"> </script>
</body>
</html>