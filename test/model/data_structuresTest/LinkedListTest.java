package model.data_structuresTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.LinkedList;


public class LinkedListTest<T extends Comparable <T>> 
{
	private LinkedList<T> list;
	private T elem;
	private T elem2;
	private T elem3;
	private T elem4;
	
	@Before
	public void testSetup( )
	{
		list = new LinkedList<T>();
	}
	
	public void testSetup2( )
	{
		list = new LinkedList<T>();
		list.add(elem);
		list.add(elem2);
		list.add(elem3);
	}
	
	@Test
	public void linkedListTest( )
	{
		assertEquals( "La lista no se inicializa correctamente", list, list );	
	}
	
	@Test
	public void sizeTest( )
	{
		testSetup2();
		assertEquals( "El tama�o de la lista no se halla correctamente", 3, list.getSize() );	
		
	}
	
	@Test
	public void addTest( )
	{
		testSetup2();
		
		list.addAtK(elem, 0);
		assertEquals( "El elemento no se a�ade correctamente", 4, list.getSize() );
	}
	
	@Test 
	public void addAtend()
	{
         testSetup2();
		
		list.addAtEnd(elem4);
		assertEquals( "El elemento no se a�ade correctamente", 4, list.getSize() );
	}
	
	
	@Test
	public void deleteTest( )
	{
		testSetup2();
		list.deleteAtK(1);
		assertEquals( "El elemento no se elimina correctamente", 2, list.getSize() );
	}
	
	
	@Test
	public void getTest( )
	{
		testSetup2();
		T nullResp = list.get(4);
		T resp = list.get(0);
		assertEquals( "El elemento no se retorna por posici�n correctamente", null, nullResp );
		assertEquals( "El elemento no se retorna por posici�n correctamente", resp ,elem );
	}
	
	@Test
	public void isEmptyTest( )
	{
		assertEquals( "El m�todo no retorna correctamente si la lista est� vacia", true, list.isEmpty() );
		testSetup2();
		assertEquals( "El m�todo no retorna correctamente si la lista est� vacia", false, list.isEmpty() );
	}
	
	
	
	
}
