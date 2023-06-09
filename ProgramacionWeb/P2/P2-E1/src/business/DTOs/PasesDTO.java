package business.DTOs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que implementa un pase de fechas con sus variables privadas y metodos
 * @author Developers
 */

public class PasesDTO 
{
		/**
		 * Variable que almacena el identificador del pase especifico
		 */
		
		private int  id; 
		
		/**
		 * Varaible que almacena fecha y hora de comienzo del espectaculo
		 */
		
		private LocalDateTime fechaInicio;
		
		/**
		 * Varaible que almacena el dia y hora de la semana en la que se repetira el espectaculo a lo largo del pase
		 */
		
		private String diaSemana;
		
		/**
		 * Varaible que almacena fecha y hora de fin del espectaculo
		 */
		
		private LocalDateTime fechaFinal;
		
		public PasesDTO(){}
		
		/**
		 * Constructor parametrizado.
		 * @param id Nombre del usuario
		 * @param fechaInicio fecha y hora de comienzo del espectaculo
		 * @param fechaFinal fecha y hora de fin del espectaculo
		 * @author Developers
		 */
		
		public PasesDTO(int id, LocalDateTime fechaInicio, String diaSemana, LocalDateTime fechaFinal)
		{
			this.id=id; 
			this.fechaInicio = fechaInicio;
			this.diaSemana = diaSemana;
			this.fechaFinal = fechaFinal;
		}
		
		//OBSERVADORES
		
		/**
		 * Getter del identificador del pase
		 * @return Numero entero con el identificador del pase
		 * @author Developers
		 */
		
		public int getID()
		{
			return this.id; 
		}
		
		/**
		 * Getter de la fecha de incio
		 * @return Variable con la fecha y hora de comienzo del pase
		 * @author Developers
		 */
		
		public LocalDateTime getFechaInicio()
		{
			return this.fechaInicio; 
		}
		
		public String getFechaInicioString()
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
			String formattedDateTime = this.getFechaInicio().format(formatter);
			return formattedDateTime; 
		}
		
		/**
		 * Getter del dia de la semana en la que se repite el espectaculo
		 * @return Variable con el dia y hora de la semana que se repetira el espectaculo a lo largo del pase
		 * @author Developers
		 */
		
		public String getDiaSemana()
		{
			return this.diaSemana; 
		}
		
		/**
		 * Getter de la fecha de fin
		 * @return Variable con la fecha y hora de final del pase
		 * @author Developers
		 */
		
		public LocalDateTime getFechaFinal()
		{
			return this.fechaFinal; 
		}
		public String getFechaFinalString()
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
			String formattedDateTime = this.getFechaFinal().format(formatter);
			return formattedDateTime; 
		}
		
		//MODIFICADORES
		
		/**
		 * Setter del ID del pase
		 * @param id Identificador del pase
		 * @author Developers
		 */
		
		public void setID(int id)
		{
			this.id = id; 
		}
		
		/**
		 * Setter de la fecha de inicio del pase
		 * @param fecha Fecha y hora del inicio del pase
		 * @author Developers
		 */
		
		public void setFechaInicio(LocalDateTime fechaInicio)
		{
			this.fechaInicio = fechaInicio; 
		}
		
		/**
		 * Setter del dia de la semana del pase
		 * @param fecha Dia y hora de la semana en la que se repetira el espectaculo a lo largo del pase
		 * @author Developers
		 */
		
		public void setDiaSemana(String diaSemana)
		{
			this.diaSemana = diaSemana; 
		}
		
		/**
		 * Setter de la fecha final del pase
		 * @param fecha Fecha y hora del fin del pase
		 * @author Developers
		 */
		
		public void setFechaFinal(LocalDateTime fechaFinal)
		{
			this.fechaFinal = fechaFinal; 
		}
	}
