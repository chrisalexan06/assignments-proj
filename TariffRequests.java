
//--------------------------------------------------------
// Assignment 3
// Question: Part 2 linked list (interface)
// Written by: Christina Alexandrakis 40326572
//--------------------------------------------------------
package part2;

public class TariffRequests{
	
	//attributes of the request 
	private String reqID;
	private String origin;
	private String destination;
	private String category;
	private double tradevalue;
	private double proposedt;
	
	
	//constructors
	//default 
	public TariffRequests() {
		this.reqID = null;
		this.origin = null;
		this.destination = null;
		this.category = null;
		this.tradevalue = 0.0;
		this.proposedt = 0.0; 
		
	}
	
	//param
	public TariffRequests(String reqID, String origin, String destination, String category, double tradevalues, double proposedt) {
		this.reqID = reqID;
		this.origin = origin;
		this.destination = destination;
		this.category = category;
		this.tradevalue = tradevalues;
		this.proposedt = proposedt; 
	}
	
	//copy
	public TariffRequests(TariffRequests other) {
		this.reqID = other.reqID;
		this.origin = other.origin;
		this.destination = other.destination;
		this.category = other.category;
		this.tradevalue = other.tradevalue;
		this.proposedt = other.proposedt;
	}
	
	//accessors, get values 
	public String getID() {
		return this.reqID;
	}
	
	public String getOrigin() {
		return this.origin;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public String getCat() {
		return this.category;
	}
	
	public double getTrade() {
		return this.tradevalue;
	}
	
	public double getProposed() {
		return this.proposedt;
	}
	
	//mutators, set values 
	public void setID(String id) {
		this.reqID = id;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setCat(String category) {
		this.category = category;
	}
	
	public void setTrade(double trade) {
		this.tradevalue = trade;
	}
	
	public void setProposed(double proposed) {
		this.proposedt = proposed;
	}
	
	
	//calculating surcharge if below the 20% mark, to use in main 
	public double surcharge(double mintariff) {
		double surcharge = this.getTrade() *((mintariff - this.getProposed())/100);
		return Double.parseDouble(String.format("%.2f", surcharge));
	}
	
	//to string 
	@Override
	public String toString() {
		return this.reqID + " " + this.origin + " " + this.destination + " " + this.category + " " + this.tradevalue + " " + this.proposedt;
	}
	
	

	

}
