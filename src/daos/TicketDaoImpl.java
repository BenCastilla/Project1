package daos;

import Interfaces.Ticket;
import dataStructures.CustomDataStructure;
import dbFactory.ConnectionManager;
import entities.TicketImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class TicketDaoImpl implements TicketDao {

    private Connection connection;

    public TicketDaoImpl() {connection = ConnectionManager.getConnection();}

    @Override
    public void insert(Ticket dbTicket){
        String sql = "INSERT INTO tickets (ticketId, approved, date, description, amount, employeeID) VALUES (default, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, dbTicket.isApproved());
            statement.setDate(2, dbTicket.getDate());
            statement.setString(3, dbTicket.getDescription());
            statement.setInt(4, dbTicket.getAmount());
            statement.setInt(5, dbTicket.employeeID());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();
            if(count == 1){
                System.out.println("Record inserted successfully!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Ticket dbTicket, int id){
        String sql = "UPDATE tickets SET approved = ?, date = ?, description = ?, amount = ?, employeeID = ? WHERE ticketId = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, dbTicket.isApproved());
            statement.setDate(2, dbTicket.getDate());
            statement.setString(3, dbTicket.getDescription());
            statement.setInt(4, dbTicket.getAmount());
            statement.setInt(5, dbTicket.employeeID());
            statement.setInt(6, id);
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Ticket dbTicket) {
        String sql = "DELETE FROM tickets WHERE ticketId = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, dbTicket.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Record deleted successfully");
            }

        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Ticket get(int ticketId){
        String sql = "SELECT * FROM tickets WHERE ticketId = ?;";
        Ticket dbTicket = new TicketImpl();

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticketId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                //resultSet.next();
                dbTicket = getDbTicketFromResultSet(resultSet);
                return dbTicket;
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());

        }
        return null;
    }

    @Override
    public CustomDataStructure<Ticket> getAll() {
        String sql = "SELECT * FROM tickets;";
        CustomDataStructure<Ticket> tickets = new CustomDataStructure<Ticket>();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket dbTicket = getDbTicketFromResultSet(resultSet) ;
                tickets.add(dbTicket);
            }
            return tickets;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Ticket getDbTicketFromResultSet(ResultSet resultSet) {
        Ticket dbTicket = new TicketImpl();
        try {
            int ticketId = resultSet.getInt("ticketId");
            Boolean isApproved = resultSet.getBoolean("approved");
            Date date = resultSet.getDate("date");
            String description = resultSet.getString("description");
            int amount = resultSet.getInt("amount");
            int employeeID = resultSet.getInt("employeeID");
            return new TicketImpl(ticketId,isApproved, date, description,amount,employeeID);
        } catch (SQLException e) {
            e.printStackTrace();
        } return dbTicket;

    }

    }

