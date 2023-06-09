package business.managers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import business.DTOs.UserDTO;
import data.DAOs.UserDAO;

/*
 * Clase que implementa las llamadas al DAO de usuarios 
 * para su posterior uso en los menus (views)
 * @author Developers
 */

public class UserManager {
	
	
	/* Funcion que comprueba que el inicio de sesion sea correcto, es decir,
	 * que el mail introducido sea el relativo a la password introducida
	 * @param mail Mail del usuario
	 * @param passwd Password del usuario
	 * @return true si el login es correcto
	 * @return false si el login es incorrecto
	 * @author Developers
	 */
	
	public Boolean login(String mail, String passwd) {
		if(this.UserExist(mail)) {
			UserDAO login = new UserDAO();
			String password = login.requestCredenciales(mail);
			if(passwd.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/* Funcion que llama al DAO y le da los valores necesarios para crear un nuevo
	 * usuario en la base de datos
	 * @param mail Mail del usuario
	 * @param username Nombre de usuario asociado al usario
	 * @param name Nombre del usuario
	 * @param passwd Password del usuario
	 * @param rol Rol del usuario dentro del sistema
	 * @param fechaRegistro Fecha en la que se registra el usuario
	 * @param fechaConex Fecha de la ultima conexion del usuario
	 * @return true si se puede crear el nuevo usuario
	 * @return false si el mail ya existe
	 * @author Developers
	 */
	
	public Boolean createUser(String mail, String username, String name, String passwd, String rol, LocalDateTime fechaRegistro, LocalDateTime fechaConex) {	
		if(!this.UserExist(mail)) {
			UserDAO newUser = new UserDAO();
			UserDTO newUserDTO = new UserDTO(name,mail,username,passwd, rol, fechaRegistro, fechaConex);  
			newUser.createUser(newUserDTO);
			return true;
		}
		return false;
	}
	
	/* Funcion que llama al DAO y le da los valores necesarios para actualizar
	 * un usuario de la base de datos
	 * @param mail Mail del usuario
	 * @param username Nombre de usuario asociado al usario
	 * @param name Nombre del usuario
	 * @param passwd Password del usuario
	 * @return true si se puede actualizar
	 * @return false si el mail no existe
	 * @author Developers
	 */
	
	public Boolean updateUser(String mail, String username, String name, String passwd) {
		if(this.UserExist(mail)) {
			UserDAO newUser = new UserDAO();
			UserDTO newUserDTO = new UserDTO(name,mail,username);
			newUserDTO.setPasswd(passwd);
			newUser.updateUser(newUserDTO);
			return true;
		}
		return false;
	}
	
	/* Funcion que llama al DAO y le da el mail del usuario a borrar
	 * @param mail Mail del usuario
	 * @return true si se puede borrar
	 * @return false si el mail no existe
	 * @author Developers
	 */
	
	public Boolean deleteUser(String mail) {
		if(this.UserExist(mail)) {
			UserDAO deleteUser = new UserDAO();
			deleteUser.deleteUser(mail);
			return true;
		}	
		return false;
	}

	/* Funcion que llama al DAO y le da el mail de un usuario cuyos datos devolver
	 * @param mail Mail del usuario a mostrar
	 * @return requestedUser DTO de Usuario 
	 * @author Developers
	 */
	
	public UserDTO requestUser(String mail) {
		UserDAO requestUser = new UserDAO();
		UserDTO requestedUser = new UserDTO();
		if(this.UserExist(mail)) {
			requestedUser = requestUser.requestUser(mail);	
		}
		return requestedUser;
	}
	
	/* Funcion que llama al DAO y le da el nombre de usuario de un usuario cuyos datos devolver
	 * @param username Nombre de usuario del usuario a mostrar
	 * @return requestedUser DTO de Usuario 
	 * @author Developers
	 */
	
	public UserDTO requestUserByUsername(String username) {
		UserDAO requestUser = new UserDAO();
		UserDTO requestedUser = new UserDTO();
		if(this.UserExistByUsername(username)) {
			requestedUser = requestUser.requestUserByUsername(username);	
		}
		return requestedUser;
	}
	
	/* Funcion que llama al DAO y le pide todos los usuarios 
	 * almacenados en la base de datos
	 * @return requestedUsers Vector de DTOs de Usuarios
	 * @author Developers
	 */
	
	public ArrayList<UserDTO> requestUsers(){
		UserDAO requestUser = new UserDAO();
		ArrayList<UserDTO> requestedUsers = new ArrayList<UserDTO>();
		requestedUsers = requestUser.requestUsers();
		return requestedUsers;
	}
	
	/* Funcion que llama al DAO y le pasa un mail para comprobar
	 * si existe algun usuario con ese mail asociado
	 * @return true si existe el usuario
	 * @return false si el usuario no existe
	 * 
	 */
	
	public Boolean UserExist(String mail) {
		ArrayList<UserDTO> users = this.requestUsers();
		for(UserDTO u : users) {
			if(u.getMail().equals(mail)) {
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion que llama al DAO y le pasa un username para comprobar
	 * si existe algun usuario con ese username asociado
	 * @return true si existe el usuario
	 * @return false si el usuario no existe
	 * @author Developers
	 */
	
	public Boolean UserExistByUsername(String username) {
		ArrayList<UserDTO> users = this.requestUsers();
		for(UserDTO u : users) {
			if(u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;	
	}
	
	/* Funcion que llama al DAO y le pasa un mail para recibir los datos
	 * del usuario asociado a dicho mail
	 * @param mail Mail del usuario
	 * @return userS Vector con los datos del usuario
	 * @author Developers
	 */
	
	public ArrayList<String> readUser(String mail){
		ArrayList<String> userS =  new ArrayList<String>();
		if(this.UserExist(mail)) {
			UserDTO user = this.requestUser(mail);
			userS.add(user.getMail());
			userS.add(user.getName());
			userS.add(user.getUsername());	
		}
		return userS;
	}
	
	/* Funcion que llama al DAO pasandole un ral especifico, 
	 * para recibir todos los usuarios con ese rol dentro del sistema
	 * @param rol Rol por el que se busca
	 * @return users Vector de Usuarios con todos los usuarios que tengan el rol buscado
	 * @author Developers
	 */
	
	public ArrayList<UserDTO> requestByRol(String rol){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		UserDAO requestByRol = new UserDAO();
		users = requestByRol.selectByRol(rol);		
		return users;
	}
	
	/* Funcion que llama al DAO y le pasa un mail para comprobar si
	 * el usuario asociado a dicho mail es administrador
	 * @param mail Mail del usuario
	 * @return true si el usuario es administrador
	 * @return false si el usuario no es administrador
	 * @author Developers
	 */
	
	public Boolean checkAdmin(String mail) {
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		users = this.requestByRol("admin");
		for(UserDTO u : users) {
			if(u.getMail().equals(mail))
				return true;
		}
		return false;
	}
}
