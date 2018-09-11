//Nate Lee

package opioid.model;

public class DrugData { 
	protected int DoctorId;
	protected int TotalDaySupply;
	protected double OpioidPresciberRate;
	protected double ErOpioidRate;

//	protected StatusLevel statusLevel;
	
//	public enum StatusLevel {
//		novice, intermediate, advanced
//	}
	public DrugData(int DoctorId)
	{
		this.DoctorId = DoctorId;
	}
	
	public DrugData(int DoctorId, int TotalDaySupply, double OpioidPresciberRate, double ErOpioidRate)
	{
		this.DoctorId = DoctorId;
		this.TotalDaySupply = TotalDaySupply;
		this.OpioidPresciberRate = OpioidPresciberRate;
		this.ErOpioidRate = ErOpioidRate;
	}
	
	/** Getters and setters. */
	
	public int getDoctorId()
	{
		return DoctorId;
	}
	public void setDoctorId(int DoctorId)
	{
		this.DoctorId = DoctorId;
	}
	
	public int getTotalDaySupply()
	{
		return TotalDaySupply;
	}
	public void setTotalDaySupply(int TotalDaySupply)
	{
		this.TotalDaySupply = TotalDaySupply;
	}
	public double getOpioidPresciberRate()
	{
		return OpioidPresciberRate;
	}
	public void setOpioidPresciberRate(double OpioidPresciberRate)
	{
		this.OpioidPresciberRate = OpioidPresciberRate;
	}
	public double getErOpioidRate()
	{
		return ErOpioidRate;
	}
	public void setErOpioidRate(double ErOpioidRate)
	{
		this.ErOpioidRate = ErOpioidRate;
	}

	
	
}
