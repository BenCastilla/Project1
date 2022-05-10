package servlets;

import Interfaces.Employee;
import Interfaces.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import daos.EmployeeDaoImpl;
import dataStructures.CustomDataStructure;
import entities.EmployeeImpl;
import entities.TicketImpl;
import entities.Tickets;

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
        try {
            Boolean b = om.readValue(req.getInputStream(), Boolean.class);
            if(b) {
                printPending(res);
            } else {
                printTickets(res);
            }
        } catch (MismatchedInputException e) {
            printTickets(res);
        }
    }

    public void printTickets(HttpServletResponse res) throws IOException {
        res.getWriter().print(loggedin.allTickets().toJSON());
        res.getWriter().print("\n\n");
        if(loggedin.isAdmin())
            res.getWriter().print(Tickets.allTickets().toJSON());
    }

    public void printPending(HttpServletResponse res) throws IOException {
        CustomDataStructure<Ticket> pending = new CustomDataStructure<Ticket>();
        for(Ticket t : loggedin.allTickets())
            if(!t.isApproved())
                pending.add(t);
        res.getWriter().print(pending.toJSON());
        if(loggedin.isAdmin()) {
            res.getWriter().print("\n\n");
            CustomDataStructure<Ticket> allpending = new CustomDataStructure<Ticket>();
            for (Ticket t : Tickets.allTickets())
                if (!t.isApproved())
                    allpending.add(t);
            res.getWriter().print(allpending.toJSON());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        postTicket pt = om.readValue(req.getInputStream(), postTicket.class);
        loggedin.submitTicket(new TicketImpl(pt.description, pt.amount));
        if(loggedin.employeeId() != 0) {
            res.getWriter().print("Ticket submitted succesfully");
            res.setStatus(200);
        } else {
            res.getWriter().print("Only logged in Employees can submit tickets");
        }
    }

    static class postTicket {
        public int amount;
        public String description;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(loggedin.isAdmin()) {
            ObjectMapper om = new ObjectMapper();
            putTicket putReq = om.readValue(req.getInputStream(), putTicket.class);
            Tickets.handleTicket(Tickets.getTicket(putReq.id), putReq.approved);
            res.setStatus(200);
            res.getWriter().print("Approval of Ticker #" + putReq.id + " set to " + putReq.approved);
        } else {
            res.getWriter().print("Only Managers can appprove or disapprove tickets");
        }
    }

    static class putTicket {
        public int id;
        public boolean approved;
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper om = new ObjectMapper();
        int id = om.readValue(req.getInputStream(), Integer.class);
        Tickets.delteTicket(id);
        res.setStatus(200);
        res.getWriter().print("Deleted id#"+ id);
    }

}
