
package fichero.users;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import espectador.*;
import fichero.criticas.IOCriticas;

/**
 *
 * Clase que implementa las funcionalidades relativas 
 * a la lectura y escritura de usuarios en el fichero 
 * correspondiente
 * @author Developers
 *
 */

public class IOUsers 
{
/**
 * Funcion que anade un usuario de nuevo registro al fichero de usuarios.
 * @param name Nombre del usuario
 * @param username Nombre de usuario
 * @param mail direccion de e-mail del usuario
 * @param passwd Contrasena del usuario
 * @author Developers
 *
 */

	public void RegisterUserToFich(String name, String username, String mail, String passwd)
	{
		if(comprobarUserExist(mail)==true)
		{
			System.err.println("No ha sido posible realizar el registro, el email ya esta registrado en nuestro sistema");
			System.exit(1);
		}
		else
		{
			String rutaAbsoluta = new File("").getAbsolutePath();
			String rutaFichero = rutaAbsoluta + "/usuarios.txt";
			FileWriter fichero = null;
		    PrintWriter pw = null; 
		    try
		    {	
		    	fichero= new FileWriter(rutaFichero, true); 
		    	pw = new PrintWriter(fichero); 
		    	pw.println(name+"//"+username+"//"+mail+"//"+passwd);
		    }catch (Exception e) {
		       e.printStackTrace();
		    } finally {
		           try {
		           if (fichero != null)
		              fichero.close();
		           
		           } catch (Exception e2) {
		              e2.printStackTrace();
		           }
	           }
	      }	
	    }
	
	/**
	 * Funcion que anade un usuario de nuevo registro al fichero de usuarios, sin la comprobacion de que el usuario ya exista, ya que se usara para updatear los datos de usuario.
	 * Asegura que el usuario pueda dejar igual su mail.
	 * @param name Nombre del usuario
	 * @param username Nombre de usuario
	 * @param mail direccion de e-mail del usuario
	 * @param passwd Contrasena del usuario
	 * @author Developers
	 *
	 */
	public void RegisterUserToFichMismoMail(String name, String username, String mail, String passwd)
	{
			String rutaAbsoluta = new File("").getAbsolutePath();
			String rutaFichero = rutaAbsoluta + "/usuarios.txt";
			FileWriter fichero = null;
		    PrintWriter pw = null; 
		    try
		    {	
		    	fichero= new FileWriter(rutaFichero, true); 
		    	pw = new PrintWriter(fichero); 
		    	pw.println(name+"//"+username+"//"+mail+"//"+passwd);
		    }catch (Exception e) {
		       e.printStackTrace();
		    } finally {
		           try {
		           if (fichero != null)
		              fichero.close();
		           
		           } catch (Exception e2) {
		              e2.printStackTrace();
	           }
	      }	
	    }
	
/**
 * Metodo que vuelca el contenido del fichero de usuarios en
 * un ArrayList de Espectadores para su posterior tratamiento.
 * @param v Array List de Espectadores (Usuarios)
 * @author Developers
 *
 */

	public ArrayList<Espectador> fichToVec(ArrayList<Espectador> v)
	{
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/usuarios.txt";
		FileReader fr = null;
		BufferedReader br = null;
		Espectador e1 = new Espectador();
		
		try {
	        fr = new FileReader (rutaFichero);
	        br = new BufferedReader(fr);
	        String linea=""; 
	        while((linea=br.readLine())!=null) {
			String[] data = linea.split("//");
			e1.setName(data[0]);
			e1.setUsername(data[1]);
        	e1.setMail(data[2]);
        	e1.setPasswd(data[3]); 
        	v.add(e1); 
        	e1 = new Espectador(); 
    	    
	        }
	        
		}catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		
		return v; 
	
	}

/**
 * Funcion que comprueba que un usuario exista
 * en el fichero plano de usuarios, comprobando su e-mail.
 * @param mail direccion del e-mail del usuario
 * @return true si el email existe, falso en caso contrario
 * @author Developers
 *
 */

	public boolean comprobarUserExist(String Mail)
	{
		ArrayList<Espectador> v = new ArrayList<Espectador>();
		fichToVec(v);
		for(Espectador e : v) {
			if(e.getMail().equals(Mail))
				return true;
		}
		return false;
	}

/**
 * Funcion que comprueba que la contraseña sea la correspondiente 
 * al usuario introducido.
 * @param mail direccion del e-mail del usuario
 * @param Passwd Contraseña del usuario
 * @return true si la contrasena es correcta, false en caso contrario
 * @author Developers
 *
 */

	public boolean comprobarPasswd(String email, String Passwd)
	{
		ArrayList<Espectador> v = new ArrayList<Espectador>();
		v = fichToVec(v);
		for(Espectador c : v) {
			if(c.getMail().equals(email)) {
				if(c.getPasswd().equals(Passwd))
					return true;
			}
		}
		return false;
	}

/**
 * Metodo que comprueba que exista un usuario en el fichero
 * de usuario buscando por email.
 * @param email direccion del e-mail del usuario
 * @return c Elemento de la clase espectador, usuario que se busca
 * @author Developers
 *
 */

	public Espectador buscarPorCorreo(String email){
		ArrayList<Espectador> v = new ArrayList<Espectador>();
		v = fichToVec(v);
		for(Espectador c : v) {
			if(c.getMail().equals(email)) {
				return c;
			}
		}
		return null;
	}
	
/**
 * Funcion que imprime los datos de un usuario.
 * @param email direccion del e-mail del usuario
 * @author Developers
 *
 */

	public void imprimirDatosUser(String email)
	{
		Espectador e1= new Espectador(); 
		e1 = buscarPorCorreo(email); 
		System.out.println("SUS DATOS DE USUARIO SON: "); 
		System.out.println("Nombre: " + e1.getName()); 
		System.out.println("Correo electronico: " + e1.getMail()); 
		System.out.println("Usuario: " + e1.getUsername()); 
	}
	
/**
 * Funcion que borra los datos de un usuario.
 * del fichero de usuarios, guiandose por un email.
 * @param email Direccion del e-mail del usuario
 * @author Developers
 *
 */

	public void borrarUser(String mail) {
		ArrayList<Espectador> v = new ArrayList<Espectador>();
		v = fichToVec(v);
		ArrayList<Espectador> v2 = new ArrayList<Espectador>();
		for(Espectador c : v) {
			if(!(c.getMail().equals(mail))) {
				v2.add(c);
			}
		}
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("usuarios.txt"));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		for(Espectador c : v2) {
			c.RegisterUserToFich(c.getName(), c.getUsername(), c.getMail(), c.getPasswd());
		}
	}
	
	/**
	 * Funcion que guarda los datos de un usuario en un
	 * nuevo objeto de la clase Espectador
	 * @return e1 objeto de la clase espectador, rellenado por el usuario del sistema
	 * @author Developers
	 */
		
	public Espectador proveerDatos() {
		String email = null, passwd=null, name=null, username=null;
		System.out.println("PROCEDA A INTRODUCIR SUS DATOS: ");
		System.out.println("Introduzca su email:");
		BufferedReader Register = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			email = Register.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Introduzca su passwd:");
		BufferedReader Register1 = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			passwd = Register1.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Introduzca su nombre:");
		BufferedReader Register11 = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			name = Register11.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Introduzca su username:");
		BufferedReader Register111 = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			username = Register111.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Espectador e1 = new Espectador(name,email, username, passwd);
		
		return e1;
	}
	
	/**
	 * Funcion que actualiza los datos de un usuario.
	 * del fichero de usuarios, guiandose por el mail
	 * el usuario
	 * @param mail Mail del usuario
	 * @author Developers
	 */
	
	public void updateUser(String mail) {
		IOCriticas ioc = new IOCriticas(); 
		String correo = null; 
		Scanner correo_scan = new Scanner(System.in);
		String nombre = null; 
		Scanner nombre_scan = new Scanner(System.in); 
		String contrasena = null; 
		Scanner contrasena_scan = new Scanner(System.in);
		String username = null; 
		Scanner username_scan = new Scanner(System.in);
		this.imprimirDatosUser(mail);
		Espectador e = this.proveerDatos();
		correo= e.getMail(); 
    			while(correo.equals("") || correo.trim().isEmpty() || comprobarUserExist(correo)==true)
    			{
    				if(comprobarUserExist(e.getMail())==true)
    				{
    					if(correo.equals(mail))
    					{
    						break; 
    					}
    					else
    					{
    						System.err.println("Ese correo electronico ya pertenece a un usuario. Por favor, indique otro correo electronico.");
        					correo = correo_scan.nextLine();
    					}
    				}
    				else if(correo.trim().isEmpty() || comprobarUserExist(e.getMail())==true)
    				{
    					System.err.println("No puede dejar vacio el campo correo electronico. Por favor, indique otro correo electronico."); 
            			correo = correo_scan.nextLine();
    				}
    				else
        			{
        				break; 
        			}
    			}
    	nombre = e.getName(); 
    			while(nombre.equals("") || nombre.trim().isEmpty())
    			{
    				if(nombre.equals("") || nombre.trim().isEmpty())
    				{
    					System.err.println("No puede dejar vacio el campo nombre. Por favor, indique otro nombre.");
    					nombre=nombre_scan.nextLine(); 
    				}
    				else
    				{
    					break; 
    				}
    			}
    	contrasena = e.getPasswd(); 
    			while(contrasena.equals("") || contrasena.trim().isEmpty())
    			{
    				if( contrasena.equals("") || contrasena.trim().isEmpty() )
    				{
    					System.err.println("No puede dejar vacio el campo contrasena. Por favor, indique otra contrasena.");
    					contrasena=contrasena_scan.nextLine(); 
    				}
    				else
    				{
    					break; 
    				}
    			}
    	username= e.getUsername(); 
    			while(username.equals("") || username.trim().isEmpty())
    			{
    				if(username.equals("") || username.trim().isEmpty())
    				{
    					System.err.println("No puede dejar vacio el campo username. Por favor, indique otro username.");
    					username=username_scan.nextLine(); 
    				}
    				else
    				{
    					break; 
    				}
    			}
		e.setUsername(username);
    	e.setMail(correo);
    	e.setName(nombre);
    	e.setPasswd(contrasena);
		this.borrarUser(mail);
		if(correo.equals(mail))
		{
			this.RegisterUserToFichMismoMail(e.getName(), e.getUsername(), e.getMail(), e.getPasswd());
		}	
		else
		{
			this.RegisterUserToFich(e.getName(), e.getUsername(), e.getMail(), e.getPasswd());
		}
		System.out.println("Sus credenciales han sido actualizadas. Por favor, acceda al sistema con sus nuevas credenciales.");
		ioc.updateUserCriticas(mail, e.getMail());
		correo_scan.close();
		nombre_scan.close(); 
		contrasena_scan.close();
		username_scan.close(); 
		
	}
	
}


