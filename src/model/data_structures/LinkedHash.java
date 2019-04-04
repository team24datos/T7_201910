package model.data_structures;

public class LinkedHash<Key extends Comparable<Key>, Value>
{
	private NodoHash<Key, Value> first;
	private NodoHash<Key, Value> next;
	private NodoHash<Key, Value> last;
	public LinkedHash() 
	{
		first=new NodoHash<Key, Value>(null, null);
		next=new NodoHash<Key, Value>(null, null);
		last=first;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key)
	{
		for(NodoHash<Key, Value> x=first; x != null; x= x.getNext())
		{
			if(key.equals(x.getKey()))
			{
				return x.getValue();
			}
		}
		return null;
	}
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value)
	{
		if(first==null)
		{
			first = new NodoHash<Key, Value>(key, value);
		}
		for(NodoHash<Key, Value> x=first; x != null; x= x.getNext())
		{
			if(key.equals(x.getKey()))
			{
				x.setValue(value);
				return;
			}
		}
		NodoHash<Key, Value> newNodo =new NodoHash<Key, Value>(key, value);
		last.setNext(newNodo);
		last=newNodo;
		
	}
	/**
	 * 
	 */
	public Value delete(Key key)
	{
		Value resp=null;
		for(NodoHash<Key, Value> x=first; x != null; x= x.getNext())
		{
			if(key.equals(x.getKey()))
			{
				resp=x.getValue();
				if(x.getPrevius()==null)
				{
					first=first.getNext();
				}
				else
				{
					x.getPrevius().setNext(x.getNext());
				}
			}
		}
		return resp;
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
	 * 
	 */
	public NodoHash<Key, Value> getFirst()
	{
		return first;
	}

}
