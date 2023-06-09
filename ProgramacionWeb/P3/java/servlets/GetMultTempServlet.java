package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="GetMultTempServlet", urlPatterns="/GetMultTempServlet")

public class GetMultTempServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6229483247667863218L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		ServletContext app = getServletContext();
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
		
			ArrayList<EspectaculoMultDTO> espMult = espDAO.requestEMs();
			ArrayList<EspectaculoTempDTO> espTemp = espDAO.requestETs();

			HttpSession session = request.getSession();
			AllEspsBean allEspsBean = new AllEspsBean();
			
			allEspsBean.setAllMult(espMult);
			allEspsBean.setNumMult(espMult.size());
			
			allEspsBean.setAllTemp(espTemp);
			allEspsBean.setNumTemp(espTemp.size());
			
			session.setAttribute("allEspsBean", allEspsBean);
			espDAO.desEspectaculoDAO();
			response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/addFechasAdmin.jsp"); 
	}
}
