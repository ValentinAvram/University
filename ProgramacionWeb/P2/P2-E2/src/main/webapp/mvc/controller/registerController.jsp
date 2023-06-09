<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.DTOs.UserDTO,es.uco.pw.data.DAOs.UserDAO, java.util.ArrayList, java.time.LocalDateTime" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%

String nextPage = "../../index.jsp";
String mensajeNextPage = "";
if (customerBean == null || customerBean.getEmailUser().equals("")) {
	String mail = request.getParameter("email");
	String password = request.getParameter("password");
	String username = request.getParameter("username");
	String name = request.getParameter("name");
	String rol = request.getParameter("rol");
	LocalDateTime regTime = LocalDateTime.now();
	if (mail != null) {
		//String file =application.getInitParameter("properties");
		String url = application.getInitParameter("url");
		String userC = application.getInitParameter("user");
		String passwd = application.getInitParameter("password");
		//java.io.InputStream myIO = application.getResourceAsStream(file); 
		
		UserDAO userDAO = new UserDAO(url,userC,passwd);
		
		ArrayList<UserDTO> users = userDAO.requestUsers();
		Boolean error = false;
		
		for(UserDTO u : users){
			if(u.getMail().equals(mail)){
				nextPage = "../../userExistError.jsp";
				error = true;
			}
		}
		if(!error){
			UserDTO newUser = new UserDTO(name, mail, username, password, rol, regTime);
			userDAO.createUser(newUser);
			customerBean.setUsername(username); 
			customerBean.setRol(rol); 
			customerBean.setRegisterTime(regTime); 
		}
	} else {
		nextPage = "../view/registerView.jsp";
		mensajeNextPage = "Error en el registro. El usuario que ha indicado no existe o no es v&aacute;lido";
		}
	} else {
		nextPage = "../view/registerView.jsp";		
}
		

%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>