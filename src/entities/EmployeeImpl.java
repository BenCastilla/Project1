package entities;

import Interfaces.Employee;
import Interfaces.Ticket;

import java.util.List;

public class EmployeeImpl implements Employee {
    int id;
    public String username;
    public String password;
    boolean isAdmin;
    private static List<Employee> users;

    //Empty Constructor
    public EmployeeImpl() {

    }



    public static List<Employee> getUsers() {
        return users;
    }

    public static void setUsers(List<Employee> users) {
        EmployeeImpl.users = users;
    }


    //Previous override methods

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

    @Override
    public int getId() {
        return employeeId();
    }

    @Override
    public int compareTo(Employee o) {
        return this.employeeId()-o.employeeId();
    }
}
