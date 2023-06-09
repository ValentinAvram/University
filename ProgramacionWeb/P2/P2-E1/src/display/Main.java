package display;

import java.io.IOException;
import java.util.Scanner;

import display.views.*;
public class Main 
{
	public static void main(String[] args) throws IOException
	{
		LoginView login = new LoginView();
		String mail = login.loginMenu();
		String opc="0";
	   	Scanner reader = new Scanner(System.in);
		if(!mail.equals(""))
		{
			while(opc.equals("1") || opc.equals("2") || opc.equals("3") || opc.equals("0") && (!mail.equals(""))) 
			{
				System.out.println("Bienvenido a nuestro sistema.");
			   	System.out.println("Para ir a gestion de usuario, pulse 1.");
			   	System.out.println("Para ir a gestion de criticas, pulse 2.");
			   	System.out.println("Para ir a gestion de espectaculos, pulse 3.");
			   	System.out.println("Para salir del menu, pulse cualquier otra tecla."); 
			   	
			   	if(reader.hasNextLine())
			   	{
			   		opc = reader.nextLine();
			   	}

				if("1".equals(opc)) 
				{ 
					UsuariosView userView = new UsuariosView(mail);
					userView.userMenu();
				}
				else if("2".equals(opc))
				{ 
					CriticasView criticasView = new CriticasView(mail);
					criticasView.CriticaMenu();
				}
				else if("3".equals(opc))
				{ 
					EspectaculosView espView = new EspectaculosView(mail);
					espView.EspectaculoMenu();
				}
				else 
				{
					System.out.println("Gracias por usar nuestro sistema.");
					System.out.println("Saliendo. . .");
					break;
				}
			}
			reader.close(); 
		}
	}
}