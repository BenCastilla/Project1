package daos;

import dbFactory.ConnectionManager;
import entities.DbEmployee;
import entities.EmployeeImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void insert(DbEmployee dbEmployee) {
        String sql = "INSERT INTO employees (id, username, password, isAdmin VALUES (default, ?, ?, ?));";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, dbEmployee.getUsername());
            statement.setString(2, dbEmployee.getPassword());
            statement.setBoolean(3, dbEmployee.isAdmin());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();
            if (count == 1) {
                System.out.println("Record inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(DbEmployee dbEmployee) {
        String sql = "UPDATE employees SET username = ?, password = ?, isAdmin = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, dbEmployee.getUsername());
            statement.setString(2, dbEmployee.getPassword());
            statement.setBoolean(3, dbEmployee.isAdmin());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt("id");
            System.out.println("genereated id is: " + id);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(DbEmployee dbEmployee) {
        String sql = "DELETE FROM employees WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, dbEmployee.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Record deleted successfully");
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public DbEmployee get(int id) {

        String sql = "SELECT * FROM employees WHERE id  = ?;";
        DbEmployee dbEmployee = new DbEmployee();
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultSet.next();
                dbEmployee = getDbEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<DbEmployee> getAll() {
        String sql = "SELECT * FROM employees;";
        List<DbEmployee> dbEmployees = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DbEmployee dbEmployee = getDbEmployeeFromResultSet(resultSet);
                dbEmployees.add(dbEmployee);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;


    }

    private DbEmployee getDbEmployeeFromResultSet(ResultSet resultSet) {
        DbEmployee dbEmployee = new DbEmployee();
        try {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Boolean isAdmin = resultSet.getBoolean("isAdmin");
            return new DbEmployee(id, username, password, isAdmin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbEmployee;

    }
}

