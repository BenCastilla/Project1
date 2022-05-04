package Interfaces;

import java.util.Collection;
import java.util.LinkedList;

public interface Employee {
	boolean login(String password);

	/** Registers this user
	 *
	 * @return The generated UserId
	 */
	int register();

	int employeeId();

	/**
	 * Implement so that Employees see only their tickets
	 * @return a list of all tickets
	 */
	Collection<Ticket> allTickets();
	
	default Collection<Ticket> pending() {
		Collection<Ticket> pending = new LinkedList<Ticket>();
		for(Ticket t : allTickets()) {
			if(!t.isApproved())
				pending.add(t);
		}
		return pending;
	}
	
	default Collection<Ticket> approved() {
		Collection<Ticket> approved = new LinkedList<Ticket>();
		for(Ticket t : allTickets()) {
			if(t.isApproved())
				approved.add(t);
		}
		return approved;
	}

	void submitTicket(Ticket t);
}
