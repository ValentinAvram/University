package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="CreateEspServlet", urlPatterns="/CreateEspServlet")

public class CreateEspServlet extends HttpServlet 
{

	private static final long serialVersionUID = 2261076027687745273L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("tipo").equals("Puntual"))
		{
			response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/createEspPunt.jsp"); 
		}
		else if(request.getParameter("tipo").equals("Multiple"))
		{
			String num = request.getParameter("opcion");
			response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/createEspMult.jsp?number="+num);
		}
		else
		{
			String num = request.getParameter("opcion");
			response.sendRedirect(request.getContextPath() + "/P3-Alt/views/EspMenu/createEspTemp.jsp?number="+num);	
		}
		
	}
}