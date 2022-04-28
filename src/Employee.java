import java.util.Collection;
import java.util.LinkedList;

public interface Employee extends User {

	Collection<Ticket> allTickets();
	
	default Collection<Ticket> pending() {
		Collection<Ticket> pending = new LinkedList<Ticket>();
		for(Ticket t : allTickets()) {
			if(!t.isApproved())
				pending.add(t);
		}
		return pending;
	}
}
