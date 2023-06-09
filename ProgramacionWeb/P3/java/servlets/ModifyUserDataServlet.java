package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.UserDTO;
import data.DAOs.UserDAO;
import beans.CustomerBean;

@WebServlet(name="ModifyUserDataServlet", urlPatterns="/ModifyUserDataServlet")

public class ModifyUserDataServlet extends HttpServlet 
{

	private static final long serialVersionUID = 2712014577923922790L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		String mail = customerBean.getEmailUser();
		String name = request.getParameter("name");
		String userName = request.getParameter("username");
		
		ServletContext app = getServletContext();
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		String password = request.getParameter("passw");
		LocalDateTime reg = LocalDateTime.now();
		UserDAO userDAO = new UserDAO(urlBD, userBD, passBD);
		
		for(UserDTO u : userDAO.requestUsers())
		{
			if(u.getMail().equals(mail))
			{
				if(name.equals(""))
				{
					name  = u.getName();
				}
				else if(userName.equals(""))
				{
					userName = u.getUsername();
				}
				else if(password.equals(""))
				{
					password = u.getPasswd();
				}
				
				reg = u.getRegisterTime();
			}
		}
		
		CustomerBean customersBean = new CustomerBean();
		
		customersBean.setEmailUser(mail);
		customersBean.setName(name);
		customersBean.setRol("User");
		customersBean.setUsername(userName);
		customersBean.setLastLogged(LocalDateTime.now());
		customersBean.setRegisterTime(reg);

		session.setAttribute("customerBean", customersBean);
		
		UserDTO userDTO = new UserDTO(name, mail, userName, password, "User");
		
		userDAO.updateUser(userDTO);
			
		String ruta = request.getContextPath() + "/P3-Alt/views/home.jsp";
		userDAO.desUserDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Datos de usuario editados correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
} 
