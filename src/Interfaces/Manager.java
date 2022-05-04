package Interfaces;

import java.util.List;

public class Manager {
    public static List<Ticket> tickets;

    public static List<Ticket> allTickets(){
        return tickets;
    }

	public void handleTicket(Ticket t, boolean accept){
        t.approve(accept);
    }
}
