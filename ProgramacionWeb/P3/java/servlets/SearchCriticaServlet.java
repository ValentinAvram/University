package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import business.*;
import data.DAOs.CriticaDAO;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="SearchCriticaServlet", urlPatterns="/SearchCriticaServlet")

public class SearchCriticaServlet extends HttpServlet 
{
	private static final long serialVersionUID = -2204662920245626705L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext app = getServletContext();

		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		
		if(!"null".equals(request.getParameter("titulo")))
		{
			String title = request.getParameter("titulo");
			int idEsp = -1;
			CriticaDAO critDAO = new CriticaDAO(urlBD, userBD, passBD);
			EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
			
			ArrayList<EspectaculoPuntDTO> punts = espDAO.requestEPs();
			ArrayList<EspectaculoMultDTO> mults = espDAO.requestEMs();
			ArrayList<EspectaculoTempDTO> temps = espDAO.requestETs();
			ArrayList<CriticaDTO> criticas = critDAO.requestCriticas();
			ArrayList<CriticaDTO> foundCriticas = new ArrayList<CriticaDTO>();
			
			for(EspectaculoPuntDTO e : punts)
			{
				if(title.equals(e.getTitulo()))
				{
					idEsp = e.getID();
				}
			}
			
			for(EspectaculoMultDTO e : mults)
			{
				if(title.equals(e.getTitulo()))
				{
					idEsp = e.getID();
				}
			}
			
			for(EspectaculoTempDTO e : temps)
			{
				if(title.equals(e.getTitulo()))
				{
					idEsp = e.getID();
				}
			}
			
			//TODO:
			for(CriticaDTO c : criticas)
			{
				if(c.getIdEsp() == idEsp)
				{
					foundCriticas.add(c);
				}
			}
			
			HttpSession session = request.getSession();
			AllCritBean allCritBean = new AllCritBean();
			
			allCritBean.setAllCrit(foundCriticas);
			allCritBean.setNumCrit(foundCriticas.size());
			
			session.setAttribute("allCritBean", allCritBean);
			critDAO.desCriticaDAO();
			espDAO.desEspectaculoDAO();
			response.sendRedirect(request.getContextPath() + "/P3-Alt/views/criticasMenu/allCriticasView.jsp");
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/P3-Alt/error/errorAllCriticas.jsp"); //TODO:
		}
		
	}
}