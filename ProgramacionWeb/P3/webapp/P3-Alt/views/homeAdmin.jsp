<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Admin</title>
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
.cont {  display: grid;
  grid-template-columns: 1.3fr 1.9fr 0.2fr 1.9fr 1.3fr;
  grid-template-rows: 0.2fr 1.8fr;
    grid-auto-columns: 1fr;
  gap: 0px 0px;
  grid-auto-flow: row;
  grid-template-areas:
    ". . . . ."
    ". users . esp .";
}

.users { grid-area: users; }

.esp { grid-area: esp; }



 div.users, div.esp {
  display: grid;
  margin-left: auto;
  margin-right: auto;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
	max-height: 450px;
	max-width: 377px;
	min-height: 150px;
	min-width: 100px;
}

div.container {
  text-align: center;
  padding: 10px 20px;
}
.container p{
  font-size: 1.5em;
  color: #fdfdfd;
  text-transform: uppercase;
  font-weight: 300;
  text-align: center;
  margin-bottom: 15px;
}


</style>
</head>
<body>
	<jsp:include page="headerNavAdmin.jsp"></jsp:include>
	<div class="cont">
	<div class="users">
	  <a href=../../../P3-Alt/AdminUsersControl><img src="/P3-Alt/P3-Alt/img/user.png" alt="users" style="width:100%" class="foto"></a>
	  <div class="container">
	  <p>Informaci&oacuten de Usuarios</p>
	  </div>
	</div>
	<div class="esp">
	  <a href=./EspMenu/homeEspAdmin.jsp><img src="/P3-Alt/P3-Alt/img/esp.png" alt="espectaculos" style="width:100%" class="foto"></a>
	  <div class="container">
	  <p>Espect&aacuteculos</p>
	  </div>
	</div>
	</div>

</body>
</html>