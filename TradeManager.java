package part2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TradeManager {

    public static void main(String[] args) throws IOException, part2.NoSuchElementException {
    	
    	//tariff list objects for testing 
        TariffList one = new TariffList();
        TariffList two = new TariffList(); 

        // go through file Tariffs.txt
        Scanner reader = new Scanner(new FileInputStream("Tariffs.txt"));
        int linecount = 0;

        while (reader.hasNextLine()) { //check if line
            try {
                String line = reader.nextLine().trim(); //go through each line and separate the contents for the attributes 
                String[] tokens = line.split("\\s+");//split by space 

                if (tokens.length >= 4) { //sure there is the right number of inputs 
                	//attributes for the tariff object 
                    String destination = tokens[0];
                    String origin = tokens[1];
                    String category = tokens[2];
                    double mintariff = Double.parseDouble(tokens[3]); //parse 

                    //create new tariff object 
                    Tariff add = new Tariff(destination, origin, category, mintariff); 
                    //if it contains do not add, avoid duplicates 
                    if (one.contains(origin, destination, category)) {
                        System.out.println("This tariff is already present in the list: " + add);
                        //if false then we can add 
                    } else {
                        one.addToStart(add); //adding to the list "one"
                    }
                    linecount++; //increasing line count to process the number of iterations 
                }

            } catch (NumberFormatException e) { //throws when parsing is incorrect  
                System.err.println("Error parsing a number: " + e.getMessage());
                break;
            }
        }

        reader.close(); //close first reader of tariffs.txt file
       // one.display(); //display list that was extracted  

        // handling TradeRequests.txt file
        ArrayList<TariffRequests> requests = new ArrayList<>(); //store the request objects, easier to handle if they are objects 
        Scanner reader2 = new Scanner(new FileInputStream("TradeRequests.txt")); //create new reader for new file  

        while (reader2.hasNextLine()) { //check for line in the text file 
            try {
                String line = reader2.nextLine().trim(); //cut up data in the line to place for each attribute of the request 
                String[] tokens = line.split("\\s+");//split by space 
                
                if (tokens.length >= 6) { //get attributes for all requests 
                    String reqID = tokens[0];
                    String origin = tokens[1];
                    String destination = tokens[2];
                    String category = tokens[3];
                    double tradeval = Double.parseDouble(tokens[4]); //parse trade value 
                    double proposedt = Double.parseDouble(tokens[5]); //parse tariff value 

                    //make new request object w the attributes 
                    TariffRequests request = new TariffRequests(reqID, origin, destination, category, tradeval, proposedt);
                    requests.add(request); //add req to the list of reqs 
                }

            } catch (NumberFormatException e) { //if bad parsing 
                System.err.println("Error parsing a number: " + e.getMessage());
                break;
            }
        }

        reader2.close(); //close reader 

        // check each trade request
        for (TariffRequests current : requests) { //go through all the requests in the list 
        	//get origin and destination country and category from the objects in the list 
            String org = current.getOrigin();
            String dest = current.getDestination();
            String cat = current.getCat();

            boolean exists = one.contains(org, dest, cat); //check if actually in list of tariffs 
            if (exists == false ) { //if not then req is rejected 
                System.out.println(current.getID() + " - Rejected\nNo matching tariff found in list.\n");
                continue;
            }

            //get the node from searching and get the minimum tariff to check if valid or not 
            double min = one.find(org, dest, cat).getTariff().getMinTariff();

            //evaluated trade outputs string, compare if the right string which is the condition of the request
            switch (one.evaluateTrade(current.getProposed(), min)) {
                case "Accepted": //if in range of minimum tariff 
                    System.out.println(current.getID() + " - Accepted\nProposed tariff meets or exceeds the minimum requirement\n");
                    break;
                case "Conditionally Accepted": //close to in range of minimum tariff 
                    System.out.println(current.getID() + " - Conditionally Accepted\nProposed tariff " +
                            current.getProposed() + "% is within 20% of the required minimum tariff " + min + "%.\n");
                    break;
                case "Rejected": //not in the right range 
                    System.out.println(current.getID() + " - Rejected\nProposed tariff " +
                            current.getProposed() + "% is more than 20% below the required minimum tariff " + min + "%.\n");
                    break;
                default: //error or outputting something wrong
                    System.out.println(current.getID() + " - Error\nUnexpected evaluation result.\n");
                    break;
            }
        }

        // prompt user to search for tariffs 
        Scanner keyboard = new Scanner(System.in); //input by user 
        System.out.println("Would you like to search for a tariff? (yes/no)"); //option yes or no 

        while (keyboard.next().equalsIgnoreCase("yes")) { //if the user says yes proceed to check 
        	//prompt for each identifying attribute tariff, no need for minimum
            System.out.println("Input the destination country:"); 
            String destination = keyboard.next();

            System.out.println("Input the origin country:");
            String origin = keyboard.next();

            System.out.println("Input the product category:");
            String category = keyboard.next();

            if (one.contains(origin, destination, category)) { //use contains, outputs true or false 
                one.find(origin, destination, category); // already prints location
            } else {
                System.out.println("This tariff is not in the list."); //if not in the list 
            }

            System.out.println("Would you like to search for another tariff? (yes/no)"); //will loop if user inputs yes again 
        }

        keyboard.close();
        
        
        
        // Create some sample Tariff objects
        Tariff t1 = new Tariff("USA", "Canada", "Electronics", 50.0);
        Tariff t2 = new Tariff("Germany", "France", "Automotive", 30.0);
        Tariff t3 = new Tariff("China", "India", "Textiles", 40.0);
        Tariff t4 = new Tariff("USA", "Mexico", "Electronics", 60.0);
        
        // Add Tariffs to the list using addToStart (this will add them at the start)
        two.addToStart(t1);
        two.addToStart(t2);
        two.addToStart(t3);
        two.addToStart(t4);
        
        
        // Display Tariff List after additions
        System.out.println("Tariff List after adding items to start:");
        two.display();
        
        //insertAtIndex method (add a tariff at a specific index)
        Tariff t5 = new Tariff("Brazil", "Argentina", "Agriculture", 55.0);
        
        two.insertAtIndex(t5, 2); 
        
        //Tariff List after
        System.out.println("\nTariff List after inserting item at index 2:");
        two.display();
        
        //test contains
        boolean contains = two.contains("China", "India", "Textiles");
        System.out.println("\nContains tariff related to China to India & textiles?  " + contains);
        
        //test find 
        if( two.find("Germany", "France", "Automotive") != null) {
        	 System.out.println("\nFound tariff: " + two.find("Germany", "France", "Automotive").getTariff());
        }
        
        //test delete from start 
        try {
            String deletedTariff = two.deleteFromStart();
            System.out.println("\nDeleted tariff from start: " + deletedTariff);
        } catch (Exception e) {
            System.err.println("Error deleting from start: " + e.getMessage());
        }
        
        //display after deleting
        two.display();
        
        //test delete from index
        try {
            String deletedAtIndex = two.deleteFromIndex(1); // Delete from index 1
            System.out.println("\nDeleted tariff from index 1: " + deletedAtIndex);
        } catch (Exception e) {
            System.err.println("Error deleting from index: " + e.getMessage());
        }
        
        boolean areEqual = one.equals(two);
        System.out.println("\nAre the two tariff lists equal? " + areEqual);
        
        
        //test replace 
        Tariff t6 = new Tariff("India", "Nepal", "Spices", 45.0);
        try {
            two.replaceAtIndex(t6, 1); // Replace element at index 1
        } catch (Exception e) {
            System.err.println("Error replacing tariff: " + e.getMessage());
        }
        
        //display after changes 
        System.out.println("\nFinal Tariff List after replacements:");
        one.display();
        
    }
}
