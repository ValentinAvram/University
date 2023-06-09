package factory;

import espectaculo.EspectaculoMultiple;
import espectaculo.EspectaculoPuntual;
import espectaculo.EspectaculoTemporada;

public abstract class AbstractFactory {
	public abstract EspectaculoPuntual createEspPunt();
	public abstract EspectaculoMultiple createEspMult();
	public abstract EspectaculoTemporada createEspTemp();

}
