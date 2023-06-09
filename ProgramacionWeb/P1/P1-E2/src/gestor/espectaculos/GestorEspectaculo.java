package gestor.espectaculos;

import espectaculo.EspectaculoMultiple;
import espectaculo.EspectaculoPuntual;
import espectaculo.EspectaculoTemporada;
import fichero.espectaculos.IOEspectaculos;

public class GestorEspectaculo {

	
	//TODO: Fabric en vez de Singleton??


	// 1 - The singleton

			private static GestorEspectaculo instance = null;

			// Other properties
			private String mail = null;
			private IOEspectaculos c = new IOEspectaculos(); 
			
			// 2 - Private constructor

			private GestorEspectaculo(String Mail) 
			{
				this.mail=Mail; 
			}

			// 3 - Access point to the instance

			public static GestorEspectaculo getInstance(String Mail) {
				if(instance == null) {
					instance = new GestorEspectaculo(Mail);
					
				}
				return instance;
			}
			
			/**
			 * Funcion que crea una critica
			 * @param c Objeto de la clase critica
			 */	
			public void CrearEspectaculoPunt(EspectaculoPuntual ep)
			{
				if(instance!=null)
				{
					this.c.EspectaculoPuntToFich(ep.getTitulo(),ep.getCategoria(), ep.getDescripcion(), ep.getLocalidadesVenta(), ep.getLocalidadesVendidas(), ep.getHoraFecha(), ep.getCritica());
				}
			}
			
			public void CrearEspectaculoMult(EspectaculoMultiple em)
			{
				if(instance!=null)
				{
					this.c.EspectaculoMultToFich(em.getTitulo(),em.getCategoria(), em.getDescripcion(), em.getLocalidadesVenta(), em.getLocalidadesVendidas(), em.getPases(), em.getCritica());
				}
			}
			
			public void CrearEspectaculoTemp(EspectaculoTemporada et)
			{
				if(instance!=null)
				{
					this.c.EspectaculoTempToFich(et.getTitulo(),et.getCategoria(), et.getDescripcion(), et.getLocalidadesVenta(), et.getLocalidadesVendidas(), et.getFechaInicio(), et.getFechaFinal(), et.getFechas(), et.getCritica());
				}
			}
			
			/**
			 * Funcion que imprime por pantalla todas
			 * las criticas almacenadas
			 * 
			 */	
			
			public void consultarEspectaculosPunt()
			{
				if(instance!=null)
				{
					this.c.printAllEspectaculosPunt();
				}
			}
			
			public void consultarEspectaculosMult()
			{
				if(instance!=null)
				{
					this.c.printAllEspectaculosMult();
				}
			}
			
			public void consultarEspectaculosTemp()
			{
				if(instance!=null)
				{
					this.c.printAllEspectaculosTemp();
				}
			}
			

			
			public void buscarEspectaculosPunt(String title)
			{
				if(instance!=null)
				{
					this.c.buscarEspectaculosPunt(title);
				}
			}
			
			public void buscarEspectaculosMult(String title)
			{
				if(instance!=null)
				{
					this.c.buscarEspectaculosMult(title);
				}
			}
			
			public void buscarEspectaculosTemp(String title)
			{
				if(instance!=null)
				{
					this.c.buscarEspectaculosTemp(title);
				}
			}
			
			/**
			 * Funcion que busca una critica concreta
			 * @param c Objeto de la clase critica
			 */
			
			public void BorrarEspectaculosPunt(String title)
			{
				if(instance!=null)
					this.c.borrarEspectaculoPunt(title);
			}
			
			public void BorrarEspectaculosMult(String title)
			{
				if(instance!=null)
					this.c.borrarEspectaculoMult(title);
			}
			public void BorrarEspectaculosTemp(String title)
			{
				if(instance!=null)
					this.c.borrarEspectaculoTemp(title);
			}
			
			public void localidadesDisponiblesPunt()
			{
				if(instance!=null)
				{
					this.c.localidadesDisponiblesPunt();
				}
			}
			
			public void localidadesDisponiblesMult()
			{
				if(instance!=null)
				{
					this.c.localidadesDisponiblesMult();
				}
			}
			
			public void localidadesDisponiblesTemp()
			{
				if(instance!=null)
				{
					this.c.localidadesDisponiblesTemp();
				}
			}
			
			public void buscarCategoriaPunt(String categoria)
			{
				if(instance!=null)
				{
					this.c.buscarCategoriaPunt(categoria);
				}
			}
			
			public void buscarCategoriaMult(String categoria)
			{
				if(instance!=null)
				{
					this.c.buscarCategoriaMult(categoria);
				}
			}
			
			public void buscarCategoriaTemp(String categoria)
			{
				if(instance!=null)
				{
					this.c.buscarCategoriaTemp(categoria);
				}
			}
			
			public void updateEspectaculoPunt(String title)
			{
				if(instance!=null)
				{
					this.c.updateEspectaculoPunt(title);
				}
			}
			
			public void updateEspectaculoMult(String title)
			{
				if(instance!=null)
				{
					this.c.updateEspectaculoMult(title);
				}
			}
			
			public void updateEspectaculoTemp(String title)
			{
				if(instance!=null)
				{
					this.c.updateEspectaculoTemp(title);
				}
			}
}
			
			/**
			 * Funcion que anade un voto positivo
			 * a una critica concreta
			 * @param c Objeto de la clase critica
			 */
			
			/*
			public void votarCriticasPos(Critica c)
			{
				if(instance!=null)
					this.c.votarCriticaPos(this.mail, c);
			}
			
			/**
			 * Funcion que anade un voto positivo
			 * a una critica concreta
			 * @param id Identificador de la critica
			 */
			/*
			public void votarCriticasPos(int id)
			{
				if(instance!=null)
					this.c.votarCriticaPos(this.mail, id);
			}
			
			/**
			 * Funcion que anade un voto negativo
			 * a una critica concreta
			 * @param c Objeto de la clase critica
			 */
			/*
			public void votarCriticasNeg(Critica c)
			{
				if(instance!=null)
					this.c.votarCriticaNeg(this.mail, c);
			}
			
			/**
			 * Funcion que anade un voto positivo
			 * a una critica concreta
			 * @param id Identificador de la critica
			 */
		/*
			public void votarCriticasNeg(int id)
			{
				if(instance!=null)
					this.c.votarCriticaNeg(this.mail, id);
			}
			*/
			

