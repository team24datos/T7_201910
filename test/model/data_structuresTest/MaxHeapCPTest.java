package model.data_structuresTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxHeapCP;
import model.vo.LocationVO;

public class MaxHeapCPTest< T extends Comparable<T>> 
{
	private  MaxHeapCP<LocationVO> lista;
	private LocationVO elemento1;
	private LocationVO elemento2; 
	private LocationVO elemento3;

	@Before
	public void testSetUp()
	{
		lista = new MaxHeapCP<LocationVO>(3);
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
		testSetUp2();
		lista.agregar(elemento1);
		assertEquals("No se añade correcamente", 1, lista.getSize());
		lista.agregar(elemento2);
		assertEquals("No se añade correcamente", 2, lista.getSize());
		lista.agregar(elemento3);
		assertEquals("No se añade correcamente", 3, lista.getSize());
	}

	@Test
	public void delMaxTest()
	{
		testSetUp2();
		//TODO
		/*
		lista.delMax();
		assertEquals("No se elimina correcamente", 2, lista.getSize());
		lista.delMax();
		assertEquals("No se elimina correcamente", 1, lista.getSize());*/
	}
}
