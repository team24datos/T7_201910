package model.vo;

public class VOWay implements Comparable<VOWay>{

	// Atributos -------------------------------------------------------
	
	/** Un identificador que representa al arco*/
	private int id;
	
	/** El identificador del primer nodo que conecta el arco*/
	private long idNodo1;
	
	/** El identificador del segundo nodo que conecta el arco */
	private long idNodo2;
	
	// Constructor -------------------------------------------------------
	
	/**
	 * Método que construye un objeto de tipo VOWay que representa un arco. 
	 * @param pId
	 * @param pIdNodo1
	 * @param pIdNodo2
	 */
	public VOWay(int pId, long pIdNodo1, long pIdNodo2) {
		id = pId;
		idNodo1 = pIdNodo1;
		idNodo2 = pIdNodo2;
	}
	
	// Métodos ---------------------------------------------------------------
	
	public int getId() {
		return id;
	}
	
	public long getNodo1() {
		return idNodo1;
	}
	
	public long getNodo2() {
		return idNodo2;
	}
	
	public boolean hasNodo(int pId) {
		boolean respuesta = false;
		if(pId == idNodo1 || pId == idNodo2) {
			respuesta = true;
		}
		return true;
	}
	
	@Override
	public int compareTo(VOWay arg0) {
		return id - arg0.getId();
	}

}
