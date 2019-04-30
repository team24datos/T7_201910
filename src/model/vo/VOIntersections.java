package model.vo;

public class VOIntersections implements Comparable<VOIntersections>
{
	private int id;
	private double lat;
	private double lon;
	public VOIntersections(int pId,double pLat, double pLon) 
	{
		id=pId;
		lat=pLat;
		lon=pLon;
	}
	
	public int getId()
	{
		return id;
	}
	
	public double getLat() 
	{
		return lat;
	}
	
	public double getLon() 
	{
		return lon;
	}

	@Override
	public int compareTo(VOIntersections o) {
		
		return id - o.getId();
	}

}
