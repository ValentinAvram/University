<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	body{
		background: #9aafc7;
	   	font-family: 'Roboto', sans-serif;
	   	color: #fdfdfd;
	}
	h1{
		font-size: 2.4em;
		text-align: center;
	}
	h2{
		font-size: 1.8em;
		text-align: center;
	}
	.container {
    position:fixed;
    font-size: 1.2 em;
    text-align: center;
    vertical-align: center;
    top: 50%;
    left: 50%;
    width:50em;
    height:32em;
    margin-left: -25em; /*set to a negative number 1/2 of your width*/
    margin-top: -16em; /*set to a negative number 1/2 of your height*/
    border: 3px dotted #fdfdfd;
	}
	.static{
		text-align: center;
		height: 5em;
		font: italic bold 1em #fdfdfd";
		padding:2em;
		margin-top: 2em;
	}
	.var {
		text-align: center;
		height: 5em;
		font: bold .8em #fdfdfd";
		padding:2em;
		margin-top: 2em;
		
	}
hr{
    border-top: 2px solid #fdfdfd;
	border-radius: 1px;
	text-align: center;
	padding: 0;
}
hr.a{
	width: 50%;
}
hr.b{
  width:45%;
}
hr.c{
 	width: 40%;
}
hr.d{
	width: 35%;
}
</style>
<meta charset="UTF-8">
<jsp:useBean  id="customerBean" scope="session" class="beans.CustomerBean"></jsp:useBean>
<%@ page import="beans.CustomerBean"%>
<%@ page import="business.UserDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>

<title>Datos de usuario</title>
</head>
<body>
	<jsp:include page="../headerNav.jsp"></jsp:include>
<% 
request.getSession().getAttribute("customerBean");
if (customerBean != null) {
%>

<div class="container">
<h1>Bienvenido al panel de usuario</h1>
<h2>Sus Datos son:</h2>
	<hr class="a">
	<a class="static">Nombre de Usuario:  </a><a class="var"><%= customerBean.getUsername()%></a>
	<hr class="b">
	<a class="static">Nombre:  </a><a class="var"><%= customerBean.getName()%></a>
	<hr class="c">
	<a class="static">Rol:  </a><a class="var"><%= customerBean.getRol()%></a>
	<hr class="d">
	<a class="static">Correo asociado:  </a><a class="var"><%=customerBean.getEmailUser()%></a>
	<hr class="c">
	<a class="static">Fecha de registro:  </a><a class="var"><%= customerBean.getRegisterString()%></a>
	<hr class="b">
	<a class="static">Fecha de &uacuteltima conexión:  </a><a class="var"><%= customerBean.getLoggedString()%></a>
	<hr class="a">
</div>

<%
	}
%>

</body>
</html>