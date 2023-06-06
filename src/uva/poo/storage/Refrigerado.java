package uva.poo.storage;


import uva.poo.transport.PackCamionTren;
import uva.poo.transport.TTren;
import uva.poo.transport.Trayecto;

/**
 * Implementacion de un tipo de Contenedor Refrigerado con alguna caracteristica modificada de Contenedor
 * @author marpere
 * @author juapage
*/

public class Refrigerado extends Contenedor{
	
	/**
	 * Metodo Constructor de Refrigerado
	 * @param transito
	 * @param volumen
	 * @param peso
	 * @param cargaMax
	 * @param codigo
	 */
	public Refrigerado(boolean transito, double volumen, double peso, double cargaMax, String codigo) {
		super(transito, volumen, peso, cargaMax, codigo);
	}
	
	/**
	 * Comprueba si el contendor tiene techo
	 * @return true este tipo de contendor siempre tiene techo
	 */
	@Override
	public boolean hasTecho() {
		return true;
	}
	
	/**
	 * Pone o quita el techo al contenedor
	 * @throws IllegalStateException si quitas techo a Refrigerado
	 */
	@Override
	public void setTecho(boolean techo) {
		if(!techo) {
			throw new IllegalStateException("Refrigerado tiene que tener techo");
		}
	}
	
	/**
	 * Añade una ruta al contendor de tipo refrigerado
	 * @param ruta 
	 * @throws IllegalArgumentException si la ruta añadida es por tren
	 */
	@Override
	public void addRuta(Trayecto ruta) {
		if (ruta.getClass().equals(TTren.class) || ruta.getClass().equals(PackCamionTren.class)) {
			throw new IllegalArgumentException("Refrigerado no puede ser transportado en tren");
		}
		addRutaToList(ruta);
	}
}
