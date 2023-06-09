package business.DTOs;

import java.time.LocalDateTime;

/**
 * Clase que implementa un Usuario con sus variables privadas y metodos
 * @author Developers
 *
 */

public class UserDTO{

	/**
	 * Cadena que almacena el nombre del usuario
	 */
	
	private String name; 
	
	/**
	 * Cadena que almacena el mail del usuario
	 */
	
	private String mail;
	
	/**
	 * Cadena que almacena el username del usuario
	 */
	
	private String username;
	
	/**
	 * Cadena que almacena la contrasena del usuario
	 */
	
	private String passwd;

	/**
	 * Cadena que almacena el rol del usuario
	 */
	
	private String rol;
	
	/**
	 * Cadena que almacena la fecha de registro del usuario
	 */
	
	private LocalDateTime registerTime;
	
	/**
	 * Cadena que almacena la fecha de la ultima conexion del registro
	 */
	
	private LocalDateTime lastLogged;

	
	public UserDTO() {

	}
	
	/**
	 * Constructor parametrizado.
	 * @param name Nombre del usuario
	 * @param mail Mail del usuario
	 * @param username Username del usuario
	 * @param passwd Contraseña del usuario
	 * @author Developers
	 */
	
	public UserDTO(String name, String mail, String username, String passwd, String rol)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
		this.passwd = passwd;
		this.rol = rol;
	}
	
	public UserDTO(String name, String mail, String username, String passwd, String rol, LocalDateTime registerTime, LocalDateTime lastLogged)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
		this.passwd = passwd;
		this.rol = rol;
		this.registerTime = registerTime;
		this.lastLogged = lastLogged;
	}
	
	public UserDTO(String name, String mail, String username, String rol)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
		this.rol = rol;
	}
	
	public UserDTO(String name, String mail, String username)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
	}
	
	
	//OBSERVADORES
	

	/**
	 * Getter del nombre del usuario
	 * @return Cadena con el nombre del usuario
	 * @author Developers
	 */
	
	public String getName()
	{
		return this.name; 
	}
	
	/**
	 * Getter del mail del usuario
	 * @return Cadena con el mail del usuario
	 * @author Developers
	 */
	
	public String getMail()
	{
		return this.mail; 
	}
	
	/**
	 * Getter del username del usuario
	 * @return Cadena con el username del usuario
	 * @author Developers
	 */
	
	public String getUsername()
	{
		return this.username; 
	}
	
	/**
	 * Getter de la contrasena. Usado para hacer login. 
	 * No es seguro que sea public y la contrasena 
	 * esta almacenada en texto plano.
	 * @return Cadena que contiene la contrasena
	 * @author Developers
	 */
	
	public String getPasswd() {
		return this.passwd;
	}
	
	/* Getter de la fecha de registro del usuario
	 * @return registerTime Fecha y hora de registro
	 * @author Developers
	 * 
	 */
	public LocalDateTime getRegisterTime() {
		return this.registerTime;
	}
	
	/* Getter de la fecha de la ultima conexion del usuario
	 * @return registerTime Fecha y hora de la ultima conexion
	 * @author Developers
	 * 
	 */
	
	public LocalDateTime getLastLogged() {
		return this.lastLogged;
	}
	//MODIFICADORES
	
	/**
	 * Setter del nombre del usuario
	 * @param name Nombre del usuario
	 * @author Developers
	 */
	
	public void setName(String name)
	{
		this.name = name; 
	}
	
	/**
	 * Setter del mail del usuario
	 * @param mail Mail del usuario
	 * @author Developers
	 */
	
	public void setMail(String mail)
	{
		this.mail = mail; 
	}
	
	/**
	 * Setter del username del usuario
	 * @param username Username del usuario
	 * @author Developers
	 */
	
	public void setUsername(String username)
	{
		this.username = username; 
	}	
	
	/**
	 * Setter de la contrasena
	 * @param Passwd Contrasena del user
	 * @author Developers
	 */
	
	public void setPasswd(String Passwd) {
		this.passwd = Passwd;
	}
	
	/* Setter de la fecha de registro
	 * @param timeDate fecha de registro
	 * @author Developers
	 * 
	 */
	
	public void setRegisterTime(LocalDateTime timeDate) {
		this.registerTime = timeDate;
	}
	
	/* Setter de la fecha de ultima conexion
	 * @param timeDate fecha de conexion
	 * @author Developers
	 * 
	 */
	
	public void setLastLogged(LocalDateTime timeDate) {
		this.lastLogged = timeDate;
	}

	/* Getter del rol
	 * @return rol Rol del usario
	 * @author Developers
	 * 
	 */
	
	public String getRol() {
		return this.rol;
	}
	
	/* Setter del rol
	 * @param rol Rol del usuario
	 * @author Developers
	 * 
	 */

	public void setRol(String rol) {
		this.rol = rol;
	}
}
