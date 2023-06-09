package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.CriticaDTO;
import business.VotantesCriticaDTO;

import javax.servlet.*;
import data.DAOs.CriticaDAO;

@WebServlet(name="DislikeCriticaServlet", urlPatterns="/DislikeCriticaServlet")

public class DislikeCriticaServlet extends HttpServlet 
{
	private static final long serialVersionUID = 46658568493939722L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext app = getServletContext();
			
		String passBD = app.getInitParameter("password");
		String urlBD = app.getInitParameter("url");
		String userBD = app.getInitParameter("user");
		
		CriticaDAO critDAO = new CriticaDAO(urlBD, userBD, passBD);
		ArrayList<CriticaDTO> crits = critDAO.requestCriticas();
		
		String ID = request.getParameter("idCritica");
		String mailVot = request.getParameter("mailVot");
		int id = Integer.parseInt(ID);	
		String titulo = null, mail=null, resena=null;
		float punt=0;
		int like=0, dislike=0, idEsp=0;
		for(CriticaDTO c : crits)
		{
			if(c.getId() == id)
			{
				titulo = c.getTitle();
				mail = c.getMail();
				punt = c.getPuntuacion();
				resena = c.getResena();
				like = c.getLike();
				dislike = c.getDislike()+1;
				idEsp = c.getIdEsp();
				for(VotantesCriticaDTO cri : c.getVotantes())
				{
					if(cri.getVotante().equals(mailVot) && cri.getVoto().equals("like"))
					{
						like = like-1;
						critDAO.removeVotanteCritica(mailVot, id);
					}
				}
				critDAO.addVotanteCritica(mailVot, id, "dislike");
			}
		}
		CriticaDTO likeCrit = new CriticaDTO(titulo, punt, resena, id, mail, like, dislike, idEsp);
		critDAO.updateCritica(likeCrit);
		critDAO.desCriticaDAO();
		String ruta = request.getContextPath() + "/GetAllEspsDoneServlet";
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
        out.println("alert('Dislike añadido correctamente.');");
        out.println("window.location.href =" + "'" + ruta + "'" + ";" );
        out.println("</script>");	
	}
}