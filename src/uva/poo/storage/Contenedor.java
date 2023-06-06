
package uva.poo.storage;

import java.util.ArrayList;
import java.util.HashMap;
import uva.poo.transport.Trayecto;

/**
 * Implementacion de un tipo de dato que representa un contenedor el cual tiene 
 * diferentes atributos y propiedades basicas
 * @author marpere
 * @author juapage
 */

public abstract class Contenedor {
	
	private boolean transito; // Indica si el contenedor esta en transito = true o si no lo esta = false
	private double volumen; // Indica el volumen del contenedor en m3 de manera predeterminada
	private double peso; // Indica el peso del contenedor en Kg de manera predeterminada
	private double cargaMax; // Indica la carga maxima autorizada del contenedor
	private String codigo; // Ejemplo: AAAU123456
	private ArrayList<Trayecto> rutas; // Lista con los trayectos del contenedor
	
	/**
	 * Constructor del objeto contenedor
	 * @param transito
	 * @param volumen
	 * @param peso
	 * @param cargaMax
	 * @param codigo
	 * @throws IllegalArgumentException si las medidas del contenedor son negativas
	 * @throws IllegalArgumentException si el codigo no es valido
	 * @throws IllegalArgumentException si la longitud del codigo es menor a 10 o mayor que 11
	 * @throws IllegalArgumentException si el codigo no tiene checkDigit 
	 */
	public Contenedor(boolean transito, double volumen, double peso, double cargaMax, String codigo) {
		if (volumen < 0 || peso < 0 || cargaMax < 0) {
			throw new IllegalArgumentException("Las medidas del contenedor deben ser positivas");
		}
		if(codigo.length()<10 || codigo.length() > 11) {
			throw new IllegalArgumentException("Codigo de contendor no valido");
			
		}
		this.transito = transito;
		this.volumen = volumen;
		this.cargaMax = cargaMax;
		setPeso(peso);
		this.codigo = calculaCheckDigit(codigo);
		rutas = new ArrayList<>();		
	}
	
	
	protected String calculaCheckDigit(String codigo) {
		int checkDigit=0;
		int suma=0;
		int cont=0;
		HashMap<Character, Integer> letrasYValores= new HashMap<>();
		for(int i=65;i<91;i++) {
			if((i-55)==11||(i-55)==21||(i-55)==31) {
				cont++;
				letrasYValores.put((char)(i), i-55+cont);
			} else {				
				letrasYValores.put((char)(i), i-55+cont);
			}
		}
		
		for(int j=0;j<10;j++) {
			if(codigo.charAt(j)>64) {
				suma=(int) (suma+Math.pow(2, j)*letrasYValores.get(codigo.charAt(j)));
			}else {
				suma=(int) (suma+Math.pow(2, j)*(codigo.charAt(j)-48));
			}
		}
		
		int divide11=suma/11;
		checkDigit=suma-(divide11*11);
		if (checkDigit==10) {
			checkDigit=0;
		}
		
		if (codigo.length()<11) {
	
			throw new IllegalArgumentException("El codigo no tiene check digit");
			
		} else {
			if (codigo.charAt(10)-48==checkDigit) {
				return codigo;
			} else {
				throw new IllegalArgumentException("El check digit no es valido");
			}
		}	
	}
	
	protected abstract void addRuta(Trayecto trayecto);
	
	protected void addRutaToList(Trayecto ruta) {
		rutas.add(ruta);
	}
	
	/**
	 * Obtiene una ruta a traves de su indice
	 * @param indice del trayecto que se quiere obtener
	 * @return trayecto del contenedor
	 */
	public Trayecto getRuta(int indice) {
		return rutas.get(indice);
	}
	
	/**
	 * Calcula el precio del contenedor a partir de todas sus rutas
	 * @return el precio del contenedor en euros
	 */
	public double precioContenedor() {
		double precio=0;
		
		for(Trayecto trayecto : rutas) {
			precio = precio + trayecto.precioTrayecto();
		}
		return precio;
	}
	
	/**
	 * Comprueba si el contenedor esta en transito
	 * @return true si esta en transito y false si no lo esta
	 */
	public boolean isOnTransito() {
		return transito;
	}
	
	/**
	 * Comprueba si el contenedor tiene techo
	 * @return true si el contenedor tiene techo y false si no tiene
	 */
	public abstract boolean hasTecho();
	
	/**
	 * Obtiene el volumen del contendor en m3
	 * @return volumen en m3
	 */
	public double getVolumen() {
		return volumen;
	}
	
	/**
	 * Obtiene el volumen del contenedor en pies cubicos
	 * @return volumen en pies cubicos
	 */
	public double getPies3() {
		return volumen*35.3147;
	}
	
	/**
	 * Obtiene el peso en kg
	 * @return peso en kg
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Obtiene el peso del contenedor en libras
	 * @return peso en libras
	 */
	public double getLibras() {
		return peso*2.20462;
	}
	
	/**
	 * Obtiene la cargaMaxima del contenedor en kg
	 * @return cargaMax en kg
	 */
	public double getCargaMax() {
		return cargaMax;
	}
	
	/**
	 * Obtiene el codigo del contenedor 
	 * @return codigo identificador
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Almacena el transito
	 * @param transito
	 */
	public void setTransito(boolean transito) {
		this.transito = transito;
	}
	
	/**
	 * Almacena el techo
	 * @param techo
	 */
	public abstract void setTecho(boolean techo);
	
	/**
	 * Almacena el volumen
	 * @param volumen en m3
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Almacena el volumen
	 * @param volumen en pies cubicos
	 */
	public void setPies3(double volumen) {
		double nuevoVolumen = volumen/35.3147;
		setVolumen(nuevoVolumen);
	}
	
	/**
	 * Almacena el peso
	 * @param peso en kg
	 * @throws IllegalArgumentException si peso es mayor que la carga maxima
	 */
	public void setPeso(double peso) {
		if(peso>getCargaMax()) {
			throw new IllegalArgumentException("Peso excede la carga maxima");
		}
		this.peso = peso;
	}
	
	/**
	 * Almacena el peso
	 * @param peso en libras
	 */
	public void setLibras(double peso) {
		double nuevoPeso = peso/2.20462;
		setPeso(nuevoPeso);
	}
	
	/**
	 * Almacena la carga Maxima
	 * @param cargaMax en kg
	 */
	public void setCargaMax(double cargaMax) {
		this.cargaMax = cargaMax;
	}
	
	/**
	 * Almacena el codigo
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = calculaCheckDigit(codigo);
	}
}
