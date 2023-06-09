package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="SearchTempServlet", urlPatterns="/SearchTempServlet")

public class SearchTempServlet extends HttpServlet 
{
	private static final long serialVersionUID = -9104506009763236615L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext app = getServletContext();
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
		
		ArrayList<EspectaculoTempDTO> espTemp = espDAO.requestETs();
		ArrayList<EspectaculoTempDTO> foundTemp = new ArrayList<EspectaculoTempDTO>();
		
		for(EspectaculoTempDTO e : espTemp)
		{
			foundTemp.add(e);
		}

		HttpSession session = request.getSession();
		AllEspsBean allEspsBean = new AllEspsBean();
			
		allEspsBean.setAllTemp(foundTemp);
		allEspsBean.setNumTemp(foundTemp.size());	
			
		session.setAttribute("allEspsBean", allEspsBean);
		espDAO.desEspectaculoDAO();
		response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/allEspsView.jsp"); 
	}	
}