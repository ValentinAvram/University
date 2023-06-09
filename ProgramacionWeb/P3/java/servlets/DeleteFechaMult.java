package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name="DeleteFechaMult", urlPatterns="/DeleteFechaMult")

public class DeleteFechaMult extends HttpServlet 
{
	private static final long serialVersionUID = 1281705201806865442L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");

		EspectaculoDAO multDAO = new EspectaculoDAO(urlBD,userBD,passBD);
		ArrayList<EspectaculoMultDTO> multiples= multDAO.requestEMs();
		
		int id = Integer.parseInt(request.getParameter("idEsp"));
		int idFecha = Integer.parseInt(request.getParameter("idFecha"));
		ArrayList<FechasDTO> fechasUpdate = new ArrayList<FechasDTO>();
		
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
				multDAO.deleteFecha(f);
			}
		}
		
		String ruta = request.getContextPath() + "/GetMultTempDeleteServlet";
		multDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Fecha eliminada correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
}