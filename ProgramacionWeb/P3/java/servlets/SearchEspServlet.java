package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="SearchEspServlet", urlPatterns="/SearchEspServlet")

public class SearchEspServlet extends HttpServlet 
{

	private static final long serialVersionUID = 7313417310612984176L;

	/**
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext app = getServletContext();
		String title = null, category = null;
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		if(!"".equals(request.getParameter("titulo")))
		{
			title = request.getParameter("titulo");
		}
		else 
		{
			category = request.getParameter("categoria");
		}
		if(!"".equals(title) || !"".equals(category))
		{
			EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
			ArrayList<EspectaculoPuntDTO> espPunt = espDAO.requestEPs();
			ArrayList<EspectaculoMultDTO> espMult = espDAO.requestEMs();
			ArrayList<EspectaculoTempDTO> espTemp = espDAO.requestETs();
	
			ArrayList<EspectaculoPuntDTO> foundPunt = new ArrayList<EspectaculoPuntDTO>();
			ArrayList<EspectaculoMultDTO> foundMult = new ArrayList<EspectaculoMultDTO>();
			ArrayList<EspectaculoTempDTO> foundTemp = new ArrayList<EspectaculoTempDTO>();
				
			for(EspectaculoPuntDTO e : espPunt)
			{
				if(e.getCategoria().equals(category) || e.getTitulo().equals(title))
				{
					foundPunt.add(e);
				}
			}
				
			for(EspectaculoMultDTO e : espMult)
			{
				if(e.getCategoria().equals(category) || e.getTitulo().equals(title))
				{System.out.println(e.getCategoria());
					foundMult.add(e);
				}
			}
				
			for(EspectaculoTempDTO e : espTemp)
			{
				if(e.getCategoria().equals(category) || e.getTitulo().equals(title))
				{System.out.println(e.getCategoria());
					foundTemp.add(e);
				}
			}

			HttpSession session = request.getSession();
			AllEspsBean allEspsBean = new AllEspsBean();
			
			allEspsBean.setAllPunt(foundPunt);
			allEspsBean.setNumPunt(foundPunt.size());
			
			allEspsBean.setAllMult(foundMult);
			allEspsBean.setNumMult(foundMult.size());
			
			allEspsBean.setAllTemp(foundTemp);
			allEspsBean.setNumTemp(foundTemp.size());
			
			session.setAttribute("allEspsBean", allEspsBean);
			espDAO.desEspectaculoDAO();
			response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/allEspsView.jsp"); 
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/P3-Alt/error/errorAllViews.jsp"); //TODO: 

		}
	}
}
