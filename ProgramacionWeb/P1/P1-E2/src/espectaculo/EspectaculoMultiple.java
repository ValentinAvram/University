package espectaculo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EspectaculoMultiple extends AbstractEspectaculo {
	private ArrayList<LocalDateTime> pases;
	
	public EspectaculoMultiple() {
		
	}	
	public ArrayList<LocalDateTime> getPases() {
		return this.pases;
	}
	public void setPases(ArrayList<LocalDateTime> pases) {
		this.pases = pases;
	}
	
}
