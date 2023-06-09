package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="SearchMultServlet", urlPatterns="/SearchMultServlet")

public class SearchMultServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6659878034720266309L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext app = getServletContext();
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
		
		ArrayList<EspectaculoMultDTO> espMult = espDAO.requestEMs();
		ArrayList<EspectaculoMultDTO> foundMult = new ArrayList<EspectaculoMultDTO>();
		
		for(EspectaculoMultDTO e : espMult)
		{
			foundMult.add(e);
		}

		HttpSession session = request.getSession();
		AllEspsBean allEspsBean = new AllEspsBean();
			
		allEspsBean.setAllMult(foundMult);
		allEspsBean.setNumMult(foundMult.size());	
			
		session.setAttribute("allEspsBean", allEspsBean);
		espDAO.desEspectaculoDAO();
		response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/allEspsView.jsp"); 
	}	
}