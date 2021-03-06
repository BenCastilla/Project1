package Interfaces;

import dataStructures.CDSCompatible;

import java.sql.Date;

public interface Ticket extends CDSCompatible<Ticket> {

	boolean isApproved();
	
	Date dateMade();
	
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

	Date getDate();

	String getDescription();

	int getAmount();

    void setEmployeeID(int id);
}
