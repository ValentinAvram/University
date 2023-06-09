package espectaculo;

import java.time.LocalDateTime;

public class EspectaculoPuntual extends AbstractEspectaculo {
	private LocalDateTime horaFecha;
	
	public EspectaculoPuntual() {
		
	}
	
	public LocalDateTime getHoraFecha() {
		return this.horaFecha;
	}
	public void setHoraFecha(LocalDateTime horaFecha) {
		this.horaFecha = horaFecha;
	}
}
