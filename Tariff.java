//--------------------------------------------------------
// Assignment 3
// Question: Part 2 tariffs 
// Written by: Christina Alexandrakis 40326572
//--------------------------------------------------------
		
package part2;


public class Tariff {

	//attributes
	private String destinationCountry;
	private String originCountry;
	private String productCategory;
	private double minimumTarrif;
	
	//constructors
	//default
	public Tariff() {
		this.destinationCountry = null;
		this.originCountry = null;
		this.productCategory = null;
		this.minimumTarrif = 0.0;
	}
	
	//param constructor
	public Tariff(String dCountry, String oCountry, String pCat, double mintarrif) {
		this.destinationCountry = dCountry;
		this.originCountry = oCountry;
		this.productCategory = pCat;
		this.minimumTarrif = mintarrif;
	}
	
	//copy constructor
	public Tariff(Tariff other) {
		this.destinationCountry = other.destinationCountry;
		this.originCountry = other.originCountry;
		this.productCategory = other.productCategory;
		this.minimumTarrif = other.minimumTarrif;
	}
	
	//getters for all attributes
	public String getDestination() {
		return this.destinationCountry;
	}
	
	public String getOrigin() {
		return this.originCountry;
	}
	
	public String getProductCat() {
		return this.productCategory;
	}
	
	public double getMinTariff() {
		return this.minimumTarrif;
	}
	
	//setter for all attributes 
	public void setDestination(String desti) {
		this.destinationCountry = desti;
	}
	
	public void setOrigin(String origin) {
		this.originCountry = origin;
	}
	
	public void setCategory(String pcat) {
		this.productCategory = pcat;
	}
	
	public void setMiniTarriff(double min) {
		this.minimumTarrif = min;
	}
	
	//cloning method
	@Override
	public Tariff clone() {
		return new Tariff(this);
	}
	
	//toString 
	@Override
	public String toString() {
		return destinationCountry + " " + originCountry + " " + productCategory + " " + minimumTarrif + "\n";
	}//output all attributes 
	
	//new equals method
	@Override
	public boolean equals(Object other) { //compare to other object and compare all attributes if both tariff objects 
		if (this == other) 
			return true;
        if (!(other instanceof Tariff)) 
        	return false;
        Tariff obj = (Tariff) other;
        return (this.destinationCountry.equals(obj.destinationCountry) && this.originCountry.equals(obj.originCountry)&& this.productCategory.equals(obj.productCategory)&& (this.minimumTarrif == obj.minimumTarrif));
    
	}
}
