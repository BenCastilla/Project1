package entities;

import Interfaces.Ticket;
import daos.TicketDao;
import daos.TicketDaoImpl;
import dataStructures.CustomDataStructure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Tickets {
    static TicketDao td = new TicketDaoImpl();

    public static void addTicket(Ticket t){
        td.insert(t);
    }

    public static CustomDataStructure<Ticket> allTickets(){
        return td.getAll();
    }

	public static void handleTicket(Ticket t, boolean accept){
        t.approve(accept);
        td.update(t, t.getId());
    }

    public static Ticket getTicket(int id){
        return td.get(id);
    }

    public static void delteTicket(int id){
        td.delete(getTicket(id));
    }
}
