package es.uco.pw.business.DTOs;

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
	 * Cadena que almacena la contraseña del usuario
	 */
	
	private String passwd;

	private String rol;
	
	private LocalDateTime registerTime;
	
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
	
	public UserDTO(String name, String mail, String username, String passwd, LocalDateTime registerTime)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
		this.passwd = passwd;
		this.registerTime = registerTime;
	}
	
	public UserDTO(String name, String mail, String username, String passwd, String rol, LocalDateTime registerTime)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
		this.passwd = passwd;
		this.rol = rol;
		this.registerTime = registerTime;
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
	 * Getter de la contrasenaa. Usado para hacer login. 
	 * No es seguro que sea public y la contrasena 
	 * esta almacenada en texto plano.
	 * @return Cadena que contiene la contrasena
	 * @author Developers
	 */
	
	public String getPasswd() {
		return this.passwd;
	}
	
	public LocalDateTime getRegisterTime() {
		return this.registerTime;
	}
	
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
	
	public void setRegisterTime(LocalDateTime timeDate) {
		this.registerTime = timeDate;
	}
	
	public void setLastLogged(LocalDateTime timeDate) {
		this.lastLogged = timeDate;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}