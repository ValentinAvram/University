package fichero.espectaculos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import espectaculo.EspectaculoMultiple;
import espectaculo.EspectaculoPuntual;
import espectaculo.EspectaculoTemporada;


public class IOEspectaculos {

	
	public void EspectaculoPuntToFich(String title, String categoria, String descripcion, int localidades_venta, int localidades_vendidas, LocalDateTime horaFecha, ArrayList<Integer> ids)
	{		
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/espectaculos.txt";
		FileWriter fichero = null;
	    PrintWriter pw = null; 
	    try
	    {	
	    	fichero= new FileWriter(rutaFichero, true); 
	    	pw = new PrintWriter(fichero); 
	    	pw.print("1//"+title+"//"+categoria+"//" +descripcion+"//"+localidades_venta+ "//" +localidades_vendidas+ "//" +DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(horaFecha)+ "//");
	    	if(ids != null) {
	    		for(int s : ids) {
		    		pw.print(s+"::");
		    	}
		    	pw.print("//");
	    	}
	    	else {
	    		pw.print("void//");
	    	}
	    	pw.println("");
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
	
	public void EspectaculoMultToFich(String title, String categoria, String descripcion, int localidades_venta, int localidades_vendidas, ArrayList<LocalDateTime> pases, ArrayList<Integer> ids)
	{		
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/espectaculos.txt";
		FileWriter fichero = null;
	    PrintWriter pw = null; 
	    try
	    {	
	    	fichero= new FileWriter(rutaFichero, true); 
	    	pw = new PrintWriter(fichero); 
	    	pw.print("2//"+title+"//"+categoria+"//" +descripcion+"//"+localidades_venta+ "//" +localidades_vendidas+ "//");
	    	if(pases != null) {
	    		for(LocalDateTime d : pases) {
		    		pw.print(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(d)+",,");
		    	}
		    	pw.print("//");
	    	}
	    	if(ids != null) {
	    		for(int s : ids) {
		    		pw.print(s+"::");
		    	}
		    	pw.print("//");
	    	}
	    	else {
	    		pw.print("void//");
	    	}
	    	pw.println("");
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
	
	public void EspectaculoTempToFich(String title, String categoria, String descripcion, int localidades_venta, int localidades_vendidas, LocalDateTime inicio, LocalDateTime fin, ArrayList<LocalDateTime>fechas, ArrayList<Integer> ids)
	{		
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/espectaculos.txt";
		FileWriter fichero = null;
	    PrintWriter pw = null; 
	    try
	    {	
	    	fichero= new FileWriter(rutaFichero, true); 
	    	pw = new PrintWriter(fichero); 
	    	pw.print("3//"+title+"//"+categoria+"//" +descripcion+"//"+localidades_venta+ "//" +localidades_vendidas+ "//" +DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(inicio)+ "//" +DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(fin)+ "//");
	    	if(fechas != null) {
	    		for(LocalDateTime f : fechas) {
		    		pw.print(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(f)+",,");
		    	}
		    	pw.print("//");
	    	}
	    	if(ids != null) {
	    		for(int s : ids) {
		    		pw.print(s+"::");
		    	}
		    	pw.print("//");
	    	}
	    	else {
	    		pw.print("void//");
	    	}
	    	pw.println("");
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
	
	public ArrayList<EspectaculoPuntual> fichEspectaculoPuntToVec(ArrayList<EspectaculoPuntual> v)
	{
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/espectaculos.txt";
		FileReader fr = null;
		BufferedReader br = null;
		EspectaculoPuntual espp = new EspectaculoPuntual(); 
				
		try {

	        fr = new FileReader (rutaFichero);
	        br = new BufferedReader(fr);
	       
	        String linea; 
	        //ArrayList<Integer> ids = new ArrayList<Integer>(); 
        	while ((linea = br.readLine()) != null) {
        		if(linea.substring(0, 1).equals("1"));
        		{
        	    String[] data = linea.split("//");
		    	espp.setTitulo(data[1]);
		    	espp.setCategoria(data[2]);
		    	espp.setDescripcion(data[3]);
		    	espp.setLocalidadesVenta(Integer.parseInt(data[4]));
		    	espp.setLocalidadesVendidas(Integer.parseInt(data[5]));
		    	
		    	DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        		LocalDateTime dateTime0 = LocalDateTime.parse(data[6], formatter0);
		    	espp.setHoraFecha(dateTime0);
		    	//TODO: Vincular IDs de criticas a espectaculos
		    	/*if(!("void".equals(data[6]))) {
		    		String[] data2 = data[6].split("::");
			    	for(int i=1; i<data2.length; i++) 
			    	{
			    		ids.add(Integer.parseInt(data2[i]));
			    	} 

		    	}
		    	else {
		    		espp.setCritica(new ArrayList<Integer>());
		    	}*/
		    	v.add(espp); 
		    	espp = new EspectaculoPuntual();
		    	//ids = new ArrayList<Integer>(); 
        		}
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

	public ArrayList<EspectaculoMultiple> fichEspectaculoMultToVec(ArrayList<EspectaculoMultiple> v)
	{
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/espectaculos.txt";
		FileReader fr = null;
		BufferedReader br = null;
		EspectaculoMultiple espm = new EspectaculoMultiple(); 
				
		try {

	        fr = new FileReader (rutaFichero);
	        br = new BufferedReader(fr);
	       
	        String linea; 
	        //ArrayList<Integer> ids = new ArrayList<Integer>();
	        ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
        	while ((linea = br.readLine()) != null) {
        		if(linea.substring(0, 1).equals("2"))
        		{
	        	    String[] data = linea.split("//");
			    	espm.setTitulo(data[1]);
			    	espm.setCategoria(data[2]);
			    	espm.setDescripcion(data[3]);
			    	espm.setLocalidadesVenta(Integer.parseInt(data[4]));
			    	espm.setLocalidadesVendidas(Integer.parseInt(data[5]));		    	
			    	
			    	String[] data2 = data[6].split(",,");
				    for(int i=0; i<data2.length; i++) 
				    {
					    DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			        	LocalDateTime dateTime0 = LocalDateTime.parse(data2[i], formatter0);
					    dates.add(dateTime0);
				    } 
				    espm.setPases(dates);
			    	
        		}
			    	//TODO: Vincular IDs de criticas a espectaculos
			    	/*
			    	if(!("void".equals(data[6]))) {
			    		String[] data3 = data[6].split("::");
				    	for(int i=1; i<data3.length; i++) 
				    	{
				    		ids.add(Integer.parseInt(data3[i]));
				    	} 
	
			    	}
			    	else {
			    		espm.setCritica(new ArrayList<Integer>());
			    	}
			    	*/

			    	v.add(espm); 
			    	espm = new EspectaculoMultiple();
			    	//ids = new ArrayList<Integer>(); 
			    	dates = new ArrayList<LocalDateTime>();
        		}
        	}
	        
		catch(Exception e){
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
	
	public ArrayList<EspectaculoTemporada> fichEspectaculoTempToVec(ArrayList<EspectaculoTemporada> v)
	{
		String rutaAbsoluta = new File("").getAbsolutePath();
		String rutaFichero = rutaAbsoluta + "/espectaculos.txt";
		FileReader fr = null;
		BufferedReader br = null;
		EspectaculoTemporada espt = new EspectaculoTemporada(); 
				
		try {

	        fr = new FileReader (rutaFichero);
	        br = new BufferedReader(fr);
	       
	        String linea; 
	        //ArrayList<Integer> ids = new ArrayList<Integer>();
	        ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
        	while ((linea = br.readLine()) != null ) {
        		if(linea.substring(0, 1).equals("2"))
        		{
	        	    String[] data = linea.split("//");
			    	espt.setTitulo(data[1]);
			    	espt.setCategoria(data[2]);
			    	espt.setDescripcion(data[3]);
			    	espt.setLocalidadesVenta(Integer.parseInt(data[4]));
			    	espt.setLocalidadesVendidas(Integer.parseInt(data[5]));	
			    	
			    	DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        		LocalDateTime dateTime0 = LocalDateTime.parse(data[6], formatter0);
			    	espt.setFechaInicio(dateTime0);
			    	
			    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        		LocalDateTime dateTime1 = LocalDateTime.parse(data[7], formatter1);
			    	espt.setFechaFinal(dateTime1);
		
			    		String[] data2 = data[8].split(",,");
				    	for(int i=0; i<data2.length; i++) 
				    	{
					    	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			        		LocalDateTime dateTime2 = LocalDateTime.parse(data2[i], formatter2);
					    	dates.add(dateTime2);
				    	} 
				    	
				    	espt.setFechas(dates);

			    	//TODO: Vincular IDs de criticas a espectaculos
			    	/*
			    	if(!("void".equals(data[8]))) {
			    		String[] data3 = data[8].split("::");
				    	for(int i=1; i<data3.length; i++) 
				    	{
				    		ids.add(Integer.parseInt(data3[i]));
				    	} 
	
			    	}
			    	else {
			    		espt.setCritica(new ArrayList<Integer>());
			    	}
			    	/*/
			    	v.add(espt); 
			    	espt = new EspectaculoTemporada();
			    	//ids = new ArrayList<Integer>(); 
			    	dates = new ArrayList<LocalDateTime>();
        		}
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
	
	public boolean comprobarEspectaculoPuntExists(String title)
	{
		ArrayList<EspectaculoPuntual> v = new ArrayList<EspectaculoPuntual>();
		fichEspectaculoPuntToVec(v);
		for(EspectaculoPuntual ep : v) {
			if(ep.getTitulo().equals(title))
				return true;
		}
		return false;
	}
	
	public boolean comprobarEspectaculoMultExists(String title)
	{
		ArrayList<EspectaculoMultiple> v = new ArrayList<EspectaculoMultiple>();
		fichEspectaculoMultToVec(v);
		for(EspectaculoMultiple em : v) {
			if(em.getTitulo().equals(title))
				return true;
		}
		return false;
	}
	
	public boolean comprobarEspectaculoTempExists(String title)
	{
		ArrayList<EspectaculoTemporada> v = new ArrayList<EspectaculoTemporada>();
		fichEspectaculoTempToVec(v);
		for(EspectaculoTemporada et : v) {
			if(et.getTitulo().equals(title))
				return true;
		}
		return false;
	}
	
	//TODO: AÑADIR OTRO VECTOR VACIO MAS
	public void borrarEspectaculoPunt(String title) {
		ArrayList<EspectaculoPuntual> v = new ArrayList<EspectaculoPuntual>();
		v = fichEspectaculoPuntToVec(v);
		ArrayList<EspectaculoPuntual> aux = new ArrayList<EspectaculoPuntual>();
		ArrayList<EspectaculoMultiple> aux1 = new ArrayList<EspectaculoMultiple>();
		ArrayList<EspectaculoTemporada> aux2 = new ArrayList<EspectaculoTemporada>();
		
		aux1 = fichEspectaculoMultToVec(aux1);
		aux2 = fichEspectaculoTempToVec(aux2);
		
		for(EspectaculoPuntual ep : v) {
			if(!(ep.getTitulo().equals(title))) {
				aux.add(ep);
			}
		}
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("espectaculos.txt"));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(EspectaculoPuntual ep : aux) 
		{
			ep.EspectaculoPuntToFich(ep.getTitulo(), ep.getCategoria(), ep.getDescripcion(), ep.getLocalidadesVenta(), ep.getLocalidadesVendidas(), ep.getHoraFecha(),ep.getCritica());
		}
		

		for(EspectaculoMultiple em : aux1)
		{
			em.EspectaculoMultToFich(em.getTitulo(), em.getCategoria(), em.getDescripcion(), em.getLocalidadesVenta(), em.getLocalidadesVendidas(), em.getPases(), em.getCritica());
		}
		

		for(EspectaculoTemporada et : aux2)
		{
			et.EspectaculoTempToFich(et.getTitulo(), et.getCategoria(), et.getDescripcion(), et.getLocalidadesVenta(), et.getLocalidadesVendidas(), et.getFechaInicio(), et.getFechaFinal(), et.getFechas() ,et.getCritica());
		}

		
	}
	
	public void borrarEspectaculoMult(String title) {
		ArrayList<EspectaculoMultiple> v = new ArrayList<EspectaculoMultiple>();
		v = fichEspectaculoMultToVec(v);
		ArrayList<EspectaculoPuntual> aux = new ArrayList<EspectaculoPuntual>();
		ArrayList<EspectaculoTemporada> aux2 = new ArrayList<EspectaculoTemporada>();
		aux = fichEspectaculoPuntToVec(aux);
		aux2 = fichEspectaculoTempToVec(aux2);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("espectaculos.txt"));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!aux.isEmpty()) {
		for(EspectaculoPuntual ep : aux) 
		{
			ep.EspectaculoPuntToFich(ep.getTitulo(), ep.getCategoria(), ep.getDescripcion(), ep.getLocalidadesVenta(), ep.getLocalidadesVendidas(), ep.getHoraFecha(),ep.getCritica());
		}
		}
		if(v.isEmpty()) {
		for(EspectaculoMultiple em : v)
		{
			em.EspectaculoMultToFich(em.getTitulo(), em.getCategoria(), em.getDescripcion(), em.getLocalidadesVenta(), em.getLocalidadesVendidas(), em.getPases(), em.getCritica());
		}
		}
		if(!aux2.isEmpty())
		{
		for(EspectaculoTemporada et : aux2)
		{
			et.EspectaculoTempToFich(et.getTitulo(), et.getCategoria(), et.getDescripcion(), et.getLocalidadesVenta(), et.getLocalidadesVendidas(), et.getFechaInicio(), et.getFechaFinal(), et.getFechas() ,et.getCritica());
		}
		}
	}
	
	public void borrarEspectaculoTemp(String title) {
		ArrayList<EspectaculoTemporada> v = new ArrayList<EspectaculoTemporada>();
		v = fichEspectaculoTempToVec(v);
		ArrayList<EspectaculoPuntual> aux = new ArrayList<EspectaculoPuntual>();
		ArrayList<EspectaculoMultiple> aux1 = new ArrayList<EspectaculoMultiple>();
		aux1 = fichEspectaculoMultToVec(aux1);
		aux = fichEspectaculoPuntToVec(aux);
		for(EspectaculoTemporada et : v) {
			if(title.equals(et.getTitulo())) {
				v.remove(et);
			}
		}
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("espectaculos.txt"));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!aux.isEmpty()) {
		for(EspectaculoPuntual ep : aux) 
		{
			ep.EspectaculoPuntToFich(ep.getTitulo(), ep.getCategoria(), ep.getDescripcion(), ep.getLocalidadesVenta(), ep.getLocalidadesVendidas(), ep.getHoraFecha(),ep.getCritica());
		}
		}
		if(!aux1.isEmpty()) {
		for(EspectaculoMultiple em : aux1)
		{
			em.EspectaculoMultToFich(em.getTitulo(), em.getCategoria(), em.getDescripcion(), em.getLocalidadesVenta(), em.getLocalidadesVendidas(), em.getPases(), em.getCritica());
		}
		}
		if(v.isEmpty()) {
		for(EspectaculoTemporada et : v)
		{
			et.EspectaculoTempToFich(et.getTitulo(), et.getCategoria(), et.getDescripcion(), et.getLocalidadesVenta(), et.getLocalidadesVendidas(), et.getFechaInicio(), et.getFechaFinal(), et.getFechas() ,et.getCritica());
		}
		}	
	}
	
	public void printAllEspectaculosPunt()
	{
		ArrayList<EspectaculoPuntual> c = new ArrayList<EspectaculoPuntual>(); 
		c = fichEspectaculoPuntToVec(c);
		int aux = 1;
		for(EspectaculoPuntual ep : c)
		{
			System.out.println("ESPECTACULO PUNTUAL "+ aux);
			System.out.println("-------------------");
			System.out.println("Titulo: " + ep.getTitulo());
			System.out.println("Fecha: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(ep.getHoraFecha()));
			System.out.println("Descripcion: " + ep.getDescripcion());
			System.out.println("Localidades disponibles: " + ((ep.getLocalidadesVenta())-(ep.getLocalidadesVendidas())) ); 
			System.out.println("-------------------");
			aux++;
		}
		if(c.isEmpty())
		{
			System.err.println("No hay ningun Espectaculo Puntuales registrado en el sistema");
		}
	}
	
	public void printAllEspectaculosMult()
	{
		ArrayList<EspectaculoMultiple> c = new ArrayList<EspectaculoMultiple>(); 
		c = fichEspectaculoMultToVec(c);
		int aux = 1;
		for(EspectaculoMultiple em : c)
		{
			System.out.println("ESPECTACULO MULTIPLE "+ aux);
			System.out.println("-------------------");
			System.out.println("Titulo: " + em.getTitulo());
			if(em.getPases() != null)
			{
				for(int i = 0; i < em.getPases().size(); i++) 
				{
					System.out.println("Fecha "+ (i+1) + "º :"  + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(em.getPases().get(i)));
				}
			}
			System.out.println("Descripcion: " + em.getDescripcion());
			System.out.println("Localidades disponibles: " + ((em.getLocalidadesVenta())-(em.getLocalidadesVendidas())) );
			System.out.println("-------------------");
			aux++;
		}
		if(c.isEmpty())
		{
			System.err.println("No hay ningun Espectaculo Multiple registrado en el sistema");
		}
	}
	
	public void printAllEspectaculosTemp()
	{
		ArrayList<EspectaculoTemporada> c = new ArrayList<EspectaculoTemporada>(); 
		c = fichEspectaculoTempToVec(c);
		int aux = 1;
		for(EspectaculoTemporada et: c)
		{
			System.out.println("ESPECTACULO DE TEMPORADA "+ aux);
			System.out.println("-------------------");
			System.out.println("Titulo: " + et.getTitulo());
			System.out.println("Fecha de Inicio: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(et.getFechaInicio()));

			for(int i = 0; i < et.getFechas().size(); i++) 
			{
				System.out.println("Fecha "+ (i+1) + "º :"  + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(et.getFechas().get(i)));
			}
			System.out.println("Fecha de Fin: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(et.getFechaFinal()));
			System.out.println("Descripcion: " + et.getDescripcion());
			System.out.println("Localidades disponibles: " + ((et.getLocalidadesVenta())-(et.getLocalidadesVendidas())) ); 
			System.out.println("-------------------");
			aux++;
		}
		if(c.isEmpty())
		{
			System.err.println("No hay ningun Espectaculo de Temporada registrado en el sistema");
		}
	}
	
	public boolean existEspectaculoPunt(String title)
	{
		ArrayList<EspectaculoPuntual> v = new ArrayList<EspectaculoPuntual>();
		fichEspectaculoPuntToVec(v);
		for(EspectaculoPuntual ep : v) {
			if(ep.getTitulo().equals(title));
				return true;
		}
		return false;
	}
	
	public boolean existEspectaculoTemp(String title)
	{
		ArrayList<EspectaculoTemporada> v = new ArrayList<EspectaculoTemporada>();
		fichEspectaculoTempToVec(v);
		for(EspectaculoTemporada et : v) {
			if(et.getTitulo().equals(title));
				return true;
		}
		return false;
	}
	
	public boolean existEspectaculoMult(String title)
	{
		ArrayList<EspectaculoMultiple> v = new ArrayList<EspectaculoMultiple>();
		v = fichEspectaculoMultToVec(v);
		for(EspectaculoMultiple em : v) {
			if(em.getTitulo().equals(title));
				return true;
		}
		return false;
	}
	
	
	public void buscarEspectaculosPunt(String title)
	{
		ArrayList<EspectaculoPuntual> c = new ArrayList<EspectaculoPuntual>(); 
		c = fichEspectaculoPuntToVec(c);

		if(existEspectaculoPunt(title)==true)
		{
			for(int i=0; i<c.size(); i++)
			{
				if(c.get(i).getTitulo().equals(title))
				{
					System.out.println("ESPECTACULO PUNTUAL ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Fecha: " + c.get(i).getHoraFecha());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
					System.out.println("-------------------");
					System.out.println("");
				}
			}
		}
		else
		{
			System.err.println("El espectaculo no esta registrado en nuestro sistema."); 
			System.exit(-1); 
		}
	}
	
	public void buscarEspectaculosMult(String title)
	{
		ArrayList<EspectaculoMultiple> c = new ArrayList<EspectaculoMultiple>(); 
		c = fichEspectaculoMultToVec(c);

		if(existEspectaculoMult(title)==true)
		{
			for(int i=0; i<c.size(); i++)
			{
				if(c.get(i).getTitulo().equals(title))
				{
					System.out.println("ESPECTACULO MULTIPLE ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					for(int j = 0; j < c.get(i).getPases().size(); j++) 
					{
						System.out.println("Fecha "+ (j+1) + "º :"  + c.get(j).getPases());
					}
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) );
					System.out.println("-------------------");
					System.out.println("");  

				}
			}
		}
		else
		{
			System.err.println("El espectaculo no esta registrado en nuestro sistema."); 
			System.exit(-1); 
		}
	}
	
	public void buscarEspectaculosTemp(String title)
	{
		ArrayList<EspectaculoTemporada> c = new ArrayList<EspectaculoTemporada>(); 
		c = fichEspectaculoTempToVec(c);

		if(existEspectaculoTemp(title)==true)
		{
			for(int i=0; i<c.size(); i++)
			{
				if(c.get(i).getTitulo().equals(title))
				{
					System.out.println("ESPECTACULO DE TEMPORADA ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Fecha de Inicio: " + c.get(i).getFechaInicio());

					for(int j = 0; j < c.get(i).getFechas().size(); j++) 
					{
						System.out.println("Fecha "+ (j+1) + "º :"  + c.get(j).getFechas());
					}
					System.out.println("Fecha de Final: " + c.get(i).getFechaFinal());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) );
					System.out.println("-------------------");
					System.out.println("");
				}
			}
		}
		
		else
		{
			System.err.println("El espectaculo no esta registrado en nuestro sistema."); 
			System.exit(-1); 
		}
	}
	
	public void localidadesDisponiblesPunt()
	{
		ArrayList<EspectaculoPuntual> c = new ArrayList<EspectaculoPuntual>(); 
		c = fichEspectaculoPuntToVec(c);
			for(int i=0; i<c.size(); i++)
			{
				if((c.get(i).getLocalidadesVenta()) > (c.get(i).getLocalidadesVendidas()))
				{
					System.out.println((i+1) + "º ESPECTACULO PUNTUAL ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Fecha: " + c.get(i).getHoraFecha());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
				}
			}
		}
	
	public void localidadesDisponiblesMult()
	{
		ArrayList<EspectaculoMultiple> c = new ArrayList<EspectaculoMultiple>(); 
		c = fichEspectaculoMultToVec(c);
			for(int i=0; i<c.size(); i++)
			{
				if((c.get(i).getLocalidadesVenta()) > (c.get(i).getLocalidadesVendidas()))
				{
					System.out.println((i+1) + "º ESPECTACULO MULTIPLE ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					for(int j = 0; j < c.get(i).getPases().size(); j++) 
					{
						System.out.println("Fecha "+ (j+1) + "º :"  + c.get(j).getPases());
					}
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
				}
			}
		}
	
	public void localidadesDisponiblesTemp()
	{
		ArrayList<EspectaculoTemporada> c = new ArrayList<EspectaculoTemporada>(); 
		c = fichEspectaculoTempToVec(c);
			for(int i=0; i<c.size(); i++)
			{
				if((c.get(i).getLocalidadesVenta()) > (c.get(i).getLocalidadesVendidas()))
				{
					System.out.println((i+1) + "º ESPECTACULO DE TEMPORADA ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Fecha de Inicio: " + c.get(i).getFechaInicio());

					for(int j = 0; j < c.get(i).getFechas().size(); j++) 
					{
						System.out.println("Fecha "+ (j+1) + "º :"  + c.get(j).getFechas());
					}
					System.out.println("Fecha de Fin: " + c.get(i).getFechaFinal());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
				}
			}
		}
	
	public boolean existCategoriaPunt(String categoria)
	{
		ArrayList<EspectaculoPuntual> v = new ArrayList<EspectaculoPuntual>();
		v =fichEspectaculoPuntToVec(v);
		for(EspectaculoPuntual ep : v) {
			if(v == null)
			{
				return false;
			}
			else if(ep.getCategoria().equals(categoria));
				return true;
		}
		return false;
	}
	
	public boolean existCategoriaTemp(String categoria)
	{
		ArrayList<EspectaculoTemporada> v = new ArrayList<EspectaculoTemporada>();
		v =fichEspectaculoTempToVec(v);
		for(EspectaculoTemporada et : v) {
			if(v == null)
			{
				return false;
			}
			else if(et.getCategoria().equals(categoria));
				return true;
		}
		return false;
	}
	
	public boolean existCategoriaMult(String categoria)
	{
		ArrayList<EspectaculoMultiple> v = new ArrayList<EspectaculoMultiple>();
		v = fichEspectaculoMultToVec(v);
		for(EspectaculoMultiple em : v) {
			if(v == null) {
				return false;
			}
			else if(em.getCategoria().equals(categoria));
				return true;
		}
		return false;
	}
	
	public void buscarCategoriaPunt(String categoria)
	{
		ArrayList<EspectaculoPuntual> c = new ArrayList<EspectaculoPuntual>(); 
		c = fichEspectaculoPuntToVec(c);

		if(existCategoriaPunt(categoria)==true)
		{
			for(int i=0; i<c.size(); i++)
			{
				if(c.get(i).getCategoria().equals(categoria))
				{
					System.out.println((i+1) + "º ESPECTACULO PUNTUAL ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Fecha: " + c.get(i).getHoraFecha());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
				}
			}
		}
		else
		{
			System.err.println("No hay ningun espectaculo con esa categoria almacenado en el sistema"); 
		}
	}
	
	public void buscarCategoriaMult(String categoria)
	{
		ArrayList<EspectaculoMultiple> c = new ArrayList<EspectaculoMultiple>(); 
		c = fichEspectaculoMultToVec(c);

		if(existCategoriaMult(categoria)==true)
		{
			for(int i=0; i<c.size(); i++)
			{
				if(c.get(i).getCategoria().equals(categoria))
				{
					System.out.println((i+1) + "º ESPECTACULO MULTIPLE ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					for(int j = 0; j < c.get(i).getPases().size(); j++) 
					{
						System.out.println("Fecha "+ (j+1) + "º :"  + c.get(j).getPases());
					}
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
				}
			}
		}
		else
		{
			System.err.println("No hay ningun espectaculo con esa categoria almacenado en el sistema"); 
		}
	}
	
	public void buscarCategoriaTemp(String categoria)
	{
		ArrayList<EspectaculoTemporada> c = new ArrayList<EspectaculoTemporada>(); 
		c = fichEspectaculoTempToVec(c);

		if(existCategoriaTemp(categoria)==true)
		{
			for(int i=0; i<c.size(); i++)
			{
				if(c.get(i).getCategoria().equals(categoria))
				{
					System.out.println((i+1) + "º ESPECTACULO DE TEMPORADA ");
					System.out.println("-------------------");
					System.out.println("Titulo: " + c.get(i).getTitulo());
					System.out.println("Descripcion: " + c.get(i).getDescripcion());
					System.out.println("Fecha de Inicio: " + c.get(i).getFechaInicio());

					for(int j = 0; j < c.get(i).getFechas().size(); j++) 
					{
						System.out.println("Fecha "+ (j+1) + "º :"  + c.get(j).getFechas());
					}
					System.out.println("Fecha de Fin: " + c.get(i).getFechaFinal());
					System.out.println("Localidades disponibles :" + ((c.get(i).getLocalidadesVenta())-(c.get(i).getLocalidadesVendidas())) ); 
				}
			}
		}
		else
		{
			System.err.println("No hay ningun espectaculo con esa categoria almacenado en el sistema"); 
		}
	}
	
	public void updateEspectaculoPunt(String title) {
		
		ArrayList<Integer> ids = new ArrayList<Integer>();

		Scanner teclado1 = new Scanner(System.in);
		
		System.out.println("Introduzca la categoria del espectaculo:");
		String category = teclado1.nextLine();
		
		
		System.out.println("Introduca la descripcion del espectaculo:");
		String descripcion = teclado1.nextLine();
		
		
		System.out.println("Introduca el numero de localidades disponibles para el espectaculo:");
		int totalloc = Integer.parseInt(teclado1.nextLine());
		
		
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
    	
		System.out.println("Introduzca la fecha y hora del espectaculo");
		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
		String datetime = teclado1.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);

		borrarEspectaculoPunt(title);
		EspectaculoPuntToFich(title, category, descripcion, totalloc, soldloc, dateTime, ids);
		teclado1.close();
	}
	
public void updateEspectaculoMult(String title) {
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<LocalDateTime> pases = new ArrayList<LocalDateTime>();

		Scanner teclado1 = new Scanner(System.in);
		
		System.out.println("Introduzca la categoria del espectaculo:");
		String category = teclado1.nextLine();
		
		
		System.out.println("Introduca la descripcion del espectaculo:");
		String descripcion = teclado1.nextLine();
		
		
		System.out.println("Introduca el numero de localidades disponibles para el espectaculo:");
		int totalloc = Integer.parseInt(teclado1.nextLine());
		
		
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

		borrarEspectaculoMult(title);
		EspectaculoMultToFich(title, category, descripcion, soldloc, numpases, pases, ids);
		teclado1.close();
	}

public void updateEspectaculoTemp(String title) {
	
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ArrayList<LocalDateTime> pases = new ArrayList<LocalDateTime>();
	Scanner teclado1 = new Scanner(System.in);
	
	System.out.println("Introduzca la categoria del espectaculo:");
	String category = teclado1.nextLine();
	
	
	System.out.println("Introduca la descripcion del espectaculo:");
	String descripcion = teclado1.nextLine();
	
	
	System.out.println("Introduca el numero de localidades disponibles para el espectaculo:");
	int totalloc = Integer.parseInt(teclado1.nextLine());
	
	
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
	
	System.out.println("Introduzca la fecha de Inicio");
	System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
	String datetime1 = teclado1.nextLine();
	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	LocalDateTime dateTime1 = LocalDateTime.parse(datetime1, formatter1);
	
	System.out.println("Introduzca la fecha de Fin");
	System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
	String datetime2 = teclado1.nextLine();
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	LocalDateTime dateTime2 = LocalDateTime.parse(datetime2, formatter2);
	
	System.out.println("Indique cuantas veces se repetira el espectaculo");
	int numpases0 = Integer.parseInt(teclado1.nextLine());
	for(int i = 0; i < numpases0; i++)
	{
		System.out.println("Introduzca la fecha y hora del" + (i+1) + "º pase del espectaculo");
		System.out.println("Formato de fecha : AAAA-MM-DD HH:MM");
		String datetime0 = teclado1.nextLine();
		DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime0 = LocalDateTime.parse(datetime0, formatter0);
		pases.add(dateTime0);
	}

	borrarEspectaculoTemp(title);
	EspectaculoTempToFich(title, category, descripcion, totalloc, soldloc, dateTime1, dateTime2, pases, ids);
	teclado1.close();
}
	
}
