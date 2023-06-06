package uva.poo.control;

import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Implementacion de un tipo de dato que representa un Puerto
 * el cual proporciona las funciones basicas para la buena gestion del Puerto
 * @author marpere
 * @author juapage
 */
public class Puerto {
	
	private String id;  // Ejemplo: ES-VAL, donde ES pertenece al nombre del pais y VAL a la localidad de Valencia
	private ArrayList<Muelle> listaMuelles; // Vector de Muelles
	
	/**
	 * Inicializacion del objeto Puerto indicando su id y si esta lleno 
	 * @param id del puerto
	 * @throws IllegalArgumentException si la id es no valida
	 */
	public Puerto(String id) {
		setId(id);
		listaMuelles = new ArrayList<>();
	}
	
	
	/**	
	 * Añade un nuevo muelle a un puerto, con id = id del muelle anterior +1
	 * @param numPlazas del muelle
	 * @param GPSMuelle
	 * @param si el muelle es seco o no
	 * @param si el muelle tiene acceso a trenes o no
	 */
	public void addMuelle(int numPlazas, GPSCoordinate gpsMuelle, boolean seco, boolean vias) {
		int identificador = listaMuelles.size();
		 Muelle nuevoMuelle = new Muelle(numPlazas, gpsMuelle, identificador, seco, vias);
		 addMuelle(nuevoMuelle);
	}
	
	/**
	 * Añade un muelle a la lista de muelles del puerto
	 * @param muelle
	 */
	public void addMuelle(Muelle muelle) {
		listaMuelles.add(muelle);
	}
	
	/**
	 * Elimina un Muelle de un Puerto a partir de su identificador
	 * @param id del muelle
	 * @throws IllegalArgumentException si la id no coincide con algun muelle del puerto
	 */
	public void eliminaMuelle(int id) {
		boolean esta = false;
		int index = 0;
		
		for(Muelle muelle : listaMuelles) {
			
			if(muelle.getId()==id){
				index = listaMuelles.indexOf(muelle);
				esta = true;
			}
		}
		
		if(!esta) {
			throw new IllegalArgumentException("Id no encontrada");
		}else {
			listaMuelles.remove(index);
		}
		
	}
	
	/**
	 * Obtiene el muelle a partir de la id
	 * @param id
	 * @return muelle a partir de la id
	 * @throws IllegalArgumentException si la id no coincide con algun muelle del puerto
	 */
	public Muelle getMuelle(int id) {
		for (Muelle muelle: listaMuelles) {
			if (muelle.getId() == id) {
				return muelle;
			}
		}
		throw new IllegalArgumentException("Id no encontrada");
	}
	/**
	 * Obtiene el numero de muelles en el puerto
	 * @return numero de muelles en el puerto
	 */
	public int getNumMuelles() {
		return listaMuelles.size();
	}
	
	/**
	 * Obtiene si el Puerto esta completo.
	 * Entendiendo por completo que no hay posibilidad de almacenar ningun contenedor mas
	 * @return true indicando que esta lleno o false si queda hueco
	 */
	public boolean isLleno() {
		for(Muelle muelle: listaMuelles) {
			if(!muelle.isLleno()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Obtiene una lista de los Muelles que estan operativos
	 * @return ArrayList<Muelle> donde aparecen los muelles operativos
	 */
	public List<Muelle> muellesOperativos() {
		ArrayList<Muelle> listaMuellesOperativos = new ArrayList<>();
		for(Muelle muelle :listaMuelles) {
			if(muelle.isOperativo()) {
				listaMuellesOperativos.add(muelle);
			}
		}
		return listaMuellesOperativos;
	}
	
	/**
	 * Obtiene una lista de los Muelles que tienen espacio
	 * @return ArrayList<Muelle> donde aparecen los muelles con espacio
	 */
	public List<Muelle> muellesConEspacios() {
		ArrayList<Muelle> listaMuellesConEspacios = new ArrayList<>();
		for(Muelle muelle: listaMuelles) {
			if(!muelle.isLleno()) {
				 listaMuellesConEspacios.add(muelle);
			}
		}
		return listaMuellesConEspacios;
		
	}
	
	/**
	 * Obtiene una lista de los Muelles que se encuentran a una distancia menor dada de cierto punto GPS
	 * @return ArrayList<Muelle> donde aparecen los Muelles a una distancia inferior dada cierta coordenada GPS 
	 */
	public List<Muelle> distanciaMenorX(double distancia, GPSCoordinate gpsCoord) {
		ArrayList<Muelle> listaMuellesEnRango = new ArrayList<>();
		for(Muelle muelle: listaMuelles) {
			if(gpsCoord.getDistanceTo(muelle.getGPSCoord()) <= distancia){
				listaMuellesEnRango.add(muelle);
			}
		}
		return listaMuellesEnRango;
	}
	
	/**
	 * Obtiene la id del puerto
	 * @return id del Puerto
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Almacena la id
	 * @param id
	 * @throws IllegalArgumentException si la id es no valida
	 */
	public void setId(String id) {
		
		String patron = "[A-Z]{2}[-][A-Z]{3}";
		
		if(!id.matches(patron)) {
			throw new IllegalArgumentException("Id del Puerto no valida");
		}
		
		this.id = id;
	}
	
}
