package uva.poo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uva.poo.control.Plaza;
import uva.poo.storage.Contenedor;
import uva.poo.storage.Estandar;



public class PlazaTest {
	Plaza plaza;
	ArrayList<Contenedor> largeList;
	ArrayList<Contenedor> smallList;
	ArrayList<Contenedor> fullList;
	ArrayList<Contenedor> techoList;
	
	Estandar estandar;
	
	String codigo1 = "AAAU1234566";
	String codigo2 = "AAAU1234571";
	
	@Before
	public void before() {
		
		estandar = new Estandar(true, true, 5000, 20000, 25000, "ABCU1234560");
		
		plaza = new Plaza();
		largeList = new ArrayList<>();
		largeList.add(new Estandar(false, true, 2000, 20000, 25000, (codigo1)));
		largeList.add(new Estandar(false, true, 2000, 20000, 25000, (codigo2)));
		largeList.add(new Estandar(false, true, 2000, 20000, 25000, ("AAAU1234587")));
		largeList.add(new Estandar(false, true, 2000, 20000, 25000, ("AAAU1234592")));
		largeList.add(new Estandar(false, true, 2000, 20000, 25000, ("AAAU1234606")));
		largeList.add(new Estandar(false, true, 2000, 20000, 25000, ("AAAU1234611")));

		
		smallList = new ArrayList<>();
		smallList.add(estandar);
		fullList = new ArrayList<>();
		fullList.add(new Estandar(false, true, 2000, 20000, 25000, (codigo1)));
		fullList.add(new Estandar(false, true, 2000, 20000, 25000, (codigo2)));
		fullList.add(new Estandar(false, true, 2000, 20000, 25000, ("AAAU1234587")));
		fullList.add(new Estandar(false, true, 2000, 20000, 25000, ("AAAU1234592")));

		techoList = new ArrayList<>();
		techoList.add(new Estandar(false, false, 2000, 20000, 23000, ("AAAU1234524")));
		
	}

	@Test
	public void testPlaza() {
		assertTrue(plaza.isEmpty());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPlazaArray() {
		new Plaza(largeList);
	}

	@Test
	public void testPlazaArrayListOfContenedor() {
		Plaza plaza2 = new Plaza(smallList);
		assertFalse(plaza2.isFull());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetContenedoreOverLimit() {
		Plaza plaza2 = new Plaza();
		plaza2.setContenedores(largeList);
	}
	@Test
	public void testGetContenedores() {
		plaza.setContenedores(fullList);
		plaza.getContenedores().toString();
	}
	
	@Test
	public void testGetContenedor() {
		plaza.setContenedores(fullList);
		Contenedor cont = new Estandar(false, true, 2000, 20000, 25000, (codigo1));
		assertEquals(cont.getCodigo(), plaza.getContenedor(codigo1).getCodigo());
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetNullContenedor() {
		plaza.setContenedores(fullList);
		plaza.getContenedor("ABC12355215");
	}
	
	@Test
	public void testSetContenedores() {
		ArrayList<Contenedor> list = new ArrayList<>();
		list.add(0, estandar);
		plaza.setContenedores(list);
	}

	
	@Test
	public void testAddContenedor() {
		plaza.addContenedor(estandar);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddOverLimitContenedor() {
		plaza.setContenedores(fullList);
		plaza.addContenedor(estandar);
	}
	
	@Test
	public void testRemoveContenedor() {
		plaza.setContenedores(fullList);
		plaza.removeContenedor(codigo2);
		assertEquals(3, plaza.getContenedores().size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveContenedorException() {
		plaza.removeContenedor("BBBU2345523");
	}
	
	@Test
	public void testHasContenedor() {
		plaza.setContenedores(fullList);
		assertTrue(plaza.hasContenedor(codigo2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testHasContenedorBadCode() {
		plaza.setContenedores(fullList);
		plaza.hasContenedor("BBBU1234524");
	}
	
	@Test
	public void testFindLevelContenedor() {
		plaza.setContenedores(fullList);
		assertEquals(1, plaza.findLevelContenedor(codigo2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFindLevelContenedorBadCode() {
		plaza.setContenedores(fullList);
		plaza.findLevelContenedor("BBBU1234525");
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(plaza.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty() {	
		plaza.addContenedor(estandar);
		assertFalse(plaza.isEmpty());
	}
	
	@Test
	public void testIsFull() {
		plaza.setContenedores(techoList);
		assertTrue(plaza.isFull());
	}

	@Test
	public void testHasSpace() {
		assertTrue(plaza.hasSpace());
	}
	
	@Test
	public void testHasSpace2() {
		plaza.setContenedores(smallList);
		assertTrue(plaza.hasSpace());
	}
	
	@Test
	public void testHasNotSpace() {
		plaza.setContenedores(fullList);
		assertFalse(plaza.hasSpace());
	}

	@Test
	public void testHasNotSpaceTecho() {
		plaza.setContenedores(techoList);
		assertFalse(plaza.hasSpace());
	}
	
	@Test
	public void testToString() {
		plaza.toString();
	}

}
