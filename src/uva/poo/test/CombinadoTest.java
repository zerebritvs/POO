package uva.poo.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;
import uva.poo.control.Muelle;
import uva.poo.control.Puerto;
import uva.poo.transport.PackCamionBarco;
import uva.poo.transport.PackCamionTren;

public class CombinadoTest {
	
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
	
	
	PackCamionBarco trayectoCombi;
	PackCamionTren trayectoCombi2;

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
		
		trayectoCombi= new PackCamionBarco(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		trayectoCombi2= new PackCamionTren(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		
	}

	@Test
	public void constructorValidoPackCamionTrenTest() {
		
		assertEquals(muelleOrigen, trayectoCombi2.getMuelleOrigen());
		assertEquals(muelleDestino, trayectoCombi2.getMuelleDestino());
		assertEquals(puertoOrigen, trayectoCombi2.getPuertoOrigen());
		assertEquals(puertoDestino, trayectoCombi2.getPuertoDestino());
		assertEquals(fechaOrigen, trayectoCombi2.getFechaOrigen());
		assertEquals(fechaDestino, trayectoCombi2.getFechaDestino());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMuelleSinVias() {
		
		new PackCamionTren(muelleOrigenSinTren, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMuelleSinVias2() {
		
		new PackCamionTren(muelleOrigen, muelleDestinoSinTren, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
	}
	
	@Test
	public void testPrecioPackCamionTren() {
		assertEquals(10*trayectoCombi2.getDistancia(), trayectoCombi2.precioTrayecto(),0.1);
		
	}
		
	
}
