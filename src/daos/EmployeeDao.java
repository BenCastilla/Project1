package daos;

import Interfaces.Employee;
import dataStructures.CustomDataStructure;

public interface EmployeeDao {
    public int insert(Employee dbEmployee);
    public void update(Employee dbEmployeeS, int id);
    public void delete(Employee dbEmployee);
    public Employee get(int id);
    CustomDataStructure<Employee> getAll();
}
