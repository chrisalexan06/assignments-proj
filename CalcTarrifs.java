//--------------------------------------------------------
// Assignment 3
// Question: Part 1 file i/o
// Written by: Christina Alexandrakis 40326572
//--------------------------------------------------------

package part1;

import java.io.*;
import java.util.*;

public class CalcTarrifs {
	
	 public static int compareStrings(String str1, String str2) {
	        // Base case: If one string is empty, the other string is "greater" by default.
	        if (str1.isEmpty() && str2.isEmpty()) {
	            return 0; // Both strings are equal
	        } else if (str1.isEmpty()) {
	            return -1; // An empty string is considered "less than" any non-empty string
	        } else if (str2.isEmpty()) {
	            return 1; // A non-empty string is considered "greater than" an empty string
	        }

	        // Compare the first character of both strings
	        char ch1 = str1.charAt(0);
	        char ch2 = str2.charAt(0);

	        if (ch1 < ch2) {
	            return -1; // str1 is lexicographically smaller
	        } else if (ch1 > ch2) {
	            return 1;  // str1 is lexicographically larger
	        } else {
	            // If the first characters are equal, compare the rest of the strings recursively
	            return compareStrings(str1.substring(1), str2.substring(1));
	        }
	    }

	public static void main(String[] args) throws IOException{

		//part 1 read data from file
		ArrayList<Product> products = new ArrayList<Product>();
		String line = null;
        Scanner reader = new Scanner(new FileInputStream("TradeData.txt")); //new reader
        
        boolean end = true; //check if there are any lines left in text file
        int linecount = 0; //number of lines, how many times we need to write after 
        while (end) {
            try {
   
                if (reader.hasNextLine()) {
                   line = reader.nextLine();
                    reader.useDelimiter(",");
                    linecount++;
                    
                    String[] parts = line.split(",\\s*");  // This will split by commas and ignore any extra spaces

                    if (parts.length >= 4) { // Ensure there are at least 4 fields for each of the parameters 
                        String name = parts[0];
                        String country = parts[1];
                        String category = parts[2];
                        Double iniPrice = Double.parseDouble(parts[3]); // Parse price as Double
                        
                        
                        Product prod = new Product(name, country, category, iniPrice); //make new object to add to list 
                        products.add(prod); //add to array list 
                    }
                    
                    
                } else //stop when no more lines 
                    end = false;

            } catch (NumberFormatException e) { //throw error when error with parsing values 
            
            	System.err.println("Error parsing a number: " + e.getMessage());
                break; // Exit the loop if an error occurs
            }
        }

        reader.close();  // Close the reader after done
    
        //part 2 applying tariffs
        //getting country and checking if tariffs should be applied and applying them if yes
        for(int i = 0; i< products.size(); i++) {
        	Product current = products.get(i); //add variable for product in list that is being evaluated 
        	String count = current.getCountry();
        	double ini = current.getIniPrice();
        	
        	switch(count) { //check for the country to determine how to adjust the tariffs 
        	case "China":{
        		double now = ini*1.25; //added tariffs 
        		double value = Double.parseDouble(String.format("%.1f", now)); //round to keep decimals short to not have many decimals, irrelevant to price rounding need max 2 
        		current.setIniPrice(value); //change price 
        	}
        	break;
        	
        	case "USA":{
        		if(current.getCat().equals("Electronics")){ //depends on what type of category, not all USA products will have adjusted prices 
        			double now = ini*1.10;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);
        		}	
        	}
        	break;
        	
        	case "Japan":{
        		if(current.getCat().equals("Automobiles")){ //same as USA  and for the rest that have specified categories 
        			double now = ini*1.15;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);
        		}
        	}
        	break;
        	
        	case "India":{ //agriculture products from India
        		if(current.getCat().equals("Agriculture")){
        			double now = ini*1.05;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);        		}
        	}
        	break;
        	
        	case "South Korea":{ //electronics from South Korea
        		if(current.getCat().equals("Electronics")){
        			double now = ini*1.08;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);        		}
        	}
        	break;
        	
        	case "Saudi Arabia":{ //energy products from Saudi Arabia
        		if(current.getCat().equals("Energy")){
        			double now = ini*1.12;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);
        		}
        	}
        	break;
        	
        	case "Germany":{//manufacturing products from Germany 
        		if(current.getCat().equals("Manufacturing")){
        			double now = ini*1.06;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);
        		}
        	}
        	break;
        	
        	case "Bangladesh":{
        		if(current.getCat().equals("Textile")){
        			double now = ini*1.04;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);
        		}
        	}
        	break;
        	
        	case "Brazil":{ //agriculture products from Brazil 
        		if(current.getCat().equals("Agriculture")){
        			double now = ini*1.09;
        			double value = Double.parseDouble(String.format("%.1f", now));
        			current.setIniPrice(value);        		}
        	}
        	break;
        	default:{ //if country inputed is not valid or in the list 
        		System.out.println("Invalid country input present, no tarrifs applied "); //nothing will change 
        	}
        	}
        	
        }
        
        //part 3 sort products alphabetically
        //temp object holders for products to rearrange order of list without losing the data 
        Product temp1 = null;
        Product temp2 = null;
        
        for(int j = 0; j <products.size(); j++) {
        	for(int k = j+1; k < products.size(); k++) {
        		temp1 = products.get(k);
        		temp2 = products.get(j);
        		
        		if(compareStrings(temp1.getName(),temp2.getName()) == -1) { //compare strings based on method written compareStrings 
        			products.set(j, temp1); //swapping places for the one that is higher up in the list alphabetically
        			products.set(k, temp2);
        		}
        	}
        }
        
        //part 4 writing changed data to the file 
        BufferedWriter writer = new BufferedWriter(new FileWriter("UpdatedTradeData.txt")); //new writer to write adjusted values into text file 
        for(int n=0; n < products.size(); n++) {
			writer.write(products.get(n).toString()); //iterate for each updated priced product in the new file
		}
        writer.close();
        
	}
}

