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
<link rel="stylesheet" href="../../css/allCriticasView.css">
<title>Menú de Críticas</title>
</head>
<body>
    <jsp:include page="../headerNav.jsp"></jsp:include>
<% 
request.getSession().getAttribute("allEspsBean");
if(allEspsBean.getAllPunt() == null)
{
	String redirectURL = "../../../GetAllEspsDoneServlet";
	response.sendRedirect(redirectURL);
}
else
{
	if (allEspsBean != null) {
		%>
		<div class="container">
		<div class="sticky">
		<h1>Menú de Críticas</h1>
		<input type="text" placeholder="Título" id="buscador">
		</div>
				<%
				
					for (EspectaculoPuntDTO punt : allEspsBean.getAllPunt()){
				%>
				<div class = "critica"> 
					<h2> <%= punt.getTitulo()%> </h2>
					<div class="side">
					<form method="Post" action="../../../P3-Alt/views/criticasMenu/newCritica.jsp">
					    <button type="submit" value="Añadir Crítica">Añadir Cr&iacutetica</button>
					    <input type="hidden" name="nombreEsp" value= "<%=punt.getTitulo() %>">
					    <input type="hidden" name="idEsp" value= "<%=punt.getID() %>">
					</form>
					<form method="Post" action="../../../GetAllCritsServlet">
					    <button type="submit" value="Ver Críticas">Ver Cr&iacuteticas</button>
					    <input type="hidden" name="nombreEsp" value= "<%=punt.getTitulo() %>">
					    <input type="hidden" name="idEsp" value= "<%=punt.getID() %>">
					</form>
					</div>
				</div>
				<%
					}
				%>
				
				<%
				for (EspectaculoMultDTO mult : allEspsBean.getAllMult()){
				%>
				<div class = "critica"> 
					<h2 class = "titulo"> <%= mult.getTitulo()%> </h2>
					<div class="side">
					<form method="Post" action="../../../P3-Alt/views/criticasMenu/newCritica.jsp">
		   			    <button type="submit" value="Añadir Crítica">Añadir Cr&iacutetica</button>
					    <input type="hidden" name="idEsp" value= "<%=mult.getID() %>">
					    <input type="hidden" name="nombreEsp" value= "<%=mult.getTitulo() %>">
					</form>
				
					<form method="Post" action="../../../GetAllCritsServlet">
		   			    <button type="submit" value="Ver Críticas">Ver Cr&iacuteticas</button>
					    <input type="hidden" name="nombreEsp" value= "<%=mult.getTitulo() %>">
					    <input type="hidden" name="idEsp" value= "<%=mult.getID() %>">
					</form>
					</div>
				</div>
			<%
				}
			%>
			
			<%
			
				for (EspectaculoTempDTO temp : allEspsBean.getAllTemp()){
			%>
				<div class = "critica"> 
					<h2 class = "titulo"><%= temp.getTitulo()%></h2>
					<div class="side">
					<form method="Post" action="../../../P3-Alt/views/criticasMenu/newCritica.jsp">
		   			    <button type="submit" value="Añadir Crítica">Añadir Cr&iacutetica</button>
					    <input type="hidden" name="nombreEsp" value= "<%=temp.getTitulo() %>">
					    <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
					</form>
					<form method="Post" action="../../../GetAllCritsServlet">
		   			    <button type="submit" value="Ver Críticas">Ver Cr&iacuteticas</button>
					    <input type="hidden" name="nombreEsp" value= "<%=temp.getTitulo() %>">
					    <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
					</form>
					</div>
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