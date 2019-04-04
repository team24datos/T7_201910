package model.data_structures;

public class NodoHash<Key extends Comparable<Key>, Value>
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * siguiente nodo.
	 */
	private NodoHash<Key,Value> next;

	/**
	 * anterior nodo.
	 */
	private NodoHash<Key,Value>  previous;
	/**
	 * llave
	 */
	private Key key;
	/**
	 * valor
	 */
	private Value value;


	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	public NodoHash(Key pKey,Value pValue)
	{
		key=pKey;
		value=pValue;
		next = null;
		previous = null;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------


	public void put(Key key, Value value)
	{
		this.key = key;
		this.value = value;
	}
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
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
	 * Indica si hay un anterior 
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
	public boolean setNext( NodoHash<Key, Value> pSiguiente)
	{
		next = pSiguiente;
		return true;
	}

	/**
	 * Cambia el anterior en la lista.
	 * @param elemento a cambiar
	 * @return true si se logra cambiar el anterior 
	 */
	public boolean setPrevius( NodoHash<Key, Value> PreviousElem )
	{
		previous = PreviousElem;
		return true;
	}

	/**
	 * Da el siguiente nodo en la lista
	 * @return el siguiente nodo de la lista
	 */
	public NodoHash<Key, Value> getNext( )
	{
		return next;
	}

	/**
	 * Da el anterior nodo de la lista
	 * @return el aanterior nodo de la lista
	 */
	public NodoHash<Key, Value> getPrevius()
	{
		return previous;
	}



}
