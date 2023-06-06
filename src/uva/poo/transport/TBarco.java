package uva.poo.transport;

import java.util.Calendar;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
 * Implementacion de un tipo de Trayecto Simple que utiliza como transporte un barco 
 * @author marpere
 * @author juapage
*/
public class TBarco extends Simple{
	
	/**
	 * Metodo constructor de TBarco
	 * 
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 * @throws IllegalArgumentException si alguno de los muelles no soporta barcos
	 */
	public TBarco(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino,	Calendar fechaOrigen, Calendar fechaDestino) {
		super(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		if(muelleOrigen.isSeco() || muelleDestino.isSeco()) {
			throw new IllegalArgumentException("Un muelle no soporta barcos");
		}
	}
	
	/**
	 * Obtener el precio del trayecto en barco
	 * @return precio en euros del trayecto
	 */
	@Override
	public double precioTrayecto() {		
		return totalDias(getFechaOrigen(), getFechaDestino()) * 4000.0;
	}

}
