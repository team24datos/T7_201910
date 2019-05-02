package model.data_structuresTest;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.data_structures.Grafo;

public class GrafoTest extends TestCase {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	private Grafo<Integer,String,Integer> grafoTest;
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	/**
	 * Escenario con el grafo vacío
	 */
	@Before
	public void setUpEscenario1()
	{
		grafoTest = new Grafo<Integer,String,Integer>();
		
	}
	
	public void setUpEscenario2()
	{
		grafoTest = new Grafo<Integer,String,Integer>();
		
	}
	@Test
	public void addEdgeTest()
	{
		setUpEscenario1();
		grafoTest.addVertex(1, "a");
		assertEquals("No agregó bien", grafoTest.V(), 1);
	}
	@Test
	public void VTest()
	{
		setUpEscenario1();
		assertEquals("Cantidad de vertices incorrecta", grafoTest.V(), 0);
	}
	
}
