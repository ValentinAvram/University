package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import business.UserDTO;
import data.DAOs.UserDAO;
import beans.CustomerBean;

@WebServlet(name="RegisterServlet", urlPatterns="/RegisterServlet")

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 2712014577923922790L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String ruta = request.getContextPath() + "/index.jsp";
		String mail = request.getParameter("Mail");
		if (mail != null) {
			ServletContext app = getServletContext();
			String passBD = app.getInitParameter("password");
			String urlBD = app.getInitParameter("url");
			String userBD = app.getInitParameter("user");
			
			String pass = request.getParameter("Password");
			String rol = request.getParameter("rol");
			String username = request.getParameter("Username");
			String name = request.getParameter("Name");
			LocalDateTime regTime = LocalDateTime.now();
			UserDAO userDAO = new UserDAO(urlBD,userBD,passBD);

			ArrayList<UserDTO> users = userDAO.requestUsers();
			
			for(UserDTO u : users){
				if(u.getMail().equals(mail)){
					ruta = request.getContextPath() + "/P3-Alt/error/userExistError.jsp";
				}
			}
			UserDTO newUser = new UserDTO(name, mail, username, pass, rol, regTime);
			userDAO.createUser(newUser);
			HttpSession session = request.getSession();
			CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
			customerBean.setUsername(username); 
			customerBean.setRol(rol); 
			customerBean.setRegisterTime(regTime);
			userDAO.desUserDAO();
		}
		response.sendRedirect(ruta);
	}
} 
