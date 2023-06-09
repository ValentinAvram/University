package es.uco.pw.display.javabean;

import java.time.LocalDateTime;

public class CustomerBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String emailUser = "";
	private String userName = "";
	private LocalDateTime regTime;
	private String rol = "";
	private LocalDateTime lastLogged;

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
	
}
