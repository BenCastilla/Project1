import java.util.Collection;
import java.util.LinkedList;

public interface Manager extends User {

	void acceptTicket(Ticket t);
	
	void denyTicket(Ticket t);
	
}
