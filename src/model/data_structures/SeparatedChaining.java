package model.data_structures;

import java.util.Iterator;

public class SeparatedChaining<Key extends Comparable<Key>, Value> implements TablaHash<Key, Value>
{
	private int N;
	private int M;
	private float factorDeCarga;
	private LinkedHash<Key, Value> tablaHash[];
	public SeparatedChaining(int pM)
	{
		M=pM;
		N=0;
		factorDeCarga = 0;

		tablaHash = new LinkedHash[M];
		for(int i = 0; i<M;i++)
		{
			tablaHash[i]=new LinkedHash<Key, Value>();
		}
	}
	//Para las pruebas
	public int getM()
	{
		return M;
	}
	public int getN()
	{
		return N;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private int hash(Key key)
	{ 
		return (key.hashCode() & 0x7fffffff) % M;
	}

	@Override
	public void put(Key key, Value value) 
	{
		int i = hash(key);
		tablaHash[i].put(key, value);

		if(actualizarFactorDeCarga())
		{
			rehash();
		}
	}

	@Override
	public Value get(Key key) 
	{
		return (Value) tablaHash[hash(key)].get(key);
	}
	@Override
	public Value delete(Key key) 
	{
		int i = hash(key);
		return tablaHash[i].delete(key);
	}
	/**
	 * 
	 * @param pNumero
	 * @return
	 */
	private boolean esPrimo(int pNumero)
	{
		boolean esPrimo = true;
		for(int i=2; i<=Math.sqrt(pNumero) && esPrimo;i++)
		{
			if(pNumero%i == 0)
			{
				esPrimo = false;
			}
		}
		return esPrimo;
	}
	/**
	 * @return
	 */
	private boolean actualizarFactorDeCarga()
	{
		boolean excedio = false;
		factorDeCarga = N/M;
		if(factorDeCarga>5)
		{
			excedio = true;
		}
		return excedio;
	}

	/**
	 * 
	 */
	public void rehash() 
	{
		M = M*2;
		while(!esPrimo(M))
		{
			M++;
		}
		Object[] aux = tablaHash;
		tablaHash = new LinkedHash[M];
		N=0;
		for (int i = 0; i < aux.length; i++) 
		{
			NodoHash actual = (NodoHash)aux[i];
			while(actual != null)
			{
				put((Key) actual.getKey(), (Value) actual.getValue());
				actual = actual.getNext();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public Iterator<Key> keys()
	{
		Iterator<Key> it = new IteratorSeparatedChaining();
		return it;
	}

	/**
	 * 
	 */
	public class IteratorSeparatedChaining implements Iterator<Key>
	{
		// -----------------------------------------------------------------
		// Atributos
		// -----------------------------------------------------------------			
		private Key proxiKey;
		private NodoHash proximo;
		private int index;

		// -----------------------------------------------------------------
		// Constructor
		// -----------------------------------------------------------------
		public IteratorSeparatedChaining() 
		{
			proximo = (NodoHash)tablaHash[0].getFirst();
			proxiKey=(Key)proximo.getKey();
			index = 1;
		}

		// -----------------------------------------------------------------
		// Métodos
		// -----------------------------------------------------------------

		@Override
		public boolean hasNext() 
		{
			return proximo != null;
		}

		@Override
		public Key next() 
		{
			Key respuesta = null;
			if(proximo != null)
			{
				respuesta = (Key) proximo.getKey();
				if(proximo.getNext()!=null)
				{
					proximo = proximo.getNext();
				}
				else if(tablaHash.length!=index)
				{
					// TODO Recorrer toda la tabla
					index++;
					proximo = (NodoHash)tablaHash[index].getFirst();					
				}
				else
				{
					proximo=null;
				}
			}
			return respuesta;
		}
	}
}
