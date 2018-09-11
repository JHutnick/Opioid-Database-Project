//Nate Lee

package opioid.model;

public class DrugCost { 
	protected int DoctorId;
	protected double TotalDrugCost;
	protected double BrandDrugCost;
	protected double GenericDrugCost;
	protected double OpioidDrugCost;
	protected double ErOpioidDrugCost;
	protected double AntiDrugCost;

//	protected StatusLevel statusLevel;
	
//	public enum StatusLevel {
//		novice, intermediate, advanced
//	}
	public DrugCost(int DoctorId)  //not sure about this one
	{
		this.DoctorId = DoctorId;
	}
	
	public DrugCost(int DoctorId, double TotalDrugCost, double BrandDrugCost, double GenericDrugCost, double OpioidDrugCost,
			double ErOpioidDrugCost, double AntiDrugCost)
	{
		this.DoctorId = DoctorId;
		this.TotalDrugCost = TotalDrugCost;
		this.BrandDrugCost = BrandDrugCost;
		this.GenericDrugCost = GenericDrugCost;
		this.OpioidDrugCost = OpioidDrugCost;
		this.ErOpioidDrugCost = ErOpioidDrugCost;
		this.AntiDrugCost = AntiDrugCost;
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
	
	public double getTotalDrugCost()
	{
		return TotalDrugCost;
	}
	public void setTotalDrugCost(double TotalDrugCost)
	{
		this.TotalDrugCost = TotalDrugCost;
	}
	
	public double getBrandDrugCost()
	{
		return BrandDrugCost;
	}
	public void setBrandDrugCost(double BrandDrugCost)
	{
		this.BrandDrugCost = BrandDrugCost;
	}
	
	public double getGenericDrugCost()
	{
		return GenericDrugCost;
	}
	public void setGenericDrugCost(double GenericDrugCost)
	{
		this.GenericDrugCost = GenericDrugCost;
	}
	
	public double getOpioidDrugCost()
	{
		return OpioidDrugCost;
	}
	public void setOpioidDrugCost(double OpioidDrugCost)
	{
		this.OpioidDrugCost = OpioidDrugCost;
	}
	
	public double getErOpioidDrugCost()
	{
		return ErOpioidDrugCost;
	}
	public void setErOpioidDrugCost(double ErOpioidDrugCost)
	{
		this.ErOpioidDrugCost = ErOpioidDrugCost;
	}
	
	public double getAntiDrugCost()
	{
		return AntiDrugCost;
	}
	public void setAntiDrugCost(double AntiDrugCost)
	{
		this.AntiDrugCost = AntiDrugCost;
	}
}
