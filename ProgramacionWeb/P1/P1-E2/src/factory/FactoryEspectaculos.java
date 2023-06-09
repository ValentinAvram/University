package factory;

import espectaculo.EspectaculoMultiple;
import espectaculo.EspectaculoPuntual;
import espectaculo.EspectaculoTemporada;

public class FactoryEspectaculos extends AbstractFactory {
	
	@Override
	public EspectaculoMultiple createEspMult() {
		EspectaculoMultiple esp = new EspectaculoMultiple();
		return esp;
	}
	@Override
	public EspectaculoPuntual createEspPunt() {
		EspectaculoPuntual esp = new EspectaculoPuntual();
		return esp;
	}
	@Override
	public EspectaculoTemporada createEspTemp() {
		EspectaculoTemporada esp = new EspectaculoTemporada();
		return esp;
	}
}
