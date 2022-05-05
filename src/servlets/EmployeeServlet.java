package servlets;

import Interfaces.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.EmployeeDao;
import entities.EmployeeImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet{
    Employees employees;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(202);
        ObjectMapper om = new ObjectMapper();
        int id = om.readValue(req.getInputStream(), Integer.class);
        res.getWriter().print(employees.getEmployee(id));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        EmployeeImpl empl = om.readValue(req.getInputStream(), EmployeeImpl.class);

        int id = empl.register();
        res.getWriter().print("Generated id = "+id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        EmployeeImpl empl = om.readValue(req.getInputStream(), EmployeeImpl.class);
        employees.updateEmployee(empl, empl.employeeId());
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        int id = om.readValue(req.getInputStream(), Integer.class);
        employees.deleteEmployee(id);
    }
}