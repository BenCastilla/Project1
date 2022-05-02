import java.util.Collection;
import java.util.LinkedList;

public interface User {
	boolean login(String username, String password);
	
	boolean register(String username, String password);
	
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
}
