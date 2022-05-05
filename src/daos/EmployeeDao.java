package daos;

import Interfaces.Employee;
import entities.DbEmployee;
import entities.EmployeeImpl;

import java.util.List;

public interface EmployeeDao {
    public void insert(DbEmployee dbEmployee);
    public void update(DbEmployee dbEmployee);
    public void delete(DbEmployee dbEmployee);
    public DbEmployee get(int id);
    List<DbEmployee> getAll();
}
