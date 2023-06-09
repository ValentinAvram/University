package data.DAOs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import business.DTOs.CriticaDTO;
import business.DTOs.EspectaculoDTO;
import business.DTOs.VotantesCriticaDTO;
import data.common.DBConnection;

/*
 * Clase que implementa las funciones relativas a la gestion de 
 * criticas con respecto a la Base de Datos
 * @author Developers
 */
public class CriticaDAO {


	
	String ruta = "./src/sql.properties";
	
	/*
	 * Funcion que busca y devuleve el titulo de un espectaculo
	 * almacenado en la base de datos
	 * @param id Identificador del espectaculo buscado
	 * @return titulo Titulo del espectaculo deseado
	 * @author Developers
	 */
	public String selectTituloEsp(int id) {
		String titulo=null;
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectTituloEsp");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if(rs.next())
			{
				titulo = rs.getString("titulo");
			}
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return titulo;
	}
	
	/*
	 * Funcion que busca una critica y comprueba si existe
	 * @param id Identificador del espectaculo buscado
	 * @return true si existe 
	 * @return false si no existe
	 * @author Developers
	 */
	private boolean existIdCritica(int id)
	{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDCriticas");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/*
	 * Funcion recursiva que genera un ID aleatorio para una critica,
	 * asegurandose que no no exista
	 * @return id Identificador aleatorio creado
	 * @author Developers
	 */
	public int generarIDCritica()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdCritica(id) == true)
		{
			generarIDCritica();
		}
		return id;
	}
	
	/*
	 * Funcion que busca un ID asociado a una votacion 
	 * y comprueba que exista
	 * @param id Identificador de la votacion buscada
	 * @return true si existe 
	 * @return false si no existe
	 * @author Developers
	 */

	private boolean existIdVC(int id)
	{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDVC");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("idvc"))==id)
				{
					return true; 
				}
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/*
	 * Funcion recursiva que genera un ID para un voto, 
	 * comprobando que no se exista ya
	 * @return id Identificador del voto
	 * @author Developers
	 */
	
	public int generarIdVC()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdVC(id) == true)
		{
			generarIDCritica();
		}
		return id;
	}
	
	/*
	 * Funcion que crea una critica a partir de los datos pasados por un DTO
	 * @param newCritica DTO de Critica con los datos provenientes del manager
	 * @author Developers
	 */
	
	public void createCritica(CriticaDTO newCritica)
	{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("createCritica");
			query=query.replaceAll("varid", Integer.toString(generarIDCritica()));
			query=query.replaceAll("vartitulo", newCritica.getTitle());
			query=query.replaceAll("varresena", newCritica.getResena()); 
			query=query.replaceAll("varmail", newCritica.getMail());
			query=query.replaceAll("varpuntuacion", Float.toString(newCritica.getPuntuacion()));
			query=query.replaceAll("varespid", Integer.toString(newCritica.getIdEsp()));
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcion que añade un votante y su voto a una critica
	 * @param mail Mail del votantes
	 * @param id Identificador de la critica a la que vota
	 * @param voto Voto dado a la critica
	 * @author Developers
	 */
	
	public void addVotanteCritica(String mail, int id, String voto) {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("createVC");
			query=query.replaceAll("varmail", mail);
			query=query.replaceAll("varid", Integer.toString(id));
			query=query.replaceAll("varvoto", voto);
			query = query.replaceAll("varvcid", Integer.toString(generarIdVC())); 
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcion que borra de la base de datos las votaciones asociadas a una critica
	 * @param id Identificador de la votacion a eliminar
	 * @author Developers
	 */
	
	public void removeVotantesCritica(int id) {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteVC");
			query=query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcion que borra de la base de datos una votacion asociada a una critica
	 * @param mail Mail del votante a eliminar
	 * @param id Identificador de la critica a la que pertenece el voto
	 * @author Developers
	 */
	
	public void removeVotanteCritica(String mail, int id) {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteUSERVC");
			query=query.replaceAll("varmail", mail);
			query=query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcion que devuelve las votaciones asociadas a una critca
	 * @param id Identificador de la critica
	 * @return votantes vector de Votaciones asociadas a la critica
	 * @author Developers
	 */
	
	public ArrayList<VotantesCriticaDTO> requestVotantes(int id){
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		ArrayList<VotantesCriticaDTO> votantes = new ArrayList<VotantesCriticaDTO>();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataVC");
			query=query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while(rs.next()) {
				VotantesCriticaDTO votCri = new VotantesCriticaDTO();
				String votante = rs.getString("mail");
				String voto = rs.getString("voto");
				votCri.setId(id);
				votCri.setVotante(votante);
				votCri.setVoto(voto);
				votantes.add(votCri);
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return votantes;
	}
	
	/*
	 * Funcion que actuliza la informacion de una critica especifica,
	 * a partir de datos provenientes del manager
	 * @param updateCritica DTO de Critica con la informacion actualizada
	 * @author Developers
	 */
	
	public void updateCritica(CriticaDTO updateCritica)
	{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("updateCritica");
			query=query.replaceAll("vartitulo", updateCritica.getTitle());
			query=query.replaceAll("varresena", updateCritica.getResena());
			query=query.replaceAll("varresena", updateCritica.getResena());
			query=query.replaceAll("varlike", Integer.toString(updateCritica.getLike()));
			query=query.replaceAll("vardislike", Integer.toString(updateCritica.getDislike()));
			query=query.replaceAll("varid", Integer.toString(updateCritica.getId()));
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcion que elimina de la base de datos una critica concreta
	 * @param id Identificador de la critica a eliminar
	 * @author Developers
	 */
	
	public void deleteCritica(int id) {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteCritica");
			query=query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			String query2 = prop.getProperty("deleteVC");
			query2=query2.replaceAll("varid", Integer.toString(id));
			Statement stmt2 = connection.createStatement();
			stmt2.executeUpdate(query2);
			
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcion que devuelve la informacion de una critica concreta
	 * @param id Identificador de la critica a buscar
	 * @return criticaRequesto DTO de Critica con la informacion de la critica buscada
	 * @author Developers
	 */
	
	public CriticaDTO requestCritica(int id)
	{
		CriticaDTO criticaRequest = new CriticaDTO();
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataCritica");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			if(rs.next())
			{
				int iduser = Integer.parseInt(rs.getString("id"));
				float puntuacion = Float.parseFloat(rs.getString("puntuacion"));
				String titulo = rs.getString("titulo");
				String resena= rs.getString("resena");
				String mail = rs.getString("mail");
				int like = rs.getInt("vlike");
				int dislike =rs.getInt("dislike");
				ArrayList<VotantesCriticaDTO> votantes = this.requestVotantes(id);
				int idEsp = Integer.parseInt(rs.getString("idEsp")); 
				criticaRequest.setMail(mail);
				criticaRequest.setId(iduser);
				criticaRequest.settitle(titulo);
				criticaRequest.setResena(resena);
				criticaRequest.setPuntuacion(puntuacion); 
				criticaRequest.setLike(like);
				criticaRequest.setDislike(dislike);
				criticaRequest.setVotantes(votantes);
				criticaRequest.setIdEsp(idEsp);
			}




			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return criticaRequest;
	}
	
	/*
	 * Funcion que devuelve la informacion de todas las criticas
	 * @return listCriticas Vector de DTOs de Criticas con la informacion de cada critica
	 * @author Developers
	 */
	
	public ArrayList<CriticaDTO> requestCriticas(){
		ArrayList<CriticaDTO> listCriticas = new ArrayList<CriticaDTO>();
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllCriticas");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				float puntuacion = rs.getFloat("puntuacion");
				String resena = rs.getString("resena");
				int id = rs.getInt("id");
				String mail = rs.getString("mail");
				int like = rs.getInt("vlike");
				int dislike = rs.getInt("dislike");
				ArrayList<VotantesCriticaDTO> votantes = this.requestVotantes(id);
				int idEsp = Integer.parseInt(rs.getString("idEsp"));
				
				CriticaDTO critic = new CriticaDTO(titulo,puntuacion,resena,id,mail,like,dislike, idEsp);
				critic.setVotantes(votantes);
				listCriticas.add(critic);
			}

			if (stmt != null){ 
				stmt.close(); 
			}

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listCriticas;
	}
	
	/*
	 * Funcion que devuelve la informacion de todas las criticas escritas por un usuario concreto
	 * @param mail Mail del autor de las criticas
	 * @return listCriticas Vector de DTOs de Criticas con la informacion de cada critica
	 * @author Developers
	 */
	
	public ArrayList<CriticaDTO> requestCriticasUser(String mail){
		ArrayList<CriticaDTO> listCriticas = new ArrayList<CriticaDTO>();
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllCriticasUser");
			query=query.replaceAll("varmail", mail);
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				float puntuacion = rs.getFloat("puntuacion");
				String resena = rs.getString("resena");
				int id = rs.getInt("id");
				int like = rs.getInt("vlike");
				int dislike = rs.getInt("dislike");
				ArrayList<VotantesCriticaDTO> votantes = this.requestVotantes(id);
				int idEsp = Integer.parseInt(rs.getString("idEsp"));
				
				CriticaDTO critic = new CriticaDTO(titulo,puntuacion,resena,id,mail,like,dislike, idEsp);
				critic.setVotantes(votantes);
				listCriticas.add(critic);
			}

			if (stmt != null){ 
				stmt.close(); 
			}

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listCriticas;
	}
	
	/*
	 * Funcion que devuelve la informacion de todas las criticas salvo las escritas por un usuario concreto
	 * @param mail Mail del usuario concreto
	 * @return listCriticas Vector de DTOs de Criticas con la informacion de cada critica
	 * @author Developers
	 */
	
	public ArrayList<CriticaDTO> requestCriticasExUser(String mail){
		ArrayList<CriticaDTO> listCriticas = new ArrayList<CriticaDTO>();
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllCriticasPermit");
			query = query.replace("varmail", mail);
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				float puntuacion = rs.getFloat("puntuacion");
				String resena = rs.getString("resena");
				int id = rs.getInt("id");
				String mailW = rs.getString("mail");
				int like = rs.getInt("vlike");
				int dislike = rs.getInt("dislike");
				ArrayList<VotantesCriticaDTO> votantes = this.requestVotantes(id);
				int idEsp = Integer.parseInt(rs.getString("idEsp"));
				CriticaDTO critic = new CriticaDTO(titulo,puntuacion,resena,id,mailW,like,dislike, idEsp);
				critic.setVotantes(votantes);
				listCriticas.add(critic);
			}
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listCriticas;
	}

	/*
	 * Funcion que devuelve los mails de los usuarios que hayan escrito alguna critica
	 * @return listWriters Vector que almacena los mails de los usuarios que hayan escrito alguna critica
	 * @author Developers
	 */
	
	public ArrayList<String> requestWriters(){
		ArrayList<String> listWriters = new ArrayList<String>();
		
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectWriters");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String writer = rs.getString("mail");
				listWriters.add(writer);
			}
			if (stmt != null){ 
				stmt.close(); 
			}

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listWriters;
	}
	
	/*
	 * Funcion que devuelve los espectaculos puntuales ya acabados
	 * @return esp Vector de Espectaculos 
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoDTO> requestEspPuntPast(){
		ArrayList<EspectaculoDTO> esp = new ArrayList<EspectaculoDTO>();
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataEspPuntPast");
			query = query.replace("varfechaactual", LocalDateTime.now().toString());
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String venta = rs.getString("localidades");
				String vendidas = rs.getString("localidadesVendidas");
				int localidades = Integer.parseInt(venta);
				int localidadesVendidas = Integer.parseInt(vendidas);
				
				EspectaculoDTO espPunt = new EspectaculoDTO(id,titulo,categoria,descripcion,localidades,localidadesVendidas);
				esp.add(espPunt);
			}

			if (stmt != null){ 
				stmt.close(); 
			}

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return esp;
	}

	/*
	 * Funcion que devuelve los espectaculos multiples con al menos una fecha pasada
	 * @return esp Vector de Espectaculos 
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoDTO> requestEspMultPast(){
		ArrayList<EspectaculoDTO> esp = new ArrayList<EspectaculoDTO>();
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataEspMultPast");
			query = query.replace("varfechaactual", LocalDateTime.now().toString());
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String venta = rs.getString("localidades");
				String vendidas = rs.getString("localidadesVendidas");
				int localidades = Integer.parseInt(venta);
				int localidadesVendidas = Integer.parseInt(vendidas);
				
				EspectaculoDTO espMult = new EspectaculoDTO(id,titulo,categoria,descripcion,localidades, localidadesVendidas);
				esp.add(espMult);
			}

			if (stmt != null){ 
				stmt.close(); 
			}

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return esp;
	}
	
	/*
	 * Funcion que devuelve los espectaculos Puntuales con al menos una fecha pasada
	 * @return esp Vector de Espectaculos 
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoDTO> requestEspTempPast(){
		ArrayList<EspectaculoDTO> esp = new ArrayList<EspectaculoDTO>();
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataEspTempPast");
			query = query.replace("varfechaactual", LocalDateTime.now().toString());
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String venta = rs.getString("localidades");
				String vendidas = rs.getString("localidadesVendidas");
				int localidades = Integer.parseInt(venta);
				int localidadesVendidas = Integer.parseInt(vendidas);
				
				EspectaculoDTO espTemp = new EspectaculoDTO(id,titulo,categoria,descripcion,localidades, localidadesVendidas);
				esp.add(espTemp);
			}

			if (stmt != null){ 
				stmt.close(); 
			}

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return esp;
	}

}
	
