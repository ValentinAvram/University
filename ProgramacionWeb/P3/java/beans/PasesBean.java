package beans;

import java.time.LocalDateTime;

public class PasesBean implements java.io.Serializable 
{
	private static final long serialVersionUID = -9076744544153726044L;

	private int id = 0;
	private LocalDateTime fechaInicio;
	private String diaSemana = "";
	private LocalDateTime fechaFinal;
	
	public int getId()
	{
		return id;
	}
	
	public LocalDateTime getFechaInicio()
	{
		return fechaInicio;
	}
	
	public String getDiaSemana()
	{
		return diaSemana;
	}
	
	public LocalDateTime getFechaFinal()
	{
		return fechaFinal;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setFechaInicio(LocalDateTime fechaInicio)
	{
		this.fechaInicio = fechaInicio;
	}
	
	public void setDiaSemana(String diaSemana)
	{
		this.diaSemana = diaSemana;
	}
	
	public void setFechaFinal(LocalDateTime fechaFinal)
	{
		this.fechaFinal = fechaFinal;
	}

}