package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import business.UserDTO;
import data.DAOs.UserDAO;
import beans.CustomerBean;

@WebServlet(name="LoginServlet", urlPatterns="/LoginServlet")

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4261016544681578021L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String mail = request.getParameter("Mail");
		String pass = request.getParameter("Password");
		ServletContext app = getServletContext();
		String ruta = request.getContextPath() + "/index.jsp";
			if (mail != null) {
				String passBD = app.getInitParameter("password");
				String urlBD = app.getInitParameter("url");
				String userBD = app.getInitParameter("user");

				UserDAO userDAO = new UserDAO(urlBD,userBD,passBD);
				String password = userDAO.requestCredenciales(mail);
				ArrayList<UserDTO> users = userDAO.requestUsers();		
				int flag=0;
				for(UserDTO u : users){
					if(u.getMail().equals(mail))
					{
						if (password.equals(pass)){
							String username = u.getUsername();
							String rol = u.getRol();
							HttpSession session = request.getSession();
							CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
							customerBean.setEmailUser(mail); 
							customerBean.setName(u.getName());
							customerBean.setUsername(username); 
							customerBean.setRol(rol);
							
							LocalDateTime fecha = LocalDateTime.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							String fechaLog = fecha.format(formatter);
							userDAO.updateLog(u, fechaLog);
							customerBean.setLastLogged(fecha);
							customerBean.setRegisterTime(u.getRegisterTime());
							
							flag=1;
							if(rol.equals("Admin"))
								ruta = request.getContextPath() + "/P3-Alt/views/homeAdmin.jsp";
							else
								ruta = request.getContextPath() + "/P3-Alt/views/home.jsp";
						}
						else {
							flag=2;
							ruta = request.getContextPath() + "/P3-Alt/error/userBadPass.jsp";
						}
					}
				}
				userDAO.desUserDAO();
				if(flag==0)
					ruta = request.getContextPath() + "/P3-Alt/error/userNotExist.jsp";
			}
			else
				ruta = request.getContextPath() + "/index.jsp";
		response.sendRedirect(ruta);	
	}
		
}

 