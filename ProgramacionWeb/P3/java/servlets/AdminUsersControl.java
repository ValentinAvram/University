package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import business.UserDTO;
import data.DAOs.UserDAO;
import beans.AllUsersBean;

@WebServlet(name="AdminUsersControl", urlPatterns="/AdminUsersControl")

public class AdminUsersControl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		
		UserDAO userDAO = new UserDAO(urlBD,userBD,passBD);		
		ArrayList<UserDTO> allUsers = userDAO.requestUsers();
		
		HttpSession session = request.getSession();
		AllUsersBean allUsersBean = new AllUsersBean();
				//(AllUsersBean) session.getAttribute("allUsersBean");
		allUsersBean.setAllUsers(allUsers);
		allUsersBean.setNumUsers(allUsers.size());
		session.setAttribute("allUsersBean", allUsersBean);
		userDAO.desUserDAO();
		response.sendRedirect(request.getContextPath() + "/P3-Alt/views/homeDataMenu/adminUsersHome.jsp"); 
		
	}
}