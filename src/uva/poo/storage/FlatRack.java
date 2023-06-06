package uva.poo.storage;

import uva.poo.transport.*;

/**
 * Implementacion de un tipo de Contenedor FlatRack con alguna caracteristica distinta a Estandar
 * @author marpere
 * @author juapage
*/

public class FlatRack extends Contenedor{
	
	/**
	 * Metodo constructor de FlatRack
	 * @param transito
	 * @param peso
	 * @param cargaMax
	 * @param codigo
	 */
	public FlatRack(boolean transito, double peso, double cargaMax, String codigo) {
		super(transito, 0, peso, cargaMax, codigo);
	}
	
	/**
	 * Comprueba si el contenedor tiene techo
	 * @return false flatRack no tiene techo 
	 */
	@Override
	public boolean hasTecho() {
		return false;
	}
	
	/**
	 * Tira una excepcion si se intenta cambiar el volumen de FlatRack
	 * @throws IllegalArgumentException si se intenta cambiar el volumen de FlatRack
	 */
	@Override
	public void setVolumen(double volumen){
		throw new IllegalArgumentException("FlatRack no tiene volumen");
	}
	
	/**
	 * Tira una excepcion si se intenta cambiar el volumen de FlatRack en pies3
	 * @throws IllegalAccessexception si se intenta cambiar el volumen de FlatRack en pies3
	 */
	@Override
	public void setPies3(double volumen){
		throw new IllegalArgumentException("FlatRack no tiene volumen");
	}
	
	
	/**
	 * Pone o quita techo al contenedor
	 * @throws IllegalStateException si pones techo a flatRack
	 */
	@Override
	public void setTecho(boolean techo) {
		if(techo) {
			throw new IllegalStateException("FlatRack no puede tener techo");
		}
	}
	
	/**
	 * Añade una ruta al contenedor de tipo FlatRack
	 * @param ruta
	 * @throws IllegalArgumentException si esa ruta es en camion
	 * 
	 */
	@Override
	public void addRuta(Trayecto ruta) {
		if (ruta.getClass().equals(TCamion.class) || ruta.getClass().equals(PackCamionBarco.class) || ruta.getClass().equals(PackCamionTren.class)) {
			throw new IllegalArgumentException("FlatRack no puede viajar en Camion");
		}
		addRutaToList(ruta);
	}

}
