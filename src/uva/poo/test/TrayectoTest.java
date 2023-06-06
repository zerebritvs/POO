package uva.poo.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;
import uva.poo.control.Muelle;
import uva.poo.control.Puerto;
import uva.poo.transport.TCamion;



public class TrayectoTest {
	
	TCamion trayecto;

	Calendar fechaOrigen;
	Calendar fechaDestino;
	
	Muelle muelleOrigen;
	Muelle muelleDestino;
	
	Puerto puertoOrigen;
	Puerto puertoDestino;
	
	
	@Before 
	public void before() {
		
		fechaOrigen = new GregorianCalendar();
		fechaOrigen.set(2020, 1, 1);
		fechaDestino = new GregorianCalendar();
		fechaDestino.set(2020,1, 3);
		
		muelleOrigen = new Muelle(10,new GPSCoordinate(10, 15), 10, false, true);
		muelleDestino = new Muelle(10,new GPSCoordinate(10, 10), 20, false, true);
		
		puertoOrigen = new Puerto("ES-VAL");
		puertoDestino = new Puerto("ES-BAR");
		
		trayecto = new TCamion(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		
	}

	@Test
	public void testTrayectoValido() {
		assertEquals(muelleOrigen, trayecto.getMuelleOrigen());
		assertEquals(muelleDestino, trayecto.getMuelleDestino());
		assertEquals(puertoOrigen, trayecto.getPuertoOrigen());
		assertEquals(puertoDestino, trayecto.getPuertoDestino());
		assertEquals(fechaOrigen, trayecto.getFechaOrigen());
		assertEquals(fechaDestino, trayecto.getFechaDestino());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTrayectoFechaNoValida() {
		
		new TCamion(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaDestino, fechaOrigen);
	}
	
	@Test
	public void testInfoTrayecto() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals("Localidad y pais del puerto origen: " + puertoOrigen.getId() + " partiendo del muelle con identificador " + muelleOrigen.getId() + " y fecha de inicio: "
				+ sdf.format(fechaOrigen.getTime()) + "\nLocalidad y pais del puerto destino: " + puertoDestino.getId() + " llegando al muelle con identificador " 
				+ muelleDestino.getId()+" y fecha de llegada: " + sdf.format(fechaDestino.getTime()), trayecto.getInfo());
		
	}

	@Test
	public void testGetMuelleOrigen() {
		assertEquals(muelleOrigen, trayecto.getMuelleOrigen());
	}

	@Test
	public void testSetMuelleOrigen() {
		trayecto.setMuelleOrigen(muelleDestino);
		assertEquals(muelleDestino, trayecto.getMuelleOrigen());
		
	}

	@Test
	public void testGetMuelleDestino() {
		assertEquals(muelleDestino,trayecto.getMuelleDestino());
	}

	@Test
	public void testSetMuelleDestino() {
		trayecto.setMuelleDestino(muelleOrigen);
		assertEquals(muelleOrigen, trayecto.getMuelleDestino());
	}

	@Test
	public void testGetPuertoOrigen() {
		assertEquals(puertoOrigen, trayecto.getPuertoOrigen());
	}

	@Test
	public void testSetPuertoOrigen() {
		trayecto.setPuertoOrigen(puertoDestino);
		assertEquals(puertoDestino, trayecto.getPuertoOrigen());
	}

	@Test
	public void testGetPuertoDestino() {
		assertEquals(puertoDestino, trayecto.getPuertoDestino());
	}

	@Test
	public void testSetPuertoDestino() {
		trayecto.setPuertoDestino(puertoOrigen);
		assertEquals(puertoOrigen, trayecto.getPuertoDestino());
	}

	@Test
	public void testGetFechaOrigen() {
		assertEquals(fechaOrigen, trayecto.getFechaOrigen());
	}

	@Test
	public void testSetFechaOrigen() {
		
		trayecto.setFechaOrigen(fechaDestino);
		assertEquals(fechaDestino, trayecto.getFechaOrigen());
	}

	@Test
	public void testGetFechaDestino() {
		assertEquals(fechaDestino, trayecto.getFechaDestino());
	}

	@Test
	public void testSetFechaDestino() {
		
		trayecto.setFechaDestino(fechaOrigen);
		assertEquals(fechaOrigen, trayecto.getFechaDestino());
	}

	@Test
	public void testGetDistancia() {
		assertEquals(muelleOrigen.getGPSCoord().getDistanceTo(muelleDestino.getGPSCoord()), trayecto.getDistancia(), 0.1);
	}

	@Test
	public void testGetMillasMarinas() {
		assertEquals(trayecto.getDistancia() * 0.539957, trayecto.getMillasMarinas(),0.1);
	}

}
