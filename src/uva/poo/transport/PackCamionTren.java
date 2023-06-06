package uva.poo.transport;

import java.util.Calendar;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
* Implementacion de un tipo de Trayecto Combinado que utiliza como transporte un tren y un camion
* @author marpere
* @author juapage
*/
public class PackCamionTren extends Combinado{

	/**
	 * Crea un trayecto en camion y en tren haciendo el viaje mas barato
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 * @throws IllegalArgumentException si algun muelle no tiene soporte para trenes
	 */
	public PackCamionTren(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino,	Calendar fechaOrigen, Calendar fechaDestino) {
		super(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		if (!muelleOrigen.hasVias() || !muelleDestino.hasVias()) {
			throw new IllegalArgumentException("Un muelle no soporta trenes");
		}
	}

	
	/**
	 * Obtiene el precio del trayecto PackCamionTren en euros
	 * @return el precio del trayecto en euros
	 */
	@Override
	public double precioTrayecto() {
		return 10.0 * getDistancia();
	}

}
