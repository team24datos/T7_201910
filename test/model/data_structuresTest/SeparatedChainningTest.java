package model.data_structuresTest;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.data_structures.LinearProbing;
import model.data_structures.SeparatedChaining;
import model.vo.VOMovingViolations;

public class SeparatedChainningTest extends TestCase
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private SeparatedChaining<Integer,VOMovingViolations> movSCtest;

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
		movSCtest = new SeparatedChaining<Integer,VOMovingViolations>(m);
		movingElementos = new VOMovingViolations[n];
	}
	
	public void setUpEscenario2()
	{
		m=3;
		n=8;
		movSCtest = new SeparatedChaining<Integer,VOMovingViolations>(5);
		movingElementos = new VOMovingViolations[n];
		for(int i=0; i<n;i++)
		{
			movingElementos[i]= new VOMovingViolations(i, "location"+i, i, i, 0.0 , 0.0, false, "ticketIssue"+i, "violationCode"+i, "violationDes"+i, 0.0 , 0.0);
			movSCtest.put(movingElementos[i].getAddressId(), movingElementos[i]);
		}
	}
	@Test
	public void testPut()
	{
		setUpEscenario1();
		movingElementos[0]= new VOMovingViolations(1, "location"+1, 1, 1, 0.0 , 0.0, false, "ticketIssue"+1, "violationCode"+1, "violationDes"+1, 0.0 , 0.0);
		// TODO Pruebas
		movSCtest.put(movingElementos[0].getAddressId(), movingElementos[0]);
		assertEquals("ae1",0, movSCtest.getN());
		movSCtest.put(movingElementos[0].getAddressId(), movingElementos[0]);
		assertEquals("ae2",0, movSCtest.getN());
		//assertEquals("ae3",2, movSCtest.getM());
		//De paso se prueba el rehash
		movingElementos[1]= new VOMovingViolations(2, "location"+2, 2, 2, 0.0 , 0.0, false, "ticketIssue"+2, "violationCode"+2, "violationDes"+2, 0.0 , 0.0);
		movSCtest.put(movingElementos[1].getAddressId(), movingElementos[1]);
	}
@Test
	public void testGet()
	{
		setUpEscenario2();
		for(int i=0; i<2; i++)
		{
			assertEquals("get incorrecto", movingElementos[i], movSCtest.get(i));
		}
	}
@Test
	public void testDelete()
	{
		setUpEscenario2();
		movSCtest.delete(1);
		assertEquals("delete incorrecto", movingElementos[1], movSCtest.get(1));
	}

}
