package entities;

import Interfaces.Ticket;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Manager {
    public static List<Ticket> tickets = new LinkedList<Ticket>();

    public static  List<Ticket> allTickets(){
        Collections.sort(tickets);
        return tickets;
    }

	public static void handleTicket(Ticket t, boolean accept){
        t.approve(accept);
    }

    public static Ticket getTicket(int id){
        return tickets.get(id);
    }

    public static void delteTicket(int id){
        tickets.remove(getTicket(id));
    }
}
