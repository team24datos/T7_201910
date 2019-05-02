package model.data_structuresTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Grafo;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.MaxHeapCP;
import model.vo.LocationVO;

public class MaxColaPrioridadTest < T extends Comparable<T>>
{
	/*private MaxColaPrioridad<LocationVO> lista;
	private LocationVO elemento1;
	private LocationVO elemento2; 
	private LocationVO elemento3;

	@Before
	public void testSetUp()
	{
		lista = new MaxColaPrioridad<LocationVO>();
	}
	public void testSetUp2()
	{
		testSetUp();
		elemento1= new LocationVO(1, "a",3);
		elemento2= new LocationVO(2, "b",1);
		elemento3= new LocationVO(3, "c",2);
	}

	@Test
	public void agregarTest()
	{
		lista.agregar(elemento1);
		assertEquals("No se añade correcamente", 1, lista.getSize());
		lista.agregar(elemento2);
		assertEquals("No se añade correcamente", 2, lista.getSize());
		lista.agregar(elemento3);
		assertEquals("No se añade correcamente", 3, lista.getSize());
	}*/
	// -----------------------------------------------------------------
	// Se intenta hacer el test del grafo aquí para verificar funcionamiento pero aun 
	// así no modifica el test, no sé que pasa
	// Prueba grafoo
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
		/*	public static void main(String[] args) {
					System.out.println("hi");
				}*/

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
