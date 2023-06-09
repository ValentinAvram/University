<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
 @import url(https://fonts.googleapis.com/css?family=Roboto:100,200,300,400,500);

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
		font-size: 1.4em;
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
    height:34em;
    margin-left: -25em; /*set to a negative number 1/2 of your width*/
    margin-top: -17em; /*set to a negative number 1/2 of your height*/
    border: 3px dotted #fdfdfd;
    overflow: auto;
	}
	.side{
		display: flex;
		flex-direction: row;
		gap: 2em;
		justify-content: center;
	}
	.side1{
		display: flex;
		flex-direction: row;
		gap: 6.3em;
		justify-content: center;
	}
	.side2{
		display: flex;
		flex-direction: row;
		gap: 4.5em;
		justify-content: center;
	}

form button {
	 appearance: none;
	 outline: 0;
	 background-color: white;
	 border: 0;
	 padding: 10px 15px;
	 color: #9aafc7; 
	 border-radius: 3px;
	 width: 6em;
	 cursor: pointer;
	 font-size: 90%;
	 transition: transform .6s;
	}
 form button:hover {
	 background-color: #f5f7f9;
 	 transition: background-color 0.4s ease;
	 transform: scale(1.1);
}  

	.delete:hover{
	 transition-timing-function: ease-in-put;
	 background-color: #F4989C;
	 color: #fdfdfd;
	 transform: scale(1.1);
}
	/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px #c2cfdd; 
  border-radius: 4px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #8a9db3; 
  border-radius: 4px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #b30000; 
}
</style>
<meta charset="UTF-8">
<jsp:useBean  id="allCritBean" scope="session" class="beans.AllCritBean"></jsp:useBean>
<jsp:useBean  id="customerBean" scope="session" class="beans.CustomerBean"></jsp:useBean>
<%@ page import="business.CriticaDTO"%>
<%@ page import="business.VotantesCriticaDTO"%>

<title>Menú de Críticas</title>
</head>
<body>
    <jsp:include page="../headerNav.jsp"></jsp:include>
<% 
request.getSession().getAttribute("allEspsBean");
if (allCritBean != null) {
%>
<div class="container">
<h1>Menú de Críticas</h1>

<div class="elem">
<h2>Críticas del espectáculo : <%= request.getParameter("nombreEsp") %></h2>
<%

	for (CriticaDTO punt : allCritBean.getAllCrit())
	{
%>
	<a>Titulo: <%= punt.getTitle() %></a> <br>
	<a>Reseña: <%= punt.getResena() %> </a> <br>
	<a>Puntuacion: <%= punt.getPuntuacion() %></a> <br>
	<div class="side1">
		<a><%= punt.getLike() %></a>
		<a><%= punt.getDislike() %></a>
	</div>
	<%
	if(punt.getMail().equals(customerBean.getEmailUser()))
	{
	%>
	<div class="side2">
		<a>Likes</a>
		<a>Dislikes</a>
	</div>
		<form method="Post" action="../../../DeleteCriticaServlet">
		    <button type="submit" value="Borrar Crítica" class="delete">Borrar Cr&iacutetica</button>
		    <input type="hidden" name="idCritica" value= "<%=punt.getId() %>">
		</form>
	<%
	}
	else
	{
		int flag = 0;
		for(VotantesCriticaDTO vot : punt.getVotantes())
		{
			if(vot.getVotante().equals(customerBean.getEmailUser()) && vot.getVoto().equals("like"))
            {			
				flag = 1;	
             %>	
             	<div class="side">
             	<button disabled="disabled">Like</button>
					<form method="Post" action="../../../DislikeCriticaServlet">
					    <button type="submit" value="Dislike Critica">Dislike</button>
					    <input type="hidden" name="mailVot" value= "<%=customerBean.getEmailUser()%>">
					    <input type="hidden" name="idCritica" value= "<%=punt.getId() %>">
					</form>
				</div>
             <%            
            }
            else if(vot.getVotante().equals(customerBean.getEmailUser()) && vot.getVoto().equals("dislike"))
            {
            	flag = 1;
			%>
				<div class="side">
					<form method="Post" action="../../../LikeCriticaServlet">
					    <button type="submit" value="Like Critica">Like</button>
					    <input type="hidden" name="mailVot" value= "<%=customerBean.getEmailUser() %>">
					    <input type="hidden" name="idCritica" value= "<%=punt.getId() %>">
					</form>
	             	<button disabled="disabled">Dislike</button>
				</div>
			<%
            }
        }
        if(flag == 0)
        {
		%>
			<div class="side">
				<form method="Post" action="../../../LikeCriticaServlet">
				    <button type="submit" value="Like Critica">Like</button>
				    <input type="hidden" name="mailVot" value= "<%=customerBean.getEmailUser() %>">
				    <input type="hidden" name="idCritica" value= "<%=punt.getId() %>">
				</form>
				<form method="Post" action="../../../DislikeCriticaServlet">
				    <button type="submit" value="Dislike Critica">Dislike</button>
				    <input type="hidden" name="mailVot" value= "<%=customerBean.getEmailUser() %>">
				    <input type="hidden" name="idCritica" value= "<%=punt.getId() %>">
				</form>
			</div>
		<%
        }
	}
%>
	<br>
<%
}
%>
</div>
</div>
<%
}
session.setAttribute("allEspsBean", null);

%>

</body>
</html>