package uva.poo.storage;

import uva.poo.transport.Trayecto;

/**
 * Implementacion de un tipo de Contenedor Estandar con las caracteristicas basicas de un Contendor
 * @author marpere
 * @author juapage
*/

public class Estandar extends Contenedor{
	private boolean techo;
	
	/**
	 * Metodo constructor de Estandar
	 * @param transito
	 * @param techo
	 * @param volumen
	 * @param peso
	 * @param cargaMax
	 * @param codigo
	 */
	public Estandar(boolean transito, boolean techo, double volumen, double peso, double cargaMax, String codigo) {
		super(transito, volumen, peso, cargaMax, codigo);
		this.techo = techo;
	}
	
	/**
	 * Comprueba si el contendor de tipo Estandar tiene techo o no
	 * @return techo 
	 */
	@Override
	public boolean hasTecho() {
		return techo;
	}

	/**
	 * Pone o quita el techo al contenedor de tipo Estandar
	 * @param techo
	 */
	@Override
	public void setTecho(boolean techo) {
		this.techo = techo;
	}

	/**
	 * Añade un trayecto al contenedor de tipo Estandar 
	 * @param ruta
	 * 
	 */
	@Override
	public void addRuta(Trayecto ruta) {
		addRutaToList(ruta);
	}

}
