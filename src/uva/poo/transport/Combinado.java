package uva.poo.transport;

import java.util.Calendar;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
 * Implementacion de un tipo de Trayecto Combinado 
 * @author marpere
 * @author juapage
*/
public abstract class Combinado extends Trayecto{
	
	/**
	 * Metodo constructor de trayecto de tipo Combinado
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 */
	public Combinado(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino, Calendar fechaOrigen, Calendar fechaDestino) {
		super(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
}
