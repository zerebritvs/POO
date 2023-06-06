package uva.poo.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;
import uva.poo.control.Muelle;
import uva.poo.control.Puerto;

import uva.poo.storage.Estandar;
import uva.poo.storage.FlatRack;
import uva.poo.storage.Refrigerado;
import uva.poo.transport.PackCamionBarco;
import uva.poo.transport.PackCamionTren;
import uva.poo.transport.TBarco;
import uva.poo.transport.TCamion;
import uva.poo.transport.TTren;

public class ContenedorTest {
	
	Estandar estandar;
	FlatRack flRack;
	Refrigerado refrigerado;
	
	String codigoCon = "ABCU1234560";
	String codigoSin = "ABCU123456";

	TCamion trayectoCamion;
	TBarco trayectoBarco;
	TTren trayectoTren;
	
	PackCamionBarco trayectoCombi;
	PackCamionTren trayectoCombi2;

	Estandar estandarVacio;
	
	FlatRack flatRackValido;
	
	Refrigerado refrigeradoValido;
	
	Calendar fechaOrigen;
	Calendar fechaDestino;
	Muelle muelleOrigen;
	Muelle muelleDestino;
	Muelle muelleSinTren;
	Muelle muelleSinBarco;
	Puerto puertoOrigen;
	Puerto puertoDestino;
	
	
	@Before
	public void before() {
		estandar = new Estandar(true, true, 5000, 20000, 25000, codigoCon);
		flRack = new FlatRack(false, 5000, 50000, "ABDU1234564");
		refrigerado = new Refrigerado(false, 5000, 20000, 25000, "ABEU1234568");		
		estandarVacio = new Estandar(false,false,0,0,0, codigoCon);
		
		
		
		fechaOrigen = new GregorianCalendar();
		fechaOrigen.set(2020, 1, 1);
		fechaDestino = new GregorianCalendar();
		fechaDestino.set(2020, 1, 3);
		muelleOrigen = new Muelle(20, new GPSCoordinate(10, 10), 10, false, true);
		muelleDestino = new Muelle(20, new GPSCoordinate(20, 20), 30, false, true);
		muelleSinTren = new Muelle(20, new GPSCoordinate(5, 15), 40, false, false);
		muelleSinBarco = new Muelle(20, new GPSCoordinate(10, 15), 40, true, true);
		puertoOrigen = new Puerto("ES-VAL");
		puertoDestino = new Puerto("ES-BAR");
		
		trayectoCamion= new TCamion(muelleDestino, muelleDestino, puertoDestino, puertoDestino, fechaDestino, fechaDestino);
		trayectoTren= new TTren(muelleDestino, muelleDestino, puertoDestino, puertoDestino, fechaDestino, fechaDestino);
		trayectoBarco= new TBarco(muelleDestino, muelleDestino, puertoDestino, puertoDestino, fechaDestino, fechaDestino);
		
		trayectoCombi = new PackCamionBarco(muelleDestino, muelleDestino, puertoDestino, puertoDestino, fechaDestino, fechaDestino);
		trayectoCombi2 = new PackCamionTren(muelleDestino, muelleDestino, puertoDestino, puertoDestino, fechaDestino, fechaDestino);
	}
	

	@Test
	public void testEstandars() {
		assertTrue(estandar.isOnTransito());
		assertTrue(estandar.hasTecho());
		assertEquals(5000,estandar.getVolumen(),0);
		assertEquals(20000,estandar.getPeso(),0);
		assertEquals(25000,estandar.getCargaMax(),0);
	}
	
	@Test
	public void testRefrigerado() {
		assertFalse(refrigerado.isOnTransito());
		assertTrue(refrigerado.hasTecho());
		assertEquals(5000,refrigerado.getVolumen(),0);
		assertEquals(20000,refrigerado.getPeso(),0);
		assertEquals(25000,refrigerado.getCargaMax(),0);
	}
	
	@Test
	public void testFlatRack() {
		assertFalse(flRack.isOnTransito());
		assertFalse(flRack.hasTecho());
		assertEquals(0,flRack.getVolumen(),0);
		assertEquals(5000,flRack.getPeso(),0);
		assertEquals(50000,flRack.getCargaMax(),0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCheckDigitNoExiste() {
		new Estandar(true, true, 30, 20000, 25000, codigoSin);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCheckDigitNoValido() {
		new Estandar(true, true, 30, 20000, 25000, "ABCU1234567");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testArgsNegativos() {
		new Estandar(true, true, 30, -20000, -25000, codigoSin);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testArgsNegativos2() {
		new Estandar(true, true, -30, -20000, -25000, codigoSin);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testArgsNegativos3() {
		new Estandar(true, true, 30, 20000, -25000, codigoSin);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCodigoNoValido() {
		new Estandar(true, true, 30, 20000, 25000, "ABCU123456897");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCodigoNoValido2() {
		new Estandar(true, true, 30, 20000, 25000, "ABCU1");
	}
	
	@Test 
	public void testCheckDigitValido() {
		new Estandar(true, true, 30, 20000, 25000, "AAAU1234566");
	}
	@Test 
	public void testCheckDigit10() {
		new Estandar(true, true, 30, 20000, 25000, "AAAU1234530");
	}

	@Test
	public void testPrecioContenedorBarco() {
		TBarco tbarco = new TBarco(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, 
				fechaOrigen, fechaDestino);
		estandar.addRuta(tbarco);
		assertEquals(2.0 * 4000.0, estandar.precioContenedor(),0.1);
	}
	
	@Test
	public void testPrecioContenedorTren() {
		TTren tTren = new TTren(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		estandar.addRuta(tTren);
		assertEquals(20 + 12.5 * tTren.getDistancia(), estandar.precioContenedor(),0.1);
	}
	
	@Test
	public void testPrecioContenedorCamion() {
		TCamion tCamion = new TCamion(muelleOrigen, muelleDestino, puertoOrigen, puertoDestino, fechaOrigen, fechaDestino);
		estandar.addRuta(tCamion);
		assertEquals(200 + 4.5 * tCamion.getDistancia(), estandar.precioContenedor(),0.1);
	}
	
	@Test
	public void testAddRutaEstandar() {
		estandar.addRuta(trayectoCamion);
		assertEquals(trayectoCamion.getInfo(), estandar.getRuta(0).getInfo());
	}
	@Test
	public void testAddRutaRefrigerado() {
		refrigerado.addRuta(trayectoCamion);
		assertEquals(trayectoCamion.getInfo(), refrigerado.getRuta(0).getInfo());
	}
	
	@Test
	public void testAddRutaFlatRack() {
		flRack.addRuta(trayectoBarco);
		assertEquals(trayectoBarco.getInfo(), flRack.getRuta(0).getInfo());
	}
	
	@Test
	public void testAddRutaFlatRack2() {
		flRack.addRuta(trayectoTren);
		assertEquals(trayectoTren.getInfo(), flRack.getRuta(0).getInfo());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddRutaRefrigeradoNoValido() {
		refrigerado.addRuta(trayectoTren);
		
	}
	@Test (expected = IllegalArgumentException.class)
	public void testAddRutaRefrigeradoNoValido2() {
		refrigerado.addRuta(trayectoCombi2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddRutaFlatRackNoValido() {
		flRack.addRuta(trayectoCamion);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddRutaFlatRackNoValido2() {
		flRack.addRuta(trayectoCombi);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddRutaFlatRackNoValido3() {
		flRack.addRuta(trayectoCombi2);
	}
	
	@Test
	public void testGetRuta() {
		estandar.addRuta(trayectoCamion);
		assertEquals(trayectoCamion, estandar.getRuta(0));
	}

	@Test
	public void testIsOnTransito() {
		assertTrue(estandar.isOnTransito());
	}

	@Test
	public void testHasTecho() {
		assertTrue(estandar.hasTecho());
	}

	@Test
	public void testGetVolumen() {
		assertEquals(5000,estandar.getVolumen(),0.00001);
	}

	@Test
	public void testGetPies3() {
		assertEquals( 176573.5,estandar.getPies3(),0.00001);
	}

	@Test
	public void testGetPeso() {
		assertEquals(20000,estandar.getPeso(), 0.00001);
	}

	@Test
	public void testGetLibras() {
		assertEquals(44092.4,estandar.getLibras(), 0.00001);
	}

	@Test
	public void testGetCargaMax() {
		assertEquals(25000,estandar.getCargaMax(),0);
	}

	@Test
	public void testGetCodigo() {
		assertEquals(codigoCon, estandar.getCodigo());
	}

	@Test
	public void testSetTransito() {
		estandar.setTransito(false);
		assertFalse(estandar.isOnTransito());
	}

	@Test
	public void testSetTecho() {
		estandar.setTecho(false);
		assertFalse(estandar.hasTecho());
	}
	
	@Test (expected = IllegalStateException.class)
	public void testSetTechoRefrigeradoNoValido() {
		refrigerado.setTecho(false);
	}
	
	@Test
	public void testSetTechoRefrigeradoValido() {
		refrigerado.setTecho(true);
		assertTrue(refrigerado.hasTecho());
	}
	
	@Test (expected = IllegalStateException.class)
	public void testSetTechoFlatRackNoValido() {
		flRack.setTecho(true);
	}
	
	@Test
	public void testSetTechoFlatRackValido() {
		flRack.setTecho(false);
		assertFalse(flRack.hasTecho());
	}
	
	@Test
	public void testSetVolumen(){
		estandar.setVolumen(5000);
		
		assertEquals(5000,estandar.getVolumen(),0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetVolumenNoValido(){
		flRack.setVolumen(5000);
	}

	@Test
	public void testSetPies3() {
		estandar.setPies3(176573.5);
		
		assertEquals(176573.5,estandar.getPies3(),0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetPies3NoValido() {
		flRack.setPies3(176573.5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetPesoNoValido() {
		estandar.setPeso(26000);
	}
	
	@Test 
	public void testSetPesoValido() {
		estandar.setPeso(20000);
		assertEquals(20000,estandar.getPeso(),0);
	}

	@Test
	public void testSetLibras() {
		estandar.setLibras(44092.4);
		assertEquals(44092.4,estandar.getLibras(),0);
	}

	@Test
	public void testSetCargaMax() {
		estandar.setCargaMax(25000);
		assertEquals(25000,estandar.getCargaMax(),0);
	}

	@Test
	public void testSetCodigo() {
		estandar.setCodigo(codigoCon);
		assertEquals(codigoCon,estandar.getCodigo());
	}

}
