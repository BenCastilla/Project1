package servlets;

import Interfaces.Employee;
import Interfaces.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import daos.EmployeeDao;
import daos.EmployeeDaoImpl;
import entities.EmployeeImpl;
import entities.EmployeesImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Employees employees = new EmployeesImpl();
        res.setStatus(202);
        ObjectMapper om = new ObjectMapper();
        try {
            login login = om.readValue(req.getInputStream(), login.class);
            Employee e = null;
            for(Employee emp : employees.getAll()){
                if(emp.getPassword().equals(login.password) && emp.getUsername().equals(login.username))
                    e = emp;
            }
            if(e == null)
                res.getWriter().print("Authentication failed");
            else {
                res.getWriter().print("Welcome "+e.getUsername()+", you are now Logged in");
                TicketServlet.loggedin = e;
            }
        } catch (MismatchedInputException e) {
            EmployeeDaoImpl ed = new EmployeeDaoImpl();
            res.getWriter().print(employees.getAll().toJSON());
        }
    }

    static class login {
        public String username;
        public String password;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Employees employees = new EmployeesImpl();
        ObjectMapper om = new ObjectMapper();
        EmployeeImpl empl = om.readValue(req.getInputStream(), EmployeeImpl.class);
        System.out.println(TicketServlet.loggedin.employeeId());
        res.getWriter().print("Welcome "+empl.getUsername()+", you are now Logged in");
        int id = empl.register(employees);
        res.getWriter().print("Generated id = "+ id);
        TicketServlet.loggedin = employees.getEmployee(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Employees employees = new EmployeesImpl();
        ObjectMapper om = new ObjectMapper();
        EmployeeImpl empl = om.readValue(req.getInputStream(), EmployeeImpl.class);

        employees.updateEmployee(empl, empl.employeeId());
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Employees employees = new EmployeesImpl();
        ObjectMapper om = new ObjectMapper();
        int id = om.readValue(req.getInputStream(), Integer.class);
        employees.deleteEmployee(id);
        res.getWriter().print("Deleted id#"+ id);
    }
}