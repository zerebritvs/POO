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

public class PackCamionBarcoTest {
	
	PackCamionBarco trayectoCombi;
	
	Calendar fechaOrigen;
	Calendar fechaDestino;
	
	Muelle muelleOrigen;
	Muelle muelleDestino;
	Muelle muelleDestinoSinBarco;
	Muelle muelleOrigenSinBarco;
	
	Puerto puertoOrigen;
	Puerto puertoDestino;
	
	
	
	@Before
	public void before() {
		
		fechaOrigen = new GregorianCalendar();
		fechaOrigen.set(2020, 1, 1);
		
		fechaDestino = new GregorianCalendar();
		fechaDestino.set(2020, 1, 3);
		
		muelleOrigen = new Muelle(20, new GPSCoordinate(10, 10), 20, false, true);
		muelleDestino = new Muelle(20, new GPSCoordinate(20, 20), 30, false, true);
		muelleOrigenSinBarco = new Muelle(20, new GPSCoordinate(5, 15), 40, true, true);
		muelleDestinoSinBarco = new Muelle(20, new GPSCoordinate(10, 15), 40, true, true);
		
		puertoOrigen = new Puerto("ES-VAL");
		puertoDestino = new Puerto("ES-BAR");
		
		trayectoCombi = new PackCamionBarco(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		
		
	}
	
	@Test
	public void constructorValidoPackCamionBarcoTest(){
		
		assertEquals(muelleOrigen, trayectoCombi.getMuelleOrigen());
		assertEquals(muelleDestino, trayectoCombi.getMuelleDestino());
		assertEquals(puertoOrigen, trayectoCombi.getPuertoOrigen());
		assertEquals(puertoDestino, trayectoCombi.getPuertoDestino());
		assertEquals(fechaOrigen, trayectoCombi.getFechaOrigen());
		assertEquals(fechaDestino, trayectoCombi.getFechaDestino());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packCamionBarcoNoValidoTest() {
		
		new PackCamionBarco(muelleOrigenSinBarco, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packCamionBarcoNoValidoTest3() {
		
		new PackCamionBarco(muelleOrigen, muelleDestinoSinBarco, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		
	}
	
	@Test
	public void precioTrayectoValidoTest() {
		
		assertEquals((0.85 * 4000 * 2 + 4.5 * trayectoCombi.getDistancia() + 200),trayectoCombi.precioTrayecto(),0.1);
	}

}
