
package menus;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import critica.Critica;
import fichero.criticas.IOCriticas;
import gestor.criticas.GestorCriticas;

/**
 * La clase MenuCriticas comprende la impresion por pantalla de un menu,
 * que enlaza la interfaz de usuario con las funciones del gestor de
 * criticas.
 * @author Developers
 *
 */
public class MenuCriticas {

	
	
	public boolean isNumeric(String string)
	{
		boolean aux = false; 
	   	 for (int i = 0; i < string.length(); i++) 
	   	 {
		     if (Character.isDigit(string.charAt(i))) 
		     {
		       aux = true; 
		     }
	    }
	    return aux;
	}
/** 
* Menu que enlaza con las gestiones relacionadas
* a la gestionn de criticas
* @param usuario usuario que realiza la gestion.
* @author Developers
*
*/

	public void reviewMenu(String mail) 
	{

		String opc = null;
		Critica crit = new Critica();
        GestorCriticas newGestor = GestorCriticas.getInstance(mail);
        IOCriticas newIOCriticas = new IOCriticas();
        	while(true) {
        		System.out.println(""); 
        		System.out.println("Bienvenido a nuestro Menu de gestion de usuarios.");
        	    System.out.println("Para crear una critica, pulse 1.");
        	    System.out.println("Para consultar criticas, pulse 2");
        	    System.out.println("Para borrar una critica, pulse 3.");
        	    System.out.println("Para votar positivamente una critica, pulse 4.");
        	    System.out.println("Para votar negativamente, pulse 5.");
        	    System.out.println("Para buscar sus criticas, pulse 6.");
        	    System.out.println("Para ver las criticas de un usuario concreto, pulse 7.");
        	    System.out.println("Para salir del menu, pulse cualquier otra tecla.");
        	    
                BufferedReader login = new BufferedReader(
        	            new InputStreamReader(System.in));
                try {
        			opc = login.readLine();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
                
                switch(opc)
                {
                	case "1":
                		
                        Scanner teclado1 = new Scanner(System.in);              
                        
                       ArrayList<String> votantes = new ArrayList<String>();
                        
                		System.out.println("CREACION DE CRITICAS");
                		System.out.println("Introduzca el nombre del espectaculo.");
                		
                		String title;
                		title = teclado1.nextLine();
                		while(true)
                		{
                			if(title.equals("") || title.trim().isEmpty())
                			{
	                			System.err.println("No puede dejar vacio este campo. Intentelo de nuevo"); 
	                			title = teclado1.nextLine();
                			}
                			else
                			{
                                break; 
                			}
                		}
                        crit.settitle(title);
                        

                		System.out.println("Puntua el espectaculo del 0 al 10:");
                		System.out.println("(Ejemplo: 6.5)");
                		
                		String puntuacion; 
                		puntuacion = teclado1.nextLine(); 
              		
	                        while(!isNumeric(puntuacion) || Float.parseFloat(puntuacion)<0 || Float.parseFloat(puntuacion)>10)
	                        {
	                        	if(!isNumeric(puntuacion))
	                        	{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = teclado1.nextLine(); 
	                        	}
	                        	else
	                        	{
			                        System.err.println("La puntuacion debe ser un numero entre 1 y 10. Intentelo de nuevo.");
			                    	System.out.println("(Ejemplo: 6.5)");
			                    	puntuacion = teclado1.nextLine();
			                    	if(!isNumeric(puntuacion))
			                		{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = teclado1.nextLine(); 
			                		}
	                        	}
	                        }
                        crit.setPuntuacion(Float.parseFloat(puntuacion));       		
                		System.out.println("Escriba su resena");
                		
                		String resena;
                		resena = teclado1.nextLine();
                		while(true)
                		{
                			if(resena.equals("") || resena.trim().isEmpty())
                			{
	                			System.err.println("No puede dejar vacio este campo. Intentelo de nuevo"); 
	                			resena = teclado1.nextLine();
                			}
                			else
                			{
                                break; 
                			}
                		}
                        crit.setId(newIOCriticas.generarID()); 
                        crit.setResena(resena);
                        crit.setMail(mail);
                        crit.setVotantes(votantes);
                		newGestor.crearCritica(crit);
                		break;
                	
                	case "2":
                		
                		String rutaAbsoluta = new File("").getAbsolutePath();
                		String rutaFichero = rutaAbsoluta + "/criticas.txt";
                		
                		FileReader fr = null;
                		BufferedReader br = null;
                		try
                		{
                	        fr = new FileReader (rutaFichero);
                	        br = new BufferedReader(fr);	
                	        newGestor.consultarCriticas();
                	        
	                		if (( br.readLine()) == null) 
	                		{
	                			System.out.println("No hay ninguna critica registrada en el sistema !");
	                			System.out.println("");
	                		}
                		}
                		
                		catch(Exception e)
                		{
                			e.printStackTrace();
                		}
                		finally
                		{
                			try
                			{                    
                				if( null != fr )
                				{   
                					fr.close();     
                				}                  
                			}
                			catch (Exception e2)
                			{ 
                				e2.printStackTrace();
                			}
                		}
                		break;
                		                	
                	case "3":
                		
                		
                		Scanner teclado3 = new Scanner(System.in);  
                		String id;
                		
                		System.out.println("ELIMINACION DE CRITICAS");
                		newGestor.buscarCriticas(mail);
                		
                		System.out.println("Introduzca el id de la critica que desea borrar");
                       
                        id = (teclado3.nextLine());
                        
                        while(!isNumeric(id))
            			{
            				System.err.println("El ID de la critica debe ser numérico.Intentelo de nuevo.");
            				id = (teclado3.nextLine());
            			}
                        if(newIOCriticas.existId(Integer.parseInt(id)) == true)
                        {
                        	newIOCriticas.borrarCritica(Integer.parseInt(id), mail);
                        	System.out.println("Critica borrada correctamente."); 
                        }
                        else 
                        {
                        	System.err.println("El ID indicado no existe. Intentelo de nuevo.");	
                        }
                        
                		break;
                		
                	case "4":
                
                		String id1;
                		
                		System.out.println("Se mostraran por pantalla las diferentes criticas para que selecciona el ID de la critica que desea puntuar:");
                		System.out.println("");
                		String rutaAbsoluta1 = new File("").getAbsolutePath();
                		String rutaFichero1 = rutaAbsoluta1 + "/criticas.txt";
                		
                		FileReader fr1 = null;
                		BufferedReader br1 = null;
                		try
                		{
                	        fr1 = new FileReader (rutaFichero1);
                	        br1 = new BufferedReader(fr1);	
                	        newGestor.consultarCriticas();
                	        
	                		if (( br1.readLine()) == null) 
	                		{
	                			System.out.println("No hay ninguna critica registrada en el sistema !");
	                			System.out.println("");
	                		}
	                		else
	                		{
	                    		System.out.println("Indique el ID de la critica que desea puntuar");
	                    		BufferedReader reader1 = new BufferedReader(
	                		            new InputStreamReader(System.in));
	                    		
	                    		
	                			id1 = reader1.readLine();
	                			while(!isNumeric(id1))
	                			{
	                				System.err.println("El ID de la critica debe ser numérico.Intentelo de nuevo.");
	                				id1 = reader1.readLine();
	                			}
	                				
	                			if(newIOCriticas.existId(Integer.parseInt(id1)))
	                			{
	                				newGestor.votarCriticasPos(Integer.parseInt(id1));
	                			}
	                            else 
	                            {
	                            System.err.println("El ID indicado no existe. Intentelo de nuevo.");	
	                            }
	                            
	                		}
                		}
                		
                		catch(Exception e)
                		{
                			e.printStackTrace();
                		}
                		finally
                		{
                			try
                			{                    
                				if( null != fr1 )
                				{   
                					fr1.close();     
                				}                  
                			}
                			catch (Exception e2)
                			{ 
                				e2.printStackTrace();
                			}
                		}                		
                		
                		break;
                	
                	case "5":
                		
                		System.out.println("Se mostraran por pantalla las diferentes criticas para que selecciona el ID de la critica que desea puntuar:");
                		System.out.println("");
                		String rutaAbsoluta2 = new File("").getAbsolutePath();
                		String rutaFichero2 = rutaAbsoluta2 + "/criticas.txt";
                		
                		FileReader fr2 = null;
                		BufferedReader br2 = null;
                		try
                		{
                	        fr2 = new FileReader (rutaFichero2);
                	        br2 = new BufferedReader(fr2);	
                	        newGestor.consultarCriticas();
                	        
	                		if (( br2.readLine()) == null) 
	                		{
	                			System.out.println("No hay ninguna critica registrada en el sistema !");
	                			System.out.println("");
	                		}
	                		else
	                		{
	                    		System.out.println("Indique el ID de la critica que desea puntuar");
	                    		BufferedReader reader1 = new BufferedReader(
	                		            new InputStreamReader(System.in));
	                    		
	                    		
	                			id1 = reader1.readLine();
	                			while(!isNumeric(id1))
	                			{
	                				System.out.println("El ID de la critica debe ser numérico. Intentelo de nuevo.");
	                				id1 = reader1.readLine();
	                			}
	                				
	                			if(newIOCriticas.existId(Integer.parseInt(id1)))
	                			{
	                				newGestor.votarCriticasNeg(Integer.parseInt(id1));
	                			}
	                            else 
	                            {
	                            System.out.println("El ID indicado no existe. Intentelo de nuevo.");	
	                            }
	                            
	                		}
                		}
                		
                		catch(Exception e)
                		{
                			e.printStackTrace();
                		}
                		finally
                		{
                			try
                			{                    
                				if( null != fr2 )
                				{   
                					fr2.close();     
                				}                  
                			}
                			catch (Exception e2)
                			{ 
                				e2.printStackTrace();
                			}
                		}
                		break;

                	case "6":
                	
                		String rutaAbsoluta3 = new File("").getAbsolutePath();
                		String rutaFichero3 = rutaAbsoluta3 + "/criticas.txt";
                		
                		FileReader fr3 = null;
                		BufferedReader br3 = null;
                		try
                		{
                	        fr3 = new FileReader (rutaFichero3);
                	        br3 = new BufferedReader(fr3);	
                	        newGestor.buscarCriticas(mail);
                	        
	                		if (( br3.readLine()) == null) 
	                		{
	                			System.out.println("No hay ninguna critica registrada en el sistema !");
	                			System.out.println("");
	                		}
                		}
                		
                		catch(Exception e)
                		{
                			e.printStackTrace();
                		}
                		finally
                		{
                			try
                			{                    
                				if( null != fr3 )
                				{   
                					fr3.close();     
                				}                  
                			}
                			catch (Exception e2)
                			{ 
                				e2.printStackTrace();
                			}
                		}
                		break;                		

                	case "7":
                		String email = null;
                		Scanner teclado4 = new Scanner(System.in);  
	
                		System.out.println("Introduzca el mail del usuario cuyas criticas quiere ver");
                       
                        email = (teclado4.nextLine());
            	        newGestor.buscarCriticas(email);
                		break;
                		
                	default:
                		System.out.println("Saliendo...\n\n");
                		return;
                
                }
        	}
	}
}
