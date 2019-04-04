package model.vo;

import model.data_structures.ArregloDinamico;

public class VOGeographicLocation implements Comparable<VOGeographicLocation>
{
	private double x;
	private double y;
	private ArregloDinamico<VOMovingViolations> sameXY;
	private int totalSinAccident;
	private int totalConAccident;
	private double totalPaid;
	private String location;

	/**
	 * CONSTRUCTOR
	 * @param pAddressId
	 * @param pLocation
	 * @param pNumero
	 */
	public VOGeographicLocation(double x, double y, ArregloDinamico<VOMovingViolations> pSameXY)
	{
		this.x=x;
		this.y=y;
		sameXY=pSameXY;
	}

	@Override
	public int compareTo(VOGeographicLocation that) 
	{
		int resp=2;
		if(that==null)
		{
			return resp;
		}
		
		if(this.x>that.getX())
		{
			return 1;
		}
		else if(this.x<that.getX())
		{
			return -1;
		}
		else
		{
			if(this.y>that.getY())
			{
				return 1;
			}
			else if(this.y<that.getY())
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
		
	} 
	
	public int compareLocation(VOGeographicLocation that)
	{
		return this.location.compareToIgnoreCase(that.location);
	}
	
	//
	// Getters
	//
	public double getX() 
	{
		return x;
	}

	public double getY() 
	{
		return y;
	}

	public ArregloDinamico<VOMovingViolations> getSameXY() 
	{
		return sameXY;
	}

	public double getPorSinAccident() 
	{
		return totalSinAccident/getTotalInfra();
	}

	public double getPorConAccident() 
	{
		return totalConAccident/getTotalInfra();
	}
	
	public int getTotalInfra()
	{
		return totalSinAccident+totalConAccident;
	}

	public double getTotalPaid() 
	{
		return totalPaid;
	}

	public String getLocation() 
	{
		return location;
	}
	

	public String toString()
	{
		return "GeoLocation: ("+x+","+y+"), total de infracciones: "+getTotalInfra()+", porcentaje sin accidente:"+getPorSinAccident()+", porcentaje con: "+getPorConAccident()+", el valor total a pagar: "+totalPaid;
	}


}
