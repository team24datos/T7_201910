package model.data_structuresTest;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.NodeList;

public class NodeListTest<T> 
{
	private NodeList<T> node;
	private NodeList<T> next;
	private NodeList<T> previous;
	private T elem;
	private T elem2;
	private T elem3;
	
	@Before
	public void testSetup( )
	{
		next = new NodeList<T>(elem2, null, node);
		
		previous = new NodeList <T> (elem3, node, null);
		
		node = new NodeList<T>(elem, next,previous);
		
	}
	
	@Test
	public void nodeTest( )
	{
		assertEquals( "El nodo no se inicializa correctamente", node, node );

	}
	@Test
	public void nextTest( )
	{
		assertEquals( "No se retorna el nodo siguiente correctamente", next, node.getNext() );
	
		
	}
	@Test
	public void previousTest( )
	{
		assertEquals( "No se retorna el nodo anterior correctamente", previous, node.getPrevius() );
	
	}
	@Test
	public void hasNextTest( )
	{
		assertEquals( "No retorna correctamente si el nodo tiene uno siguiente", true, node.hasNext() );
		assertEquals ("No retorna correctamente si el nodo tiene uno siguiente", false ,next.hasNext() ) ;
	
	}
	@Test
	public void hasPreviousTest( )
	{
		assertEquals( "No retorna correctamente si el nodo tiene uno anterior", false, previous.hasPrevious() );
		assertEquals ("No retorna correctamente si el nodo tiene uno anterior", true ,node.hasPrevious());
	}
	@Test
	public void elemTest( )
	{
		assertEquals( "No se asigna el elemento correctamente", elem, node.getelem() );
	
	}
	@Test
	public void setElemTest( )
	{
		node.setElement(elem2);
		assertEquals( "No se asigna el elemento correctamente", elem2, node.getelem() );
	
	}

}
