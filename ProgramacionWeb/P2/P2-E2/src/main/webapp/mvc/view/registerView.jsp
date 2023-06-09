<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register view</title>
</head>
<body>
<%
String nextPage = "../controller/registerController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";

if (customerBean != null && !customerBean.getEmailUser().equals("")) {
	nextPage = "../../index.jsp";
} else {
%>
<h3>Registro de nuevo usuario</h3>
Si desea registrarse, introduzca los siguientes datos. 
<form method="post" action="../controller/registerController.jsp">
	<label for="name">Name: </label>
	<input type="text" name="name" value="Name"><br/>
	<label for="email">Email: </label>
	<input type="text" name="email" value="username@mail.com"><br/>
	<label for="username">Username: </label>
	<input type="text" name="username" value="username"><br/>
	<label for="password">Password: </label>
	<input type="password" name="password" value=""><br/>
	<label for="rol">Rol: </label><br/>
	<select name="rol">
		<option value="Admin">Administrador</option>
		<option value="User">Usuario</option>
	</select>
	
	<br/>
	<input type="submit" value="Registrar">
</form>
<%
}
%>

</body>
</html>