package beans;

import java.util.ArrayList;

import business.VotantesCriticaDTO;

public class CriticasBean implements java.io.Serializable 
{
	private static final long serialVersionUID = -1691006932988053186L;

	private String title = "";
	private float puntuacion = 0;
	private int id = 0;
	private String resena = "";
	private int like = 0;
	private int dislike = 0;
	private String mail = "";
	private int idEsp = 0;
	private ArrayList<VotantesCriticaDTO> votantes;
	
	public String getTitle()
	{
		return title;
	}
	
	public float getPuntuacion()
	{
		return puntuacion;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getResena()
	{
		return resena;
	}
	
	public int getLike()
	{
		return like;
	}
	
	public int getDislike()
	{
		return dislike;
	}
	
	public String getMail()
	{
		return mail;
	}
	
	public int getIdEsp()
	{
		return idEsp;
	}
	public ArrayList<VotantesCriticaDTO> getVotantesCritica()
	{
		return this.votantes;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	public void setPuntuacion(float puntuacion)
	{
		this.puntuacion = puntuacion;
	}
	
	public void setResena(String resena)
	{
		this.resena = resena;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setLike(int like)
	{
		this.like = like;
	}
	
	public void setDislike(int dislike)
	{
		this.dislike = dislike;
	}
	
	public void setIdEsp(int idEsp)
	{
		this.idEsp = idEsp;
	}
	
	public void setVotantesCritica (ArrayList<VotantesCriticaDTO> votantes)
	{
		this.votantes = votantes;
	}
}