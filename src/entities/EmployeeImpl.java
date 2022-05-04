package entities;

import Interfaces.Employee;
import Interfaces.Manager;
import Interfaces.Ticket;

import java.util.Collection;
import java.util.List;

public class EmployeeImpl implements Employee {
    private static List<Employee> users;
    public String username;
    public String password;
    int id;
    boolean isAdmin;

    public EmployeeImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public EmployeeImpl() {

    }

    @Override
    public boolean login(String password) {
        return this.password.equals(password);
    }

    @Override
    public int register() {
        users.add(this);
        this.id = users.indexOf(this);
        return users.indexOf(this);
    }

    @Override
    public int employeeId() {
        return id;
    }

    @Override
    public void submitTicket(Ticket t) {
        Manager.allTickets().add(t);
    }
}
