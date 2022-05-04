package Interfaces;

import entities.Manager;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
	default Collection<Ticket> allTickets() {
		List<Ticket> theseTickets = new LinkedList<Ticket>();
		for(Ticket t : Manager.allTickets()){
			if(t.employeeID() == this.employeeId())
				theseTickets.add(t);
		}
		return theseTickets;
	}
	
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
