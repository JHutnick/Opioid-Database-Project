//Nate Lee

package opioid.model;

public class DeathData { 
	protected int Year;
	protected long Population;
	protected double DeathRateRangeLow;
	protected String County;
	protected String State;

//	protected StatusLevel statusLevel;
	
//	public enum StatusLevel {
//		novice, intermediate, advanced
//	}
	public DeathData(double DeathRateRangeLow)  //not sure about this one
	{
		this.DeathRateRangeLow = DeathRateRangeLow;
	}
	
	public DeathData(int Year, long Population, double DeathRateRangeLow, String County, String State)
	{
		this.Year = Year;
		this.Population = Population;
		this.DeathRateRangeLow = DeathRateRangeLow;
		this.County = County;
		this.State = State;
	}
	
	/** Getters and setters. */
	
	public int getYear()
	{
		return Year;
	}
	public void setYear(int Year)
	{
		this.Year = Year;
	}
	
	public long getPopulation()
	{
		return Population;
	}
	public void setPopulation(long Population)
	{
		this.Population = Population;
	}
	
	public double getDeathRateRangeLow()
	{
		return DeathRateRangeLow;
	}
	public void setDeathRateRangeLow(long DeathRateRangeLow)
	{
		this.DeathRateRangeLow = DeathRateRangeLow;
	}
	
	public String getCounty()
	{
		return County;
	}
	public void setCounty(String County)
	{
		this.County = County;
	}
	
	public String getState()
	{
		return State;
	}
	public void setState(String State)
	{
		this.State = State;
	}
	
	
	
}
