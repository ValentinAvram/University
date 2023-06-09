package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.CustomerBean;
import business.CriticaDTO;
import javax.servlet.*;
import data.DAOs.CriticaDAO;

@WebServlet(name="NewCriticaServlet", urlPatterns="/NewCriticaServlet")

public class NewCriticaServlet extends HttpServlet 
{
	private static final long serialVersionUID = -4895013935062049884L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		
		CriticaDAO critDAO = new CriticaDAO(urlBD, userBD, passBD);
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		
		int ID = critDAO.generarIDCritica();
		String titulo = request.getParameter("titulo");
		String mail = customerBean.getEmailUser();
		String puntuacion = request.getParameter("puntuacion");
		float punt = Float.parseFloat(puntuacion);
		String resena = request.getParameter("resena");
		int IDEsp = Integer.parseInt(request.getParameter("idEsp"));

		CriticaDTO newCritica = new CriticaDTO(titulo, punt, resena, ID, mail, 0, 0, IDEsp);
		critDAO.createCritica(newCritica);

		String ruta = request.getContextPath() + "/GetAllEspsDoneServlet";
		critDAO.desCriticaDAO();
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Crítica creada correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
		
		
	}
}