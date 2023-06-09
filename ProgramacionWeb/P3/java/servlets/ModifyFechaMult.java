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
import business.EspectaculoMultDTO;
import business.FechasDTO;
import javax.servlet.*;
import data.DAOs.EspectaculoDAO;

@WebServlet(name="ModifyFechaMult", urlPatterns="/ModifyFechaMult")

public class ModifyFechaMult extends HttpServlet 
{
	private static final long serialVersionUID = -8238728246051471251L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO multDAO = new EspectaculoDAO(urlBD,userBD,passBD);
		ArrayList<EspectaculoMultDTO> multiples= multDAO.requestEMs();
		
		int id = Integer.parseInt(request.getParameter("idEsp"));
		ArrayList<FechasDTO> fechasUpdate = new ArrayList<FechasDTO>();
		int idFecha = Integer.parseInt(request.getParameter("idFecha"));
		
		for(EspectaculoMultDTO e : multiples)
		{
			if(id == e.getID())
			{
				fechasUpdate = e.getFechas();
			}
		}
		
		for(FechasDTO f : fechasUpdate)
		{
			if(idFecha == f.getID())
			{
				String fechaStr = request.getParameter("fecha");
				fechaStr = fechaStr.replace('T', ' ');
				fechaStr = fechaStr + ":00.0";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime Fecha = LocalDateTime.parse(fechaStr, formatter);
				
				FechasDTO newFecha = new FechasDTO(idFecha, Fecha);
				multDAO.updateFecha(newFecha, id);
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