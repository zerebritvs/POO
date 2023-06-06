package uva.poo.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;
import uva.poo.control.Muelle;
import uva.poo.control.Puerto;
import uva.poo.transport.TBarco;
import uva.poo.transport.TCamion;
import uva.poo.transport.TTren;

public class SimpleTest {
	
	
	Calendar fechaOrigen;
	Calendar fechaDestino;
	Muelle muelleOrigen;
	Muelle muelleDestino;
	Muelle muelleOrigenSinTren;
	Muelle muelleOrigenSinBarco;
	Muelle muelleDestinoSinTren;
	Muelle muelleDestinoSinBarco;
	Puerto puertoOrigen;
	Puerto puertoDestino;
	
	TCamion trayectoCamion;
	TTren trayectoTren;
	TBarco trayectoBarco;

	@Before
	public void before() {
		
		
		fechaOrigen = new GregorianCalendar();
		fechaOrigen.set(2020, 1, 1);
		fechaDestino = new GregorianCalendar();
		fechaDestino.set(2020, 1, 3);
		
		muelleOrigen = new Muelle(20, new GPSCoordinate(10, 10), 20, false, true);
		muelleDestino = new Muelle(20, new GPSCoordinate(20, 20), 30, false, true);
		muelleOrigenSinTren = new Muelle(20, new GPSCoordinate(5, 15), 40, false, false);
		muelleOrigenSinBarco = new Muelle(20, new GPSCoordinate(10, 15), 40, true, true);
		muelleDestinoSinTren = new Muelle(20, new GPSCoordinate(20, 20), 50, false, false);
		muelleDestinoSinBarco = new Muelle(20, new GPSCoordinate(20, 20), 50, true, true);
		
		puertoOrigen = new Puerto("ES-VAL");
		puertoDestino = new Puerto("ES-BAR");
		
		trayectoCamion= new TCamion(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		trayectoTren= new TTren(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		trayectoBarco= new TBarco(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		
	}
	
	@Test
	public void constructorValidoTCamionTest() {
		
		assertEquals(muelleOrigen, trayectoCamion.getMuelleOrigen());
		assertEquals(muelleDestino, trayectoCamion.getMuelleDestino());
		assertEquals(puertoOrigen, trayectoCamion.getPuertoOrigen());
		assertEquals(puertoDestino, trayectoCamion.getPuertoDestino());
		assertEquals(fechaOrigen, trayectoCamion.getFechaOrigen());
		assertEquals(fechaDestino, trayectoCamion.getFechaDestino());
		
	}
	
	@Test
	public void constructorValidoTBarcoTest() {
		
		assertEquals(muelleOrigen, trayectoBarco.getMuelleOrigen());
		assertEquals(muelleDestino, trayectoBarco.getMuelleDestino());
		assertEquals(puertoOrigen, trayectoBarco.getPuertoOrigen());
		assertEquals(puertoDestino, trayectoBarco.getPuertoDestino());
		assertEquals(fechaOrigen, trayectoBarco.getFechaOrigen());
		assertEquals(fechaDestino, trayectoBarco.getFechaDestino());
		
	}
	
	@Test
	public void constructorValidoTTrenTest() {
		
		assertEquals(muelleOrigen, trayectoTren.getMuelleOrigen());
		assertEquals(muelleDestino, trayectoTren.getMuelleDestino());
		assertEquals(puertoOrigen, trayectoTren.getPuertoOrigen());
		assertEquals(puertoDestino, trayectoTren.getPuertoDestino());
		assertEquals(fechaOrigen, trayectoTren.getFechaOrigen());
		assertEquals(fechaDestino, trayectoTren.getFechaDestino());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMuelleSinVias() {
		
		new TTren(muelleOrigenSinTren, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMuelleSinVias2() {
		
		new TTren(muelleOrigen, muelleDestinoSinTren, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMuelleSinBarco() {
		new TBarco(muelleOrigenSinBarco, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMuelleSinBarco2() {
		new TBarco(muelleOrigen, muelleDestinoSinBarco, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
	
	@Test
	public void testPrecioTCamion() {
		assertEquals(200 + 4.5 * trayectoCamion.getDistancia(), trayectoCamion.precioTrayecto(),0.1);
		
	}
	
	@Test
	public void testPrecioTBarco() {
		assertEquals(2.0 * 4000.0, trayectoBarco.precioTrayecto(), 0.1);
		
	}
	@Test
	public void testPrecioTTren() {
		assertEquals(20 + 12.5 * trayectoTren.getDistancia(), trayectoTren.precioTrayecto(), 0.1);
		
	}

}
