package Interfaces;

import dataStructures.CustomDataStructure;

public interface Employees {

    public int addEmployee(Employee e);

    public void deleteEmployee(int id);

    public Employee getEmployee(int id);

    public void updateEmployee(Employee e, int id);

    CustomDataStructure<Employee> getAll();
}
