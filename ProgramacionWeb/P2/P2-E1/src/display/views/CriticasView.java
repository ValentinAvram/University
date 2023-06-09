package display.views;
import java.util.ArrayList;
import java.util.Scanner;

import business.DTOs.CriticaDTO;
import business.DTOs.EspectaculoDTO;
import business.managers.CriticaManager;

/**
 * La clase CriticasView contiene la funcion CriticaMenu, el menu de gestion de criticas.
 * @author Developers
 */

public class CriticasView {
	private String mail;
	
	private String getMail() {
		return mail;
	}
	
	public CriticasView(String mail) {
		this.mail = mail;
	}
	
	public void CriticaMenu() 
	{
		
    	String opc = "1";
    	Scanner reader = new Scanner(System.in);
		while(opc.equals("1") || opc.equals("2") || opc.equals("3") || opc.equals("4") || opc.equals("5") || opc.equals("6") || opc.equals("7")) {
			System.out.println("Para crear una critica, pulse 1.");
    	    System.out.println("Para consultar criticas, pulse 2");
    	    System.out.println("Para borrar una critica, pulse 3.");
    	    System.out.println("Para votar positivamente una critica, pulse 4.");
    	    System.out.println("Para votar negativamente, pulse 5.");
    	    System.out.println("Para buscar sus criticas, pulse 6.");
    	    System.out.println("Para ver las criticas de un usuario concreto, pulse 7.");
    	    System.out.println("Para salir del menu, pulse cualquier otra tecla.");
	        
			opc = reader.nextLine();

	        
	        if("1".equals(opc)) 
			{ 
	        	String tipo = "1";
	     
	        	System.out.println("CREAR CRITICA");
	        	System.out.println("Desea escribir una critica de un espectaculo:");
	        	System.out.println("1. Puntual");
	        	System.out.println("2. Temporada");
	        	System.out.println("3. Multiple");
	        	tipo = reader.nextLine();
	        	
	        	if(tipo.equals("1")) {
	        		CriticaManager managerCriticaCreatePunt = new CriticaManager(this.getMail());
	        		ArrayList<EspectaculoDTO> esps = managerCriticaCreatePunt.requestEspCriticablesPunt();
	        		int cont = 0;
	        		for(EspectaculoDTO e : esps) {
	        			cont++;
	        			System.out.println(cont + ". " + e.getTitulo());
	        			System.out.println("CATEGORIA: " + e.getCategoria());
	        			System.out.println("DESCRIPCION:" + e.getDescripcion()); 
	        		}
	        		System.out.println("Introduzca el numero del espectaculo a criticar");

	            	String criticarEsp = reader.nextLine();
	            	while(!isNumeric(criticarEsp))
	            	{
	            		System.out.println("Introduzca el numero del espectaculo a criticar (¡DEBE SER UN NUMERO!)");
	            		criticarEsp = reader.nextLine();
	            	}
	            	if(Integer.parseInt(criticarEsp) > 0 && Integer.parseInt(criticarEsp) <= cont ) {
	            		int id = esps.get(Integer.parseInt(criticarEsp)-1).getID();
	            		String titulo = null;
	    	        	String puntuacion=null;
	    	        	String resena = null;
	    	        	System.out.println("Va a criticar: " + esps.get(Integer.parseInt(criticarEsp)-1).getTitulo());
	    	        	System.out.println("Introduzca el titulo de la critica");
	    	        	titulo = reader.nextLine();	
	    	        	System.out.println("Introduzca la puntuacion que da al espectaculo");
	    	        	puntuacion = (reader.nextLine());
	    	        	 while(!isNumeric(puntuacion) || Float.parseFloat(puntuacion)<0 || Float.parseFloat(puntuacion)>10)
	                        {
	                        	if(!isNumeric(puntuacion))
	                        	{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = reader.nextLine(); 
	                        	}
	                        	else
	                        	{
			                        System.err.println("La puntuacion debe ser un numero entre 1 y 10. Intentelo de nuevo.");
			                    	System.out.println("(Ejemplo: 6.5)");
			                    	puntuacion = reader.nextLine();
			                    	if(!isNumeric(puntuacion))
			                		{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = reader.nextLine(); 
			                		}
	                        	}
	                        }
	    	        	System.out.println("Escriba la resena del mismo");
	    	        	resena = reader.nextLine(); 
	    	        	managerCriticaCreatePunt.createCritica(titulo, Float.parseFloat(puntuacion) , resena, id); 
	            	}
	            	else {
	            		System.out.println("Espectaculo no valido");
	            	}
	        	}
	        	else if (tipo.equals("2")) {
	        		CriticaManager managerCriticaCreateTemp = new CriticaManager(this.getMail());
	        		ArrayList<EspectaculoDTO> esps = managerCriticaCreateTemp.requestEspCriticablesTemp();
	        		int cont = 0;
	        		for(EspectaculoDTO e : esps) {
	        			cont++;
	        			System.out.println(cont + ". " + e.getTitulo());
	        			System.out.println(e.getCategoria());
	        			System.out.println(e.getDescripcion());
	        		}
	        		System.out.println("Introduzca el numero del espectaculo a criticar");
	            	String criticarEsp = reader.nextLine();
	            	while(!isNumeric(criticarEsp))
	            	{
	            		System.out.println("Introduzca el numero del espectaculo a criticar (¡DEBE SER UN NUMERO!)");
	            		criticarEsp = reader.nextLine();
	            	}
	            	if(Integer.parseInt(criticarEsp) > 0 && Integer.parseInt(criticarEsp) <= cont ) {
	            		int id = esps.get(Integer.parseInt(criticarEsp)-1).getID();
	            		String titulo = null;
	    	        	String puntuacion = null;
	    	        	String resena = null;
	    	        	System.out.println("Va a criticar: " + esps.get(Integer.parseInt(criticarEsp)-1).getTitulo());
	    	        	System.out.println("Introduzca el titulo de la critica");
	    	        	titulo = reader.nextLine();	
	    	        	System.out.println("Introduzca la puntuacion que da al espectaculo");
	    	        	puntuacion = (reader.nextLine());
	    	        	 while(!isNumeric(puntuacion) || Float.parseFloat(puntuacion)<0 || Float.parseFloat(puntuacion)>10)
	                        {
	                        	if(!isNumeric(puntuacion))
	                        	{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = reader.nextLine(); 
	                        	}
	                        	else
	                        	{
			                        System.err.println("La puntuacion debe ser un numero entre 1 y 10. Intentelo de nuevo.");
			                    	System.out.println("(Ejemplo: 6.5)");
			                    	puntuacion = reader.nextLine();
			                    	if(!isNumeric(puntuacion))
			                		{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = reader.nextLine(); 
			                		}
	                        	}
	                        }
	    	        	System.out.println("Escriba la resena del mismo");
	    	        	resena = reader.nextLine(); 
	    	        	managerCriticaCreateTemp.createCritica(titulo, Float.parseFloat(puntuacion) , resena, id); 
	            	}
	            	else {
	            		System.out.println("Espectaculo no valido");
	            	}
	        		
	        	}
	        	else if (tipo.equals("3")) {
	        		CriticaManager managerCriticaCreateMult = new CriticaManager(this.getMail());
	        		ArrayList<EspectaculoDTO> esps = managerCriticaCreateMult.requestEspCriticablesMult();
	        		int cont = 0;
	        		for(EspectaculoDTO e : esps) {
	        			cont++;
	        			System.out.println(cont + ". " + e.getTitulo());
	        			System.out.println(e.getCategoria());
	        			System.out.println(e.getDescripcion());
	        		}
	        		System.out.println("Introduzca el numero del espectaculo a criticar");
	            	String criticarEsp = reader.nextLine();
	            	while(!isNumeric(criticarEsp))
	            	{
	            		System.out.println("Introduzca el numero del espectaculo a criticar (¡DEBE SER UN NUMERO!)");
	            		criticarEsp = reader.nextLine();
	            	}
	            	if(Integer.parseInt(criticarEsp) > 0 && Integer.parseInt(criticarEsp) <= cont ) {
	            		int id = esps.get(Integer.parseInt(criticarEsp)-1).getID();
	            		String titulo = null;
	    	        	String puntuacion = null;
	    	        	String resena = null;
	    	        	System.out.println("Va a criticar: " + esps.get(Integer.parseInt(criticarEsp)-1).getTitulo());
	    	        	System.out.println("Introduzca el titulo de la critica");
	    	        	titulo = reader.nextLine();	
	    	        	System.out.println("Introduzca la puntuacion que da al espectaculo");
	    	        	puntuacion = (reader.nextLine());
	    	        	 while(!isNumeric(puntuacion) || Float.parseFloat(puntuacion)<0 || Float.parseFloat(puntuacion)>10)
	                        {
	                        	if(!isNumeric(puntuacion))
	                        	{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = reader.nextLine(); 
	                        	}
	                        	else
	                        	{
			                        System.err.println("La puntuacion debe ser un numero entre 1 y 10. Intentelo de nuevo.");
			                    	System.out.println("(Ejemplo: 6.5)");
			                    	puntuacion = reader.nextLine();
			                    	if(!isNumeric(puntuacion))
			                		{
				                     System.err.println("La puntuación tiene que ser numerica. Intentelo de nuevo.");
				                     System.out.println("(Ejemplo: 6.5)");
				                     puntuacion = reader.nextLine(); 
			                		}
	                        	}
	                        }
	    	        	System.out.println("Escriba la resena del mismo");
	    	        	resena = reader.nextLine(); 
	    	        	managerCriticaCreateMult.createCritica(titulo, Float.parseFloat(puntuacion) , resena, id);
	            	}
	            	else {
	            		System.out.println("Espectaculo no valido");
	            	}
	        	}
			}
		        else if("2".equals(opc))
		        { 
		        	System.out.println("CONSULTAR TODAS LAS CRITICAS");
		        	CriticaManager managerCriticaReadAll = new CriticaManager(this.getMail());
		        	ArrayList<CriticaDTO> criticas = managerCriticaReadAll.requestCriticas();
		        	for(CriticaDTO c : criticas) {
		        		System.out.println("------------------------------------------------");
		        		System.out.println("Titulo : " + c.getTitle());
			        	System.out.println("	Autor : " + c.getMail());
		        		System.out.println("	Espectaculo : " + managerCriticaReadAll.selectTituloEsp(c.getIdEsp()));
			        	System.out.println("	Puntuacion : " + c.getPuntuacion());
			        	System.out.println("	Resena : " + c.getResena());
			        	System.out.println("	Likes : " + c.getLike());
			        	System.out.println("	Dislikes : " + c.getDislike());
		        		System.out.println("------------------------------------------------");
		        	}
		        }
		        else if("3".equals(opc))
		        {
			        	System.out.println("ELMINACION DE CRITICA");
			        	System.out.println("Estas son todas sus criticas");
			        	CriticaManager managerCriticaMostrarDelUser = new CriticaManager(this.getMail());
		        		ArrayList<CriticaDTO> criticasUser = managerCriticaMostrarDelUser.requestAllCriticasUser(this.getMail());
		        		int cont = 0;
		        		for(CriticaDTO c : criticasUser) {
		        				cont++;
		        				System.out.println("------------------------------------------------");
		    	        		System.out.println(cont + " Titulo : " + c.getTitle());
		    	        		System.out.println("	Espectaculo : " + managerCriticaMostrarDelUser.selectTituloEsp(c.getIdEsp()));
		    		        	System.out.println("	Puntuacion : " + c.getPuntuacion());
		    		        	System.out.println("	Resena : " + c.getResena());
		    		        	System.out.println("	Likes : " + c.getLike());
		    		        	System.out.println("	Dislikes : " + c.getDislike());
		    	        		System.out.println("------------------------------------------------");
		        		}
		        		System.out.println("Introduzca el numero de la critica que desea borrar");
			        	String delete = reader.nextLine();
			        	while(!isNumeric(delete))
		            	{
		            		System.out.println("Introduzca el numero del la critica que desea borrar. (¡DEBE SER UN NUMERO!)");
		            		delete = reader.nextLine();
		            	}
			        	
			        	if(Integer.parseInt(delete) > 0 && Integer.parseInt(delete) <= cont ) {
			        		System.out.println("Esta accion es permanente. ï¿½Desea borrar la critica " + delete + " ? Y/N.");
				        	String deleteOpc = reader.nextLine();
				        	if(deleteOpc.equals("Y")) {
				        		CriticaManager managerCriticaDelete = new CriticaManager(this.getMail());
				        		int id = criticasUser.get(Integer.parseInt(delete)-1).getId();
				        		managerCriticaDelete.deleteCritica(id);
				        		System.out.println("La critica ha sido borrada.");
					    	}
				        	else {
				        		System.out.println("La critica no ha sido borrada.");	
				        	}
			        	}
		        }
		        else if("4".equals(opc)) 
				{ 
		        	System.out.println("Estas son todas las criticas que puede valorar");
		        	CriticaManager managerCriticaLike = new CriticaManager(this.getMail());
		        	ArrayList<CriticaDTO> criticasValorables = managerCriticaLike.requestCriticasExUser();
		        	int cont = 0;
		        	for(CriticaDTO c : criticasValorables) {
		        		cont++; 
		        		System.out.println("------------------------------------------------");
		        		System.out.println(cont + " Titulo : " + c.getTitle());
			        	System.out.println("	Autor : " + c.getMail());
		        		System.out.println("	Espectaculo : " + managerCriticaLike.selectTituloEsp(c.getIdEsp()));
			        	System.out.println("	Puntuacion : " + c.getPuntuacion());
			        	System.out.println("	Resena : " + c.getResena());
			        	System.out.println("	Likes : " + c.getLike());
			        	System.out.println("	Dislikes : " + c.getDislike());
		        		System.out.println("------------------------------------------------");
		        	}
		        	System.out.println("Introduzca el numero de la critica a la que desea dar like");
		        	String like = reader.nextLine();
		        	while(!isNumeric(like))
	            	{
	            		System.out.println("Introduzca el numero del la critica a la que quiere dar like (¡DEBE SER UN NUMERO!)");
	            		like = reader.nextLine();
	            	}
		        	if(Integer.parseInt(like) > 0 && Integer.parseInt(like) <= cont ) {
			        	if(managerCriticaLike.darLike(criticasValorables.get(Integer.parseInt(like)-1).getId())) {
			        		System.out.println("Ha dado like a la critica:");
			        		CriticaDTO critica = criticasValorables.get(Integer.parseInt(like)-1);
			        		System.out.println("Titulo : " + critica.getTitle());
				        	System.out.println("	Autor : " + critica.getMail());
			        		System.out.println("	Espectaculo : " + managerCriticaLike.selectTituloEsp(critica.getId()));
				        	System.out.println("	Puntuacion : " + critica.getPuntuacion());
				        	System.out.println("	Resena : " + critica.getResena());		
			        	}
			        	else {
			        		System.out.println("No ha podido dar like");
			        	}
		        	}
		        	else {
		        		System.out.println("Numero de critica no valido");
		        	}

		        }
		        else if("5".equals(opc))
		        { 
		        	System.out.println("Estas son todas las criticas que puede valorar");
		        	CriticaManager managerCriticaLike = new CriticaManager(this.getMail());
		        	ArrayList<CriticaDTO> criticasValorables = managerCriticaLike.requestCriticasExUser();
		        	int cont = 0;
		        	for(CriticaDTO c : criticasValorables) {
		        		cont++; 
		        		System.out.println("------------------------------------------------");
		        		System.out.println(cont + " Titulo : " + c.getTitle());
			        	System.out.println("	Autor : " + c.getMail());
		        		System.out.println("	Espectaculo : " + managerCriticaLike.selectTituloEsp(c.getIdEsp()));
			        	System.out.println("	Puntuacion : " + c.getPuntuacion());
			        	System.out.println("	Resena : " + c.getResena());
			        	System.out.println("	Likes : " + c.getLike());
			        	System.out.println("	Dislikes : " + c.getDislike());
		        		System.out.println("------------------------------------------------");
		        	}
		        	System.out.println("Introduzca el numero de la critica a la que desea dar dislike");
		        	String dislike = reader.nextLine();
		        	while(!isNumeric(dislike))
	            	{
	            		System.out.println("Introduzca el numero del la critica a la que quiere dar dislike (¡DEBE SER UN NUMERO!)");
	            		dislike = reader.nextLine();
	            	}
		        	if(Integer.parseInt(dislike) > 0 && Integer.parseInt(dislike) <= cont ) {
			        	if(managerCriticaLike.darDislike(criticasValorables.get(Integer.parseInt(dislike)-1).getId())) {
			        		System.out.println("Ha dado dislike a la critica:");
			        		CriticaDTO critica = criticasValorables.get(Integer.parseInt(dislike)-1);
			        		System.out.println("Titulo : " + critica.getTitle());
				        	System.out.println("	Autor : " + critica.getMail());
			        		System.out.println("	Espectaculo : " + managerCriticaLike.selectTituloEsp(critica.getIdEsp()));
				        	System.out.println("	Puntuacion : " + critica.getPuntuacion());
				        	System.out.println("	Resena : " + critica.getResena());		
			        	}
			        	else {
			        		System.out.println("No ha podido dar dislike");
			        	}
		        	}
		        	else {
		        		System.out.println("Numero de critica no valido");
		        	}
		        }
		        else if("6".equals(opc))
		        { 
		        	System.out.println("Estas son todas sus criticas");
		        	CriticaManager managerCriticaMostrarDelUser = new CriticaManager(this.getMail());
	        		ArrayList<CriticaDTO> criticasUser = managerCriticaMostrarDelUser.requestCriticas();
	        		for(CriticaDTO c : criticasUser) {
	        			if(c.getMail().equals(this.getMail())) {
	        				System.out.println("------------------------------------------------");
	        				System.out.println("	Titulo : " + c.getTitle());
	    	        		System.out.println("	Espectaculo : " + managerCriticaMostrarDelUser.selectTituloEsp(c.getIdEsp()));
	    		        	System.out.println("	Puntuacion : " + c.getPuntuacion());
	    		        	System.out.println("	Resena : " + c.getResena());
	    		        	System.out.println("	Likes : " + c.getLike());
	    		        	System.out.println("	Dislikes : " + c.getDislike());
	    	        		System.out.println("------------------------------------------------");
	        			}
	        		}
		        }
		        else if("7".equals(opc))
		        {
		        	System.out.println("MOSTRAR CRITICAS DE UN USUARIO");
		        	System.out.println("Estos son los usuarios que han escrito criticas");
		        	CriticaManager managerCriticaMostrarCriticasWriter = new CriticaManager(this.getMail());
		        	ArrayList<String> writers = managerCriticaMostrarCriticasWriter.requestWriters();
		        	int cont = 0;
		        	for(String s : writers) {
	        			cont++; 
		        		System.out.println(cont + ". " + s);
		        	}
		        	System.out.println("Introduzca el numero del autor del que quiere ver todas sus criticas");
		        	String s = reader.nextLine();
		        	while(!isNumeric(s) || (Integer.parseInt(s) > writers.size()) || (Integer.parseInt(s) < 0))
	            	{
		        		if(!isNumeric(s))
		        		{
		            		System.out.println("Introduzca el numero del autor del que quiere ver todas sus criticas (¡DEBE SER UN NUMERO!)");
		            		s = reader.nextLine();
		        		}
		        		else if(Integer.parseInt(s) > writers.size() || Integer.parseInt(s) < 0)
		        		{
		        			System.out.println("Numero no valido. Introduzca un numero válido por favor");
		        			s = reader.nextLine();
		        		}
	            	}
		        	String writer = writers.get(Integer.parseInt(s)-1);
		        	ArrayList<CriticaDTO> criticasUser = managerCriticaMostrarCriticasWriter.requestAllCriticasUser(this.getMail()); ;
		        	for (CriticaDTO c : criticasUser) {
		        		if(c.getMail().equals(writer)) {
		        			System.out.println("------------------------------------------------");
	    	        		System.out.println("Titulo : " + c.getTitle());
	    	        		System.out.println("	Espectaculo : " + managerCriticaMostrarCriticasWriter.selectTituloEsp(c.getIdEsp()));
	    		        	System.out.println("	Puntuacion : " + c.getPuntuacion());
	    		        	System.out.println("	Resena : " + c.getResena());
	    		        	System.out.println("	Likes : " + c.getLike());
	    		        	System.out.println("	Dislikes : " + c.getDislike());
	    	        		System.out.println("------------------------------------------------");
		        		}
		        	}
		        }
		        else
		        { 
		        	System.out.println("Saliendo...\n\n");
		        }
			}
		}
	
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
		
	}
