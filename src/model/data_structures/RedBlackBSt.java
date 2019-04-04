/**
 * 
 */
package model.data_structures;

/**
 * @author Team 24
 *
 */
public class RedBlackBSt<K,V>
{
	private int size;
	private int height;
	/**
	 * 
	 */
	public RedBlackBSt() 
	{
		size=0;
		height=0;
	}

	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int getHeight(K key)
	{
		//TODO
		return 0;
	}
	
	public boolean contains(K key)
	{
		//TODO
		return true;
	}
	
	public void put(K key, V val)
	{
		//TODO
		size++;
	}
	
	public int height()
	{
		return height;
	}
	
	public K min()
	{
		//TODO 
		return null;
	}
	
	public K max()
	{
		//TODO 
		return null;
	}
	
	public boolean check()
	{
		//TODO
		return true;
	}
	/*
	Iterator <K> keys()
	Iterator<V> valuesInRange(K init, K end)
	Iterator<K> keysInRange(K init, K end)*/
}
