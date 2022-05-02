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
}
