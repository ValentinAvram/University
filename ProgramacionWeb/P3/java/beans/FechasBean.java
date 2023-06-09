package beans;

import java.time.LocalDateTime;

public class FechasBean implements java.io.Serializable 
{
	private static final long serialVersionUID = -6569372407937313682L;

	private int id = 0;
	private LocalDateTime fecha;
	
	public int getId()
	{
		return id;
	}
	
	public LocalDateTime getFecha()
	{
		return fecha;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setFecha(LocalDateTime fecha)
	{
		this.fecha = fecha;
	}
}