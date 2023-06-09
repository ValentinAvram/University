package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.EspectaculoTempDTO;
import business.PasesDTO;
import javax.servlet.*;
import data.DAOs.EspectaculoDAO;

@WebServlet(name="ModifyDiaSemana", urlPatterns="/ModifyDiaSemana")

public class ModifyDiaSemana extends HttpServlet 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8027247685850352153L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO multDAO = new EspectaculoDAO(urlBD,userBD,passBD);
		ArrayList<EspectaculoTempDTO> temporadas= multDAO.requestETs();
		
		int id = Integer.parseInt(request.getParameter("idEsp"));
		int idPase = Integer.parseInt(request.getParameter("idpase"));
		ArrayList<PasesDTO> pasesUpdate = new ArrayList<PasesDTO>();
		
		for(EspectaculoTempDTO e : temporadas)
		{
			if(id == e.getID())
			{
				pasesUpdate = e.getPases();
			}
		}
				
		for(PasesDTO p : pasesUpdate)
		{
			if(p.getID() == idPase)
			{
				
				LocalDateTime Fechaf = p.getFechaFinal();			
				LocalDateTime Fechai = p.getFechaInicio();				
				String diaSemana = request.getParameter("diasemana");				
				PasesDTO updPase = new PasesDTO(idPase, Fechai, diaSemana, Fechaf);
				multDAO.updatePase(updPase);
			}
		}		
		String ruta = request.getContextPath() + "/GetAllEsps"; //TODO: 
		multDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Día de la semana editado correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
}