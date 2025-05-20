

package part2;

public class NoSuchElementException extends Exception{ //extends exception, type of exception
	
	//should be thrown if the index is out of bound for the given list 
	
	//default constructor
	public NoSuchElementException() {
        super();
    }
	//param constructor to output message 
    public NoSuchElementException(String message) {
        super(message);
    }

}
