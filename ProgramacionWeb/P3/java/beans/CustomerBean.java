package beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomerBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String emailUser = "";
	private String userName = "";
	private LocalDateTime regTime;
	private String rol = "";
	private LocalDateTime lastLogged;
	private String nombre = "";
	
	public void setName(String name)
	{
		this.nombre = name;
	}
	
	public String getName()
	{
		return nombre;
	}
	
	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public LocalDateTime getRegisterTime() {
		return regTime;
	}

	public void setRegisterTime(LocalDateTime registerTime) {
		this.regTime = registerTime;
	}

	public String getRol() {
		return rol;
	}
	
	
	public void setRol(String rol) {
		this.rol = rol;
	}

	public LocalDateTime getLastLogged() {
		return lastLogged;
	}

	public void setLastLogged(LocalDateTime lastLogged) {
		this.lastLogged = lastLogged;
	}
	
	public String getRegisterString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		String formattedDateTime = this.getRegisterTime().format(formatter);
		return formattedDateTime;
	}
	
	public String getLoggedString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		String formattedDateTime = this.getLastLogged().format(formatter);
		return formattedDateTime;
	}
}