package uva.poo.control;

import java.util.List;
import java.util.ArrayList;

import uva.poo.storage.*;

/**
 * Parcela donde se pueden almacenar hasta 4 contenedores
 * @author marpere
 * @author juapage
 *
 */
public class Plaza {
	private ArrayList<Contenedor> contenedores;	//lista de contenedores en la plaza
	
	/**
	 * Crea una plaza vacia
	 */
	public Plaza() {
		this(new ArrayList<>());
	}
	
	/**
	 * Crea una plaza con contenedores (maximo 4)
	 * @param contenedores
	 * @throws illegalArgumentExceprion si hay mas de 4 contenedores
	 */
	public Plaza(List<Contenedor> contenedores) {
		if (contenedores.size() > 4) {
			throw new IllegalArgumentException("Como mucho puede haber 4 contenedores en la lista");
		}
		setContenedores(contenedores);
	}
	
	/**
	 * Obtiene una lista con los contendores en la plaza
	 * @return contendores en la plaza 
	 */
	public List<Contenedor> getContenedores() {
		ArrayList<Contenedor> temp = new ArrayList<>();
		for (Contenedor contenedor : contenedores) {
			temp.add(contenedor);
		}
		
		return temp;
	}
	
	/**
	 * Añade contenedores a una plaza (maximo 4)
	 * @param contenedores
	 * @throws IllegalArgumentException si la lista de contenedores tiene un tamaño superior a 4
	 */
	public void setContenedores(List<Contenedor> contenedores) {
		if (contenedores.size() > 4) {
			throw new IllegalArgumentException("Tamaño maximo de la plaza excedido");
		}
		this.contenedores = (ArrayList<Contenedor>) contenedores;
	}
	
	/**
	 * Añade un contenedor a una plaza si no esta llena
	 * @param contenedor
	 * @throws IllegalAccessException si la plaza esta llena
	 */
	public void addContenedor(Contenedor contenedor) {
		if (!isFull()) {
			contenedores.add(contenedor);
		} else {
			throw new IllegalArgumentException("La plaza esta llena");
		}
	}
	
	/**
	 * Busca a partir de el codigo el contenedor en la plaza
	 * @param codigo
	 * @return contenedor de la plaza a partir del codigo
	 * @throws NullPointerException si el contenedor no esta en la plaza
	 */
	public Contenedor getContenedor(String codigo) {
		for (Contenedor contenedor : contenedores) {
			if (contenedor.getCodigo().equals(codigo)) {
				return contenedor;
			}
		}
		throw new NullPointerException("El contenedor buscado no esta en la plaza");
	}
	
	
	/**
	 * Elimina un contenedor de la plaza a partir de su codigo
	 * @param codigo
	 * @throws illegalArgumentException si no encuentra el contenedor de la plaza
	 */
	public void removeContenedor(String codigo) {
		boolean esta = false;
		int index = 0;
		
		for (Contenedor contenedor : contenedores) {
			
			if (contenedor.getCodigo().equals(codigo)) {
				index = contenedores.indexOf(contenedor);
				esta = true;
			}
			
		}
		
		if(!esta) {
			throw new IllegalArgumentException("Codigo no encontrado");
		}else {
			contenedores.remove(contenedores.get(index));
		}
		
		
	}
	
	/**
	 * Comprueba si el codigo coincide con alguno de los contenedores en la plaza
	 * @param codigo
	 * @return Si el contenedor esta en la plaza o no
	 * @throws IllegalArgumentException si el contenedor no esta en la plaza
	 */
	public boolean hasContenedor(String codigo) {
		for (Contenedor cont : contenedores) {
			if (cont.getCodigo().equals(codigo)) {
				return true;
			}
		}
		throw new IllegalArgumentException("El contenedor no esta en la plaza");
	}
	
	/**
	 * Obtiene el nivel en la plaza donde se encuentra un contenedor
	 * @param codigo
	 * @return el nivel en la plaza en el que se encuentra el contenedor
	 * @throws IllegalArgumentException si el contenedor no esta en la plaza 
	 */
	public int findLevelContenedor(String codigo) {
		for (Contenedor cont : contenedores) {
			if (cont.getCodigo().equals(codigo)) {
				return contenedores.indexOf(cont);
			}
		}
		throw new IllegalArgumentException("El contenedor no esta en la plaza");
	}
	
	/**
	 * Comprueba si esta o no vacia la plaza
	 * @return hay contenedores o no
	 */
	public boolean isEmpty() {
		return contenedores.isEmpty();
	}
	
	/**
	 * Comprueba si la plaza tiene o no espacio para apilar mas contenedores
	 * @return true si la plaza tiene espacio para apilar otro contenedor
	 * @return false si no se pueden apilar mas
	 */
	public boolean hasSpace() {
		if (isEmpty()) {
			return true;
		}
		for (Contenedor contenedor : contenedores) {
			if (!contenedor.hasTecho()) {
				return false;
			}	
		} 
		return (contenedores.size() < 4);
	}
	
	/**
	 * Comprueba si la plaza esta llena o vacia
	 * @return true si esta la plaza llena
	 * @return false si la plaza esta vacia
	 */
	public boolean isFull() {
		for (Contenedor contenedor : contenedores) {
			if (!contenedor.hasTecho()) {
				return true;
			}
		}
		return (contenedores.size() >= 4);
	}
	
	/**
	 * Obtiene los contendores en la plaza
	 * @return string con los conenedores en la plaza	
	 */
	@Override
	public String toString() {
		return "Contenedores en la plaza: " + contenedores.toString();
	}
	
}
