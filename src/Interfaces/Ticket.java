package Interfaces;

import java.util.Date;

public interface Ticket extends Comparable<Ticket>{

	boolean isApproved();
	
	Date dateMade();
	
	String name();
	
	String description();
	
	int amount();
	
	int employeeID();

	@Override
	default public int compareTo(Ticket arg0) {
		// TODO Auto-generated method stub
		return this.dateMade().compareTo(arg0.dateMade());
	}

	/**Accept or Deny Ticket Request
	 *
	 * @param accept
	 * true if you are accepting the ticket, false if denying the ticket
	 */
	void approve(boolean accept);
}
