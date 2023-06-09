package gestor.usuarios;
import espectador.Espectador;
import fichero.users.IOUsers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Clase que implementa las funcionalidades relativas 
 * a la gestion de datos de los usuarios.
 * @author Developers
 */


public class GestorUsuarios {

	/**
	 * Instancia del gestor de usuarios
	 */
	private static GestorUsuarios instance = null;
	/**
	 * Mail del usuario que lanzó el gestor.Asociado a la instancia.
	 */
	private IOUsers User = new IOUsers();
	/**
	 * Clase IOUsers para llamar a las funciones necesarias
	 */
	private String Mail;

	/**
	 * Constructor privado (patron Singleton).
	 * @param Mail del user que ejecuta el gestor
	 * @author Developers
	 */

	private GestorUsuarios(String Mail) 
	{
		this.Mail = Mail; 
	}
	/**
	 * Funcion para inicializar el gestor. Además escribe en el fichero properties
	 * la ruta del fichero usuarios.txt
	 * @param Mail del usuario que ejecuta el gestor
	 * @return La instancia del gestor.
	 * @author Developers
	 */

	public static GestorUsuarios getInstance(String Mail)
	{
		if(instance == null) 
		{
			instance = new GestorUsuarios(Mail);
			try {
				String path = new File("").getAbsolutePath();
				path = path + "/data.properties";
				File file = new File(path);
				Properties table = new Properties();
				FileInputStream in = new FileInputStream(file);
				table.load(in);
				in.close();
				String rutaAbsoluta = new File("").getAbsolutePath();
				String rutaFichero = rutaAbsoluta + "/usuarios.txt";
				table.setProperty("UsersFilePath", rutaFichero);
				FileOutputStream fr = new FileOutputStream(file);
		        table.store(fr, "Properties");
		        fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

/**
 * Funcion que da de alta un usuario en el sistema
 * @param e1 Objeta de clase Espectador
 * @author Developers
 *
 */	
			
	public void darAltaUser(Espectador e1) 
	{
		if(instance!=null)
		{
			this.User.RegisterUserToFich(e1.getName(), e1.getUsername(), e1.getMail(), e1.getPasswd());
		}
	}

/**
 * Funcion que da de baja al propio usuario del sistema
 * @author Developers
 *
 */	

	public void darBajaUser() 
	{
		if(instance!=null)
		{
			this.User.borrarUser(this.Mail);
			System.exit(1);
		}
	}
			

/**
 * Funcion que muestra los datos del propio usuario
 * @author Developers
 *
 */	

	public void verDatosUser()
	{
		if(instance!=null)
		{
			User.imprimirDatosUser(this.Mail);
		}
	}
	
	/**
	 * Funcion que actualiza los datos del propio usuario
	 * @author Developers
	 *
	 */	
	
	public void updateUser() 
	{
		if(instance!=null)
		{
			User.updateUser(this.Mail);
			System.exit(1);
		}	
	}

		
	}
