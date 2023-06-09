package menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


import gestor.espectaculos.GestorEspectaculo;

public class MenuEspectaculo {
	
	public void SpectacleMenu(String mail) 
	{
		String opc = null;
        GestorEspectaculo newGestor = GestorEspectaculo.getInstance(mail);

		while(true)
		{
			System.out.println("Bienvenido al menu de Espectaculos");

			System.out.println("Para ver los espectaculos registrados, pulse 1");
			System.out.println("Para ver los espectaculos con entradas disponibles, pulse 2");
			System.out.println("Para buscar un espectaculo por categoria, pulse 3");
			System.out.println("Para ver la informacion de un espectaculo concreto, pulse 4");
			System.out.println("Para salir del menu, pulse cualquier otra tecla");
						 
            BufferedReader login = new BufferedReader(new InputStreamReader(System.in));
            try 
            {
    			opc = login.readLine();
    		} 
            catch (IOException e) 
            {
    			e.printStackTrace();
    		}
            
            switch(opc)
            {
            
            case "1":
            	System.out.println("ESPECTACULOS REGISTRADOS:");
            	System.out.println("FUNCION NO IMPLEMENTADA. DISCULPE LAS MOLESTIAS");
            	System.out.println("");
            	/*String rutaAbsoluta = new File("").getAbsolutePath();
        		String rutaFichero = rutaAbsoluta + "/criticas.txt";
        		
        		FileReader fr = null;
        		BufferedReader br = null;
        		try
        		{
        	        fr = new FileReader (rutaFichero);
        	        br = new BufferedReader(fr);	
        	        newGestor.consultarEspectaculosPunt();
        	        newGestor.consultarEspectaculosMult();
        	        newGestor.consultarEspectaculosTemp();

            		if (( br.readLine()) == null) 
            		{
            			System.out.println("No hay ningun espectaculo registrada en el sistema !");
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
            	*/
            break;
            
            case "2":
            	System.out.println("ESPECTACULOS CON LOCALIDADES DISPONIBLES:");
            	//TODO:
            	Scanner teclado3 = new Scanner(System.in); 
            	System.out.println("Indique la clase de espectaculo que desea visualizar");
            	System.out.println("1. Espectaculo Puntual");
            	System.out.println("2. Espectaculo Multiple");
            	System.out.println("3. Espectaculo Temporada");
            	int opc3 = Integer.parseInt(teclado3.nextLine());
            	
            	switch(opc3)
            	{
            		case 1:
            			newGestor.localidadesDisponiblesPunt(); //SI FUNCIONA!!!!
            			break;
            			
            		case 2:
            			newGestor.localidadesDisponiblesMult();
            			break;
            			
            		case 3:
            			newGestor.localidadesDisponiblesTemp();     			
            			break;
            	}
            break;
            
            case "3":
            	System.out.println("FUNCION NO IMPLEMENTADA. DISCULPE LAS MOLESTIAS");
            	System.out.println("");
            	/*System.out.println("BUSQUEDA DE ESPECTACULOS POR CATEGORIA:");
                Scanner teclado1 = new Scanner(System.in);              
                System.out.println("Indique que categoria de espectaculos desea buscar:");
                String category = teclado1.nextLine();
                
                newGestor.buscarCategoriaPunt(category);
                newGestor.buscarCategoriaMult(category);
                newGestor.buscarCategoriaTemp(category);
            	*/
            	//TODO
            break;
            
            case "4":
            	
            	System.out.println("ESPECTACULO ESPECIFICO:");
            	System.out.println("FUNCION NO IMPLEMENTADA. DISCULPE LAS MOLESTIAS");
            	System.out.println("");
            	/*
            	Scanner teclado3 = new Scanner(System.in); 
            	System.out.println("Indique la clase de espectaculo que desea visualizar");
            	System.out.println("1. Espectaculo Puntual");
            	System.out.println("2. Espectaculo Multiple");
            	System.out.println("3. Espectaculo Temporada");
            	int opc3 = Integer.parseInt(teclado3.nextLine());
            	
            	System.out.println("Introduzca el titulo del espectaculo a visualizar");
            	String title3 = teclado3.nextLine();
            	
            	switch(opc3)
            	{
            		case 1:
            			newGestor.buscarEspectaculosPunt(title3); //SI FUNCIONA!!!!
            			break;
            			
            		case 2:
            			newGestor.buscarEspectaculosMult(title3);
            			break;
            			
            		case 3:
            			newGestor.buscarEspectaculosTemp(title3);     			
            			break;
            	}
            	
            	*/
            break;
         
            
            default:
            System.out.println("Saliendo del menu . . . ");
            return;
            
            }
		}
	}	
}