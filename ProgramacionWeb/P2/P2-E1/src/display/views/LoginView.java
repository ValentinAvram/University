package display.views;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import business.managers.UserManager;

/**
 * La clase MenuUsuario contiene la funcion UserMenu, el menu de gestion de usuarios.
 * @author Developers
 */

public class LoginView 
{
	public String loginMenu() 
	{
		

		
		String opc="1";
		Scanner opcScan = new Scanner(System.in);
    	String mail = null;
		while(opc.equals("1") || opc.equals("2")) 
		{
			System.out.println("Bienvenido a nuestro sistema.");
		    System.out.println("Para iniciar sesion, pulse 1.");
		    System.out.println("Para registrarse, pulse 2.");	    
		    System.out.println("Para salir del menu, pulse cualquier otra tecla.");			
	        
		    opc = "0";
		    if(opcScan.hasNext())
		    {
			    opc = opcScan.nextLine();
		        if("1".equals(opc)) 
				{ 
		        	System.out.println("LOGIN");
		        	UserManager managerUserLogin = new UserManager();
		        	String password = null;
		        	
		        	System.out.println("Introduzca su mail");
		        	mail = opcScan.nextLine();
		     
		        	System.out.println("Introduzca su password");
		        	password = opcScan.nextLine();
		        	
		        	if(managerUserLogin.login(mail,password)) {
		        		System.out.println("Bienvenido");
		        		return mail;
		        	}
		        	else {
		        		System.out.println("Login incorrecto");
		        		System.out.println("Saliendo...\n\n");
		        		System.exit(0);
		        	}
		        }
		        else if("2".equals(opc))
		        { 
		        	System.out.println("REGISTRO");
		        	String username = null;
		        	String name = null;
		        	String password = null;
		        	String rol = "0";
		     
		        	System.out.println("Introduzca su mail:");
		        	mail = opcScan.nextLine();
		        	System.out.println("Introduzca su username:");
		        	username = opcScan.nextLine();
		        	System.out.println("Introduzca su name:");
		        	name = opcScan.nextLine();
		        	System.out.println("Introduzca su password:");
		        	password = opcScan.nextLine();	
		        	System.out.println("Introduzca 1 si es Admin, 2 si es usuario");
		        	rol = opcScan.nextLine();
		        	if(rol.equals("1"))
		        		rol = "admin";
		        	else if(rol.equals("2"))
		        	{
		        		rol = "user";	
		        	}
		        	else
		        	{
		        		while(!rol.equals("1") && !rol.equals("2"))
		        		{
			        		System.out.println("Introduzca 1 si es Admin, 2 si es usuario");
			        		rol = opcScan.nextLine();
		        		}
		        	}
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					LocalDateTime now = LocalDateTime.now();
					String aux = now.format(formatter);
					
					LocalDateTime fecha = LocalDateTime.parse(aux, formatter);
					
		        	UserManager managerUser = new UserManager();
		        	
		        	if(!managerUser.UserExist(mail)) {
		        		managerUser.createUser(mail, username, name, password, rol, fecha, fecha);
		        	}
		        	else {
		        		System.out.println("El mail que desea usar ya existe");
		        		System.out.println("Saliendo...\n\n");
		        		System.exit(0);
		        	}
		        	opc = "0";
		        }
		        else
		        { 
		        	System.out.println("Saliendo...\n\n");
		        }
		    }    
		}
		return mail; 
	}
}
