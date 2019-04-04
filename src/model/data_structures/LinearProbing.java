package model.data_structures;

import java.util.Iterator;

public class LinearProbing<Key extends Comparable<Key>, Value> implements TablaHash<Key, Value>
{
	private int N;
	private int M;
	private float factorDeCarga;
	private NodoHash[] tablaHash;

	/**
	 * 
	 * @param m
	 */
	public LinearProbing(int m)
	{
		M=m;
		tablaHash= new NodoHash[M];
		factorDeCarga=0;
		for(int i=0;i<tablaHash.length;i++)
		{
			tablaHash[i]= new NodoHash<Key, Value>(null, null);
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
	 */
	private int hash(Key key)
	{ 
		return (key.hashCode() & 0x7fffffff) % M;
	}

	@Override
	public void put(Key key, Value value) 
	{
		boolean fin=false;
		int i;
		for (i = hash(key); tablaHash[i]!= null && !fin; i = (i+1) % M)
		{
			NodoHash<Key, Value> nodoActual= tablaHash[i];
			if(nodoActual!= null &&nodoActual.getKey()!=null && nodoActual.getKey().equals(key))
			{
				nodoActual.setValue(value);
				fin=true;
			}
			else
			{
				nodoActual.put(key, value);
				N++;
				fin=true;
			}
		}
		if(excedioFactorDeCarga())
		{
			rehash();
		}
	}


	@Override
	public Value get(Key key) 
	{
		for (int i = hash(key); tablaHash[i] != null; i = (i+1) % M)
		{
			if(key.equals(tablaHash[i].getKey()))
			{
				return (Value) tablaHash[i].getValue();
			}
		}
		return null;
	}

	@Override
	public Value delete(Key key) 
	{
		Value resp=null;
		int i = hash(key);
		if(tablaHash[i]==null)
		{
			return null;
		}
		while (tablaHash[i]!=null&&!key.equals(tablaHash[i].getKey()))
		{
			i = (i + 1) % M;
		}
		resp = (Value)tablaHash[i].getValue();
		//Se vuelve null el elemento a eliminar
		tablaHash[i]=null;
		//Se re organiza el resto
		i = (i + 1) % M;
		while (tablaHash[i] != null && tablaHash[i].getKey()!= null)
		{
			Key keyToRedo = (Key) tablaHash[i].getKey();
			Value valToRedo = (Value) tablaHash[i].getValue();
			tablaHash[i]=null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N--;
		return resp;

	}
	/**
	 * true si es primo
	 */
	private boolean esPrimo(int pNumero)
	{
		boolean esPrimo = true;
		for(int i=2; i<=pNumero/2 && esPrimo;i++)
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
	private boolean excedioFactorDeCarga()
	{
		boolean excedio = false;
		factorDeCarga = N/M;
		if(factorDeCarga>0.75)
		{
			excedio = true;
		}
		return excedio;
	}
	// TODO Revisar 
	public void rehash() 
	{
		M = M*2;
		while(!esPrimo(M))
		{
			M++;
		}
		Object[] aux = tablaHash;
		tablaHash = new NodoHash[M];
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
		return new IteratorLinearProbing();
	}

	public class IteratorLinearProbing implements Iterator<Key>
	{// -----------------------------------------------------------------
		// Atributos
		// -----------------------------------------------------------------			
		private Key proxiKey;
		private NodoHash proximo;
		private int index;

		// -----------------------------------------------------------------
		// Constructor
		// -----------------------------------------------------------------
		public IteratorLinearProbing() 
		{
			proximo = (NodoHash)tablaHash[0];
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
					proximo = (NodoHash)tablaHash[index];
					index++;
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
