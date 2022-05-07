package Interfaces;

import dataStructures.CDSCompatible;
import dataStructures.CustomDataStructure;
import entities.Manager;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface Employee extends CDSCompatible<Employee> {
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
	default CustomDataStructure<Ticket> allTickets() {
		CustomDataStructure<Ticket> theseTickets = new CustomDataStructure<Ticket>();
		for(Ticket t : Manager.allTickets()){
			if(t.employeeID() == this.employeeId())
				theseTickets.add(t);
		}
		return theseTickets;
	}
	
	default CustomDataStructure<Ticket> pending() {
		CustomDataStructure<Ticket> pending = new CustomDataStructure<Ticket>();
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
