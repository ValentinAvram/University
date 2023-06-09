<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home User</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
body{
	background: #9aafc7;
   	font-family: 'Roboto', sans-serif;
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
	<jsp:include page="../headerNav.jsp"></jsp:include>
	
		<div class="cont">
	<div class="users">
	  <a href=/P3-Alt/P3-Alt/views/homeDataMenu/userDataHome.jsp><img class="foto" src="/P3-Alt/P3-Alt/img/user.png" alt="data usuario" style="width:100%"></a>
	  <div class="container">
	  <p>Datos personales</p>
	  </div>
	</div>
	<div class="crit">
	  <a href=/P3-Alt/P3-Alt/views/homeDataMenu/modifyUser.jsp><img class="foto" src="/P3-Alt/P3-Alt/img/modify.png" alt="modify usuario" style="width:100%"></a>
	  <div class="container">
	  <p>Modificar sus datos Personales</p>
	  </div>
	</div>
	<div class="espectaculos">
	  <a href=/P3-Alt/P3-Alt/views/homeDataMenu/deleteUser.jsp><img class="foto" src="/P3-Alt/P3-Alt/img/trashcan.png" alt="delete usuario" style="width:100%"></a>
	  <div class="container">
	  <p>Eliminar su cuenta de usuario</p>
	  </div>
	</div>
	</div>

</body>
</html>