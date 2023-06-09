package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AllEspsBean;
import business.EspectaculoMultDTO;
import business.EspectaculoPuntDTO;
import business.EspectaculoTempDTO;
import data.DAOs.EspectaculoDAO;

@WebServlet(name="SearchAllEspsUser", urlPatterns="/SearchAllEspsUser")

public class SearchAllEspsUser extends HttpServlet  {

	private static final long serialVersionUID = 5404424335014969036L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext app = getServletContext();
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

			EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
			ArrayList<EspectaculoPuntDTO> espPunt = espDAO.requestEPs();
			ArrayList<EspectaculoMultDTO> espMult = espDAO.requestEMs();
			ArrayList<EspectaculoTempDTO> espTemp = espDAO.requestETs();
	
			ArrayList<EspectaculoPuntDTO> foundPunt = new ArrayList<EspectaculoPuntDTO>();
			ArrayList<EspectaculoMultDTO> foundMult = new ArrayList<EspectaculoMultDTO>();
			ArrayList<EspectaculoTempDTO> foundTemp = new ArrayList<EspectaculoTempDTO>();
				
			for(EspectaculoPuntDTO e : espPunt)
			{
					foundPunt.add(e);
			}
				
			for(EspectaculoMultDTO e : espMult)
			{
					foundMult.add(e);
			}
				
			for(EspectaculoTempDTO e : espTemp)
			{
					foundTemp.add(e);
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
			
			String ruta = request.getContextPath() + "/P3-Alt/views/EspMenu/allEspsView.jsp";
			response.sendRedirect(ruta);
	}

	
}
