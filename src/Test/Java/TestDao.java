package test.java;

import Interfaces.Ticket;
import dataStructures.CDSCompatible;
import junit.framework.TestCase;
import org.junit.*;

import entities.TicketImpl;
import entities.Tickets;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class TestDao extends TestCase {
    @Test
    public void testInsert(){
        int amount = 10;
        Tickets.addTicket(new TicketImpl("Test Ticket", amount));
        for(Ticket t : Tickets.allTickets() ) {
            if (t.description() == "Test Ticket") {
                amount = t.amount();
            }
        }
        assertEquals(amount, 10);
    }
}
