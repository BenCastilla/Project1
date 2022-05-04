package servlets;

import Interfaces.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.EmployeeImpl;
import entities.Manager;
import entities.TicketImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TicketServlet  extends HttpServlet {
    public static Employee loggedin;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(202);
        ObjectMapper om = new ObjectMapper();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        TicketImpl ti = om.readValue(req.getInputStream(), TicketImpl.class);
        loggedin.submitTicket(ti);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        putTicket putReq = om.readValue(req.getInputStream(), putTicket.class);
        Manager.handleTicket(Manager.getTicket(putReq.id), putReq.approved);
    }

    class putTicket {
        int id;
        boolean approved;
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        int id = om.readValue(req.getInputStream(), Integer.class);

    }

}
