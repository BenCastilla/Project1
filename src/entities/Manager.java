package entities;

import Interfaces.Ticket;

import java.util.List;

public class Manager {
    public static List<Ticket> tickets;

    public static  List<Ticket> allTickets(){
        return tickets;
    }

	public static void handleTicket(Ticket t, boolean accept){
        t.approve(accept);
    }

    public static Ticket getTicket(int id){
        return new TicketImpl();
    }

    public static void delteTicket(int id){
        tickets.remove(getTicket(id));
    }
}
