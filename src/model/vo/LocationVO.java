package model.vo;

public class LocationVO implements Comparable<LocationVO>
{
	
	int addressId;
	String location;
	boolean repetido=false;
	/**
	 * Corresponde al número de veces que aparece el addressId en los registros cargados
	 */
	int numberOfRegisters;

	public LocationVO(int pAddressId,String pLocation, int pNumero)
	{
		addressId=pAddressId;
		location=pLocation;
		numberOfRegisters=pNumero;
	}

	@Override
	public int compareTo(LocationVO that) 
	{
		int resp=2;
		if(that==null){
			return resp;
		}
		
		if(this.numberOfRegisters==that.numberOfRegisters)
		{
			repetido=true;
			return this.location.compareToIgnoreCase(that.location);
		}
		else if(this.numberOfRegisters>that.numberOfRegisters)
		{
			resp=1;
		}
		else
		{
			resp=-1;
		}
		return resp;
	} 
	public int compareLocation(LocationVO that)
	{
		return this.location.compareToIgnoreCase(that.location);
	}
	//
	// Getters and Setters
	//
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNumberOfRegisters() {
		return numberOfRegisters;
	}
	public void setNumberOfRegisters(int numberOfRegisters) {
		this.numberOfRegisters = numberOfRegisters;
	}
	public String toString()
	{
		return "Location: "+location+" addressId: "+addressId+" #reg: "+numberOfRegisters;
	}

}
