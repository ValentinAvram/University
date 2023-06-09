package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.EspectaculoDAO;
import beans.*;

@WebServlet(name="GetAllEspsDoneServlet", urlPatterns="/GetAllEspsDoneServlet")

public class GetAllEspsDoneServlet extends HttpServlet 
{
	private static final long serialVersionUID = 3708894964874779929L;

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
				if(e.getHoraFecha().isBefore(LocalDateTime.now()))
				{
					foundPunt.add(e);
				}
			}
				
			for(EspectaculoMultDTO e : espMult)
			{
				Boolean flag = false;
				ArrayList<FechasDTO> fechas = e.getFechas();
				for(FechasDTO fecha : fechas)
				{
					if(fecha.getFecha().isBefore(LocalDateTime.now()))
					{
						flag = true;
					}
				}
				if(flag == true)
				{
					foundMult.add(e);
				}
			}
				
			for(EspectaculoTempDTO e : espTemp)
			{
				Boolean flag = false;
				ArrayList<PasesDTO> pases = e.getPases();
				for(PasesDTO pase : pases)
				{
					if(pase.getFechaInicio().isBefore(LocalDateTime.now()))
					{
						flag = true;
					}
				}
				if(flag == true)
				{
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

			String opc1 = request.getContextPath() + "/P3-Alt/views/criticasMenu/allCriticasView.jsp";
			espDAO.desEspectaculoDAO();
			response.sendRedirect(opc1); 

	}
}
