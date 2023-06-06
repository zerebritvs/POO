package uva.poo.transport;

import java.util.Calendar;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
 * Implementacion de un tipo de Trayecto Combinado que utiliza como transporte un barco y un camion
 * @author marpere
 * @author juapage
*/
public class PackCamionBarco extends Combinado{
	
	/**
	 * Crea un trayecto en camion y barco reduciendo el precio del viaje
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 * @throws IllegalArgumentException si alguno de los muelles no soporta barcos
	 */

	public PackCamionBarco(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino,
			Calendar fechaOrigen, Calendar fechaDestino) {
		super(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		if(muelleOrigen.isSeco() || muelleDestino.isSeco()) {
			throw new IllegalArgumentException("Un muelle no soporta barcos");
		}
	}

	/**
	 * Obtiene el precio del trayecto PackCamionBarco en euros
	 * @return el precio del trayecto en euros
	 */
	@Override
	public double precioTrayecto() {
		return 0.85 * 4000.0 * totalDias(getFechaOrigen(), getFechaDestino()) + 4.5 * getDistancia() + 200.0;
	}

}
