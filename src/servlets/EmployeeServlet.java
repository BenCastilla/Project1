package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.EmployeeImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(202);
        res.getWriter().print("Get Employees");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        EmployeeImpl newUser = om.readValue(req.getInputStream(), EmployeeImpl.class);
        System.out.println(newUser);
    }
}