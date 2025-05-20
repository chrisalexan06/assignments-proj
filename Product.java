

package part1;
import java.io.*;
import java.util.*;

public class Product {
//attributes for products 
	private String productName;
	private String country;
	private String category;
	private double initialPrice;
	
	//default constructor 
	public Product() {
		this.initialPrice=0;
		this.productName = null;
		this.country = null;
		this.category = null;
	}
	
	//para, constructor 
	public Product(String name, String country, String cat, double price) {
		this.productName = name;
		this.country = country;
		this.category = cat;
		this.initialPrice = price;
	}
	
	//accessors for each attribute, get the value of object product for each attribute 
	public String getName() {
		return this.productName;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getCat() {
		return this.category;
	}
	
	public double getIniPrice() {
		return this.initialPrice;
	}
	
	
	//mutators, change the value of an attribute of a given object product
	public void setName(String Name) {
		this.productName = Name;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setIniPrice(double iniPrice) {
		this.initialPrice = iniPrice;
	}
	
	//to string method, write out product in string format with all attributes 
	@Override
	public String toString() {
		return this.productName + "," + this.country + "," + this.category + "," + this.initialPrice + "\n";
	}
}
