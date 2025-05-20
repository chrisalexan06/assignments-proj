//--------------------------------------------------------
// Assignment 3
// Question: Part 2 linked list (interface)
// Written by: Christina Alexandrakis 40326572
//--------------------------------------------------------
package part2;

//interface to be implemented 
public interface TarrifPolicy {

	String evaluateTrade(double proposedTarrif, double minimumTarrif); //policy to check if tariff are valid or not 
}
