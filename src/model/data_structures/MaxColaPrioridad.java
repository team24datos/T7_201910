package model.data_structures;

public class MaxColaPrioridad<T extends Comparable<T>> 
{
	private int size;
	private LinkedList<T> PrioQueue;
	private T max;
	public MaxColaPrioridad()
	{
		PrioQueue= new LinkedList<T>();
		size=0;
	}
	/**
	 * Retorna n˙mero de elementos presentes en la cola de prioridad
	 * @return
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Agrega un elemento a la cola. Si el elemento ya existe y tiene una prioridad diferente, el elemento debe actualizarse en la cola de prioridad.
	 * @param elemento
	 */
	public void agregar(T elemento)
	{
		PrioQueue.addInorder(elemento);
		size++;
	}

	/**
	 * Saca/atiende el elemento m√°ximo en la cola y lo retorna; null en caso de cola vac√≠a
	 * @return
	 */
	public T delMax ()
	{
		size--;
		PrioQueue.deleteAtK(0);
		return max;
	}

	/**
	 * Obtener el elemento m·ximo (sin sacarlo de la Cola); null en caso de cola vac√≠a
	 * @return
	 */
	public T max()
	{
		max =(T) PrioQueue.getFirstNode().getelem();
		return max;
	}

	/**
	 * Retorna si la cola est· vacia o no
	 * @return
	 */
	public boolean esVacia ()
	{
		return size==0;
	}
}
