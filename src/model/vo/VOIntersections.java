package model.vo;

public class VOIntersections implements Comparable<VOIntersections> {
	
	// Atributos ------------------------------------------------------------------------------------
	
	/** Identificador del nodo de intersección */
	private long id;
	
	/** Latitud del nodo */
	private double lat;
	
	/** Longitud del nodo */
	private double lon;
	
	/** Cantidad de infracciones que se cometieron en esta intersección.*/
	private int cantidad;
	
	// Constructor ------------------------------------------------------------------------------------
	
	/**
	 * Construye un objeto de tipo VOIntersection que representa un nodo del grafo. 
	 * @param pId
	 * @param pLat
	 * @param pLon
	 */
	public VOIntersections(long pId,double pLat, double pLon) 
	{
		id = pId;
		lat = pLat;
		lon = pLon;
		cantidad = 0;
	}
	
	// Métodos ------------------------------------------------------------------------------------
	
	/**
	 * @return Retorna el identificador del nodo
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return la cantidad de infracciones que se cometieron en esta intersección. 
	 */
	public int getCantidad() {
		return cantidad;
	}
	
	/**
	 * Agrega una instancia a la cantidad de infracciones que se cometieron. 
	 */
	public void agregarInfraccion() {
		cantidad ++;
	}
	/**
	 * @return latitud de la ubicación del nodo
	 */
	public double getLat() {
		return lat;
	}
	
	/**
	 * @return longitud de la ubicación del nodo
	 */
	public double getLon() {
		return lon;
	}

	@Override
	public int compareTo(VOIntersections o) {
		
		return (int) id - (int) o.getId();
	}

}
