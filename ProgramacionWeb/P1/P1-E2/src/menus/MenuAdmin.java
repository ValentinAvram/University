package menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import espectaculo.EspectaculoMultiple;
import espectaculo.EspectaculoPuntual;
import espectaculo.EspectaculoTemporada;
import gestor.espectaculos.GestorEspectaculo;

/**
 * La clase MenuEspectaculos comprende la impresion por pantalla de un menu,
 * que enlaza la interfaz de usuario con las funciones del gestor de
 * espectaculos.
 * @author developers
 *
 */

public class MenuAdmin {

	
	public void AdminMenu(String mail) 
	{
		String opc = null;
        GestorEspectaculo newGestor = GestorEspectaculo.getInstance(mail);
        //IOEspectaculos newIOCEspectaculos = new IOEspectaculos();
        EspectaculoPuntual ep = new EspectaculoPuntual();
        EspectaculoMultiple em = new EspectaculoMultiple();
        EspectaculoTemporada et = new EspectaculoTemporada();
		//TODO: Que instancia creo, solo la Abstract Clase, una de cada, o al fabric (Abstract o el otro)?
		
		while(true)
		{
			System.out.println("MENU DE ADMINISTRADOR: Gestion de Espectaculos");
			System.out.println("Para dar de Alta un espectaculo, pulse 1");
			System.out.println("Para dar de baja de un espectaculo, pulse 2"); // La funcion de dar de baja debe tener dos modos, todas las sesiones, o una en particular
			System.out.println("Para actualizar la información sobre un espectaculo, pulse 3");
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
            	
            	Scanner teclado1 = new Scanner(System.in); 
            	int opc1 = 0;
            	
            	System.out.println("INTRODUCCION DE ESPECTACULOS:");
            	System.out.println("Seleccione que tipo de Espectaculo va a introducir en el Sistema :");
            	System.out.println("1. Espectaculo Puntual");
            	System.out.println("2. Espectaculo Multiple");
            	System.out.println("3. Espectaculo Temporada");
            	
            	opc1 = Integer.parseInt(teclado1.nextLine());
            	
            	switch(opc1)
            	{
            	case 1:
            		
            		ArrayList<Integer> ids = new ArrayList<Integer>();
            		System.out.println("Introduzca el nombre del espectaculo:");
            		String title = teclado1.nextLine();
            		ep.setTitulo(title);
            		

            		System.out.println("Introduzca la categoria del espectaculo:");
            		String category = teclado1.nextLine();
            		ep.setCategoria(category);
            		
            		System.out.println("Introduca la descripcion del espectaculo:");
            		String descripcion = teclado1.nextLine();
            		ep.setDescripcion(descripcion);
            		
            		System.out.println("Introduca el numero de localidades disponibles para el espectaculo:");
            		int totalloc = Integer.parseInt(teclado1.nextLine());
            		ep.setLocalidadesVenta(totalloc);
            		
            		System.out.println("Introduzca el numero de localidades ya vendidas");
            		int soldloc = Integer.parseInt(teclado1.nextLine());
            		
            		while(true)
            		{
	            		if((soldloc) > (totalloc))
	            		{
	            			System.err.println("No puede haber mas localidades vendidas de las que hay");
	            			soldloc = Integer.parseInt(teclado1.nextLine());
	            		}
	            		else
	            		{
	            			break;
	            		}
            		}
	            	ep.setLocalidadesVendidas(soldloc);
            		
            		System.out.println("Introduzca la fecha y hora del espectaculo");
            		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
            		String datetime = teclado1.nextLine();
            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            		LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
            		
            		ep.setHoraFecha(dateTime);
            		ep.setCritica(ids);
            		newGestor.CrearEspectaculoPunt(ep);
            		break;
            		
            	case 2:
            		ArrayList<Integer> ids1 = new ArrayList<Integer>();
            		ArrayList<LocalDateTime> pases = new ArrayList<LocalDateTime>();
            		System.out.println("Introduzca el nombre del espectaculo:");
            		String title1 = teclado1.nextLine();
            		em.setTitulo(title1);
            		

            		System.out.println("Introduzca la categoria del espectaculo:");
            		String category1 = teclado1.nextLine();
            		em.setCategoria(category1);
            		
            		System.out.println("Introduca la descripcion del espectaculo:");
            		String descripcion1 = teclado1.nextLine();
            		em.setDescripcion(descripcion1);
            		
            		System.out.println("Introduca el numero de localidades disponibles para el espectaculo:");
            		int totalloc1 = Integer.parseInt(teclado1.nextLine());
            		em.setLocalidadesVenta(totalloc1);
            		
            		System.out.println("Introduzca el numero de localidades ya vendidas");
            		int soldloc1 = Integer.parseInt(teclado1.nextLine());

            		while(true)
            		{
	            		if((soldloc1) > (totalloc1))
	            		{
	            			System.err.println("No puede haber mas localidades vendidas de las que hay");
	            			soldloc1 = Integer.parseInt(teclado1.nextLine());
	            		}
	            		else
	            		{
	            			break;
	            		}
            		}
            		
            		em.setLocalidadesVendidas(soldloc1);
            		
            		System.out.println("Indique cuantos pases habra para el espectaculo");
            		int numpases = Integer.parseInt(teclado1.nextLine());
            		for(int i = 0; i < numpases; i++)
            		{
                		System.out.println("Introduzca la fecha y hora del" + (i+1) + "º pase del espectaculo");
                		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
                		String datetime0 = teclado1.nextLine();
                		DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                		LocalDateTime dateTime0 = LocalDateTime.parse(datetime0, formatter0);
                		pases.add(dateTime0);
            		}
            		
            		em.setPases(pases);
            		em.setCritica(ids1);
            		newGestor.CrearEspectaculoMult(em);
            		break;
            	
            	case 3:
            		ArrayList<Integer> ids2 = new ArrayList<Integer>();
            		ArrayList<LocalDateTime> fechas = new ArrayList<LocalDateTime>();
            		System.out.println("Introduzca el nombre del espectaculo:");
            		String title2 = teclado1.nextLine();
            		et.setTitulo(title2);
            		

            		System.out.println("Introduzca la categoria del espectaculo:");
            		String category2 = teclado1.nextLine();
            		et.setCategoria(category2);
            		
            		System.out.println("Introduca la descripcion del espectaculo:");
            		String descripcion2 = teclado1.nextLine();
            		et.setDescripcion(descripcion2);
            		
            		System.out.println("Introduca el numero de localidades disponibles para el espectaculo:");
            		int totalloc2 = Integer.parseInt(teclado1.nextLine());
            		et.setLocalidadesVenta(totalloc2);
            		
            		System.out.println("Introduzca el numero de localidades ya vendidas");
            		int soldloc2 = Integer.parseInt(teclado1.nextLine());
            		
            		while(true)
            		{
	            		if((soldloc2) > (totalloc2))
	            		{
	            			System.err.println("No puede haber mas localidades vendidas de las que hay");
	            			soldloc = Integer.parseInt(teclado1.nextLine());
	            		}
	            		else
	            		{
	            			break;
	            		}
            		}
            		
            		et.setLocalidadesVendidas(soldloc2);
            		
            		System.out.println("Introduzca la fecha de Inicio");
            		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
            		String datetime1 = teclado1.nextLine();
            		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            		LocalDateTime dateTime1 = LocalDateTime.parse(datetime1, formatter1);
            		et.setFechaInicio(dateTime1);
            		
            		System.out.println("Introduzca la fecha de Fin");
            		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
            		String datetime2 = teclado1.nextLine();
            		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            		LocalDateTime dateTime2 = LocalDateTime.parse(datetime2, formatter2);
            		et.setFechaFinal(dateTime2);
            		
            		System.out.println("Indique cuantas veces se repetira el espectaculo");
            		int numpases0 = Integer.parseInt(teclado1.nextLine());
            		for(int i = 0; i < numpases0; i++)
            		{
                		System.out.println("Introduzca la fecha y hora del " + (i+1)+ "º pase del espectaculo");
                		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
                		String datetime0 = teclado1.nextLine();
                		DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                		LocalDateTime dateTime0 = LocalDateTime.parse(datetime0, formatter0);
                		fechas.add(dateTime0);
            		}
            		
            		et.setFechas(fechas);
            		et.setCritica(ids2);
            		newGestor.CrearEspectaculoTemp(et);
            		break;
            	}
            	
            break;
            
            case "2":
            	System.out.println("ELIMINACION DE ESPECTACULOS:");
            	System.out.println("FUNCION NO IMPLEMENTADA. DISCULPE LAS MOLESTIAS.");
            	System.out.println("");
            	/*
            	Scanner teclado2 = new Scanner(System.in); 
            	System.out.println("Indique la clase de espectaculo que desea borrar");
            	System.out.println("1. Espectaculo Puntual");
            	System.out.println("2. Espectaculo Multiple");
            	System.out.println("3. Espectaculo Temporada");
            	int opc2 = Integer.parseInt(teclado2.nextLine());
            	
            	System.out.println("Introduzca el titulo del espectaculo a eliminar");
            	String title = teclado2.nextLine();
            	
            	switch(opc2)
            	{
            		case 1:
            			newGestor.BorrarEspectaculosPunt(title);
            			break;
            			
            		case 2:
            			newGestor.BorrarEspectaculosMult(title);
            			break;
            			
            		case 3:
            			newGestor.BorrarEspectaculosTemp(title);     			
            			break;
            	}*/
            break;
            
            case "3":
            	System.out.println("ACTUALIZACION DE ESPECTACULOS:");
            	System.out.println("FUNCION NO IMPLEMENTADA. DISCULPE LAS MOLESTIAS");
            	System.out.println("");
            	/*
            	Scanner teclado3 = new Scanner(System.in); 
            	System.out.println("Indique la clase de espectaculo que desea borrar");
            	System.out.println("1. Espectaculo Puntual");
            	System.out.println("2. Espectaculo Multiple");
            	System.out.println("3. Espectaculo Temporada");
            	int opc3 = Integer.parseInt(teclado3.nextLine());
            	
            	System.out.println("Introduzca el titulo del espectaculo a eliminar");
            	String title3 = teclado3.nextLine();
            	
            	switch(opc3)
            	{
            		case 1:
            			newGestor.updateEspectaculoPunt(title3);
            			break;
            			
            		case 2:
            			newGestor.updateEspectaculoMult(title3);
            			break;
            			
            		case 3:
            			newGestor.updateEspectaculoTemp(title3);     			
            			break;
            	}*/
            break;
            
            
            default:
            System.out.println("Saliendo del menu . . . ");
            return;
            
            }
		}
	}	

}
