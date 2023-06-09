<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	body {
    	background-color: #9aafc7;
    	font-family: 'Roboto', sans-serif;
    	color: #fdfdfd;
    }
.foto
{
 transition: transform .2s; /* Animation */
}    
.foto:hover
{
transform: scale(1.1);
}
.cont {  
  display: grid;
  grid-template-columns: 1fr 1.3fr 0.55fr 1.3fr 0.55fr 1.3fr 1fr;
  grid-template-rows: 0.2fr 1.5fr;
  grid-auto-columns: 1fr;
  gap: 0px 0px;
  grid-auto-flow: row;
  grid-template-areas:
    ". . . . . . ."
    ". users . crit . espectaculos .";
}

.users { grid-area: users; }

.crit { grid-area: crit; }

.espectaculos { grid-area: espectaculos; }

    div.users, div.crit, div.espectaculos {
   display: grid;
  margin-left: auto;
  margin-right: auto;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

div.container {
  text-align: center;
  padding: 10px 20px;
}
.container p{
  font-size: 2em;
  color: #fdfdfd;
  text-transform: uppercase;
  font-weight: 300;
  text-align: center;
  margin-bottom: 15px;
}

    
</style>
</head>
<body>
	<jsp:include page="headerNav.jsp"></jsp:include>

	<div class="cont">
	<div class="users">
	  <a href=/P3-Alt/P3-Alt/views/homeDataMenu/userHome.jsp><img src="/P3-Alt/P3-Alt/img/user.png" alt="users" style="width:100%" class=foto></a>
	  <div class="container">
	  <p>Informaci&oacuten de Usuario</p>
	  </div>
	</div>
	<div class="crit">
	  <a href=../../../P3-Alt/GetAllEspsDoneServlet><img src="/P3-Alt/P3-Alt/img/crit.png" alt="criticas" style="width:100%" class=foto></a>
	  <div class="container">
	  <p>Cr&iacuteticas de Espect&aacuteculos</p>
	  </div>
	</div>
	<div class="espectaculos">
	  <a href=../../../P3-Alt/SearchAllEspsUser><img src="/P3-Alt/P3-Alt/img/esp.png" alt="espectaculos" style="width:100%" class=foto></a>
	  <div class="container">
	  <p>Espect&aacuteculos</p>
	  </div>
	</div>
	</div>

</body>
</html>