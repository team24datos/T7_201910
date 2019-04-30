package model.vo;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.helpers.DefaultHandler;

import model.data_structures.ArregloDinamico;

public class IntersectionParseHandler extends DefaultHandler {
	
	/**
	 * Arreglo dinámico donde se guardan las intersecciones de las calles que se van leyendo
	 */
	private ArregloDinamico<VOIntersections> intersectionList;
	
	/**
	 * Pila donde se acomulan los valores de los elementos del objeto actual. 
	 */
	private Stack elementStack;
	
	/**
	 * Pila donde se almacenan los objetos que han sido leidos. 
	 */
	private Stack<VOMovingViolations> objectStack;
	

	public IntersectionParseHandler() 
	{
		intersectionList = new ArregloDinamico<VOIntersections>(200);
	}

}
