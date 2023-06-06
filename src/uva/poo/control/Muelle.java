package uva.poo.control;

import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.maps.GPSCoordinate;
import uva.poo.storage.*;

/**
 * Implementacion de un tipo de dato que funciona como un conjunto de plazas(Muelle) que almacenan contenedores en un puerto
 * @author marpere
 * @author juapage
*/
public class Muelle {
	private ArrayList<Plaza> plazas;	//lista de plazas en el muelle
	private int identificador;	//digitos identificativo
	private GPSCoordinate gpsCoord;	//coordenadas gps
	private boolean operativo;	//esta operativo o fuera de servicio
	private boolean seco;	//esta cerca del agua o tierra adentro
	private boolean vias;	//hay vias para el uso de trenes
	private int maxPlazas;
	
	/**
	 * Crear un nuevo muelle a partir de un numero de plazas, coordenada GPS, su id, si es un muelle seco
	 * o no y si tiene acceso a vias de tren
	 * El muelle por defecto esta operativo
	 * @param numPlazas
	 * @param gpsCoord 
	 * @param identificador
	 * @param seco
	 * @param vias
	 * @throws IllegalArgumentException si el numero de plazas es negativo
	 * @throws IllegalArgumentException si el id del Muelle es no valido
	 */
	public Muelle(int numPlazas, GPSCoordinate gpsCoord, int identificador, boolean seco, boolean vias) {
		if (numPlazas < 0) {
			throw new IllegalArgumentException("El numero de plazas tiene que ser positivo");
		}
		maxPlazas = numPlazas;
		plazas = new ArrayList<>(numPlazas);
		this.gpsCoord = gpsCoord;
		operativo = true;
		setId(identificador);
		this.seco = seco;
		this.vias = vias;
	}
	
	/**
	 * Obtiene la id del Muelle
	 * @return identificador del muelle en el puerto
	 */
	public int getId() {
		return identificador;
	}
	
	/**
	 * Almacena la id del Muelle
	 * @param id
	 * @throws IllegalArgumentException si la id es distinta de 2 digitos
	 */
	public void setId(int id) {
		
		if( String.valueOf(id).length()!=2) {
			
			throw new IllegalArgumentException("Id de Muelle no valida");
		}
		identificador = id;
	}
	 /**
	  * Comprueba si el muelle esta operativo
	  * @return esta el muelle operativo
	  */
	public boolean isOperativo() {
		return operativo;
	}
	
	/**
	 * Obtiene las coordenadas del muelle
	 * @return coordenadas gps del puerto en el que esta el muelle
	 */
	public GPSCoordinate getGPSCoord() {
		return gpsCoord;
	}
	
	/**
	 * Hacer que el puerto este operativo o inoperativo
	 * @param operativo
	 */
	public void setOperativo(boolean operativo) {
		this.operativo = operativo;
	}
	
	/**
	 * Obtiene el numero de plazas
	 * @return numero de plazas
	 */
	public int getNumPlazas() {
		return maxPlazas;
	}
	
	/**
	 * Obtiene el numero de plazas vacias
	 * @return numero de plazas vacias 
	 */
	public int getPlazasVacias() {
		int num = 0;
		for (Plaza plaza : plazas) {
			if (!plaza.isEmpty()) {
				num++;
			} 
		}
		return maxPlazas - num;
	}
	 /**
	  * Obtiene el numero de plazas semillenas
	  * @return numero de plazas semillenas
	  */
	public int getPlazasSemillenas() {
		int num = 0;
		for (Plaza plaza : plazas) {
			if (plaza.hasSpace()) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * Comprueba si el muelle esta lleno
	 * @return true si el muelle esta lleno, false si queda hueco 
	 */
	public boolean isLleno() {
		for (int i = 0; i < maxPlazas; i++) {
			if (plazas.get(i).hasSpace()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Obtiene las plazas llenas 
	 * @return numero de plazas llenas
	 */
	public int getPlazasLlenas() {
		int num = 0;
		for (Plaza plaza : plazas) {
			if (plaza.isFull()) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * Encuentra el numero de la parcela de un contendor a partir de un codigo
	 * @param code
	 * @return el numero de la parcela donde esta el contenedor. 
	 * @throws IllegalArgumentException si el contenedor no esta en el muelle
	 */
	public int findContenedor(String code) {
		for (Plaza plaza : plazas) {
			if (plaza.hasContenedor(code)) {
				return plazas.indexOf(plaza);
			}
		}

		throw new IllegalArgumentException("El contenedor buscado no esta en el muelle");

	}
	
	/**
	 * Encuentra el nivel de la parcela donde esta el contenedor a partir de su codigo
	 * @param code
	 * @return el nivel de la parcela donde esta el contenedor.
	 * @throws
	 */
	public int findLevelContenedor(String code) {
		for (Plaza plaza : plazas) {
			if (plaza.findLevelContenedor(code) != -1) {
				return plaza.findLevelContenedor(code);
			}
		}

		throw new IllegalArgumentException("El contenedor no esta en el muelle");

	}
	
	/**
	 * Añade un contenedor a una parcela, apilandolo encima de otro si es posible
	 * @param contenedor
	 * @throws IllegalArgumentException si el muelle esta lleno 
	 */
	public void addContenedor(Contenedor contenedor) {
		if (plazas.isEmpty()) {
			plazas.add(new Plaza());
		}
		int cont = plazas.size()-1;
		if (plazas.get(cont).isEmpty() && contenedor.getClass().equals(FlatRack.class)) {
			plazas.get(cont).addContenedor(contenedor);
			plazas.add(new Plaza());
			plazas.get(++cont).addContenedor(contenedor);
		}
		
		if (plazas.get(cont).hasSpace()) {
			plazas.get(cont).addContenedor(contenedor);
		} else if (cont < maxPlazas){
			plazas.add(new Plaza());
			cont++;
			plazas.get(cont).addContenedor(contenedor);
			if (contenedor.getClass().equals(FlatRack.class)) {
				plazas.get(++cont).addContenedor(contenedor);
			}
		} else {
			throw new IllegalArgumentException("El muelle esta lleno");
		}
		
	}
	 /**
	  * Almacena los contenedores en el muelle
	  * @param contenedores
	  */
	public void setContenedores(List<Contenedor> contenedores) {
		if (plazas.isEmpty()) {
			plazas.add(new Plaza());
		}
		for (Contenedor contenedor: contenedores) {
			addContenedor(contenedor);	
		}
	}
	
	/**
	 * Eliminar un contenedor del muelle
	 * @param contenedor
	 */
	public void removeContenedor(Contenedor contenedor) {
		for (Plaza plaza : plazas) {
			if (plaza.hasContenedor(contenedor.getCodigo())) {
				plaza.removeContenedor(contenedor.getCodigo());
			}
		}
	}
	
	/**
	 * Obtiene el contendor a partir de un codigo
	 * @param code
	 * @return el contenedor a partir del codigo
	 * @throws IllegalArgumentException si no se encuentra el contenedor
	 */
	public Contenedor getContenedor(String code) {
		for (Plaza plaza : plazas) {
			if (plaza.hasContenedor(code)) {
				return plaza.getContenedor(code);
			}
		}
		throw new IllegalArgumentException("El contenedor no esta en el muelle");
	}
	
	
	/**
	 * El muelle puede manejar trayectos por barco o no
	 * @return es un muelle seco o no
	 */
	public boolean isSeco() {
		return seco;
	}
	
	/**
	 * Cambia la posibilidad de tener una infraestuctura para barcos
	 * @param seco
	 */
	public void setSeco(boolean seco) {
		this.seco = seco;
	}
	
	/**
	 * El muelle puede manejar trayectos por tren o no
	 * @return tiene vias o no
	 */
	public boolean hasVias() {
		return vias;
	}
	
	/**
	 * Cambia la posiblidad de tener una infraestructura para trenes
	 * @param vias
	 */
	public void setVias(boolean vias) {
		this.vias = vias;
	}
 }
