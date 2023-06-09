package espectador;
import fichero.users.IOUsers;

/**
 * Clase que implementa un espectador con sus variables privadas y metodos
 * @author Developers
 *
 */

public class Espectador extends IOUsers{

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

	/**
	 * Constructor sin parametros usado en inicializacion
	 * de variables
	 */
	
	public Espectador(){}
	
	/**
	 * Constructor parametrizado.
	 * @param name Nombre del usuario
	 * @param mail Mail del usuario
	 * @param username Username del usuario
	 * @param passwd Contraseña del usuario
	 * @author Developers
	 */
	
	public Espectador(String name, String mail, String username, String passwd)
	{
		this.name=name; 
		this.mail=mail;
		this.username = username;
		this.passwd = passwd;
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
}
