package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import data.DAOs.EspectaculoDAO;

@WebServlet(name="DeleteEspectaculo", urlPatterns="/DeleteEspectaculo")

public class DeleteEspectaculo extends HttpServlet 
{
	private static final long serialVersionUID = 4348751409631585512L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		
		EspectaculoDAO espDAO = new EspectaculoDAO(urlBD, userBD, passBD);
		
		String idEsp= request.getParameter("idEsp");
		int id = Integer.parseInt(idEsp);
		String tipo = request.getParameter("tipo");
		
		if(tipo.equals("punt"))
		{
			espDAO.deleteEspectaculoPuntual(id);
		}
		else if(tipo.equals("mult"))
		{
			espDAO.deleteEspectaculoMultiple(id);
		}
		else
		{
			espDAO.deleteEspectaculoTemporada(id);
		}
			
		String ruta = request.getContextPath() + "/P3-Alt/views/EspMenu/homeEspAdmin.jsp";
		espDAO.desEspectaculoDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Espectáculo eliminado correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
}