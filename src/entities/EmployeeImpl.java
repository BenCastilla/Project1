package entities;

import Interfaces.Employee;
import Interfaces.Ticket;
import daos.EmployeeDao;
import dataStructures.CDSCompatible;

import java.util.List;

public class EmployeeImpl implements Employee {
    int id;
    public String username;
    public String password;
    public boolean isAdmin;

    //Empty Constructor
    public EmployeeImpl() {

    }

    public EmployeeImpl(int id, String username, String password, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    //Previous override methods

    @Override
    public boolean login(String password) {
        return this.password.equals(password);
    }

    @Override
    public int employeeId() {
        return id;
    }

    @Override
    public void submitTicket(Ticket t) {
        t.setEmployeeID(this.id);
        Tickets.addTicket(t);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public int getId() {
        return employeeId();
    }

    @Override
    public int compareTo(Employee o) {
        return this.employeeId()-o.employeeId();
    }
}
