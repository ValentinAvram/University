package business.managers;

import java.util.ArrayList;
import business.DTOs.CriticaDTO;
import business.DTOs.EspectaculoDTO;
import business.DTOs.VotantesCriticaDTO;
import data.DAOs.CriticaDAO;
	
/*
 * Clase que implementa las llamadas al DAO de criticas 
 * para su posterior uso en los menus (views)
 * @author Developers
 */
public class CriticaManager {
	private String mail;
	
	/* Setter del mail que accede al manager de Criticas
	 * @param mail Mail del votante
	 * @author Developers
	 */
	public CriticaManager(String mail) {
		this.mail = mail;
	}
	
	/* Getter del mail que accede al manager de Criticas
	 * @return mail Mail del votante
	 * @author Developers
	 */
	private String getMail() {
		return this.mail;
	}
	
	/*Llamada al DAO que pide todos los Espectaculos Puntuales con alguna fecha ya pasada
	 * @return espectaculos Vector de Espectaculos
	 * @author Developers
	 */
	public ArrayList<EspectaculoDTO> requestEspCriticablesPunt(){
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>();
		CriticaDAO requestEsp = new CriticaDAO();
		espectaculos = requestEsp.requestEspPuntPast();
		return espectaculos;
	}
	
	/*Llamada al DAO que pide todos los Espectaculos Multiples con alguna fecha ya pasada
	 * @return espectaculos Vector de Espectaculos
	 * @author Developers
	 */
	public ArrayList<EspectaculoDTO> requestEspCriticablesMult(){
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>();
		CriticaDAO requestEsp = new CriticaDAO();
		espectaculos = requestEsp.requestEspMultPast();
		return espectaculos;
	}
	
	/*Llamada al DAO que pide todos los Espectaculos de Temporada con alguna fecha ya pasada
	 * @return espectaculos Vector de Espectaculos
	 * @author Developers
	 */
	public ArrayList<EspectaculoDTO> requestEspCriticablesTemp(){
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>();
		CriticaDAO requestEsp = new CriticaDAO();
		espectaculos = requestEsp.requestEspTempPast();
		return espectaculos;
	}
	
	/*Llamada al DAO que pasa los datos de una nueva critica
	 * al DAO, quien crea la critica en la Base de Datos
	 * @param titulo Titulo de la Critica a crear
	 * @param puntuacion Puntuacion otorgada al espectaculo en la critica
	 * @param resena Resena del espectaculo en la critica
	 * @param idEsp ID del espectaculo al que se critica
	 * @return true
	 * @author Developers
	 */
	public Boolean createCritica(String titulo, float puntuacion, String resena,int idEsp) {
		CriticaDAO newCritica = new CriticaDAO();
		
			CriticaDTO newCriticaDTO = new CriticaDTO(titulo,puntuacion,resena,this.getMail(), idEsp);  
			newCritica.createCritica(newCriticaDTO);	
		return true;
	}
	
	/*Llamada al DAO que pasa el ID de una critica 
	 * al DAO, quien borra la critica de la Base de Datos
	 * @param id Identificador de la critica a borrar
	 * @return true / false
	 * @author Developers
	 */
	public Boolean deleteCritica(int id) {
		if(this.CriticaExist(id)) {
			CriticaDTO critica = this.requestCritica(id);
			if(critica.getMail().equals(this.getMail())) {
				CriticaDAO deleteCritica = new CriticaDAO();
				deleteCritica.deleteCritica(id);
				deleteCritica.removeVotantesCritica(id);
				return true;				
			}
		}	
		return false;
	}
	
	/*Llamada al DAO que pasa el ID de una critica 
	 * al DAO, quien devuelve la informacion de la critica
	 * con ese ID
	 * @return requestedCritica Critica especifica
	 * @author Developers
	 */
	public CriticaDTO requestCritica(int id) {
		CriticaDAO requestCritica = new CriticaDAO();
		CriticaDTO requestedCritica = new CriticaDTO();
		requestedCritica = requestCritica.requestCritica(id);
		return requestedCritica;
	}
	
	/*Llamada al DAO que pide todas las criticas almacenadas
	 * @return requestedCriticas Vector de Criticas
	 * @author Developers
	 */
	public ArrayList<CriticaDTO> requestCriticas(){
		CriticaDAO requestCritica = new CriticaDAO();
		ArrayList<CriticaDTO> requestedCriticas = new ArrayList<CriticaDTO>();
		requestedCriticas = requestCritica.requestCriticas();
		return requestedCriticas;
	}
	
	/*Llamada al DAO que pide todas las criticas almacenadas
	 * hechas por un usuario concreto
	 * @param mail Mail del usuario cuyas criticas buscamos
	 * @return requestedCriticas Vector de Criticas hechas por un usuario
	 * @author Developers
	 */
	public ArrayList<CriticaDTO> requestAllCriticasUser(String mail)
	{
		CriticaDAO requestCritica = new CriticaDAO();
		ArrayList<CriticaDTO> requestedCriticas = new ArrayList<CriticaDTO>();
		requestedCriticas = requestCritica.requestCriticasUser(mail); 
		return requestedCriticas;
	}
	
	/*Llamada al DAO que pide todas las criticas almacenadas,
	 * salvo las hechas por un usuario concreto
	 * @return requestedCriticas Vector de Criticas no hechas por el usuario
	 * @author Developers
	 */
	public ArrayList<CriticaDTO> requestCriticasExUser(){
		CriticaDAO requestCritica = new CriticaDAO();
		ArrayList<CriticaDTO> requestedCriticas = new ArrayList<CriticaDTO>();
		requestedCriticas = requestCritica.requestCriticasExUser(this.getMail());
		return requestedCriticas;
	}
	
	/*Llamada al DAO que pide confirmar si exista una critica 
	 * con un ID especifico
	 * @param id Identificador de la critica que se busca
	 * @return true si existe / false si no existe
	 * @author Developers
	 */
	public Boolean CriticaExist(int id) {
		ArrayList<CriticaDTO> Criticas = this.requestCriticas();
		for(CriticaDTO u : Criticas) {
			if(u.getId() == id) {
				return true;
			}
		}
		return false;	
	}
	
	/*Llamada al DAO que aumenta en uno el numero de likes de una critica
	 * @param id ID de la critica que votar
	 * @return true si se le ha dado like 
	 * @return false si no se ha podido dar like
	 * @author Developers
	 */
	
	public Boolean darLike(int id) {
		if(this.CriticaExist(id)) {
			CriticaDTO critica = this.requestCritica(id);
			CriticaDAO Like = new CriticaDAO();
			if(!this.getMail().equals(critica.getMail())) {
				for(VotantesCriticaDTO v : critica.getVotantes()) {
					if(v.getVotante().equals(this.getMail())) {
						if(v.getVoto().equals("like")) {
							return false;
						}
						else {
							critica.lessDislike();
							critica.addLike();
							Like.updateCritica(critica);
							Like.removeVotanteCritica(this.getMail(), id);
							Like.addVotanteCritica(this.getMail(), id, "like");
							return true;	
						}
					}
				}
				critica.addLike();
				Like.updateCritica(critica);
				Like.removeVotanteCritica(this.getMail(), id);
				Like.addVotanteCritica(this.getMail(), id, "like");
				return true;
			}				
		}	
		return false;
	}
	
	/*Llamada al DAO que aumenta en uno el numero de dislikes de una critica
	 * @param id ID de la critica que votar
	 * @return true si se le ha dado dislike 
	 * @return false si no se ha podido dar dislike
	 * @author Developers
	 */
	public Boolean darDislike(int id) {
		if(this.CriticaExist(id)) {
			CriticaDAO Like = new CriticaDAO();
			CriticaDTO critica = this.requestCritica(id);
			if(!this.getMail().equals(critica.getMail())) {
				for(VotantesCriticaDTO v : critica.getVotantes()) {
					if(v.getVotante().equals(this.getMail())) {
						if(v.getVoto().equals("dislike")) {
							return false;
						}
						else {
							critica.lessLike();
							critica.addDislike();
							Like.updateCritica(critica);
							Like.removeVotanteCritica(this.getMail(), id);
							Like.addVotanteCritica(this.getMail(), id, "dislike");
							return true; 
							
						}
					}
				}
				Like.updateCritica(critica);
				Like.removeVotanteCritica(this.getMail(), id);
				Like.addVotanteCritica(this.getMail(), id, "dislike");
				return true;	
			}				
		}	
		return false;	
	}
	
	/*Llamada al DAO que pide el titulo de un espectaculo 
	 * a partir de us ID
	 * @param id ID del espectaculo a buscar
	 * @return titulo Titulo del espectaculo
	 * @author Developers
	 */
	public String selectTituloEsp(int id) {
		CriticaDAO selectTituloEspectaculo = new CriticaDAO();
		String titulo = selectTituloEspectaculo.selectTituloEsp(id);
		return titulo;
	}
	/*Llamada al DAO que pide los mails de usuarios
	 * que hayan escrito una critica
	 * @return requestedWriters Vector con los mails de los usuarios
	 * @author Developers
	 */
	public ArrayList<String> requestWriters(){
		CriticaDAO requestW = new CriticaDAO();
		ArrayList<String> requestedWriters = new ArrayList<String>();
		requestedWriters = requestW.requestWriters();
		return requestedWriters;
	}
	
}
