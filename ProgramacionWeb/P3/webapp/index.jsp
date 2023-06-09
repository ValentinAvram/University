<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean  id="customerBean" scope="session" class="beans.CustomerBean"></jsp:useBean>
<title>Log in</title>
<link type="text/css" rel="stylesheet" href="/P3-Alt/P3-Alt/css/bgInit.css">
</head>
<body>
<% 
if (request.getParameter("reset") != null) {
%>
<jsp:setProperty property="emailUser" value="" name="customerBean"/>
<jsp:setProperty property="userName" value="" name="customerBean"/>
<%
}
if (customerBean == null || customerBean.getEmailUser().equals("")) 
{
%>
<div class="bg"></div>
<div class="bg bg2"></div>
<div class="bg bg3"></div>
<div class="container">
		<h1>Bienvenid@</h1>
		<form method="post" class="form" action="../../P3-Alt/LoginServlet">
			<input type="email" name="Mail" placeholder="usermail@mail.com" required>
            <input type="password" name="Password" placeholder="password" required>
			<button type="submit" id="login-button">Login</button>
		</form>
		<h2>¿No estás registrado?</h2>
		<br>	
		<button type="button" value="Sign Up"onClick = "window.location.href= '/P3-Alt/P3-Alt/views/registerView.jsp';">Sign Up</button>
</div>
<% 	
} 
else 
{ 
	if(customerBean.getRol().equals("User")) {
	 	String redirectUrl = request.getContextPath() + "/P3-Alt/views/home.jsp";
	 	response.sendRedirect(redirectUrl);
	}
	else{
		String redirectUrl = request.getContextPath() + "/P3-Alt/views/homeAdmin.jsp";
	 	response.sendRedirect(redirectUrl);		
	}
} 
%>
<script type="text/javascript">
 $("#login-button").click(function(event){
		 event.preventDefault();
	 
	 $('form').fadeOut(500);
	 $('.wrapper').addClass('form-success');
});
</script>
</body>
</html>
