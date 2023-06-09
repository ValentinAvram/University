package beans;

public class VotantesCriticaBean implements java.io.Serializable 
{
	private static final long serialVersionUID = -6154647366277854569L;
	
	private String votante = "";
	private int id = 0;
	private String voto = "";
	
	public int getId()
	{
		return id;
	}
	
	public String getVotante()
	{
		return votante;
	}
	
	public String getVoto()
	{
		return voto;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setVotante(String votante)
	{
		this.votante = votante;
	}
	
	public void setVoto(String voto)
	{
		this.voto = voto;
	}
}