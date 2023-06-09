package gestor.criticas;

import critica.Critica;
import fichero.criticas.IOCriticas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Clase que llama a todas las funciones para la gestion de criticas.
 * @author Developers
 */	
public class GestorCriticas {

			/** 
		 	* Instancia del gestor de criticas;
		 	*/
			private static GestorCriticas instance = null;

			/**
			 * Mail del usuario que lanzó el gestor. Asociado a la instancia.
			 */
			private String mail = null;
			/**
			 * Clase IOCriticas para llamar a las funciones necesarias.
			 */
			private IOCriticas c = new IOCriticas(); 
	
			/**
			 * Constructor privado (patron Singleton).
			 * @param Mail del user que ejecuta el gestor
			 * @author Developers
			 */
			private GestorCriticas(String Mail) 
			{
				this.mail=Mail; 
			}

			/**
			 * Funcion para inicializar el gestor. Además escribe en el fichero properties
			 * la ruta del fichero criticas.txt
			 * @param Mail del usuario que ejecuta el gestor
			 * @return La instancia del gestor.
			 * @author Developers
			 */
			public static GestorCriticas getInstance(String Mail) {
				if(instance == null) {
					instance = new GestorCriticas(Mail);
					try {
						String path = new File("").getAbsolutePath();
						path = path + "/data.properties";
						File file = new File(path);
						Properties table = new Properties();
						FileInputStream in = new FileInputStream(file);
						table.load(in);
						in.close();
						String rutaAbsoluta = new File("").getAbsolutePath();
						String rutaFichero = rutaAbsoluta + "/criticas.txt";
						table.setProperty("CriticsFilePath", rutaFichero);		
						FileOutputStream fr = new FileOutputStream(file);
				        table.store(fr, "Properties");
				        fr.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return instance;
			}
			
			/**
			 * Funcion que crea una critica
			 * @param c Objeto de la clase critica
			 * @author Developers
			 */	
			public void crearCritica(Critica c)
			{
				if(instance!=null)
				{
					this.c.criticaToFich(c.getTitle(),c.getPuntuacion(), c.getResena(), c.getMail(), c.getLike(), c.getDislike(), c.getId(), c.getVotantes());
				}
			}
			
			/**
			 * Funcion que imprime por pantalla todas
			 * las criticas almacenadas
			 * @author Developers
			 * 
			 */	
			
			public void consultarCriticas()
			{
				if(instance!=null)
				{
					this.c.printAll();
				}
			}
			
			/**
			 * Funcion que busca una critica concreta
			 * @param mail Mail del autor
			 * @author Developers
			 */
			
			public void buscarCriticas(String mail)
			{
				if(instance!=null)
				{
					this.c.buscarCriticas(mail);
				}
			}
			
			/**
			 * Funcion que busca una critica concreta
			 * @param c Objeto de la clase critica
			 * @author Developers
			 */
			
			public void BorrarCritica(Critica c)
			{
				if(instance!=null)
					this.c.borrarCritica(c.getId(), this.mail);
			}
			

			/**
			 * Funcion que anade un voto positivo
			 * a una critica concreta
			 * @param id Identificador de la critica
			 * @author Developers
			 */
			
			public void votarCriticasPos(int id)
			{
				if(instance!=null)
					this.c.votarCriticaPos(this.mail, id);
			}

			/**
			 * Funcion que anade un voto positivo
			 * a una critica concreta
			 * @param id Identificador de la critica
			 * @author Developers
			 */
		
			public void votarCriticasNeg(int id)
			{
				if(instance!=null)
					this.c.votarCriticaNeg(this.mail, id);
			}
			
			
			
}
