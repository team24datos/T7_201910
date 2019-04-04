package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
     * Primer nodo.
     */
	private NodeList<T> first;
	
	/**
     * ultimo nodo.
     */
	private NodeList<T> last;
	
	/**
     * Primer nodo.
     */
	private int size;
	
	/**
     * Primer nodo.
     */
	private NodeList<T> current;
	
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

	public LinkedList()
	{
		size =0;
		first = null;
		last = null;
		current = first;
	}

	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	
	public NodeList getFirstNode()
	{
		return first;
	}
	
	/**
     * Devuelve el siguiente elemento en la lista
     * @return elemento T siguiente
     */
	
	public T next() {
		
		current = current.getNext();
		return  current.getelem();
		
	}

	/**
     * Devuelve el anteior elemento de la lista 
     * @return elemento T anteior
     */
	
	public T previus() {
		
		current = current.getPrevius();
		return  current.getelem();
	}

	/**
     * Devuelve el tamaño de la lista
     * @return tamaño de la lista 
     */
	public int getSize() {
		
		return size;
	}

	/**
     * Devuelve el elemento actual de la lista
     * @return elemento T actual
     */
	
	public T getCurrent() {
		
		return current.getelem();
	}

	
	public boolean isEmpty() {
		return size == 0 ? true: false;
	}

	/**
     * Indica si un elemento esta repetido
     * correquisito para a�adir elementos 
     * @param T elemento
     * @return true si se logro encontrar un elemento T igual a alguno en la lista
     */

	public boolean repeated(T dato) {
    NodeList<T> nodos = first;
		
		while(nodos.hasNext())
		{
			if(  dato.compareTo(nodos.getelem()) == 0 ) 
					{
				return true;
					}
			nodos = nodos.getNext();
		}
		return false;
	}

	
	
	/**
     * Elimina un elemento de la lista dado por parametro
     * @param T elem
     * @return true si se logro eliminar el elemento T
     */
	
	public boolean delete(T elem) {
		boolean resp = false;
		
		NodeList<T> pro = first;
		
		if( pro.getelem().equals(elem))
		{
			first = pro.getNext();
			size--;
			resp = true;
		}
		
		while( pro.hasNext())
		{
			if( pro.getelem().equals(elem))
           {
			NodeList<T> temp = pro.getNext();
			pro.getPrevius().setNext(temp);
			
			size--;
			resp =  true;
         }
			
			pro = pro.getNext();
		}
		
			
			   return resp;
			}
			
			

	/**
     * Añade un elemento a la lista de primeras, si no existe primero,
     * este se convierte en el primero
     * @param T elem a agregar
     * @return true si se logro agregar el elemento T
     */

	public boolean add(T elem) {
	
		NodeList<T> temp = new NodeList<T>(elem, null, null);
		boolean resp = false;
		
		
		if( first == null)
		{
			first = temp;
			last = temp;
			size++;
		}
		else
		{
			

			first.setPrevius(temp);
			temp.setNext(first);
			first = temp;
			resp = true;
			size++;
		}
		
		return resp;
	}

	/**
     * Añade un elemento a la lista de primeras, si no existe primero,
     * este se convierte en el primero
     * @param T elem a agregar
     * @return true si se logro agregar el elemento T
     */
	
	public boolean addAtEnd(T elem) {
		
		NodeList<T> pro = first;
		NodeList<T> aux = new NodeList<T>(elem, null, null);
		
		for ( int i = 0; i <=  size ; i++)
		{
			
			if( !pro.hasNext())
			{
				pro.setNext(aux);
				aux.setPrevius(pro);
				last = aux;
				size++;
				return true;
			}
			
				
			
			pro = pro.getNext();
		}
		return false;
			
		
        
	}

	/**
     * Añade un elemento a la lista de primeras, si no existe primero,
     * este se convierte en el primero
     * @param T elem a agregar
     * @return true si se logro agregar el elemento T
     */
	
	public boolean addAtK(T elem, int nii) {
		
		NodeList<T> pro = first;
		NodeList<T> aux = new NodeList<T>(elem, null, null); 
		
		for( int i = 0; i <= size; i++)
		{
			if( i == nii)
			{
				NodeList<T> temporal = pro.getNext();
				pro.setNext(aux);
				aux.setPrevius(pro);
				aux.setNext(temporal);
				temporal.setPrevius(aux);
				size++;
				return true;
			}
			pro = pro.getNext();
		}
		
	return false;
		
	}
	
	public T addInorder(T elemp)
	{
		NodeList<T> aux = new NodeList<T>();
		aux.setElement(elemp);
		if(elemp==null)
		{
			return null;
		}
		if( first == null||first.getelem()==null)
		{
			first = aux;
			last = aux;
		}
		else
		{
			NodeList<T> mo = first;
			while ( mo.getelem().compareTo(aux.getelem()) > 0 && mo != last)
			{
				mo = mo.getNext()!=null ? mo.getNext() :last;
			}
			if( mo.getelem().compareTo(aux.getelem()) < 0 && mo.getelem().compareTo(first.getelem())==0)
			{
				mo.setPrevius(aux);
				aux.setNext(mo);
				first = aux;
			}
			else if ( mo.getelem().compareTo(aux.getelem())==0)
			{
				aux=mo;
			}
			else if(mo.getelem().compareTo(aux.getelem()) <0 )
			{
				NodeList<T> previoustMo = mo.getPrevius();
				aux.setNext(mo);
				previoustMo.setNext(aux);
				mo.setPrevius(aux);
				aux.setPrevius(previoustMo);
			}
			else
			{
				mo.setNext(aux);
				aux.setPrevius(mo);
				last = aux;
			}
		}
		size ++;
		return null;
	}

	public T addInOrdeRRr(T pelem)
	{
		NodeList<T> aux = new NodeList<T>();
		aux.setElement(pelem);
		if ( first == null)
		{
			first = aux;
			last = first;
		}
		else
		{
			NodeList<T> mo = first;
			while(mo.getelem().compareTo(aux.getelem())>0 && mo != last )
			{
				mo = mo.getNext()!= null ? mo.getNext() : last;
			}
			if( mo.getelem().compareTo(aux.getelem())== 0)
			{
				return mo.getelem();
			}
			else if(mo.getelem().compareTo(aux.getelem()) < 0 && mo.getelem().compareTo(first.getelem() )== 0)
			{
				mo.setPrevius(aux);
				aux.setNext(mo);
				first = aux;
			}
			else if(mo.getelem().compareTo(aux.getelem()) < 0)
			{
				NodeList<T> pre = mo.getPrevius();
				mo.setPrevius(aux);
				aux.setNext(mo);
				aux.setPrevius(pre);
				pre.setNext(aux);
			}
			else
			{
				mo.setNext(aux);
				aux.setPrevius(mo);
				last = aux;
			}
		}
		size++;
		return null;
	}

	/**
     * Obtiene el elemento buscado
     * @param pPosicion 
     * @return el elemento en la posicion T 
     */
	public T get(int pPosicion) {
		
		if (pPosicion < 0)
		{
			return null;
		}
		
		NodeList<T> pro = first;
		
		if (pro != null) 
		{
			for (int i=0;i< pPosicion ;i++)
			{
				if (!pro.hasNext())
				{
					return null;
				}
				pro = pro.getNext();
			}
			return pro.getelem();
		}
		return null;
	}

	/**
     * elimina un elemento en la poscion T
     * @param T elemento , il posicion
     * @return el elemento en la posicion T 
     */
	
	public boolean deleteAtK( int iL) {
		
		NodeList<T> pro = first;
		
		for( int i = 0; i <= size; i++)
		{
			if( i == iL)
			{
				if( pro.hasPrevious())
				{
					NodeList<T> temp = pro.getNext();
					pro.getPrevius().setNext(temp);
					
					size--;
					return true;
				}
				else
				{
					size--;
					first = pro.getNext();
					return true;
				}
			}
			pro = pro.getNext();
		}
	
	return false;

       
	}
	
	
	/**
     * Clase iteradora
     */
	
	public Iterator<T> iterator() {
		Iterator<T> iterator = new IteratorLinkedList();
		return iterator;
	}
	
	
		
	
	
	public class IteratorLinkedList implements Iterator<T>
	{
		private NodeList<T> proximo = first;

		@Override
		public boolean hasNext() {
			
			return proximo != null?true:false;
			
			
		}

		@Override
		public T next() {
			
			if( proximo == null)
			{
				throw new NoSuchElementException("no hay proximo");
			}
			T elemento = proximo.getelem();
			proximo = (NodeList<T>) proximo.getNext();
			return elemento;
		}
	}





	
	

}
