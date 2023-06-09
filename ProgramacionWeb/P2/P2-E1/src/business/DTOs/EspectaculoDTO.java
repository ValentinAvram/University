package business.DTOs;


import java.util.ArrayList;

/**
 * Clase que implementa un espectaculo abstracto, con sus variables 
 * privadas y metodos. Este Espectaculo es la base de los demas
 * tipos de espectaculos, siguiendo un patron Abstract Fabric
 * @author Developers
 *
 */

public class EspectaculoDTO {

	private int id;
	private String categoria; 
	private String titulo;  
	private String descripcion; 
	private int localidades_venta; 
	private int localidades_vendidas; 
	private ArrayList<Integer> criticas;
	
	/**
	 * Constructor sin parametros. Usado para inicializacion de variables.
	 */
	
		public EspectaculoDTO(){};
		
		/**
		 * Constructor parametrizado de la clase.
		 * @param id Identificador del espectaculo
		 * @param titulo Titulo del espectaculo
		 * @param categoria Categoria del espectaculo
		 * @param descripcion Breve descripcion del Espectaculo
		 * @author Developers
		 */

		public EspectaculoDTO(int id, String titulo, String categoria, String descripcion, int localidades_venta, int localidades_vendidas) 
		{
			this.id = id;
			this.titulo = titulo; 
			this.descripcion = descripcion;
			this.categoria=categoria; 
			this.localidades_venta = localidades_venta;
			this.localidades_vendidas = localidades_vendidas;
		}
		
		//OBSERVADORES
		/**
		 * Getter del id unico del espectaculo.
		 * @return ID del espectaculo
		 * @author Developers
		 */
		
		public int getID()
		{
			return this.id; 
		}
		
		/**
		 * Getter de la categoria.
		 * @return Categoria a la que pertenece el espectaculo
		 * @author Developers
		 */
		
		public String getCategoria()
		{
			return this.categoria; 
		}
		
		/**
		 * Getter del titulo.
		 * @return Titulo del espectaculo
		 * @author Developers
		 */
		
		public String getTitulo()
		{
			return this.titulo; 
		}
		
		/**
		 * Getter de la descripcion.
		 * @return Descripcion del espectaculo
		 * @author Developers
		 */
		
		public String getDescripcion()
		{
			return descripcion; 
		}
		
		/**
		 * Getter de la localidades totales.
		 * @return Numero de localidades totales
		 * @author Developers
		 */
		
		public int getLocalidadesVenta()
		{
			return this.localidades_venta; 
		}
		
		/**
		 * Getter de la localidades vendidas.
		 * @return Numero de localidades venidas
		 * @author Developers
		 */
		
		public int getLocalidadesVendidas()
		{
			return this.localidades_vendidas; 
		}
		
		/**
		 * Getter de las criticas asociadas al espectaculo.
		 * @return Vector de enteros, IDs de las criticas
		 * @author Developers
		 */
		
		public ArrayList<Integer> getCritica() {
			return this.criticas;
		}
		
		
		//MODIFICADORES
		
		/**
		 * Setter del ID del espectaculo
		 * @param id Identificador del espectaculo
		 * @author Developers
		 */
		
		public void setId(int id)
		{
			this.id=id; 
		}
		
		/**
		 * Setter de la categoria del espectaculo
		 * @param categoria Categoria del espectaculo
		 * @author Developers
		 */
		
		public void setCategoria(String categoria)
		{
			this.categoria=categoria; 
		}
		
		/**
		 * Setter del titulo del espectaculo
		 * @param titulo Titulo del espectaculo
		 * @author Developers
		 */
		
		public void setTitulo(String titulo)
		{
			this.titulo = titulo; 
		}
		
		/**
		 * Setter de la descripcion del espectaculo
		 * @param descripcion Descripcion del espectaculo
		 * @author Developers
		 */
		
		public void setDescripcion(String descripcion)
		{
			this.descripcion=descripcion; 
		}
		
		/**
		 * Setter del numero de localidades totales
		 * @param localidades_venta Numero total de localidades
		 * @author Developers
		 */
		
		public void setLocalidadesVenta(int localidades_venta)
		{
			this.localidades_venta=localidades_venta;
		}
		
		/**
		 * Setter del numero de localidades vendidas
		 * @param localidades_vendidas Numero de localidades vendidas
		 * @author Developers
		 */
		public void setLocalidadesVendidas(int localidades_vendidas)
		{
			this.localidades_vendidas=localidades_vendidas; 
		}
		
		/**
		 * Setter del vector de IDs de criticas asociadas al espectaculo
		 * @param c ArrayList de enteros que almacena IDs de criticas
		 * @author Developers
		 */
		
		public void setCritica(ArrayList<Integer> c) {
			this.criticas = c;
		}
		

		
}
