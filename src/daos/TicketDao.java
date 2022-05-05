package daos;

import entities.DbTicket;

import java.util.List;

public interface TicketDao {
    public void insert(DbTicket dbTicket);
    public void update(DbTicket dbTicket);
    public void delete(DbTicket dbTicket);
    public DbTicket get(int id);
    List<DbTicket> getAll();

}
