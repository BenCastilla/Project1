package entities;

import Interfaces.Ticket;
import servlets.TicketServlet;

import java.sql.Date;

public class TicketImpl implements Ticket {
    public int ticketId;
    boolean approved;
    public Date date = new Date(System.currentTimeMillis());
    public String description;
    public int amount;
    int employeeID;

    public TicketImpl(int setTicketId, boolean isApproved, Date setDate, String setDescription, int setAmount, int setEmployeeID) {
        ticketId = setTicketId;
        approved = isApproved;
        date = setDate;
        description = setDescription;
        amount = setAmount;
        employeeID = setEmployeeID;
    }

    public TicketImpl(String setDescription, int setAmount) {
        approved = false;
        date = new Date(System.currentTimeMillis());
        description = setDescription;
        amount = setAmount;
        employeeID = TicketServlet.loggedin.employeeId();

    }

    public TicketImpl() {

    }


    @Override
    public boolean isApproved() {
        return approved;
    }

    @Override
    public Date dateMade() {
        return date;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public int amount() {
        return amount;
    }

    @Override
    public int employeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeId) {
        employeeID = employeeId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void approve(boolean accept) {
        approved = accept;
    }

    @Override
    public int getId() {
        return ticketId;
    }
}
