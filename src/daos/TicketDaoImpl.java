package daos;

import dbFactory.ConnectionManager;
import entities.DbTicket;
import entities.TicketImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private Connection connection;

    public TicketDaoImpl() {connection = ConnectionManager.getConnection();}

    @Override
    public void insert(DbTicket dbTicket){
        String sql = "INSERT INTO tickets (ticketId, approved, date, description, amount, employeeID) VALUES (default, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, dbTicket.isApproved());
            statement.setString(2, dbTicket.getDate());
            statement.setString(3, dbTicket.getDescription());
            statement.setInt(4, dbTicket.getAmount());
            statement.setInt(5, dbTicket.getEmployeeID());
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

    public void update(DbTicket dbTicket){
        String sql = "UPDATE tickets SET approved = ?, date = ?, description = ?, amount = ?, employeeID = ? WHERE ticketId = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, dbTicket.isApproved());
            statement.setString(2, dbTicket.getDate());
            statement.setString(3, dbTicket.getDescription());
            statement.setInt(4, dbTicket.getAmount());
            statement.setInt(5, dbTicket.getEmployeeID());
            statement.setInt(6, dbTicket.getTicketId());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int user_id = resultSet.getInt("user_id");
            System.out.println("generated user_id is: " + user_id);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(DbTicket dbTicket) {
        String sql = "DELETE FROM tickets WHERE ticketId = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, dbTicket.getTicketId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Record deleted successfully");
            }

        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public DbTicket get(int ticketId){
        String sql = "SELECT * FROM tickets WHERE ticketId = ?;";
        DbTicket dbTicket = new DbTicket();

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticketId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                resultSet.next();
                dbTicket = getDbTicketFromResultSet(resultSet);
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());

        }
        return null;
    }

    @Override
    public List<DbTicket> getAll() {
        String sql = "SELECT * FROM tickets;";
        List<DbTicket> tickets = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DbTicket dbTicket = getDbTicketFromResultSet(resultSet) ;
                tickets.add(dbTicket);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DbTicket getDbTicketFromResultSet(ResultSet resultSet) {
        DbTicket dbTicket = new DbTicket();
        try {
            int ticketId = resultSet.getInt("ticketId");
            Boolean isApproved = resultSet.getBoolean("isApproved");
            String date = resultSet.getString("date");
            String description = resultSet.getString("description");
            int amount = resultSet.getInt("amount");
            int employeeID = resultSet.getInt("employeeID");
            return new DbTicket(ticketId,isApproved,date, description,amount,employeeID);
        } catch (SQLException e) {
            e.printStackTrace();
        } return dbTicket;

    }

    }

