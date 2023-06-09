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

@WebServlet(name="NewTempServlet", urlPatterns="/NewTempServlet")

public class NewTempServlet extends HttpServlet 
{
	private static final long serialVersionUID = -1290846862882680952L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO tempDAO = new EspectaculoDAO(urlBD,userBD,passBD);
		
		ArrayList<PasesDTO> pases = new ArrayList<PasesDTO>();
		
		int numPases = Integer.parseInt(request.getParameter("number"));
		int ID = tempDAO.generarIDTemp();
		String title = request.getParameter("titulo");
		String desc = request.getParameter("descripcion");
		String local = request.getParameter("locVenta");
		String categ = request.getParameter("categoria");
		int Localidades = Integer.parseInt(local);
		int count = 0;

		for(int i = 1; i <= numPases; i++)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			String fechai = request.getParameter("fechai"+i);
			fechai = fechai.replace('T', ' ');
			fechai = fechai + ":00.0";
			LocalDateTime FechaInicio = LocalDateTime.parse(fechai, formatter);			
			
			String fechaf = request.getParameter("fechaf"+i);
			fechaf = fechaf.replace('T', ' ');
			fechaf = fechaf + ":00.0";
			LocalDateTime FechaFinal = LocalDateTime.parse(fechaf, formatter);
			
			String diaSemana = request.getParameter("diasemana"+i);
			
			if(FechaInicio.isAfter(FechaFinal))
			{
				count ++;
			}
			else
			{
				int idp = tempDAO.generarIdPases();
				PasesDTO newPase = new PasesDTO(idp, FechaInicio, diaSemana ,FechaFinal);
				pases.add(newPase);
			}

		}
		
		EspectaculoTempDTO newTemp = new EspectaculoTempDTO(ID, title, categ, desc, Localidades, 0, pases);
		tempDAO.createEspectaculoTemporada(newTemp);
		
		for(PasesDTO p : pases)
		{
			tempDAO.createPase(p, ID);
		}
		System.out.println("CONTADOR : " + count);
		String ruta = request.getContextPath() + "/P3-Alt/views/EspMenu/createEspAdmin.jsp";
		tempDAO.desEspectaculoDAO();
		if(count>0) 
		{
			out.println("<script type=\"text/javascript\">");
            out.println("alert('Uno o más pases no se han creado. La fecha de inicio no puede ser posterior a la fecha de finalización');");
            out.println("window.location.href =" + "'" + ruta + "'" + ";" );
            out.println("</script>");
		}
		else
		{
			out.println("<script type=\"text/javascript\">");
            out.println("alert('Espectaculo creado correctamente.');");
            out.println("window.location.href =" + "'" + ruta + "'" + ";" );
            out.println("</script>");
		}
	}
}