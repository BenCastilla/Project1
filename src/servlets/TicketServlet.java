package servlets;

import Interfaces.Employee;
import Interfaces.Ticket;
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
    public static Employee loggedin = new EmployeeImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(202);
        ObjectMapper om = new ObjectMapper();
        res.getWriter().print(om.writeValueAsString(Manager.allTickets()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        TicketImpl ti = om.readValue(req.getInputStream(), TicketImpl.class);
        loggedin.submitTicket(ti);
        res.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        putTicket putReq = om.readValue(req.getInputStream(), putTicket.class);
        Manager.handleTicket(Manager.getTicket(putReq.id), putReq.approved);
        res.setStatus(200);
    }

    class putTicket {
        int id;
        boolean approved;
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper om = new ObjectMapper();
        int id = om.readValue(req.getInputStream(), Integer.class);
        Manager.delteTicket(id);
        res.setStatus(200);
    }

}
