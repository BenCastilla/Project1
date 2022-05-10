package daos;

import Interfaces.Ticket;
import dataStructures.CustomDataStructure;

public interface TicketDao {
    public void insert(Ticket dbTicket);
    public void update(Ticket dbTicket, int id);
    public void delete(Ticket dbTicket);
    public Ticket get(int ticketId);
    CustomDataStructure<Ticket> getAll();

}
