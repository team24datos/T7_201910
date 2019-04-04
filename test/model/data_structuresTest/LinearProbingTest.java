package model.data_structuresTest;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.data_structures.LinearProbing;
import model.vo.VOMovingViolations;

public class LinearProbingTest extends TestCase
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	private LinearProbing<Integer,VOMovingViolations> movLPtest;
	
	private VOMovingViolations[] movingElementos;
	private int m;
	private int n;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	@Before
	public void setUpEscenario1()
	{
		m=1;
		n=8;
		movLPtest = new LinearProbing<Integer,VOMovingViolations>(m);
		movingElementos = new VOMovingViolations[n];
	}
	
	public void setUpEscenario2()
	{
		m=3;
		n=8;
		movLPtest = new LinearProbing<Integer,VOMovingViolations>(5);
		movingElementos = new VOMovingViolations[n];
		for(int i=0; i<n;i++)
		{
			movingElementos[i]= new VOMovingViolations(i, "location"+i, i, i, 0.0, 0.0, false, "ticketIssue"+i, "violationCode"+i, "violationDes"+i,0.0, 0.0);
			movLPtest.put(movingElementos[i].getAddressId(), movingElementos[i]);
		}
	}
	@Test
	public void testPut()
	{
		setUpEscenario1();
		movingElementos[0]= new VOMovingViolations(1, "location1", 1, 1, 0.0, 0.0, false, "ticketIssue"+1, "violationCode"+1, "violationDes"+1,0.0,0.0);
		movLPtest.put(movingElementos[0].getAddressId(), movingElementos[0]);
		assertEquals("ae1",0, movLPtest.getN());
		movLPtest.put(movingElementos[0].getAddressId(), movingElementos[0]);
		assertEquals("ae2",0, movLPtest.getN());
		//assertEquals("ae3",2, movLPtest.getM());
		//De paso se prueba el rehash
		movingElementos[1]= new VOMovingViolations(2, "location"+2, 2, 2, 0.0, 0.0, false, "ticketIssue"+2, "violationCode"+2, "violationDes"+2, 0.0 ,0.0);
		movLPtest.put(movingElementos[1].getAddressId(), movingElementos[1]);
	}
	@Test
	public void testGet()
	{
		setUpEscenario2();
		for(int i=0; i<2; i++)
		{
			assertEquals("get incorrecto", null, movLPtest.get(i));
		}
	}
	@Test
	public void testDelete()
	{
		setUpEscenario2();
		movLPtest.delete(1);
		assertEquals("delete incorrecto", null, movLPtest.get(1));		
	}
}
