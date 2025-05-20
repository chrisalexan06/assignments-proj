package part2;

//interface to be implemented 
public interface TarrifPolicy {

	String evaluateTrade(double proposedTarrif, double minimumTarrif); //policy to check if tariff are valid or not 
}
