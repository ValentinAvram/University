package business.DTOs;

/*
 * Clase que implementa la asociacion entre votantes y la critica a la que vota, 
 * con sus respectivas variables
 * @author Developers
 */
public class VotantesCriticaDTO {
	
	/*
	 *  E-mail del votante
	 */
	private String votante;
	
	/*
	 * Identificador de la critica en el que vota el votante
	 */
	private int id;
	
	/*
	 * Voto dado al espectaculo por el votante
	 */
	private String voto;
	
	/*Getter del mail del votante
	 * @return votante email del votante
	 * @author Developers
	 */
	public String getVotante() {
		return votante;
	}
	/*Setter del mail del votante
	 * @param votante Mail del votante
	 * @author Developers
	 */
	public void setVotante(String votante) {
		this.votante = votante;
	}
	
	/*Getter del id del votante
	 * @return id Identificador de la critica
	 * @author Developers
	 */
	public int getId() {
		return id;
	}
	/*Setter del ID de la critica
	 * @param id Identificador de la critica
	 * @author Developers
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*Getter del voto del votante
	 * @return voto Voto dado a la critica
	 * @author Developers
	 */
	public String getVoto() {
		return voto;
	}
	/*Setter del Voto 
	 * @param voto Voto dado a la critica
	 * @author Developers
	 */
	public void setVoto(String voto) {
		this.voto = voto;
	}
}
