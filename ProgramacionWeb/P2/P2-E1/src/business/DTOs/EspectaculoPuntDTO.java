package business.DTOs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que implementa un Espectaculo Puntual y sus respectivas variables.
 * @author Developers
 */
public class EspectaculoPuntDTO extends EspectaculoDTO {
	
	/*
	 * Fecha en la que se da el espectaculo
	 */
	private LocalDateTime horaFecha;
	
	/* Constructor sin parametros. Usado para inicializacion de variables
	 * @author Developers
	 */
	public EspectaculoPuntDTO() {
		
	}
	
	/*
	 * Constructor parametrizado de la clase.
	 * @param id Identificador del espectaculo
	 * @param title Titulo de la critica
	 * @param categoria Categoria del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param localidades_venta Localidades totales del espectaculo
	 * @param localidades_vendidas Localidades ya vendidas del espectaculo
	 * @param fecha fecha en la que se da el espectaculo
	 * @author Developers
	 */
public EspectaculoPuntDTO(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,LocalDateTime fecha) 
	{
		super(id, titulo, categoria,descripcion, localidades_venta, localidades_vendidas);
		this.horaFecha = fecha; 
	}
	
	/*Getter de la fecha del espectaculo
	 * @return horaFecha Fecha y hora a la que ocurre el espectaculo
	 * @author Developers
	 */
	public LocalDateTime getHoraFecha() {
		return this.horaFecha;
	}
	
	/*Getter de la fecha del espectaculo en formato String
	 * @return formattedDateTime Fecha y hora a la que ocurre el espectaculo, en formato de texto
	 * @author Developers
	 */
	public String getHoraFechaString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		String formattedDateTime = this.getHoraFecha().format(formatter);
		return formattedDateTime; 
	}
	
	/*Setter de la fecha del espectaculo
	 * @param horaFecha 
	 * @author Developers
	 */
	public void setHoraFecha(LocalDateTime horaFecha) {
		this.horaFecha = horaFecha;
	}
}