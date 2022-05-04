package entities;

import Interfaces.Ticket;

import java.util.Date;

public class TicketImpl implements Ticket {
    boolean approved;
    Date date;
    String name, description;
    int amount, employeeID;
    @Override
    public boolean isApproved() {
        return approved;
    }

    @Override
    public Date dateMade() {
        return date;
    }

    @Override
    public String name() {
        return name;
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

    @Override
    public void setEmployeeID(int employeeId) {
        employeeID = employeeId;
    }

    @Override
    public void approve(boolean accept) {
        approved = accept;
    }
}
