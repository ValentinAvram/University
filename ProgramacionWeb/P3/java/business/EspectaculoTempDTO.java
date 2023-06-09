package business;

import java.util.ArrayList;

/*
 * Clase que implementa un Espectaculo de Temporada y sus respectivas variables
 * @author Developers
 */
public class EspectaculoTempDTO extends EspectaculoDTO{
	
	/*
	 *  Pases en los que se da el espectaculo
	 */
	private ArrayList<PasesDTO> pases;
	
	/* Constructor sin parametros. Usado para inicializacion de variables
	 * @author Developers
	 */
	public EspectaculoTempDTO() {
		
	}
	
	/*Constructor parametrizado de la clase.
	 * @param id Identificador del espectaculo
	 * @param title Titulo de la critica
	 * @param categoria Categoria del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param localidades_venta Localidades totales del espectaculo
	 * @param localidades_vendidas Localidades ya vendidas del espectaculo
	 * @param pases Pases en las que se da el espectaculo
	 * @author Developers
	 */
	public EspectaculoTempDTO(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,ArrayList<PasesDTO> pases ) 
	{
		super(id,titulo,categoria,descripcion, localidades_venta, localidades_vendidas); 
		this.pases = pases; 
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
	public EspectaculoTempDTO(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas) 
	{
		super(id,titulo,categoria,descripcion, localidades_venta, localidades_vendidas); 
	}

	/*Getter de los pases del espectaculo
	 * @return pases Array List de pases asociados al espectaculo
	 * @author Developers
	 */
	public ArrayList<PasesDTO> getPases() {
		return this.pases;
	}
	
	/*Setter de los pases del espectaculo
	 * @param listPases Array List de pases asociados al espectaculo
	 * @author Developers
	 */
	public void setPases(ArrayList<PasesDTO> listPases) {
		this.pases = listPases;
	}
}