package uva.poo.transport;

import java.util.Calendar;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
 * Implementacion de un tipo de Trayecto Simple que utiliza como transporte un tren 
 * @author marpere
 * @author juapage
*/
public class TTren extends Simple{

	/**
	 * Metodo constructor de TTren
	 * 
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 * @throws IllegalArgumentException si algun muelle no soporta trenes
	 */
	public TTren(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino, Calendar fechaOrigen, Calendar fechaDestino) {
		super(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		if (!muelleOrigen.hasVias() || !muelleDestino.hasVias()) {
			throw new IllegalArgumentException("Un muelle no soporta trenes");
		}
	}

	/**
	 * Obtener el precio del trayecto en tren
	 * @return precio en euros del trayecto
	 */
	@Override
	public double precioTrayecto() {
		return 20.0 + 12.5 * getDistancia();
	}

}
