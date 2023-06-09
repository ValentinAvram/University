package business;

import java.util.ArrayList;

/**
 * Clase que implementa un Espectaculo Multiple y todas sus variables
 * @author Developers
 */

public class EspectaculoMultDTO extends EspectaculoDTO{
	
		/*
		 * Array List de las fechas almacenadas para el espectaculo 
		 */
		private ArrayList<FechasDTO> fechas;
		
		 /* Constructor sin parametros. Usado para inicializacion de variables
		 * @author Developers
		 */
		public EspectaculoMultDTO() {
			
		}
		
		/*Constructor parametrizado de la clase.
		 * @param id Identificador del espectaculo
		 * @param title Titulo de la critica
		 * @param categoria Categoria del espectaculo
		 * @param descripcion Descripcion del espectaculo
		 * @param localidades_venta Localidades totales del espectaculo
		 * @param localidades_vendidas Localidades ya vendidas del espectaculo
		 * @param fechas ArrayList que almacena todas las posibles fechas del espectaculo
		 * @author Developers
		 */
		public EspectaculoMultDTO(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendida, ArrayList<FechasDTO> fechas) {
			super(id,titulo,categoria,descripcion, localidades_venta, localidades_vendida); 
			this.fechas = fechas;
		}
		
		/*Constructor parametrizado de la clase.
		 * @param id Identificador del espectaculo
		 * @param title Titulo de la critica
		 * @param categoria Categoria del espectaculo
		 * @param descripcion Descripcion del espectaculo
		 * @param localidades_venta Localidades totales del espectaculo
		 * @param localidades_vendidas Localidades ya vendidas del espectaculo
		 * @author Developers
		 */
		public EspectaculoMultDTO(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas) 
		{
			super(id,titulo,categoria,descripcion, localidades_venta, localidades_vendidas); 
		}
		
		/*Getter de las fechas almacenadas para el espectaculo
		 * @return fechas ArrayList con las fechas almacenadas para el espectaculo 
		 * @author Developers
		 */
		public ArrayList<FechasDTO> getFechas() {
			return this.fechas;
		}
		
		/*Setter de las fechas almacenadas en el vector
		 * @param fechas ArrayList de fechas almacenadas para el vector
		 * @author Developers
		 */
		public void setFechas(ArrayList<FechasDTO> fechas) {
			this.fechas = fechas;
		}
		
}