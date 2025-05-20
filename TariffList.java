//--------------------------------------------------------
// Assignment 3
// Question: Part 2 tariffs 
// Written by: Christina Alexandrakis 40326572
//--------------------------------------------------------

package part2;


public class TariffList implements TarrifPolicy{

	//attributes
	private TariffNode head; //beginning of list
	private int size; //size/number of items in the list
	
	//constructors
	//default 
	public TariffList() {
		this.head = null;
		this.size = 0;
	}
	
	//deep copy of the list, needs to copy the whole list not just head and the size or else other positions will be null
	public TariffList(TariffList other) {
		if(other.head == null) {
			this.head = null;
			this.size = 0;
			
		}
		else {
			this.head = other.head.clone(); //copying head
			
			TariffNode og = other.head.getNode(); //get node of original object that will be copied 
			TariffNode copy = this.head; //copy is the original
			
			while(og != null) { //until list ends 
				copy.setNode(og.clone()); //copy each node onto new object
				copy = copy.getNode();  //move position along the list 
				og = og.getNode(); //for both lists new and old 
			}
			
			this.size = other.size; //adjust the size of the list 
			
		}
	}
	
	//add to start of the list
	public void addToStart(Tariff obj) {
		TariffNode n = new TariffNode(obj, this.head);
		
		this.head = n; //head now has the value of new node with new tariff object
		
		size++; //increase size as we are adding to the list 
		
	}
	
	public void insertAtIndex(Tariff obj, int index) throws NoSuchElementException {
	    if (index < 0 || index > size) { //make sure it is a valid index to add 
	        throw new NoSuchElementException("Invalid index: " + index); //from exception
	    }

	    if (index == 0) { //when index = 0 then it is at the head so we can use addToStart()
	        addToStart(obj);
	        return;
	    }

	    TariffNode position = head;
	    for (int i = 0; i < index - 1; i++) {
	        position = position.getNode(); //move throughout the list until you get to the index right before 
	    }

	    TariffNode newNode = new TariffNode(obj, position.getNode()); //create new node with the new tariff object
	    position.setNode(newNode); // set new node at the given index 
	    size++; //inserting therefore adding a node so list size increases 
	}

	
	public String deleteFromIndex(int index) throws NoSuchElementException {
	    if (index < 0 || index >= size || head == null) { //make sure it is a valid index to add 
	        throw new NoSuchElementException("Invalid index or empty list.");
	    }

	    if (index == 0) { //when index = 0 then it is at the head so we can use addToStart()
	        return deleteFromStart(); //use delete from start as we are the head 
	    }

	    TariffNode position = head; //start at head and go through list until you hit the desired index 
	    for (int i = 0; i < index - 1; i++) {
	        position = position.getNode();
	    }

	    Tariff removed = position.getNode().getTariff(); //get value deleted 
	    position.setNode(position.getNode().getNode()); //break link between two positions and reconnect the two 
	    size--; //deleting = less items in the list, decrease the size number 

	    return removed.toString() + "has been removed"; //output message when deleted 
	}

	
	public String deleteFromStart() throws NoSuchElementException { //delete at the beginning 
		if(head == null)
			throw new NoSuchElementException(); // better idea to throw an exception
		else {
		Tariff s = head.getTariff(); //careful when list is null, must be sure there is an element in head
		
		head = head.getNode(); //get value of the deleted item 
		size--;
		
		if(size == 0) {
			head = null;//if the size == 0 then the head becomes null as it is the only value
		}
		return s.toString() + "has been removed from the start."; //output what has been deleted 
		}
	}
	
	public void replaceAtIndex(Tariff obj, int index) throws NoSuchElementException{//does not add or remove, size will not change as we are replacing 
		if(index == 0) {
			head.setTariff(obj);
		}
		else if(index < size && index >= 0) { //go through list to find given index to replace 
			TariffNode position = head; 
			for(int i = 0; i < index; i++ ) { //need loop, bad about array list = size
				position= position.getNode(); //get to where the index is and change the value of position to return the value at that link 
			}
			position.setTariff(obj); //set value and change 
		}
		else {
			throw new NoSuchElementException(); //if bad index throw exception
		}
		}
	
	public TariffNode find(String origin, String destination, String category) {
		TariffNode position = head;
		if(head == null) { //if head is null then there are no elements to compare 
			System.out.println("This list has no elements, it does not contain this tariff"); //auto does not have 
			return null;
		}
		else {
			int index = 0;
			while(position != null) {
				Tariff comp = position.getTariff(); //get tariff to compare to inputed values in the method 
				
				if(comp.getOrigin().equals(origin) && comp.getDestination().equals(destination) && comp.getProductCat().equals(category)) {
					System.out.println("This list contains that tariff at index " + index); //outputs where it is in the list and shows location
					return position;
				}
				index++; //move to next index if not found 
				position = position.getNode(); //actually moving to the net position in the list 
			}
			
		}
		return null;
	
	}
	
	//check if list contains said tariff 
	public boolean contains(String origin, String destination, String category) {
		TariffNode position = head;
		if(head == null) { //if head is null then it does not contain anything therefore does not contain that tariff 
			return false;
		}
		else {
			while(position != null) { //move through whole list to find if there is said tariff 
				Tariff comp = position.getTariff();
				if(comp.getOrigin().equals(origin) && comp.getDestination().equals(destination) && comp.getProductCat().equals(category)) {
					return true; //compare each attribute of the object 
				}
					position = position.getNode(); //move to next position if it is not the one we are looking for 
			}
			return false; // if not found 
		}
		
	}
	
	//check if the lists are the same 
	public boolean equals(TariffList other) {
	    if (other == null || this.size != other.size) return false;

	    TariffNode current = this.head;
	    //go through each element of the list to see if they contain the same tariffs, not in the same order 
	    while (current != null) {
	        Tariff t = current.getTariff();
	        // Use other.contains() to check if this tariff is in the other list
	        if (!other.contains(t.getOrigin(), t.getDestination(), t.getProductCat())) {
	            return false; // Missing match in other list
	        }
	        current = current.getNode();
	    }

	    return true; // all elements matched
	}
	
	//display the contents of the list, easier to visualize 
	public void display() {
	    TariffNode current = head;
	    while (current != null) {
	        System.out.println(current.getTariff());
	        current = current.getNode();
	    }
	}

	//implemented method
	@Override
	public String evaluateTrade(double proposedTarrif, double minimumTarrif) {
		if(proposedTarrif >= minimumTarrif) {
			return "Accepted" ;
		}
		else if (minimumTarrif > proposedTarrif && proposedTarrif >= (minimumTarrif*0.80)) {
			return "Conditionally Accepted" ;
		}
		else
			return "Rejected";
	}
	
	//inner class for nodes for linked list
	public class TariffNode{
		//attributes
		private Tariff objt;
		private TariffNode nobj;
		
		//constructors
		//default
		public TariffNode() {
			this.nobj = null;
			this.objt = null;
		}
		
		//param
		public TariffNode(Tariff obj, TariffNode nobj) {
			this.nobj = nobj;
			this.objt = obj;
		}
		
		//copy
		public TariffNode(TariffNode other) {
			this.objt = new Tariff(other.objt); // or objt.clone()
			this.nobj = (other.nobj == null) ? null : new TariffNode(other.nobj);
		}
		
		//accessors
		public Tariff getTariff() {
			return this.objt;
		}
		
		public TariffNode getNode() {
			return this.nobj;
		}
		
		//mutators
		public void setTariff(Tariff other) {
			this.objt = other;
		}
		
		public void setNode(TariffNode node) {
			this.nobj = node;
		}
		
		//cloning 
		@Override
		public TariffNode clone() {
			return new TariffNode(this);
		}
		
		//equals
		@Override
		public boolean equals(Object other) {
			return this.objt.equals(other);
		}
		
		
	}

}
