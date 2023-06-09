package main.program;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import fichero.users.IOUsers;
import menus.MenuAdmin;
import menus.MenuCriticas;
import menus.MenuEspectaculo;
import menus.MenuUsuario;
/**
 * Main program
 * @author Developers
 *
 */
public class mainProgram {
	
	public static void main(String[] args) throws IOException
	{

        String email = null, passwd = null, opcion = null, name = null, username = null;
        IOUsers io = new IOUsers();
        System.out.println("Bienvenido a nuestro sistema de gestion de criticas.");
        System.out.println("Para iniciar sesion pulse 1. Para registrarse pulse 2. Para salir pulse cualquier otra tecla.");
        
        BufferedReader login = new BufferedReader(new InputStreamReader(System.in));
        try {opcion = login.readLine();} 
        catch (IOException e) {e.printStackTrace();}
        
        if("1".equals(opcion)) {
        	System.out.println("Por favor, introduzca sus credenciales.");
            System.out.println("Email:");
            
    	        BufferedReader readerUser = new BufferedReader(new InputStreamReader(System.in));
    	        try {email = readerUser.readLine();}
    	        catch (IOException e) {e.printStackTrace();}
    	        
            System.out.println("Password:");
            
    	        BufferedReader readerPasswd = new BufferedReader(new InputStreamReader(System.in));
    	        try {passwd = readerPasswd.readLine();} 
    	        catch (IOException e) {e.printStackTrace();}
    	        
            System.out.println("Cargando . . .");
            if(io.comprobarUserExist(email)==false) {
                System.out.println("Email no registrado.");
                System.out.println("Para registrarse pulse 1. Para salir pulse cualquier otra tecla.");
                
                BufferedReader readerRegister = new BufferedReader( new InputStreamReader(System.in));
    	        try {opcion = readerRegister.readLine();} 
    	        catch (IOException e) {e.printStackTrace();}
    	        
    	        if("1".equals(opcion)) {
    	        	System.out.println("Introduzca su email:");
    				BufferedReader Register = new BufferedReader(new InputStreamReader(System.in));
    				try
    				{
    					email = Register.readLine();
    					while(email.equals("") || email.trim().isEmpty())
    					{
    						System.err.println("El campo email no puede estar vacio. Por favor, vuelva a intentarlo.");
    						email = Register.readLine();
    					}
    				} 
    				catch (IOException e) {e.printStackTrace();}
    				
    				System.out.println("Introduzca su passwd:");
    				BufferedReader Register1 = new BufferedReader(new InputStreamReader(System.in));
    				try 
    				{
    					passwd = Register1.readLine();
    					while(passwd.equals("") || passwd.trim().isEmpty())
    					{
    						System.err.println("El campo contrasena no puede estar vacio. Por favor, vuelva a intentarlo.");
    						passwd = Register.readLine();
    					}
    					
    				} 
    				catch (IOException e) {e.printStackTrace();}
    				
    				System.out.println("Introduzca su nombre:");
    				BufferedReader Register11 = new BufferedReader(new InputStreamReader(System.in));
    				try {
    					name = Register11.readLine();
    					while(name.equals("") || name.trim().isEmpty())
    					{
    						System.err.println("El campo nombre no puede estar vacio. Por favor, vuelva a intentarlo.");
    						name = Register11.readLine();
    					}
    				} 
    				catch (IOException e) {e.printStackTrace();}
    				System.out.println("Introduzca su username:");
    				
    				BufferedReader Register111 = new BufferedReader(new InputStreamReader(System.in));
    				try {
    					username = Register111.readLine();
    					while(username.equals("") || username.trim().isEmpty())
    					{
    						System.err.println("El campo username no puede estar vacio. Por favor, vuelva a intentarlo.");
    						username = Register11.readLine();
    					}
    				}
    				catch (IOException e) {e.printStackTrace();}

    		        IOUsers newUser = new IOUsers();
    		        newUser.RegisterUserToFich(name,username,email, passwd);
    		        System.out.println("Ha sido registrado correctamente.");
    		        System.out.println("Bienvenido a nuestro sistema " + username + ".");
            		while(opcion!="1" || opcion!= "2" || opcion!="3" || opcion!="4") {
    	            	System.out.println(" Que desea hacer?");
    	            	System.out.println("1. Acceder al menu de usuarios");
    	            	System.out.println("2. Acceder al menu de criticas");
    	            	System.out.println("3. Acceder al menu de espectaculos");
    	            	System.out.println("4. Salir");

    	            	BufferedReader readerGestores = new BufferedReader(new InputStreamReader(System.in));
    	    			try {
    	    				opcion = readerGestores.readLine();
    	    			} 
    	    			catch (IOException e) {e.printStackTrace();}
    	    			
    	    			if("1".equals(opcion)) 
    	    			{
    	    				MenuUsuario menu = new MenuUsuario();
    	    				menu.userMenu(email);
    	    				opcion=null;
    	    			}
    	    			else if("2".equals(opcion)) 
    	    			{
    	    				MenuCriticas menu = new MenuCriticas();
    	    				menu.reviewMenu(email);
    	    				opcion=null;
    	    			}
    	    			else if("3".equals(opcion))
    	    			{
    	    				MenuEspectaculo menu = new MenuEspectaculo();
    	    				menu.SpectacleMenu(email);
    	    				opcion = null;
    	    			}
    	    			else if("4".equals(opcion)) 
    	    			{
    			            System.out.println("Gracias por usar nuestro sistema. Hasta la proxima.");
    			            return;
    	    			}
    	    			else {
    			            System.out.println("Opcion no permitida.");
    	    			}
            		}
    	        }
                else 
                {
    	        	System.out.println("Gracias por usar nuestro sistema");
    	            return;
                }
            }
            if(io.comprobarPasswd(email, passwd)==false) 
            {
            	int intentos = 1;
    	        String opcion1 = null;
    	        Boolean timeout = true;
                System.out.println("Contrasena incorrecta. Pulse 1 para volver a intentarlo. Para salir pulse cualquier otra tecla. ");
                
                BufferedReader readerWrongPasswd = new BufferedReader(new InputStreamReader(System.in));
    			try {opcion1 = readerWrongPasswd.readLine();} 
    			catch (IOException e) {e.printStackTrace();}
    			
    	        if(!("1".equals(opcion1))) 
    	        {
    	            System.out.println("Gracias por usar nuestro sistema");
    	            return;
    	        }
    	        else 
    	        {
    	        	while(!(io.comprobarPasswd(email, passwd)) && timeout) 
    	        	{
    	            	System.out.println("Password:");
    		 	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    		 	        try {passwd = reader.readLine();} 
    		 	        catch (IOException e) {e.printStackTrace();}
    		 	        intentos++;
    		 	        if(intentos>3)
    		 	        	timeout = false;
    	            }
    	        	if(!timeout) 
    	        	{
    	            	System.out.println("Ha excedido el numero de intentos permitidos.");
    		            System.out.println("Gracias por usar nuestro sistema");
    		            return;
    	        	}
    	        	else 
    	        	{
						
            			System.out.println("Bienvenido a nuestro sistema, " + io.buscarPorCorreo(email).getUsername() + ".");
    	        		while(opcion1!="1" || opcion1!= "2" || opcion1!="3" || opcion1!="4" || opcion1!="5") 
    	        		{
    		            	System.out.println(" Que desea hacer?");
    		            	System.out.println("1. Acceder al menu de usuarios");
    		            	System.out.println("2. Acceder al menu de criticas");
    		            	System.out.println("3. Acceder al menu de espectaculos");
    		            	System.out.println("4. Salir");
    		            	if(io.buscarPorCorreo(email).getMail().equals("admin"))
    		            	{
    		            		System.out.println("5. Acceder al menu de Administrador");
    		            	}


    		            	BufferedReader readerGestores = new BufferedReader(new InputStreamReader(System.in));
    		    			try {opcion1 = readerGestores.readLine();} 
    		    			catch (IOException e) {e.printStackTrace();}
    		    			
    		    			if("1".equals(opcion1)) 
    		    			{
        	    				MenuUsuario menu = new MenuUsuario();
        	    				menu.userMenu(email);
    		    				opcion1=null;
    		    			}
    		    			
    		    			else if("2".equals(opcion1)) 
    		    			{
    		    				MenuCriticas menu = new MenuCriticas();
        	    				menu.reviewMenu(email);
    		    				opcion1=null;
    		    			}
    		    			
    		    			else if("3".equals(opcion1))
    		    			{
    		    				MenuEspectaculo menu = new MenuEspectaculo();
    		    				menu.SpectacleMenu(email);
    		    				opcion1 = null;
    		    			}
    		    			
    		    			else if("5".equals(opcion1) && io.buscarPorCorreo(email).getMail().equals("admin"))
    		            	{
    		            		System.out.println("5. Acceder al menu de Administrador");
    		            		MenuAdmin menu = new MenuAdmin();
    		            		menu.AdminMenu(email);
    		            		opcion1 = null;
    		            	}
    		            	
    		    			else if("4".equals(opcion1)) 
    		    			{
    				            System.out.println("Gracias por usar nuestro sistema. Hasta la proxima.");
    				            return;
    		    			}
    		    			else 
    		    			{
    				            System.out.println("Opcion no permitida.");
    		    			}
    	        		}
    	        	}
    	        }     
            }
	        System.out.println("Bienvenido a nuestro sistema " + io.buscarPorCorreo(email).getUsername() + ".");
    		
			while(opcion!="1" || opcion!= "2" || opcion!="3" || opcion!="4" || opcion!="5") 
			{
            	System.out.println(" Que desea hacer?");
            	System.out.println("1. Acceder al menu de usuarios");
            	System.out.println("2. Acceder al menu de criticas");
            	System.out.println("3. Acceder al menu de espectaculos");
            	System.out.println("4. Salir");
            	if(io.buscarPorCorreo(email).getMail().equals("admin"))
            	{
            		System.out.println("5. Acceder al menu de Administrador");
            	}

            	BufferedReader readerGestores = new BufferedReader(new InputStreamReader(System.in));
    			try {opcion = readerGestores.readLine();} 
    			catch (IOException e) {e.printStackTrace();}
    			
    			if("1".equals(opcion)) 
    			{
    				MenuUsuario menu = new MenuUsuario();
    				menu.userMenu(email);
    				opcion=null;
    			}
    			
    			else if("2".equals(opcion)) 
    			{
    				MenuCriticas menu = new MenuCriticas();
    				menu.reviewMenu(email);
    				opcion=null;
    			}
    			
    			else if("3".equals(opcion))
    			{
    				MenuEspectaculo menu = new MenuEspectaculo();
    				menu.SpectacleMenu(email);
    				opcion = null;
    			}
    			
    			else if("5".equals(opcion) && io.buscarPorCorreo(email).getMail().equals("admin"))
            	{
            		System.out.println("5. Acceder al menu de Administrador");
            		MenuAdmin menu = new MenuAdmin();
            		menu.AdminMenu(email);
            		opcion = null;
            	}
            	
    			else if("4".equals(opcion)) 
    			{
		            System.out.println("Gracias por usar nuestro sistema. Hasta la proxima.");
		            return;
    			}
    			else 
    			{
		            System.out.println("Opcion no permitida.");
    			}
    		}
        }
        else if("2".equals(opcion)) 
        {
        	passwd = null;
		   
			System.out.println("Introduzca su email:");
			BufferedReader Register = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				email = Register.readLine();
				while(email.equals("") || email.trim().isEmpty())
				{
					System.err.println("El campo email no puede estar vacio. Por favor, vuelva a intentarlo.");
					email = Register.readLine();
				}
			}
			catch (IOException e) {e.printStackTrace();}
			
			System.out.println("Introduzca su passwd:");
			BufferedReader Register1 = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				passwd = Register1.readLine();
				while(passwd.equals("") || passwd.trim().isEmpty())
				{
					System.err.println("El campo contrasena no puede estar vacio. Por favor, vuelva a intentarlo.");
					passwd = Register.readLine();
				}
				
			} 
			catch (IOException e) {e.printStackTrace();}
			
			System.out.println("Introduzca su nombre:");
			BufferedReader Register11 = new BufferedReader(new InputStreamReader(System.in));
			try {
				name = Register11.readLine();
				while(name.equals("") || name.trim().isEmpty())
				{
					System.err.println("El campo nombre no puede estar vacio. Por favor, vuelva a intentarlo.");
					name = Register11.readLine();
				}
			} 
			catch (IOException e) {e.printStackTrace();}
			
			System.out.println("Introduzca su username:");
			BufferedReader Register111 = new BufferedReader(new InputStreamReader(System.in));
			try {
				username = Register111.readLine();
				while(username.equals("") || username.trim().isEmpty())
				{
					System.err.println("El campo username no puede estar vacio. Por favor, vuelva a intentarlo.");
					username = Register11.readLine();
				}
			} 
			catch (IOException e) {e.printStackTrace();}

			IOUsers newUser = new IOUsers();
			newUser.RegisterUserToFich(name,username,email, passwd);
			
	        System.out.println("Ha sido registrado correctamente.");
	        System.out.println("Bienvenido a nuestro sistema " + io.buscarPorCorreo(email).getUsername() + ".");
    		
			while(opcion !="1" || opcion!= "2" || opcion!="3") 
			{
            	System.out.println(" Que desea hacer?");
            	System.out.println("1. Acceder al menu de usuarios");
            	System.out.println("2. Acceder al menu de criticas");
            	System.out.println("3. Acceder al menu de espectaculos");
            	System.out.println("4. Salir");

            	BufferedReader readerGestores = new BufferedReader(new InputStreamReader(System.in));
    			try {opcion = readerGestores.readLine();} 
    			catch (IOException e) {e.printStackTrace();}
    			
    			if("1".equals(opcion)) 
    			{
    				MenuUsuario menu = new MenuUsuario();
    				menu.userMenu(email);
    				opcion=null;
    			}
    			
    			else if("2".equals(opcion)) 
    			{
    				MenuCriticas menu = new MenuCriticas();
    				menu.reviewMenu(email);
    				opcion=null;
    			}	
    			
    			else if("3".equals(opcion))
    			{
    				MenuEspectaculo menu = new MenuEspectaculo();
    				menu.SpectacleMenu(email);
    				opcion = null;
    			}
    			
    			else if("4".equals(opcion)) 
    			{
		            System.out.println("Gracias por usar nuestro sistema. Hasta la proxima.");
		            return;
    			}
    			
    			else 
    			{
		            System.out.println("Opcion no permitida.");
    			}
    		}
        }
        else
        {
        	System.out.println("Gracias por usar nuestro sistema. Hasta la proxima.");
            return;
        }
	}
}

