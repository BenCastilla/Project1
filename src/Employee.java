import java.util.Collection;

public interface Employee extends User{

	void submitTicket(Ticket t);
	
	int employeeId();
}
