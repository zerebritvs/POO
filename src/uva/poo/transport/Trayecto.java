
package uva.poo.transport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

/**
 * Implementacion de un tipo de dato que determina las trayectorias y fechas de los recorridos y los costes de las rutas
 * @author marpere
 * @author juapage
 *
 */
public abstract class Trayecto {
	
	private Muelle muelleOrigen; // Muelle de origen
	private Muelle muelleDestino; // Muelle de destino
	private Puerto puertoOrigen; // Puerto de origen 
	private Puerto puertoDestino; // Puerto de destino
	private Calendar fechaOrigen; // Fecha de origen Ej: 20/09/2016
	private Calendar fechaDestino; // Fecha de destino Ej: 20/09/2017	
	
	/**
	 * Constructor de Trayecto
	 *
	 * @param muelleOrigen
	 * @param muelleDestino
	 * @param puertoOrigen
	 * @param puertoDestino
	 * @param fechaOrigen
	 * @param fechaDestino
	 * 
	 * @throws IllegalArgumentException cuando fecha es invalida
	 */
	
	public Trayecto(Muelle muelleOrigen, Muelle muelleDestino, Puerto puertoOrigen, Puerto puertoDestino, Calendar fechaOrigen, Calendar fechaDestino) {
		this.muelleOrigen = muelleOrigen;
		this.muelleDestino = muelleDestino;
		this.puertoOrigen = puertoOrigen;
		this.puertoDestino = puertoDestino;
		this.fechaOrigen = fechaOrigen;
		this.fechaDestino = fechaDestino;
		
		if (!isFechaValid()) {
			throw new IllegalArgumentException("Fechas no validas");
		}
	}
	
	private boolean isFechaValid() {
		return fechaOrigen.getTimeInMillis() <= fechaDestino.getTimeInMillis();
	}
	
	/**
	 * Obtiene el precio del trayecto en euros
	 * @param costePorDia
	 * @param costePorMilla
	 * @return precio en euros del trayecto
	 */
	public abstract double precioTrayecto();		
	
	protected int totalDias(Calendar fechaOrigen2, Calendar fechaDestino2) {
		long dias = 0;
		dias = fechaDestino2.getTimeInMillis()-fechaOrigen2.getTimeInMillis();
		dias = TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS);
		return (int)dias;
	}
	/**
	 * Obtiene la informacion completa del trayecto: indicando localidad y pais del puerto de origen/destino y fecha de inicio/fin del trayecto  
	 * @return String con la informacion completa del trayecto
	 */
	public String getInfo() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return "Localidad y pais del puerto origen: " + puertoOrigen.getId() + " partiendo del muelle con identificador " + muelleOrigen.getId() + " y fecha de inicio: "
		+ sdf.format(fechaOrigen.getTime()) + "\nLocalidad y pais del puerto destino: " + puertoDestino.getId() + " llegando al muelle con identificador " 
		+ muelleDestino.getId()+" y fecha de llegada: " + sdf.format(fechaDestino.getTime());
		
	}

	/**
	 * Obtiene el muelle de origen del trayecto
	 * @return muelleOrigen del trayecto
	 */
	public Muelle getMuelleOrigen() {
		return muelleOrigen;
	}

	/**
	 * Almacena el muelle de origen del trayecto
	 * @param muelleOrigen
	 */
	public void setMuelleOrigen(Muelle muelleOrigen) {
		this.muelleOrigen = muelleOrigen;
	}

	/**
	 * Obtiene el muelle de destino del trayecto
	 * @return muelleDestino del trayecto
	 */
	public Muelle getMuelleDestino() {
		return muelleDestino;
	}

	/**
	 * Almacena el muelle de destino del trayecto
	 * @param muelleDestino
	 */
	public void setMuelleDestino(Muelle muelleDestino) {
		this.muelleDestino = muelleDestino;
	}

	/**
	 * Obtiene el puerto de origen del trayecto
	 * @return puertoOrigen del trayecto
	 */
	public Puerto getPuertoOrigen() {
		return puertoOrigen;
	}

	/**
	 * Almacena el puerto de origen del trayecto
	 * @param puertoOrigen
	 */
	public void setPuertoOrigen(Puerto puertoOrigen) {
		this.puertoOrigen = puertoOrigen;
	}

	/**
	 * Obtiene el puertoDestino de la ruta
	 * @return puertoDestino del trayecto
	 */
	public Puerto getPuertoDestino() {
		return puertoDestino;
	}

	/**
	 * Almacena el puerto de destino del trayecto
	 * @param puertoDestino
	 */
	public void setPuertoDestino(Puerto puertoDestino) {
		this.puertoDestino = puertoDestino;
	}

	/**
	 * Obtiene la fecha origen del trayecto
	 * @return fechaOrigen del trayecto
	 */
	public Calendar getFechaOrigen() {
		return fechaOrigen;
	}

	/**
	 * Almacena la fecha de origen del trayecto
	 * @param fechaOrigen
	 */
	public void setFechaOrigen(Calendar fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}
		
	/**
	 * Obtiene la fecha de destino
	 * @return fechaDestino del trayecto
	 */
	public Calendar getFechaDestino() {
		return fechaDestino;
	}

	/**
	 * Almacena la fecha de destino del trayecto
	 * @param fechaDestino
	 */
	public void setFechaDestino(Calendar fechaDestino) {
		this.fechaDestino = fechaDestino;
	}
	/**
	 * Obtiene la distancia en kilometros
	 * @return distancia en kilometros entre muelle de origen y muelle destino
	 */
	public double getDistancia() {
		return muelleOrigen.getGPSCoord().getDistanceTo(muelleDestino.getGPSCoord());
	}
	/*
	 * Obtiene la distancia en millas marinas
	 * @return distancia en millas marinas
	 */
	public double getMillasMarinas() {
		return getDistancia()*0.539957;
	}

	


}
	
