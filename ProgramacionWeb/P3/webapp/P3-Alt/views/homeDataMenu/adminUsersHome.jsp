<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/P3-Alt/P3-Alt/css/tableUsers.css"/>
<meta charset="UTF-8">
<jsp:useBean  id="allUsersBean" scope="session" class="beans.AllUsersBean"></jsp:useBean>
<%@ page import="beans.AllUsersBean"%>
<%@ page import="business.UserDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>
<title>Panel de Administrador</title>
</head>
<body>
	<jsp:include page="../headerNavAdmin.jsp"></jsp:include>
<% 
request.getSession().getAttribute("allUsersBean");
if (allUsersBean != null) {
%>
<section>
  <h1>Usuarios de la web</h1>
  <div class="tbl-header">
    <table>
      <thead>
        <tr>
          <th>Usuario</th>
          <th>Rol</th>
          <th>Fecha de Registro</th>
          <th>Ultima conexi&oacuten</th>
        </tr>
      </thead>
    </table>
  </div>
<%

	for (UserDTO user : allUsersBean.getAllUsers()){
%>
  <div class="tbl-content">
   <table>
    <tbody>
    <tr>
	<td><%= user.getUsername()%></td>
	<td><%= user.getRol()%></td>
	<td> <%= user.getStringRegister() %></td>
	<td><%= user.getStringLogged()%></td>
	
</tr>
<%
}
%>
</tbody>
    </table>
  </div>
</section>
<%
	}
%>
<script src="../../js/scrollTableModifyFechas.js"></script>
</body>
</html>