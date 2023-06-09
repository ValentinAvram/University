package data.DAOs;

import business.*;
import data.common.DBConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/*
 * Clase que implementa las funciones relativas a la gestion de espectaculos
 * en la base de datos
 * @author Developers
 */
public class EspectaculoDAO {
	private String ruta ="C:\\Users\\jesus\\workspace-eclipse-luna\\P3-Alt\\src\\main\\webapp\\sql.properties";
	//private String ruta ="D:\\Descargas\\P3-Alt\\src\\main\\webapp\\sql.properties";
	//private String ruta = "C:\\Users\\aluja\\Documents\\EclipseLuna\\P3-Alt\\P3-Alt\\src\\main\\webapp\\sql.properties";
	//private java.io.InputStream input;
	private String url;
	private String userC;
	private String passwd;
	private DBConnection dbConnection;
	private Connection connection;
	
	public EspectaculoDAO(/*java.io.InputStream myIO,*/ String url, String userC, String passwd){
		//this.input = myIO;
		this.url = url;
		this.userC = userC;
		this.passwd = passwd;
		this.dbConnection = new DBConnection(this.url, this.userC, this.passwd);
 		this.connection = dbConnection.getConnection();
	}
	
	public void desEspectaculoDAO(){
		this.dbConnection.closeConnection();
	}

	
	/* Funcion que comprueba si existe un ID de un espectaculo Puntual
	 * @param id Identificador a comprobar
	 * @return true si el identificador existe
	 * @return false si el identificador no existe
	 * @author Developers
	 */
	
	private boolean existIdPunt(int id)
	{
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDEP");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existe un ID de un espectaculo Multiple
	 * @param id Identificador a comprobar
	 * @return true si el identificador existe
	 * @return false si el identificador no existe
	 * @author Developers
	 */
	
	private boolean existIdMult(int id)
	{
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDEM");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existe un ID de un espectaculo de Temporada
	 * @param id Identificador a comprobar
	 * @return true si el identificador existe
	 * @return false si el identificador no existe
	 * @author Developers
	 */
	
	private boolean existIdTemp(int id)
	{
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDET");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existe un ID de una fecha
	 * @param id Identificador a comprobar
	 * @return true si el identificador existe
	 * @return false si el identificador no existe
	 * @author Developers
	 */
	
	private boolean existIdFecha(int id)
	{
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDFecha");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existe un ID para la tabala MultipleFechas
	 * @param id Identificador a comprobar
	 * @return true si el identificador existe
	 * @return false si el identificador no existe
	 * @author Developers
	 */

	private boolean existIdMultFechas(int id)
	{
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDMF");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion que comprueba si existe un ID de un pase
	 * @param id Identificador a comprobar
	 * @return true si el identificador existe
	 * @return false si el identificador no existe
	 * @author Developers
	 */
	
	private boolean existIdPases(int id)
	{
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("existIDPases");
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next())
			{
				if(Integer.parseInt(rs.getString("id"))==id)
				{
					return true; 
				}
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return false; 
	}
	
	/* Funcion recursiva que genera un ID aleatorio para un pase
	 * @return id Identificador generado
	 * @author Developers
	 */
	
	public int generarIdPases()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdPases(id) == true)
		{
			generarIdPases();
		}
		return id;
	}
	
	/* Funcion recursiva que genera un ID aleatorio para la tabal MultipleFechas
	 * @return id Identificador generado
	 * @author Developers
	 */
	
	public int generarIDMultFechas()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdMultFechas(id) == true)
		{
			generarIDMultFechas();
		}
		return id;
	}
	
	/* Funcion recursiva que genera un ID aleatorio para una fecha
	 * @return id Identificador generado
	 * @author Developers
	 */
	
	public int generarIDFecha()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdFecha(id) == true)
		{
			generarIDFecha();
		}
		return id;
	}
	
	/* Funcion recursiva que genera un ID aleatorio para un Espectaculo Multiple
	 * @return id Identificador generado
	 * @author Developers
	 */
	
	public int generarIDMult()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdMult(id) == true)
		{
			generarIDMult();
		}
		return id;
	}
	
	/* Funcion recursiva que genera un ID aleatorio para Espectaculo Puntual
	 * @return id Identificador generado
	 * @author Developers
	 */

	public int generarIDPunt()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdPunt(id) == true)
		{
			generarIDPunt();
		}
		return id;
	}
	
	/* Funcion recursiva que genera un ID aleatorio para un Espectaculo de Temporada
	 * @return id Identificador generado
	 * @author Developers
	 */
	
	public int generarIDTemp()
	{
		Random r = new Random();
		int id = r.nextInt(99999)+1; 
		if (existIdTemp(id) == true)
		{
			generarIDTemp();
		}
		return id;
	}
	
	/* Funcion que crea un nuevo espectaculo Puntual a partir
	 * de los datos provenientes del manager
	 * @param newPunt DTO de Espectaculo Puntual con los datos del nuevo espectaculo
	 * @author Developers
	 */
	
	public void createEspectaculoPuntual(EspectaculoPuntDTO newPunt) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("createEP");
			query=query.replaceAll("varid", Integer.toString(newPunt.getID())); 
			query=query.replaceAll("vartitulo", newPunt.getTitulo());
			query=query.replaceAll("vardescripcion", newPunt.getDescripcion());
			query=query.replaceAll("varlocalidades", Integer.toString(newPunt.getLocalidadesVenta()));
			query=query.replaceAll("varvendidas", Integer.toString(newPunt.getLocalidadesVendidas())); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formattedDateTime = newPunt.getHoraFecha().format(formatter);
			query=query.replaceAll("varfecha",	formattedDateTime);
			query=query.replaceAll("varcategoria", newPunt.getCategoria());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra un Espectaculo Puntual de la base de datos, junto a sus criticas asociadas
	 * y las votaciones asociadas a dichas criticas
	 * @param id Identificador del espectaculo a borrar
	 * @author Developers
	 */
	
	public void deleteEspectaculoPuntual(int id) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteEP");
			query=query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			String query2 = prop.getProperty("deleteCriticaEsp");
			query2=query2.replaceAll("varidEsp", Integer.toString(id));
			stmt = connection.createStatement();
			stmt.executeUpdate(query2);
			
			String query3 = prop.getProperty("deleteVC");
			query3=query3.replaceAll("varid", Integer.toString(id));
			stmt = connection.createStatement();
			stmt.executeUpdate(query3);
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que actualiza la informacion de un Espectaculo Puntual
	 * @param updatePunt DTO de Espectaculo Puntual con los nuevos datos
	 * @author Developers
	 */
	
	public void updateEspectaculoPuntual(EspectaculoPuntDTO updatePunt) { 
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("updateEP"); 
			query=query.replaceAll("varid", Integer.toString(updatePunt.getID()));
			query=query.replaceAll("vartitulo", updatePunt.getTitulo());
			query=query.replaceAll("vardescripcion", updatePunt.getDescripcion());
			query=query.replaceAll("varlocalidades", Integer.toString(updatePunt.getLocalidadesVenta()));
			query=query.replaceAll("varvendidas", Integer.toString(updatePunt.getLocalidadesVendidas())); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formattedDateTime = updatePunt.getHoraFecha().format(formatter);
			query=query.replaceAll("varfecha", formattedDateTime);
			query=query.replaceAll("varcategoria", updatePunt.getCategoria());
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que devuelve un Espectaculo Puntual concreto a partir de un id
	 * @param id Identificador del Espectaculo a devolver
	 * @return epRequest DTO de Espectaculo Puntual con los datos del Espectaculo buscadp
	 * @author Developers
	 */
	
	public EspectaculoPuntDTO requestEspectaculoPuntual(int id) {
		EspectaculoPuntDTO epRequest = new EspectaculoPuntDTO();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataEP");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			String ident = rs.getString("id");
			String titulo = rs.getString("titulo");
			String descripcion = rs.getString("descripcion");
			String localidades = rs.getString("localidades");
			String localidadesvendidas = rs.getString("localidadesvendidas");
			String fecha = rs.getString("fecha");
			String categoria = rs.getString("categoria");
			
			epRequest.setId(Integer.parseInt(ident));
			epRequest.setTitulo(titulo);
			epRequest.setDescripcion(descripcion);
			epRequest.setLocalidadesVenta(Integer.parseInt(localidades));
			epRequest.setLocalidadesVendidas(Integer.parseInt(localidadesvendidas));
			//Pasar de string a LocalDateTime
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			LocalDateTime fecha_date = LocalDateTime.parse(fecha, formatter);
			epRequest.setHoraFecha(fecha_date);
			epRequest.setCategoria(categoria);
			
			if (stmt != null){ 
				stmt.close(); 
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return epRequest;
	}
	
	/* Funcion que devuelve un todos los Espectaculos Puntuales almacenados en el sistema
	 * @return listEPs Vector de DTOs de Espectaculos Puntuales con los datos de los espectaculos
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoPuntDTO> requestEPs(){
		ArrayList<EspectaculoPuntDTO> listEPs = new ArrayList<EspectaculoPuntDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllEP");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String ident = rs.getString("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String localidades = rs.getString("localidades");
				String localidadesvendidas = rs.getString("localidadesvendidas");
				String fecha = rs.getString("fecha");
				String categoria = rs.getString("categoria");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha_date = LocalDateTime.parse(fecha, formatter);
				listEPs.add(new EspectaculoPuntDTO(Integer.parseInt(ident), titulo, categoria, descripcion, Integer.parseInt(localidades), Integer.parseInt(localidadesvendidas), fecha_date)); 
			}

			if (stmt != null){ 
				stmt.close(); 
			}

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listEPs;
	}
	
	/* Funcion que crea un nuevo Espectaculo Multiple 
	 * @param newMult DTO de Espectaculo Multiple con los datos del nuevo Espectaculo a crear
	 * @author Developers
	 */
	
	//Espectaculo Multiple
	public void createEspectaculoMultiple(EspectaculoMultDTO newMult) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("createEM");
			query=query.replaceAll("varid", Integer.toString(newMult.getID()));
			query=query.replaceAll("vartitulo", newMult.getTitulo());
			query=query.replaceAll("vardescripcion", newMult.getDescripcion());
			query=query.replaceAll("varlocalidades", Integer.toString(newMult.getLocalidadesVenta()));
			query=query.replaceAll("varvendidas", Integer.toString(newMult.getLocalidadesVendidas())); 
			query=query.replaceAll("varcategoria", newMult.getCategoria());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/* Funcion que crea una nueva Fecha asociada a un Espectaculo Multiple 
	 * @param newFecha DTO de Fecha con los datos de la nueva Fecha
	 * @param idEspectaculo ID del espectaculo al que esta asociada la Fecha
	 * @author Developers
	 */
	
	public void createFecha(FechasDTO newFecha, int idEspectaculo) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			
			int identfecha = generarIDFecha();
			
			String query = prop.getProperty("createFecha");
			
			query=query.replaceAll("varid", Integer.toString(identfecha)); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formattedDateTime = newFecha.getFecha().format(formatter);
			query=query.replaceAll("varfecha", formattedDateTime);
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			String query2=prop.getProperty("createMFecha");
			
			query2=query2.replaceAll("varid", Integer.toString(idEspectaculo));
			query2=query2.replaceAll("varfechaid", Integer.toString(identfecha));
			stmt.executeUpdate(query2);
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra un Espectaculo Multiple del sistema, junto a todas sus fechas, criticas asociadas
	 * y votaciones asociadas a dichas criticas.
	 * @param id ID del espectaculo Multiple a borrar
	 * @author Developers
	 */
	
	public void deleteEspectaculoMultiple(int id) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteEM");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			String query2 = prop.getProperty("deleteFechaEspect");
			query2 = query2.replaceAll("varid", Integer.toString(id));
			stmt.executeUpdate(query2);
			
			String query3 = prop.getProperty("deleteMFecha2");
			query3 = query3.replaceAll("varid", Integer.toString(id));
			stmt.executeUpdate(query3);
			
			String query4 = prop.getProperty("deleteCriticaEsp");
			query4 = query4.replaceAll("varidEsp", Integer.toString(id));
			
			String query5 = prop.getProperty("deleteVC");
			query5 = query5.replaceAll("varid", Integer.toString(id));
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra una Fecha concreta del sistema
	 * @param deleteFecha DTO de Fechas con los datos de la fecha a borrar
	 * @author Developers
	 */
	
	public void deleteFecha(FechasDTO deleteFecha) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteFecha");
			query=query.replaceAll("varid", Integer.toString(deleteFecha.getID()));
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			query = prop.getProperty("deleteMFecha3");
			query=query.replaceAll("varid", Integer.toString(deleteFecha.getID()));
			stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra una asociacion de Fechas - Espectaculos Multiples
	 * @param int idfecha ID de la fecha a la que borrar la asociacion
	 * @author Developers
	 */
	
	public void deleteMFecha(int idfecha) {
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteMFecha");
			query=query.replaceAll("varid", Integer.toString(idfecha));
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que actualiza los datos de un Espectaculo Multiple
	 * @param updateMult DTO de Espectaculo Multiple con los datos a actulizar
	 * @author Developers
	 */
	
	public void updateEspectaculoMultiple(EspectaculoMultDTO updateMult) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("updateEM"); 
			query=query.replaceAll("varid", Integer.toString(updateMult.getID()));
			query=query.replaceAll("vartitulo", updateMult.getTitulo());
			query=query.replaceAll("vardescripcion", updateMult.getDescripcion());
			query=query.replaceAll("varlocalidades", Integer.toString(updateMult.getLocalidadesVenta()));
			query=query.replaceAll("varvendidas", Integer.toString(updateMult.getLocalidadesVendidas())); 
			query=query.replaceAll("varcategoria", updateMult.getCategoria());
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que actualiza los datos de una fecha asociada a un espectaculo multiple
	 * @param updateFecha DTO de fecha con los datos actualizar
	 * @param idEspectaculo ID del especatculo al que esta asociada la fecha
	 * @author Developers
	 */
	
	public void updateFecha(FechasDTO updateFecha, int idEspectaculo) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			
			String query = prop.getProperty("updateFecha");
			query=query.replaceAll("varid", Integer.toString(updateFecha.getID()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formattedDateTime = updateFecha.getFecha().format(formatter);
			query=query.replaceAll("varfecha", formattedDateTime);
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que devuelve los datos de un espectaculo Multiple concreto
	 * @param id ID del espectaculo a buscar
	 * @return emRequest DTO de Espectaculo Multiple con los datos del espectaculo requerido
	 * @author Developers
	 */
	
	public EspectaculoMultDTO requestEspectaculoMultiple(int id) {
		EspectaculoMultDTO emRequest = new EspectaculoMultDTO();
		ArrayList<FechasDTO> listFechas = new ArrayList<FechasDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataEM");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			String ident = rs.getString("id");
			String titulo = rs.getString("titulo");
			String descripcion = rs.getString("descripcion");
			String localidades = rs.getString("localidades");
			String localidadesvendidas = rs.getString("localidadesvendidas");
			String categoria = rs.getString("categoria");
			
			emRequest.setId(Integer.parseInt(ident));
			emRequest.setTitulo(titulo);
			emRequest.setDescripcion(descripcion);
			emRequest.setLocalidadesVenta(Integer.parseInt(localidades));
			emRequest.setLocalidadesVendidas(Integer.parseInt(localidadesvendidas));
			emRequest.setCategoria(categoria);
			
			String query2 = prop.getProperty("selectFechasEspMul");
			query2 = query2.replaceAll("varid", Integer.toString(id));
			
			ResultSet rs2 = (ResultSet) stmt.executeQuery(query);
			
			while (rs2.next()) {
				String idf = rs.getString("id");
				String fecha = rs.getString("fecha");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha_date = LocalDateTime.parse(fecha, formatter);
				listFechas.add(new FechasDTO(Integer.parseInt(idf), fecha_date));
			}
			
			emRequest.setFechas(listFechas);
			
			if (stmt != null){ 
				stmt.close(); 
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return emRequest;
	}
	
	/* Funcion que devuelve los datos de todos los Espectaculos Multiples del sistema
	 * @return listEMs Vector de DTOs de Espectaculos Multiples con los datos de los espectaculos
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoMultDTO> requestEMs(){
		ArrayList<EspectaculoMultDTO> listEMs = new ArrayList<EspectaculoMultDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllEM");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String ident = rs.getString("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String localidades = rs.getString("localidades");
				String localidadesvendidas = rs.getString("localidadesvendidas");
				String categoria = rs.getString("categoria");
				int identEsp = Integer.parseInt(ident);
				
				listEMs.add(new EspectaculoMultDTO(identEsp, titulo, categoria, descripcion, Integer.parseInt(localidades), Integer.parseInt(localidadesvendidas), requestFechasEsp(identEsp)));
			}

			if (stmt != null){ 
				stmt.close(); 
			}

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listEMs;
	}
	
	/* Funcion que crea un nuevo Espectaculo de Temporada
	 * @param newTemp DTO de Espectaculo de Temporada con los datos del nuevo espectaculo a crear
	 * @author Developers
	 */
	
	public void createEspectaculoTemporada(EspectaculoTempDTO newTemp) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("createET");
			query=query.replaceAll("varid", Integer.toString(newTemp.getID()));
			query=query.replaceAll("vartitulo", newTemp.getTitulo()); 
			query=query.replaceAll("vardescripcion", newTemp.getDescripcion());
			query=query.replaceAll("varlocalidades", Integer.toString(newTemp.getLocalidadesVenta()));
			query=query.replaceAll("varvendidas", Integer.toString(newTemp.getLocalidadesVendidas())); 
			query=query.replaceAll("varcategoria", newTemp.getCategoria());
			Statement stmt = connection.createStatement();

			stmt.executeUpdate(query);
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/* Funcion que crea un nuevo pase asociado a un Espectaculo de Temporada
	 * @param newPase DTO de Pases con los datos del nuevo pase
	 * @param idEspectaculo ID del espectaculo al que esta asociado el pase
	 * @author Developers
	 */
	
	public void createPase(PasesDTO newPase, int idEspectaculo) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			int idp = generarIdPases();
			String query = prop.getProperty("createPases");
			query=query.replaceAll("varid", Integer.toString(idp));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formattedDateTime = newPase.getFechaInicio().format(formatter);
			query=query.replaceAll("varfechainicio", formattedDateTime);
			
			query = query.replaceAll("vardiasemana", newPase.getDiaSemana());
			
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			formattedDateTime = newPase.getFechaFinal().format(formatter);
			query=query.replaceAll("varfechafinal", formattedDateTime);

			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			String query2=prop.getProperty("createMPase");
			query2=query2.replaceAll("varid", Integer.toString(idEspectaculo));
			query2=query2.replaceAll("varpaseid",Integer.toString(idp));

			stmt.executeUpdate(query2);
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra un Espectaculo de Temporada
	 * @param id ID del espectaculo a eliminar
	 * @author Developers
	 */
	
	public void deleteEspectaculoTemporada(int id) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			System.out.println("dao:");
			System.out.println(id);
			String query = prop.getProperty("deleteET");
			query=query.replaceAll("varid", Integer.toString(id));
			System.out.println(query);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			String query2 = prop.getProperty("deletePases");
			query2 = query2.replaceAll("varid", Integer.toString(id));
			stmt.executeUpdate(query2);
			
			String query3 = prop.getProperty("deleteMPases2");
			query3 = query3.replaceAll("varid", Integer.toString(id));
			stmt.executeUpdate(query3);
			
			String query4 = prop.getProperty("deleteCriticaEsp");
			query4=query4.replaceAll("varidEsp", Integer.toString(id));
			stmt = connection.createStatement();
			stmt.executeUpdate(query4);
			
			String query5 = prop.getProperty("deleteVC");
			query5=query5.replaceAll("varid", Integer.toString(id));
			stmt = connection.createStatement();
			stmt.executeUpdate(query5);
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra un Pase
	 * @param deletePase DTO de Pases con los datos del pase a eliminar
	 * @author Developers
	 */
	
	public void deletePase(PasesDTO deletePase) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deletePase");
			query=query.replaceAll("varid", Integer.toString(deletePase.getID()));
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que borra la asociacion Pase - Espectaculo de Temporada
	 * @param idpase ID del pase a eliminar
	 * @author Developers
	 */
	
	public void deleteTPase(int idpase) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("deleteMPase");
			query=query.replaceAll("varid", Integer.toString(idpase));
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que actualiza los datos de un Espectaculo de Temporada
	 * @param updateTemp DTO de Espectaculo de Temporada con los nuevos valores
	 * @author Developers
	 */
	
	public void updateEspectaculoTemporada(EspectaculoTempDTO updateTemp) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("updateET"); 
			query=query.replaceAll("varid", Integer.toString(updateTemp.getID()));
			query=query.replaceAll("vartitulo", updateTemp.getTitulo());
			query=query.replaceAll("vardescripcion", updateTemp.getDescripcion());
			query=query.replaceAll("varlocalidades", Integer.toString(updateTemp.getLocalidadesVenta()));
			query=query.replaceAll("varvendidas", Integer.toString(updateTemp.getLocalidadesVendidas())); 
			query=query.replaceAll("varcategoria", updateTemp.getCategoria());
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que actualiza los datos de un pase asociado a un Espectaculo de Temporada
	 * @param updatePase DTO de Pase con los nuevos valores
	 * @param idEspectaculo ID del espectaculo al que esta asociado
	 * @author Developers
	 */
	
	public void updatePase(PasesDTO updatePase) {
		 
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			
			String query = prop.getProperty("updatePase");
			query=query.replaceAll("varid", Integer.toString(updatePase.getID()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			String formattedDateTime = updatePase.getFechaInicio().format(formatter);
			query=query.replaceAll("varfechainicio", formattedDateTime);
			
			query=query.replaceAll("vardiasemana", updatePase.getDiaSemana());
			
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
			formattedDateTime = updatePase.getFechaFinal().format(formatter);
			query=query.replaceAll("varfechafinal", formattedDateTime);
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* Funcion que devuelve los datos de un Espectaculo de Temporada concreto
	 * @param id ID del espectaculo concreto
	 * @return etRequest DTO de Espectaculo de Temporada con los datos del espectaculo
	 * @author Developers
	 */
	
	public EspectaculoTempDTO requestEspectaculoTemporada(int id) {
		EspectaculoTempDTO etRequest = new EspectaculoTempDTO();
		ArrayList<PasesDTO> listPases = new ArrayList<PasesDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataET");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			String ident = rs.getString("id");
			String titulo = rs.getString("titulo");
			String descripcion = rs.getString("descripcion");
			String localidades = rs.getString("localidades");
			String localidadesvendidas = rs.getString("localidadesvendidas");
			String categoria = rs.getString("categoria");
			
			etRequest.setId(Integer.parseInt(ident));
			etRequest.setTitulo(titulo);
			etRequest.setDescripcion(descripcion);
			etRequest.setLocalidadesVenta(Integer.parseInt(localidades));
			etRequest.setLocalidadesVendidas(Integer.parseInt(localidadesvendidas));
			etRequest.setCategoria(categoria);
			
			String query2 = prop.getProperty("selectPasesEspTemp");
			query2 = query2.replaceAll("varid", Integer.toString(id));
			
			ResultSet rs2 = (ResultSet) stmt.executeQuery(query);
			
			while (rs2.next()) {
				String idf = rs.getString("id");
				String fecha = rs.getString("fechaInicio");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaInicio = LocalDateTime.parse(fecha, formatter);
				String diaSemana = rs.getString("diaSemana");
				fecha = rs.getString("fechaFinal");
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaFinal = LocalDateTime.parse(fecha, formatter);
				listPases.add(new PasesDTO(Integer.parseInt(idf), fechaInicio, diaSemana, fechaFinal));
			}
			
			etRequest.setPases(listPases);
			
			if (stmt != null){ 
				stmt.close(); 
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return etRequest;
	}
	
	/* Funcion que devuelve los datos de todos los Espectaculos de Temporada 
	 * @return lsitETs Vector de DTOs de Espectaculos de Temporada con los datos de cada espectaculo
	 * @author Developers
	 */
	
	public ArrayList<EspectaculoTempDTO> requestETs(){
		ArrayList<EspectaculoTempDTO> listETs = new ArrayList<EspectaculoTempDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllET");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String ident = rs.getString("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String localidades = rs.getString("localidades");
				String localidadesvendidas = rs.getString("localidadesvendidas");
				String categoria = rs.getString("categoria");
				int identEsp = Integer.parseInt(ident);
				
				listETs.add(new EspectaculoTempDTO(identEsp, titulo, categoria, descripcion, Integer.parseInt(localidades), Integer.parseInt(localidadesvendidas),requestPasesEsp(identEsp)));
			}
			if (stmt != null){ 
				stmt.close(); 
			}

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listETs;
	}
	
	/* Funcion que devuelve los datos de todas las Fechas   
	 * @return listFechas Vector de DTOs de Fechas con los datos de cada fecha
	 * @author Developers
	 */
	
	public ArrayList<FechasDTO> requestFechas(){
		ArrayList<FechasDTO> listFechas = new ArrayList<FechasDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllFechas");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String ident = rs.getString("id");
				String fechaBD =  rs.getString("fecha");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha = LocalDateTime.parse(fechaBD, formatter);
				listFechas.add(new FechasDTO(Integer.parseInt(ident), fecha));
			}
			if (stmt != null){ 
				stmt.close(); 
			}

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listFechas;
	}
	
	/* Funcion que devuelve los datos de todos los Pases 
	 * @return listPases Vector de DTOs de Pases con los datos de cada pase
	 * @author Developers
	 */
	
	public ArrayList<PasesDTO> requestPases(){
		ArrayList<PasesDTO> listPases = new ArrayList<PasesDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectAllPases");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String ident = rs.getString("id");
				String fechaBD =  rs.getString("fechaInicio");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaInicio = LocalDateTime.parse(fechaBD, formatter);
				String diaSemana = rs.getString("diaSemana");
				fechaBD =  rs.getString("fechaFinal");		
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaFinal = LocalDateTime.parse(fechaBD, formatter);
				listPases.add(new PasesDTO(Integer.parseInt(ident), fechaInicio, diaSemana,fechaFinal));
			}
			if (stmt != null){ 
				stmt.close(); 
			}

		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listPases;
	}
	
	/* Funcion que devuelve los datos de una Fecha concreta 
	 * @param id ID de la fecha concreta
	 * @return fechaRequest DTO de Fecha con los datos de la fecha concreta
	 * @author Developers
	 */
	
	public FechasDTO requestFecha(int id)
	{
		FechasDTO fechaRequest = new FechasDTO();

		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataFechaAdmin");
			query=query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			if(rs.next())
			{
				String ident = rs.getString("id");
				String fechaDB = rs.getString("fecha");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha = LocalDateTime.parse(fechaDB, formatter);		
				fechaRequest.setID(Integer.parseInt(ident));
				fechaRequest.setFecha(fecha);
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return fechaRequest;	
	}
	
	/* Funcion que devuelve los datos de un pase concreto
	 * @param id ID del pase concreto
	 * @return paseRequest DTO de Pase con los datos del pase concreto
	 * @author Developers
	 */

	public PasesDTO requestPase(int id)
	{
		PasesDTO paseRequest = new PasesDTO();

		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectDataPases");
			query=query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if(rs.next())
			{
				String idf = rs.getString("id");
				String fecha = rs.getString("fechaInicio");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaInicio = LocalDateTime.parse(fecha, formatter);
				String diaSemana = rs.getString("diaSemana");
				fecha = rs.getString("fechaFinal");
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaFinal = LocalDateTime.parse(fecha, formatter);
				
				paseRequest.setID(Integer.parseInt(idf));
				paseRequest.setFechaInicio(fechaInicio);
				paseRequest.setDiaSemana(diaSemana);
				paseRequest.setFechaInicio(fechaFinal);
			}
			if (stmt != null){ 
				stmt.close(); 
			}
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return paseRequest;	
	}

	/* Funcion que devuelve los datos de todas las Fechas de un espectaculo Multiple concreto
	 * @param id ID del espectaculo concreto
	 * @return listFechas Vector de fechas con todas las fechas asociadas al espectaculo
	 * @author Developers
	 */
	
	public ArrayList<FechasDTO> requestFechasEsp(int id){
		ArrayList<FechasDTO> listFechas = new ArrayList<FechasDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectFechasEspMul");
			query = query.replaceAll("varid", Integer.toString(id));
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) {
				String ident = rs.getString("id");
				String fechaBD =  rs.getString("fecha");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fecha = LocalDateTime.parse(fechaBD, formatter);
				listFechas.add(new FechasDTO(Integer.parseInt(ident), fecha));
			}
			if (stmt != null){ 
				stmt.close(); 
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listFechas;
	}
	
	/* Funcion que devuelve los datos de todas las Fechas de un espectaculo de Temporada concreto
	 * @param id ID del espectaculo concreto
	 * @return listPases Vector de pases con todas los pases asociados al espectaculo
	 * @author Developers
	 */
	
	
	public ArrayList<PasesDTO> requestPasesEsp(int id){
		ArrayList<PasesDTO> listPases = new ArrayList<PasesDTO>();
		
		try(InputStream input = new FileInputStream(ruta)){
			Properties prop = new Properties();
			prop.load(input);
			String query = prop.getProperty("selectPasesEspTemp");
			query = query.replaceAll("varid", Integer.toString(id));
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String ident = rs.getString("id");
				String fechaBD =  rs.getString("fechaInicio");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaInicio = LocalDateTime.parse(fechaBD, formatter);
				String diaSemana = rs.getString("diaSemana");
				fechaBD =  rs.getString("fechaFinal");		
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime fechaFinal = LocalDateTime.parse(fechaBD, formatter);
				listPases.add(new PasesDTO(Integer.parseInt(ident), fechaInicio, diaSemana,fechaFinal));
			}
			if (stmt != null){ 
				stmt.close(); 
			}
			 
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listPases;
	}
}