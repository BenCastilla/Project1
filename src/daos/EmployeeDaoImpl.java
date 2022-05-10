package daos;

import Interfaces.Employee;
import dataStructures.CustomDataStructure;
import dbFactory.ConnectionManager;
import entities.EmployeeImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public int insert(Employee dbEmployee) {
        String sql = "INSERT INTO employees (id, username, password, isAdmin) VALUES (default, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1,dbEmployee.getUsername());
            statement.setString(2,dbEmployee.getPassword());
            statement.setBoolean(3, dbEmployee.isAdmin());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();
            if (count == 1) {
                System.out.println("Record inserted successfully!");
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt("id");
                System.out.println("updated id is: " + id);
                return id;
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void update(Employee dbEmployee, int id) {
        String sql = "UPDATE employees SET username = ?, password = ?, isAdmin = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, dbEmployee.getUsername());
            statement.setString(2, dbEmployee.getPassword());
            statement.setBoolean(3, dbEmployee.isAdmin());
            statement.setInt(4, id);
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = statement.executeUpdate();
            /*
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt("id");
            System.out.println("updated id is: " + id);*/


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Employee dbEmployee) {
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
    public Employee get(int id) {

        String sql = "SELECT * FROM employees WHERE id  = ?;";
        Employee dbEmployee = new EmployeeImpl();
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //resultSet.next();
                dbEmployee = getDbEmployeeFromResultSet(resultSet);
                return dbEmployee;
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public CustomDataStructure<Employee> getAll() {
        String sql = "SELECT * FROM employees;";
        CustomDataStructure<Employee> dbEmployees = new CustomDataStructure<Employee>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee dbEmployee = getDbEmployeeFromResultSet(resultSet);
                dbEmployees.add(dbEmployee);
            }
            return dbEmployees;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;


    }

    private Employee getDbEmployeeFromResultSet(ResultSet resultSet) {
        Employee dbEmployee = new EmployeeImpl();
        try {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Boolean isAdmin = resultSet.getBoolean("isAdmin");
            return new EmployeeImpl(id, username, password, isAdmin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbEmployee;

    }
}

