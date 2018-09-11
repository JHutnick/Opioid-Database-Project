//Nate Lee

package opioid.model;

public class DrugCount { 
	protected int DoctorId;
	protected int OpioidSupply;
	protected int ErSupply;


//	protected StatusLevel statusLevel;
	
//	public enum StatusLevel {
//		novice, intermediate, advanced
//	}
	public DrugCount(int DoctorId)
	{
		this.DoctorId = DoctorId;
	}
	
	public DrugCount(int DoctorId, int OpioidSupply, int ErSupply)
	{
		this.DoctorId = DoctorId;
		this.OpioidSupply = OpioidSupply;
		this.ErSupply = ErSupply;
		//this.ErOpioidRate = ErOpioidRate;
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
	
	public int getOpioidSupply()
	{
		return OpioidSupply;
	}
	public void setOpioidSupply(int OpioidSupply)
	{
		this.OpioidSupply = OpioidSupply;
	}
	public int getErSupply()
	{
		return ErSupply;
	}
	public void setErSupply(int ErSupply)
	{
		this.ErSupply = ErSupply;
	}
}
