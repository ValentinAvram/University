<%@page import="es.uco.pw.display.javabean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.DTOs.UserDTO,es.uco.pw.data.DAOs.UserDAO, java.util.ArrayList, java.time.LocalDateTime" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%

String nextPage = "../../index.jsp";
String mensajeNextPage = "";
if (customerBean == null || customerBean.getEmailUser().equals("")) {
	String mail = request.getParameter("Mail");
	String password = request.getParameter("Password");

	if (mail != null) {
		//String file =application.getInitParameter("properties");
		String url = application.getInitParameter("url");
		String userC = application.getInitParameter("user");
		String passwd = application.getInitParameter("password");
		//java.io.InputStream myIO = application.getResourceAsStream(file);
		
		UserDAO userDAO = new UserDAO(url,userC,passwd);
		String pass = userDAO.requestCredenciales(mail);
		ArrayList<UserDTO> users = userDAO.requestUsers();
		
		for(UserDTO u : users){
			if(u.getMail().equals(mail)){
				if (password.equals(pass)){
					if(u.getRol().equals("usuario")){
						String username = u.getUsername();
						LocalDateTime regTime = u.getRegisterTime();
						String rol = u.getRol();
						customerBean.setEmailUser(mail); 
						customerBean.setUsername(username); 
						customerBean.setRol(rol); 
						customerBean.setRegisterTime(regTime); 
					}
					else{
						String username = u.getUsername();
						LocalDateTime regTime = u.getRegisterTime();
						String rol = u.getRol();
						customerBean.setEmailUser(mail); 
						customerBean.setUsername(username); 
						customerBean.setRol(rol); 
						customerBean.setRegisterTime(regTime); 
					}
				}
				else{
					nextPage = "../../userBadPass.jsp";
					mensajeNextPage = "Wrong password";
				}
			}
			
		}
	} else {
		nextPage = "../view/loginView.jsp";
		mensajeNextPage = "El usuario que ha indicado no existe o no es v&aacute;lido";
		}
	} else {
		nextPage = "../view/loginView.jsp";		
	}

%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>