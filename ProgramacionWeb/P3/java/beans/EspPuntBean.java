package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;

import business.EspectaculoPuntDTO;

public class EspPuntBean implements java.io.Serializable {

	private static final long serialVersionUID = 5259493186386143210L;
	
	private int id = 0;
	private String categoria = ""; 
	private String titulo ="";  
	private String descripcion = ""; 
	private int localidades_venta = 0; 
	private int localidades_vendidas = 0; 
	private LocalDateTime horaFecha;
	private ArrayList<Integer> criticas;
	private ArrayList<EspectaculoPuntDTO> espsPunt;

	public int getId()
	{
		return id;
	}
	
	public String getCategoria()
	{
		return categoria;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}

	public int getLocalidadesVenta()
	{
		return localidades_venta;
	}
	
	public int getLocalidadesVendidas()
	{
		return localidades_vendidas;
	}
	
	public LocalDateTime getHoraFecha()
	{
		return horaFecha;
	}
	
	public ArrayList<Integer> getCriticas()
	{
		return this.criticas;
	}
	
	public ArrayList<EspectaculoPuntDTO> getEspectaculosPunt()
	{
		return this.espsPunt;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public void setLocalidadesVenta(int localidadesVenta)
	{
		this.localidades_venta = localidadesVenta;
	}
	
	public void setLocalidadesVendidas(int localidadesVendidas)
	{
		this.localidades_vendidas = localidadesVendidas;
	}
	
	public void setHoraFecha(LocalDateTime horaFecha)
	{
		this.horaFecha = horaFecha;
	}
	
	public void setCritica(ArrayList<Integer> c)
	{
		this.criticas = c;
	}
	
	public void setEspectaculosPunt(ArrayList<EspectaculoPuntDTO> esps)
	{
		this.espsPunt = esps;
	}
}