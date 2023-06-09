package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import beans.CustomerBean;
import business.UserDTO;


@WebServlet(name="UserDataServlet", urlPatterns="/UserDataServlet")

public class UserDataServlet extends HttpServlet 
{
	private static final long serialVersionUID = -2457476533071596746L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		String mail = customerBean.getEmailUser();

		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		
		for(UserDTO u : users)
		{
			if(u.getMail() == mail)
			{
				customerBean.setEmailUser(u.getMail());
				customerBean.setLastLogged(u.getLastLogged());
				customerBean.setRegisterTime(u.getRegisterTime());
				customerBean.setRol(u.getRol());
				customerBean.setUsername(u.getUsername());
				customerBean.setName(u.getName());
			}
		}
		session.setAttribute("custormerBean", customerBean);
		response.sendRedirect(request.getContextPath() + "/P3-Alt/views/homeDataMenu/UserInfoView.jsp");
	}
}