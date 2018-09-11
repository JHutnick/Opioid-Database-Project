//Nate Lee

package opioid.model;

public class County { 
	protected int Zip;
	protected String City;
	protected String State;
	protected String CountyName;
	

//	protected StatusLevel statusLevel;
	
//	public enum StatusLevel {
//		novice, intermediate, advanced
//	}
	public County(String CountyName)
	{
		this.CountyName = CountyName;
	}
	
	public County(int Zip, String City, String State, String CountyName)
	{
		this.Zip = Zip;
		this.City = City;
		this.State = State;
		this.CountyName = CountyName;
	}
	
	/** Getters and setters. */
	
	public int getZip()
	{
		return Zip;
	}
	public void setZip(int Zip)
	{
		this.Zip = Zip;
	}
	
	public String getCity()
	{
		return City;
	}
	public void setCity(String City)
	{
		this.City = City;
	}
	public String getState()
	{
		return State;
	}
	public void setState(String State)
	{
		this.State = State;
	}
	public String getCountyName()
	{
		return CountyName;
	}
	public void setCountyName(String CountyName)
	{
		this.CountyName = CountyName;
	}

	
	
}
