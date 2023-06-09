<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../css/loadModifyFechas.css">
<link type="text/css" rel="stylesheet" href="/P3-Alt/P3-Alt/css/tableModEsps.css"/>
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
if(allEspsBean.getAllPunt() == null)
{
	String redirectURL = "../../../GetAllEsps";
	response.sendRedirect(redirectURL);
}
else
{
	if (allEspsBean != null) {
		%>
		<section>
		  <h1>Modificar espectáculos</h1>
		  <div class = "box">
			  	<select name="select" id="buscador" class="selectEsp">
					  <option value="Puntual" selected>Puntual</option>
					  <option value="Multiple">Múltiple</option>
					  <option value="Temporada">Temporada</option>
				</select>
			</div>
		  <div class="critica">
		    <h2>Espectaculos puntuales</h2>
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

			for (EspectaculoPuntDTO punt : allEspsBean.getAllPunt()){
		%>
		    
		<tr>
			<td><%= punt.getTitulo()%></td>
			<td><%= punt.getHoraFechaString()%>
			<form method="Post" action="../../../ModifyFechaPuntServlet">
						 <input type="datetime-local" name="fecha" class="fecha" required >
			   			 <input type="submit" value="Editar Fecha" class="button">
			   			 <input type="hidden" name="idEsp" value= "<%=punt.getID() %>">
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
		 
		<div class="critica" id="mult">
		<h2>Espectaculos multiples</h2>
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
						<td><%= fechas.getFechaString()%>
						<form method="Post" action="../../../ModifyFechaMult">
						 <input type="datetime-local" name="fecha" class ="fecha" required>
			   			 <input type="submit" value="Editar Fecha"class="button">
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
		          <th>dia</th>
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
			<td>
			<%= temp.getTitulo()%>
			</td>
			<td>
		        <table>
			        <%
					for(PasesDTO pases : temp.getPases()){
					%>
					<tr>
						<td><%=pases.getFechaInicioString()%>
						<form method="Post" action="../../../ModifyFechaInicioServlet">
						 <input type="datetime-local" name="fecha" class ="fecha"required>
			   			 <input type="submit" value="Editar" class="button">
			   			 <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
			   			 <input type="hidden" name="idpase" value= "<%=pases.getID() %>">
						</form></td>
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
						<td><%=pases.getFechaFinalString()%>
						<form method="Post" action="../../../ModifyFechaFinalServlet">
						 <input type="datetime-local" name="fecha" class ="fecha"required>
			   			 <input type="submit" value="Editar"class="button">
			   			 <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
			   			 <input type="hidden" name="idpase" value= "<%=pases.getID() %>">
						</form></td>
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
						<td><%=pases.getDiaSemana()%>
						<form method="Post" action="../../../ModifyDiaSemana">
						<select name="diasemana">
							<option value="Lunes">Lunes</option>
							<option value="Martes">Martes</option>
							<option value="Miércoles">Miércoles</option>
							<option value="Jueves">Jueves</option>
							<option value="Viernes">Viernes</option>
							<option value="Sábado">Sábado</option>
							<option value="Domingo">Domingo</option>
						</select>
			   			 <input type="submit" value="Editar" class="button">
			   			 <input type="hidden" name="idEsp" value= "<%=temp.getID() %>">
			   			 <input type="hidden" name="idpase" value= "<%=pases.getID() %>">
						</form></td>
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
<script src="../../js/scrollTableModifyFechas.js"></script>
<script src="../../js/fechasController.js"></script>
</body>
</html>