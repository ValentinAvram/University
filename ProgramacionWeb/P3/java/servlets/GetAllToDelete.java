package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="GetAllToDelete", urlPatterns="/GetAllToDelete")

public class GetAllToDelete extends HttpServlet 
{
	private static final long serialVersionUID = -2231536240590954960L;

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
			
			String opc1 = request.getContextPath() + "/P3-Alt/views/EspMenu/deleteEspectaculo.jsp";
			response.sendRedirect(opc1);

	}
}
