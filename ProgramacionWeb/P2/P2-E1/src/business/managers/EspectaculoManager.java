package business.managers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import business.DTOs.EspectaculoMultDTO;
import business.DTOs.EspectaculoPuntDTO;
import business.DTOs.EspectaculoTempDTO;
import business.DTOs.FechasDTO;
import business.DTOs.PasesDTO;
import data.DAOs.EspectaculoDAO;

/*
 * Clase que implementa las llamadas al DAO de espectaculos 
 * para su posterior uso en los menus (views)
 * @author Developers
 */
public class EspectaculoManager
{
	/* Funcion que llama al DAO para comprobar si un ID de un 
	 * Espectaculo Puntual existe
	 * @param id Identificador del Espectaculo
	 * @return true si existe
	 * @return false si no existe
	 * @author Developers
	 */
	
	public Boolean PuntExist(int id) {
		ArrayList<EspectaculoPuntDTO> Punt = this.requestEPs();
		for(EspectaculoPuntDTO u : Punt) {
			if(u.getID() == id) {
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion que llama al DAO para comprobar si un ID de un 
	 * Espectaculo Multiple existe
	 * @param id Identificador del Espectaculo
	 * @return true si existe
	 * @return false si no existe
	 * @author Developers
	 */
	
	public Boolean MultExist(int id) {
		ArrayList<EspectaculoMultDTO> Mult = this.requestEMs();
		for(EspectaculoMultDTO u : Mult) {
			if(u.getID() == id) {
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion que llama al DAO para comprobar si un ID de un 
	 * Espectaculo de Temporada existe
	 * @param id Identificador del Espectaculo
	 * @return true si existe
	 * @return false si no existe
	 * @author Developers
	 */
	
	public Boolean TempExist(int id) {
		ArrayList<EspectaculoTempDTO> Criticas = this.requestETs();
		for(EspectaculoTempDTO u : Criticas) {
			if(u.getID() == id) 
			{
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion que llama al DAO para comprobar si un ID de un Pase existe
	 * @param id Identificador del Espectaculo
	 * @return true si existe
	 * @return false si no existe
	 * @author Developers
	 */
	
	public Boolean paseExist(int id) {
		ArrayList<PasesDTO> pases = this.requestPases();
		for(PasesDTO u : pases) {
			if(u.getID() == id) 
			{
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion que llama al DAO para comprobar si un ID de una fecha existe
	 * @param id Identificador del Espectaculo
	 * @return true si existe
	 * @return false si no existe
	 * @author Developers
	 */
	
	public Boolean fechaExist(int id) {
		ArrayList<FechasDTO> fechas = this.requestFechas();
		for(FechasDTO u : fechas) {
			if(u.getID() == id) 
			{
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion recursiva que llama al DAO para generar un ID unico
	 * para un Espectaculo Puntal
	 * @return id ID generado
	 * @author Developers
	 */
	
	public int generarIDPunt()
	{
		EspectaculoDAO puntDAO = new EspectaculoDAO();
		int id = puntDAO.generarIDPunt();
		
		return id;
	}
	
	/* Funcion recursiva que llama al DAO para generar un ID unico
	 * para un Espectaculo Multiple
	 * @return id ID generado
	 * @author Developers
	 */
	
	public int generarIDMult()
	{
		EspectaculoDAO puntDAO = new EspectaculoDAO();
		int id = puntDAO.generarIDMult();
		
		return id;
	}
	
	/* Funcion recursiva que llama al DAO para generar un ID unico
	 * para un Espectaculo de Temporada
	 * @return id ID generado
	 * @author Developers
	 */
	
	public int generarIDTemp()
	{
		EspectaculoDAO puntDAO = new EspectaculoDAO();
		int id = puntDAO.generarIDTemp();
		
		return id;
	}
	
	/* Funcion recursiva que llama al DAO para generar un ID unico
	 * para una fecha
	 * @return id ID generado
	 * @author Developers
	 */
	
	public int generarIDFechas()
	{
		EspectaculoDAO puntDAO = new EspectaculoDAO();
		int id = puntDAO.generarIDFecha();
		
		return id;
	}
	
	/* Funcion recursiva que llama al DAO para generar un ID unico
	 * para un pase
	 * @return id ID generado
	 * @author Developers
	 */
	
	public int generarIDPases()
	{
		EspectaculoDAO puntDAO = new EspectaculoDAO();
		int id = puntDAO.generarIdPases();
		return id;
	}
	
	/* Funcion que llama al DAO para crear un nuevo Espectaculo Puntual
	 * @param id ID del nuevo espectaculo
	 * @param titulo Titulo del nuevo espectaculo
	 * @param categoria Categoria del nuevo espectaculo
	 * @param localidades_venta Localidades totales del nuevo espectaculo
	 * @param localidades_vendidas Localidades vendidas del nuevo espectaculo
	 * param fecha Fecha del nuevo espectaculo
	 * @return true si se puede crear el Espectaculo
	 * @return false si no se puede crear el Espectaculo
	 * @author Developers
	 */
	
	public Boolean createEspectaculoPunt(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,LocalDateTime fecha) {
		EspectaculoDAO newPuntDAO = new EspectaculoDAO();
		if(!this.PuntExist(id)) 
		{
			EspectaculoPuntDTO newPuntDTO = new EspectaculoPuntDTO(id, titulo, categoria, descripcion, localidades_venta, localidades_vendidas, fecha);
			newPuntDAO.createEspectaculoPuntual(newPuntDTO);
			return true;
		}
		return false;
	}
	
	/* Funcion que llama al DAO para actualizar un Espectaculo Puntual
	 * @param id ID del nuevo espectaculo
	 * @param titulo Titulo del nuevo espectaculo
	 * @param categoria Categoria del nuevo espectaculo
	 * @param localidades_venta Localidades totales del nuevo espectaculo
	 * @param localidades_vendidas Localidades vendidas del nuevo espectaculo
	 * param fecha Fecha del nuevo espectaculo
	 * @return true si se puede actualizar el Espectaculo
	 * @return false si no se puede actualizar el Espectaculo
	 * @author Developers
	 */
	
	public Boolean updateEspectaculoPunt(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,LocalDateTime fecha) {
			if(this.PuntExist(id)) 
			{
				EspectaculoDAO newEspectaculo = new EspectaculoDAO();			
				EspectaculoPuntDTO newPuntDTO = new EspectaculoPuntDTO(id, titulo, categoria, descripcion, localidades_venta, localidades_vendidas, fecha);  
				newEspectaculo.updateEspectaculoPuntual(newPuntDTO);
				return true;		
		}
		return false;
	}
	
	/* Funcion que llama al DAO para borrar un Espectualo Puntual concreto 
	 * @param id ID del espectaculo a borrar
	 * @return true si el ID existe 
	 * @return false si el ID no existe
	 * @author Developers
	 */
	
	public Boolean deleteEspectaculoPunt(int id) {
		if(this.PuntExist(id)) 
		{
			EspectaculoDAO deletePunt = new EspectaculoDAO();
			deletePunt.deleteEspectaculoPuntual(id);
			return true;				
		}	
		return false;
	}
	
	/* Funcion que llama al DAO para devolver un Espectaculo Puntual concreto
	 * @param id ID del Espectaculo concreto
	 * @return requestedPunt DTO de Espectaculo Puntual con los datos del espectaculo requerido
	 * @author Developers
	 * 
	 */
	
	public EspectaculoPuntDTO requestEspectaculoPunt(int id) 
	{
		EspectaculoDAO requestPunt = new EspectaculoDAO();
		EspectaculoPuntDTO requestedPunt = new EspectaculoPuntDTO();
		requestedPunt = requestPunt.requestEspectaculoPuntual(id);
		return requestedPunt;
	}
	
	/* Funcion que llama al DAO para devolver todos los Espectaculos puntuales
	 * @return requestedPunt Vector de DTO de Espectaculo Puntual con los datos de cada espectaculo
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoPuntDTO> requestEPs()
	{
		EspectaculoDAO requestPunt = new EspectaculoDAO();
		ArrayList<EspectaculoPuntDTO> requestedPunt = new ArrayList<EspectaculoPuntDTO>();
		requestedPunt = requestPunt.requestEPs();
		return requestedPunt;
	}
	
	/* Funcion que llama al DAO para crear un nuevo Espectaculo Multiple
	 * @param id ID del nuevo espectaculo
	 * @param titulo Titulo del nuevo espectaculo
	 * @param categoria Categoria del nuevo espectaculo
	 * @param localidades_venta Localidades totales del nuevo espectaculo
	 * @param localidades_vendidas Localidades vendidas del nuevo espectaculo
	 * param fecha Vector con las fechas del nuevo espectaculo
	 * @return true si se puede crear el Espectaculo
	 * @return false si no se puede crear el Espectaculo
	 * @author Developers
	 */
	
	public Boolean createEspectaculoMult(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas, ArrayList<FechasDTO> fechas) {
		EspectaculoDAO newMultDAO = new EspectaculoDAO();
		if(!this.MultExist(id)) 
		{
			EspectaculoMultDTO newMultDTO = new EspectaculoMultDTO(id, titulo, categoria, descripcion, localidades_venta, localidades_vendidas, fechas);  
			newMultDAO.createEspectaculoMultiple(newMultDTO);	
			return true;
		}
		return false;
	}
	
	/* Funcion que llama al DAO para actualizar un nuevo Espectaculo Multiple
	 * @param id ID del nuevo espectaculo
	 * @param titulo Titulo del nuevo espectaculo
	 * @param categoria Categoria del nuevo espectaculo
	 * @param localidades_venta Localidades totales del nuevo espectaculo
	 * @param localidades_vendidas Localidades vendidas del nuevo espectaculo
	 * param fecha Vector con las fechas del nuevo espectaculo
	 * @return true si se puede crear el Espectaculo
	 * @return false si no se puede crear el Espectaculo
	 * @author Developers
	 */
	
	public Boolean updateEspectaculoMultiple(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,ArrayList<FechasDTO> fechas) {
			if(this.MultExist(id)) 
			{
				EspectaculoDAO newEspectaculo = new EspectaculoDAO();			
				EspectaculoMultDTO newMultDTO = new EspectaculoMultDTO(id, titulo, categoria, descripcion, localidades_venta, localidades_vendidas, fechas);  
				newEspectaculo.updateEspectaculoMultiple(newMultDTO);
				return true;		
		}
		return false;
	}
	
	/* Funcion que llama al DAO para borrar un Espectualo Multiple concreto 
	 * @param id ID del espectaculo a borrar
	 * @return true si el ID existe 
	 * @return false si el ID no existe
	 * @author Developers
	 */
	
	public Boolean deleteEspectaculoMultiple(int id) {
		if(this.MultExist(id)) 
		{
			EspectaculoDAO deleteMult = new EspectaculoDAO();
			deleteMult.deleteEspectaculoMultiple(id);
			return true;				
		}	
		return false;
	}
	
	/* Funcion que llama al DAO para devolver un Espectaculo Multiple concreto
	 * @param id ID del Espectaculo concreto
	 * @return requestedMult DTO de Espectaculo Multiple con los datos del espectaculo requerido
	 * @author Developers
	 */
	
	public EspectaculoMultDTO requestEspectaculoMultiple(int id) 
	{
		EspectaculoDAO requestMult = new EspectaculoDAO();
		EspectaculoMultDTO requestedMult = new EspectaculoMultDTO();
		requestedMult = requestMult.requestEspectaculoMultiple(id);
		return requestedMult;
	}
	
	/* Funcion que llama al DAO para devolver todos los Espectaculos Multiples
	 * @return requestedMult Vector de DTO de Espectaculo Multiple con los datos de cada espectaculo
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoMultDTO> requestEMs()
	{
		EspectaculoDAO requestMult = new EspectaculoDAO();
		ArrayList<EspectaculoMultDTO> requestedMult = new ArrayList<EspectaculoMultDTO>();
		requestedMult = requestMult.requestEMs();
		return requestedMult;
	}
	
	/* Funcion que llama al DAO para devolver todas las fechas
	 * @return requestedFechas Vector de Fechas con todas las fechas del sistema
	 * @author Developers
	 */
	
	public ArrayList<FechasDTO> requestFechas()
	{
		EspectaculoDAO requestFechas = new EspectaculoDAO();
		ArrayList<FechasDTO> requestedFechas = new ArrayList<FechasDTO>();
		requestedFechas = requestFechas.requestFechas();
		return requestedFechas;
	}
	
	/* Funcion que llama al DAO para devolver todos los pases
	 * @return requestedPases Vector de pases con todos los pases del sistema
	 * @author Developers
	 */
	
	public ArrayList<PasesDTO> requestPases()
	{
		EspectaculoDAO requestPases = new EspectaculoDAO();
		ArrayList<PasesDTO> requestedPases = new ArrayList<PasesDTO>();
		requestedPases = requestPases.requestPases();
		return requestedPases;
	}
	
	/* Funcion que llama al DAO para crear un nuevo Espectaculo de Temporada
	 * @param id ID del nuevo espectaculo
	 * @param titulo Titulo del nuevo espectaculo
	 * @param categoria Categoria del nuevo espectaculo
	 * @param localidades_venta Localidades totales del nuevo espectaculo
	 * @param localidades_vendidas Localidades vendidas del nuevo espectaculo
	 * param pases Vector con los pases del nuevo espectaculo
	 * @return true si se puede crear el Espectaculo
	 * @return false si no se puede crear el Espectaculo
	 * @author Developers
	 */
	
	public Boolean createEspectaculoTemp(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,ArrayList<PasesDTO> pases) {
		EspectaculoDAO newTempDAO = new EspectaculoDAO();
			EspectaculoTempDTO newTempDTO = new EspectaculoTempDTO(id, titulo, categoria, descripcion, localidades_venta, localidades_vendidas,pases);  
			newTempDAO.createEspectaculoTemporada(newTempDTO);	
			return true;
	}
	
	/* Funcion que llama al DAO para actualizar un nuevo Espectaculo de Temporada
	 * @param id ID del nuevo espectaculo
	 * @param titulo Titulo del nuevo espectaculo
	 * @param categoria Categoria del nuevo espectaculo
	 * @param localidades_venta Localidades totales del nuevo espectaculo
	 * @param localidades_vendidas Localidades vendidas del nuevo espectaculo
	 * param pases Vector con los pases del nuevo espectaculo
	 * @return true si se puede actualizar el Espectaculo
	 * @return false si no se puede actualizar el Espectaculo
	 * @author Developers
	 */
	
	public Boolean updateEspectaculoTemp(int id, String titulo, String categoria,String descripcion, int localidades_venta, int localidades_vendidas,ArrayList<PasesDTO> pases) {
			if(this.TempExist(id)) 
			{
				
				EspectaculoDAO newEspectaculo = new EspectaculoDAO();			
				EspectaculoTempDTO newTempDTO = new EspectaculoTempDTO(id, titulo, categoria, descripcion, localidades_venta, localidades_vendidas, pases);  
				newEspectaculo.updateEspectaculoTemporada(newTempDTO);
				return true;		
		}
		return false;
	}
	
	/* Funcion que llama al DAO para borrar un Espectualo de Temporada concreto 
	 * @param id ID del espectaculo a borrar
	 * @return true si el ID existe 
	 * @return false si el ID no existe
	 * @author Developers
	 */
	
	public Boolean deleteEspectaculoTemp(int id) {
		if(this.TempExist(id)) 
		{
			EspectaculoDAO deleteTemp = new EspectaculoDAO();
			deleteTemp.deleteEspectaculoTemporada(id);
			return true;				
		}	
		return false;
	}
	
	/* Funcion que llama al DAO para devolver un Espectaculo de Temporada concreto
	 * @param id ID del Espectaculo concreto
	 * @return requestedTemp DTO de Espectaculo Temporada con los datos del espectaculo requerido
	 * @author Developers
	 */
	
	public EspectaculoTempDTO requestEspectaculoTemporada(int id) 
	{
		EspectaculoDAO requestTemp = new EspectaculoDAO();
		EspectaculoTempDTO requestedTemp = new EspectaculoTempDTO();
		requestedTemp = requestTemp.requestEspectaculoTemporada(id);
		return requestedTemp;
	}
	
	/* Funcion que llama al DAO para devolver todos los Espectaculos de Temporada
	 * @param id ID del Espectaculo concreto
	 * @return requestedTemp DTO de Espectaculo Temporada con los datos del espectaculo requerido
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoTempDTO> requestETs()
	{
		EspectaculoDAO requestTemp = new EspectaculoDAO();
		ArrayList<EspectaculoTempDTO> requestedTemp = new ArrayList<EspectaculoTempDTO>();
		requestedTemp = requestTemp.requestETs();
		return requestedTemp;
	}
	
	/* Funcion que llama al DAO para comprobar si un Espectaculo Multiple concreto ya tiene
	 * al menos una fecha terminada
	 * @param espect DTO del Espectaculo a comprobar
	 * @return true si tiene alguna fecha ya terminada
	 * @return false si no tienen ninguna fecha terminada
	 * @author Developers
	 */
	
	public boolean sesionTerminadaMult(EspectaculoMultDTO espect)
	{
		for(int i=0; i<espect.getFechas().size(); i++)
		{
			if(espect.getFechas().get(i).getFecha().isBefore(LocalDateTime.now()))
			{
				return true; 
			}
		}
		return false; 
	}
	
	/* Funcion que llama al DAO para comprobar si un Espectaculo de Temporada concreto ya tiene
	 * al menos un pase terminado
	 * @param espect DTO del Espectaculo a comprobar
	 * @return true si tiene algun pase ya terminado
	 * @return false si no tienen ningun pase terminado
	 * @author Developers
	 */
	
	public boolean sesionTerminadaTemp(EspectaculoTempDTO espect)
	{
		for(int i=0; i<espect.getPases().size(); i++)
		{
			if(espect.getPases().get(i).getFechaInicio().isBefore(LocalDateTime.now()))
			{
				return true; 
			}
		}
		return false; 
	}
	
	/* Funcion que llama al DAO para crear una nueva fecha asociada a un espectaculo Multiple
	 * @param id ID de la fecha a crear
	 * @param fecha Fecha del espectaculo
	 * @para idE ID del espectaculo al que se asocia la fecha
	 * @return true si se puede crear la fechas
	 * @return false si no se puede crear
	 * @author Developers
	 */
	
	public Boolean createFecha(int id, LocalDateTime fecha, int idE) 
	{
		EspectaculoDAO newPuntDAO = new EspectaculoDAO();
		if(!this.MultExist(idE)) 
		{
			FechasDTO newFecha = new FechasDTO(id, fecha);
			newPuntDAO.createFecha(newFecha, idE);
			return true;
		}
		return false;
	}
	
	/* Funcion que llama al DAO para crear un nuevo pase asociado a un espectaculo de Temporada
	 * @param id ID del pase a crear
	 * @param fechaInicio Fecha inicial del pase
	 * @param diaSemana Dia se la semana en el que se repite el espectaculo
	 * @param fechaFinal Fecha final del pase
	 * @para idE ID del espectaculo al que se asocia la fecha
	 * @return true si se puede crear la fechas
	 * @return false si no se puede crear
	 * @author Developers
	 */
	
	public Boolean createPase(int id, LocalDateTime fechaInicio, String diaSemana, LocalDateTime fechaFinal, int idE) 
	{
		EspectaculoDAO newPuntDAO = new EspectaculoDAO();
		if(!this.TempExist(idE)) 
		{
			PasesDTO newPase = new PasesDTO(id, fechaInicio, diaSemana, fechaFinal);
			newPuntDAO.createPase(newPase, idE);
			return true;
		}
		return false;
	}
	
	/* Funcion que llama al DAO para obtener los datos de una fecha concreta
	 * @param id ID de la fecha concreta
	 * @return requestedFecha DTO de fecha con los datos
	 * @author Developers
	 */
	
	public FechasDTO requestFecha(int id) 
	{
		EspectaculoDAO requestFecha = new EspectaculoDAO();
		FechasDTO requestedFecha = new FechasDTO();
		requestedFecha = requestFecha.requestFecha(id);
		return requestedFecha;
	}
	
	/* Funcion que llama al DAO para obetener los datos de un pase concreto
	 * @param id ID del pase concreto
	 * @return requestedPase DTO de pase con los datos
	 * @author Developers
	 */
	
	public PasesDTO requestPase(int id) 
	{
		EspectaculoDAO requestPase = new EspectaculoDAO();
		PasesDTO requestedPase = new PasesDTO();
		requestedPase = requestPase.requestPase(id);
		return requestedPase;
	}
	
	/* Funcion que llama al DAO para actualizar los datos de una fecha
	 * @param id ID de la fecha a actualizar
	 * @param fecha Nueva fecha 
	 * @param idE ID del espectaculo al que se asocia la fecha
	 * @return true si se puede actualizar la fecha
	 * @return false si no existe la fecha
	 * @author Developers
	 */
	
	public Boolean updateFechas(int id, LocalDateTime fechas, int idE) {
		if(this.fechaExist(id)) 
		{
			EspectaculoDAO newEspectaculo = new EspectaculoDAO();			
			FechasDTO newFechasDTO = new FechasDTO(id, fechas);  
			newEspectaculo.updateFecha(newFechasDTO, idE);
			return true;		
		}
		return false;
	}
	
	/* Funcion que llama al DAO para actualizar los datos de un pase
	 * @param id ID del pasea actualizar
	 * @param fechaInicio Nueva fecha incial del pase
	 * @param diaSemana Nuevo dia de la Semana 
	 * @param fechaFinal Nueva fecha final del pase
	 * @param idE ID del espectaculo al que se asocia el pase
	 * @return true si se puede actualizar el pase
	 * @return false si no existe el pase
	 * @author Developers
	 */
	
	public Boolean updatePases(int id, LocalDateTime fechaInicio, String diaSemana, LocalDateTime fechaFinal, int idE) {
		if(this.fechaExist(id)) 
		{
			EspectaculoDAO newEspectaculo = new EspectaculoDAO();			
			PasesDTO newPasesDTO = new PasesDTO(id, fechaInicio, diaSemana, fechaFinal);  
			newEspectaculo.updatePase(newPasesDTO, idE);
			return true;		
		}
		return false;
	}

}
