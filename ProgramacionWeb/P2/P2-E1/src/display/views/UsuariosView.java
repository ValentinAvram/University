package display.views;

import java.util.Scanner;
import business.DTOs.UserDTO;
import business.managers.UserManager;
/**
 * La clase MenuUsuario contiene la funcion UserMenu, el menu de gestion de usuarios.
 * @author Developers
 */

public class UsuariosView 
{
	private String mail;
	
	private String getMail() {
		return mail;
	}
	
	public UsuariosView(String mail) {
		this.mail = mail;
	}
	
	public void userMenu() 
	{
		String opc = "0";
		Scanner reader = new Scanner(System.in);

		while(opc.equals("1") || opc.equals("2") || opc.equals("3") || opc.equals("0")) 
		{
			System.out.println("Bienvenido a nuestro Menu de gestion de usuarios.");
		    System.out.println("Para ver los datos de su perfil, pulse 1.");
		    System.out.println("Para actualizar sus datos de usuario, pulse 2.");
		    System.out.println("Para dar de baja su usuario, pulse 3.");	    
		    System.out.println("Para salir del menu, pulse cualquier otra tecla.");
		    
	        if(reader.hasNext())
	        {
			    opc = reader.nextLine();
		        if("1".equals(opc)) 
				{ 
		        	System.out.println("DATOS DE USUARIO");
		        	UserManager managerUserRead = new UserManager();
		        	UserDTO user = managerUserRead.requestUser(this.getMail());
		        	System.out.println("Mail : " + user.getMail());
		        	System.out.println("Name : " + user.getName());
		        	System.out.println("Username : " + user.getUsername());  
		        	System.out.println("Rol : " + user.getRol());
		        }
		        else if("2".equals(opc))
		        { 
		        	System.out.println("ACTUALIZACION DE DATOS");
		        	System.out.println("Estos son sus datos actuales:");
		        	UserManager managerUser = new UserManager();
		        	UserDTO user = managerUser.requestUser(this.getMail());
		        	System.out.println("Mail : " + user.getMail());
		        	System.out.println("Name : " + user.getName());
		        	System.out.println("Username : " + user.getUsername());  
		
		        	System.out.println("IMPORTANTE: Si hay un valor que NO desee actualizar, escriba su valor actual");
		        	String username = null;
		        	
		        	String name = null;
		        	
		        	String password = null;
		        	
		        	System.out.println("Introduzca su nuevo nombre de usuario");
		        	username = reader.nextLine();
		        	
		        	System.out.println("Introduzca su nuevo nombre");
		        	name = reader.nextLine();
		        	
		        	System.out.println("Introduzca su nueva Password");
		        	password = reader.nextLine();
	
		        	UserManager managerUserUpdate = new UserManager();
		        	managerUserUpdate.updateUser(this.getMail(), username, name, password);

		        }
		        else if("3".equals(opc))
		        { 
		        	System.out.println("ELMINACION DE USUARIO");
		        	System.out.println("Esta accion es permanente. Desea borrar su usuario? Y / N.");
		        	
		        	String delete = reader.nextLine();
		        	if(delete.equals("Y")) {
		        	UserManager managerUserDelete = new UserManager();
		        	managerUserDelete.deleteUser(this.getMail());
		       		System.out.println("Su usuario ha sido borrado.");
		       		System.out.println("Gracias por usar nuestro sistema.");
		       		System.exit(0);
			    	}
		        	else {
		        		System.out.println("Su usuario no ha sido borrado.");	
		        	}
		        }
		        else
		        { 
		        	System.out.println("Saliendo...\n\n");
		        }
	        }
		}
	}
}