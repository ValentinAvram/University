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

@WebServlet(name="NewMultServlet", urlPatterns="/NewMultServlet")

public class NewMultServlet extends HttpServlet 
{
	private static final long serialVersionUID = 6905725230597578589L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO multDAO = new EspectaculoDAO(urlBD,userBD,passBD);
		
		ArrayList<FechasDTO> fechas = new ArrayList<FechasDTO>();
		
		int numFechas = Integer.parseInt(request.getParameter("number"));
		int ID = multDAO.generarIDMult();
		String title = request.getParameter("titulo");
		String desc = request.getParameter("descripcion");
		String local = request.getParameter("locVenta");
		String categ = request.getParameter("categoria");
		int Localidades = Integer.parseInt(local);
		int Vendidas = 0;
		
		for(int i = 1; i <= numFechas ; i++)
		{
			String fechaStr = request.getParameter("fecha"+i);
			fechaStr = fechaStr.replace('T', ' ');
			fechaStr = fechaStr + ":00.0";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			LocalDateTime Fecha = LocalDateTime.parse(fechaStr, formatter);
			int idf = multDAO.generarIDMultFechas();
			FechasDTO fecha = new FechasDTO(idf, Fecha);
			
			fechas.add(fecha);
		}
		
		EspectaculoMultDTO newMult = new EspectaculoMultDTO(ID, title, categ, desc, Localidades, Vendidas, fechas);
		multDAO.createEspectaculoMultiple(newMult);
		
		for(FechasDTO f : fechas)
		{
			multDAO.createFecha(f, ID);
		}
		
		String ruta = request.getContextPath() + "/P3-Alt/views/EspMenu/createEspAdmin.jsp";
		multDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Espectaculo creado correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");		
	}
}