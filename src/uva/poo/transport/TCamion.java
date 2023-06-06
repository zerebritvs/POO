package uva.poo.transport;

import java.util.Calendar;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
 * Implementacion de un tipo de Trayecto Simple que utiliza como transporte un camion
 * @author marpere
 * @author juapage
*/
public class TCamion extends Simple{
	
	/**
	 * Metodo constructor de TCamion
	 * 
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 */
	public TCamion(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino, Calendar fechaOrigen, Calendar fechaDestino) {
		super(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}

	/**
	 * Obtener el precio del trayecto en camion
	 * @return precio en euros del trayecto
	 */
	@Override
	public double precioTrayecto() {
		return 200.0 + 4.5 * getDistancia();
	}

}
