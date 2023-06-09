package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import business.*;
import data.DAOs.CriticaDAO;
import beans.*;

@WebServlet(name="GetAllCritsServlet", urlPatterns="/GetAllCritsServlet")

public class GetAllCritsServlet extends HttpServlet 
{
	private static final long serialVersionUID = -331376202396170659L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext app = getServletContext();
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		int IDEsp = Integer.parseInt(request.getParameter("idEsp"));
		String title = request.getParameter("nombreEsp");
		HttpSession session = request.getSession();
		
		CriticaDAO critDAO = new CriticaDAO(urlBD, userBD, passBD);
		ArrayList<CriticaDTO> criticas = critDAO.requestCriticas();
		ArrayList<CriticaDTO> crits = new ArrayList<CriticaDTO>();
		
		AllCritBean allCritBean = new AllCritBean();

		for(CriticaDTO c : criticas)
		{
			if(c.getIdEsp() == IDEsp)
			{
				CriticaDTO foundCrit = new CriticaDTO();
				foundCrit.settitle(c.getTitle());
				foundCrit.setMail(c.getMail());
				foundCrit.setPuntuacion(c.getPuntuacion());
				foundCrit.setResena(c.getResena());
				foundCrit.setId(c.getId());
				foundCrit.setIdEsp(c.getIdEsp());
				foundCrit.setLike(c.getLike());
				foundCrit.setDislike(c.getDislike());
				foundCrit.setVotantes(critDAO.requestVotantes(c.getId()));
				crits.add(foundCrit);
			}
		}
		
		allCritBean.setAllCrit(crits);
		allCritBean.setNumCrit(crits.size());
		session.setAttribute("allCritBean", allCritBean);
		
		String opc1 = request.getContextPath() + "/P3-Alt/views/criticasMenu/showCriticasEsp.jsp?nombreEsp="+title;
		critDAO.desCriticaDAO();
		response.sendRedirect(opc1); 

	}
}
