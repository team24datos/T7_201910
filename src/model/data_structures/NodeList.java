package model.data_structures;

import java.util.Iterator;

public class NodeList<T> 
{

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
     * siguiente nodo.
     */
	private NodeList<T> next;
	
	/**
     * anterior nodo.
     */
	private NodeList<T> previous;
	
	/**
     * elemento.
     */
	private T elementoAca;
	
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	
	public NodeList() 
	{
		next = null;
		elementoAca = null;
		previous = null;
	}
	
	public NodeList(T currentT, NodeList<T> pNext, NodeList<T> pPrevious)
	{
		next = pNext;
		elementoAca=currentT;
		previous = pPrevious;
	}
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

	/**
     * Indica si hay un siguiente a cambiar, requisito
     * para cambiar siguiente 
     * 
     */
	public boolean hasNext()
	{
		return (next==null) ? false: true;
	}
	
	/**
     * Indica si hay un anterior a cambiar, requisito 
     * 
     */
	public boolean hasPrevious()
	{
		return (previous==null) ? false: true;
	}
	/**
     * Cambia el siguiente en la lista.
     * @param elemento a cambiar
     * @return true si se logra cambiar el siguiente 
     */
	public boolean setNext( NodeList<T> pSiguiente)
	{
		
			next = pSiguiente;
			return true;
				
	}
	
	/**
     * Cambia el anterior en la lista.
     * @param elemento a cambiar
     * @return true si se logra cambiar el anterior 
     */
	public boolean setPrevius( NodeList<T> PreviousElem )
	{
        
        	previous = PreviousElem;
        	return true;
        
	}
	
	/**
     * Da el siguiente nodo en la lista
     * @return el siguiente nodo de la lista
     */
	public NodeList<T> getNext( )
	{
		
		return next;
	}
	
	/**
     * Da el anterior nodo de la lista
     * @return el aanterior nodo de la lista
     */
	public NodeList<T> getPrevius()
	{
		
		return previous;
	}
	
	/**
     * Cambia el elemento
     * @param elemento a cambiar
     */
	public  void setElement(T pElem)
	{
		elementoAca = pElem;
	}
	
	/**
     * Da el elemento actual de la lista
     * @return el elemento de la lista <T>
     * 
     */
	public T getelem()
	{
		return elementoAca;
	}
	
	
		
	}
	

