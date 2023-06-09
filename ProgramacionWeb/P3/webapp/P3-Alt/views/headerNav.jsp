<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Header</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
*{
  font-family: 'Roboto', sans-serif;
  font-weight: 300;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: inherit;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  font-weight: 300;
  font-size: 1.2em;
  padding: 1em 3.4em;
  border: 1px solid black;
  text-decoration: none;
}

li#esps{
  background: #FDE2A3;
}
li#crit{
  background: #A5B195;
}
li#home{
  background: #9aafc7;
}
li#logout{
  background: #E58B8F;
}
li#user{
  background: #F6C2A8;
}

#home:hover,
#home:focus-within {
  background: #57789E;
    transition-duration: 0.6s;
  cursor: pointer;
}
#crit:hover,
#crit:focus-within {
  background: #7d8d68;
    transition-duration: 0.6s;
  cursor: pointer;
}
#esps:hover,
#esps:focus-within {
  background: #fccd5f;
    transition-duration: 0.6s;
  cursor: pointer;
}
#user:hover,
#user:focus-within {
  background: #ef976b;
    transition-duration: 0.6s;
  cursor: pointer;
}
#logout:hover,
#logout:focus-within {
  background: #d9595f;
    transition-duration: 0.6s;
  cursor: pointer;
}

</style>
</head>
<body>
<body>
 <ul>
  <li id="home"><a href="/P3-Alt/P3-Alt/views/home.jsp">Home</a></li>
  <li id="user"><a href="/P3-Alt/P3-Alt/views/homeDataMenu/userHome.jsp">Usuario</a></li>
  <li id="crit"><a href="/P3-Alt/GetAllEspsDoneServlet">Cr&iacuteticas</a></li>
  <li id="esps"><a href="/P3-Alt/SearchAllEspsUser">Espect&aacuteculos</a></li>
  <li id="logout" style="float:right"><a href="/P3-Alt/LogOutServlet">LogOut</a></li>
</ul>
<br/>  
</html>