package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

@WebServlet(name="ModifyFechaInicioServlet", urlPatterns="/ModifyFechaInicioServlet")

public class ModifyFechaInicioServlet extends HttpServlet 
{
	private static final long serialVersionUID = -2680903184107626079L;

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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");				
				String fechai = request.getParameter("fecha");
				fechai = fechai.replace('T', ' ');
				fechai = fechai+ ":00.0";
				LocalDateTime Fechai = LocalDateTime.parse(fechai, formatter);	
							
				LocalDateTime Fechaf = p.getFechaFinal();			
					
				String diaSemana = p.getDiaSemana();	
							
				PasesDTO updPase = new PasesDTO(idPase, Fechai, diaSemana, Fechaf);
				multDAO.updatePase(updPase);
			}
		}		
		String ruta = request.getContextPath() + "/GetAllEsps"; 
		multDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Fecha editada correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
}