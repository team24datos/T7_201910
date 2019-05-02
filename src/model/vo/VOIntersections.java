package model.vo;

public class VOIntersections implements Comparable<VOIntersections> {
	
	// Atributos ------------------------------------------------------------------------------------
	
	/**
	 * Identificador del nodo de intersección
	 */
	private int id;
	
	/**
	 * Latitud del nodo 
	 */
	private double lat;
	
	/**
	 * Longitud del nodo
	 */
	private double lon;
	
	// Constructor ------------------------------------------------------------------------------------
	
	public VOIntersections(int pId,double pLat, double pLon) 
	{
		id=pId;
		lat=pLat;
		lon=pLon;
	}
	
	// Métodos ------------------------------------------------------------------------------------
	
	/**
	 * @return Retorna el identificador del nodo
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * @return latitud de la ubicación del nodo
	 */
	public double getLat() 
	{
		return lat;
	}
	
	/**
	 * @return longitud de la ubicación del nodo
	 */
	public double getLon() 
	{
		return lon;
	}

	@Override
	public int compareTo(VOIntersections o) {
		
		return id - o.getId();
	}

}
